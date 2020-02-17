package com.tuoming;

import com.tuoming.common.FileDealUntil;
import com.tuoming.entity.iups.IUPSDecode;
import com.tuoming.entity.s1mme.MmeCommon;
import com.tuoming.readfile.IupsReadFile;
import com.tuoming.readfile.ReadFile;
import com.tuoming.tools.*;
import com.tuoming.writefile.IuPSRelationWrite;
import com.tuoming.writefile.IuPSWriteThread;
import com.tuoming.writefile.WriteFile;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import redis.clients.jedis.Jedis;
import sort.SortEntity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class IuPSRelationDriver {
    private static Logger logger = Logger.getLogger(IuPSRelationDriver.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
    private static volatile Queue<IUPSDecode> resultBuffer = new ConcurrentLinkedQueue<>();
    public static void main(String[] args) {
        Thread.currentThread().setName("IuPS消息关联主程序");
        try {
            PropertyConfigurator.configure("./resources/log4j.properties");
        } catch (Exception e) {
            logger.error("读取日志配置文件失败:./resources/log4j.properties，程序退出");
            System.exit(0);
        }
        logger.info("==============================================================");
        if (args.length < 10) {
            logger.error("【Class    】:" + IuPSRelationDriver.class.getName());
            logger.error("【Parameter】:输入目录、备份目录、输出目录、大小粒度(k)、关联超时时间（ms）、redisIp、redisPwd、原始文件名时间索引（210000_1_LTE-S1AP_20191117060020_00.csv)、排序缓冲（条）、排序超时时间（s）");
            logger.error("【Example  】:F:/dataDemo/input、F:/dataDemo/backup、F:/dataDemo/output、1024、500、192.168.2.142、123456、3、1024、30");
            System.exit(0);
        }
        logger.info("输入参数" + Arrays.toString(args));

        String inputPath = args[0];     //输入目录
        //备份文件位置文件夹
        String backupPath = args[1];
        String outputPath = args[2];    //输出目录
        //文件生成大小（kb）
        Integer fileSize = Integer.parseInt(args[3]);
        //关联超时时间
        Long time = Long.parseLong(args[4]);

        String redisIp = args[5];

        String redisPwd = args[6];
        //文件名称时间位置
        Integer fileNameTimeIndex = Integer.parseInt(args[7]);
        //排序缓冲区大小
        Integer sortMaxBuffer = Integer.parseInt(args[8]);
        //排序超时时间
        Integer sortOutTime = Integer.parseInt(args[9]);

        Jedis redis = RedisUntil.getRedis(redisIp, redisPwd);

        ReadFile.MaxCount = sortMaxBuffer;
        IUPSDecode.jedis = redis;

        IupsReadFile readFile = new IupsReadFile();

        //分组 key:imsi  value:该用户所有话单
        Map<String, IUPSDecode> groupMap = new HashMap<>();
        //时间管理器
        TimeManager timeManager = new TimeManager();
        //时钟驱动
        long timeStamp = 0l;

        SortEntity line = null;

        IUPSDecode headIuPS = null;
        IUPSDecode iupsDecodeFlag = null;
        //保存结果的列表
//        List<String> resultBuffer = new LinkedList<>();
        IuPSWriteThread iuPSWriteThread = new IuPSWriteThread(resultBuffer, outputPath, fileSize);
        new Thread(iuPSWriteThread).start();
        //用于文件写出
//        IuPSRelationWrite write = new IuPSRelationWrite();
        /*WriteFile wf = new WriteFile(fileSize);
        write.setWf(wf);
        write.getWf().initialize(outputPath + File.separator + write.getFile());*/

        int sortOutTimeCount = 0;
        int listCount = 0;
        int rem = 0;
        int quit = 0;
        //循环扫描文件目录

        long start = System.currentTimeMillis();
        while (true) {
            //列出所有可用文件（.csv结尾）文件，并且排序
            List<String> list = FileDealUntil.fileListAndSort(inputPath, fileNameTimeIndex);
            logger.info("[扫描文件列表]<->" + list);
            for (String file : list) {
                //读取一个文件，放到容器
                readFile.read(inputPath + "/" + file, file);
                logger.info("[读入文件]<->" + inputPath + "/" + file);
                logger.info("[文件条数]<->" + readFile.size());
                FileDealUntil.moveFile(inputPath + "/" + file, backupPath + "/" + file);
                logger.info(inputPath + "/" + file + "处理完毕，移动到" + backupPath + "/" + file);
            }
            list.clear();
            if (sortOutTimeCount >= sortOutTime) {
                //排序缓冲区置零，吐出所有缓冲区中数据
                ReadFile.MaxCount = 0;
            }

            if (ReadFile.MaxCount == 0) {
                logger.info("[输出残存在map中的数据,数据数量:]<->"+groupMap.size());
                for (String str : groupMap.keySet()) {
                    resultBuffer.add(groupMap.get(str));
                }

                groupMap.clear();
                headIuPS = null;
                iupsDecodeFlag = null;
            }

            while ((line = readFile.list.getFirst()) != null) {
//                long sta1 = System.nanoTime();
//                System.out.println("开始："+sta1);
                sortOutTimeCount = 0;
//                System.out.println("头记录：" + headIuPS);
//                System.out.println("当前记录：" + iupsDecodeFlag);

                logger.info("处理条数："+listCount);
                logger.info("链表条数："+ (quit = Untils.count(headIuPS)));
                logger.info("map中条数："+groupMap.size());
                logger.info("舍弃条数："+rem);
                logger.info("输出buf大小：" +resultBuffer.size());
                listCount++;
                //1.切分 构建结构体
                String[] split = line.str.split("\\|", -1);
                IUPSDecode thisIuPS = new IUPSDecode();
                thisIuPS.decode(split);
//                long sta2 = System.nanoTime();
//                System.out.println("构建对象时间："+(sta2-sta1));
                //2.头节点赋值
                if (headIuPS == null) {
                    headIuPS = thisIuPS;
                }
                //3.时钟驱动相关
                long curtime = System.currentTimeMillis();
                if (timeStamp == 0) {
                    timeStamp = curtime;
                } else if ((curtime - timeStamp) > time) {
//                    long che1 = System.nanoTime();
                    //3.1.去检查超时队列
                    logger.info("每隔5s进行队列超时检查！");
                    headIuPS = timeManager.OvertimeJugle(headIuPS, thisIuPS.getStarttime(), groupMap, resultBuffer);
                    timeStamp = curtime;
//                    System.out.println((System.nanoTime()-che1));
                }
                //2.头节点赋值
                if (headIuPS == null) {
                    headIuPS = thisIuPS;
                    iupsDecodeFlag = null;
                }
//                long mid = System.nanoTime();
//                System.out.println("中间："+(mid-start));
                String imsiKey = thisIuPS.getImsi();
//                long sta3 = System.nanoTime();
                //4.存取元素，关联
                if (iupsDecodeFlag != null) {         //此时不是第一条
                    //4.1.加入链表
                    iupsDecodeFlag.setNextIUPSDecode(thisIuPS);
                    thisIuPS.setPreIUPSDecode(iupsDecodeFlag);
                    //4.2.map中包含该用户其余话单
                    if (groupMap.containsKey(imsiKey)) {
                        IUPSDecode preIuPS = groupMap.get(imsiKey);
                        //4.2.1.关联
                        // 返回值说明：0：只移除子话单 1：头节点关联 2：中间节点关联
                        //             3：尾节点关联 4：头节点写出 5：中间节点写出
//                        long zi = System.nanoTime();
                        int relationResult = IuPSRelation.relations(preIuPS, thisIuPS, groupMap, resultBuffer);
//                        System.out.println("特殊关联："+(System.nanoTime() - zi));
//                        System.out.println("返回值："+relationResult);
                        if(relationResult == 0){
                            rem++;
                        }
                        /*if(relationResult != 5){
                            System.out.println("关联结果:"+relationResult);
                        }*/

                        //4.2.2.原头节点关联时，头节点下移，当前节点保留原头节点，并切断连接关系，防止闭环链
                        if (relationResult == 1) {
                            headIuPS = headIuPS.getNextIUPSDecode();
                            headIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                            headIuPS.setPreIUPSDecode(null);
                            iupsDecodeFlag = preIuPS;
                        }
                        //4.2.3.中间节点和尾节点关联，要将当前节点更改
                        else if (relationResult == 2 || relationResult == 3) {
                            iupsDecodeFlag = preIuPS;
                        }
                        //4.2.4.原头节点写出，头节点下移，并切断连接关系
                        else if (relationResult == 4) {
                            headIuPS = headIuPS.getNextIUPSDecode();
                            headIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                            headIuPS.setPreIUPSDecode(null);
                            iupsDecodeFlag = thisIuPS;
                        }
                        //4.2.5.原节点中间写出
                        else if (relationResult == 5) {
                            iupsDecodeFlag = thisIuPS;
                        }
                        else if(relationResult == 6){
                            headIuPS = null;
                            iupsDecodeFlag = null;
                        }
                        else if(relationResult == 7){
                            headIuPS = headIuPS.getNextIUPSDecode();
                            headIuPS.getPreIUPSDecode().setNextIUPSDecode(null);
                            headIuPS.setPreIUPSDecode(null);
                        }
                        else if(relationResult == 8){
                            iupsDecodeFlag = iupsDecodeFlag.getPreIUPSDecode();
                            iupsDecodeFlag.getNextIUPSDecode().setPreIUPSDecode(null);
                            iupsDecodeFlag.setNextIUPSDecode(null);
                        }
                    }
                    //4.3.map中不包含该用户其余话单
                    else {
                        //4.3.1.判断是不是大流程
                        if (!VoiceTypeUtils.isOutVoiceType(thisIuPS.getVoiceType())) {
                            iupsDecodeFlag.setNextIUPSDecode(null);
//                            thisIuPS.setPreIUPSDecode(null);
                            thisIuPS.clear();
                            thisIuPS = null;
                            continue;
                        }
                        //4.3.2.是大流程加入map
                        groupMap.put(imsiKey, thisIuPS);
                        iupsDecodeFlag = thisIuPS;
                    }
                } else {    //map开始存第一个元素
                    //判断是不是大流程
                    if (!VoiceTypeUtils.isOutVoiceType(thisIuPS.getVoiceType())) {
                        headIuPS.clear();
                        headIuPS = null;
                        continue;
                    }
                    groupMap.put(imsiKey, thisIuPS);
                    iupsDecodeFlag = thisIuPS;
                }
//                System.out.println("关联时间："+(System.nanoTime()-sta3));
//                System.out.println("结尾："+(System.nanoTime()-mid));

//                    Thread.sleep(20);

            }
            logger.info("处理用时："+(System.currentTimeMillis()-start));
            //结果大于一定条数写入文件
           /* if (resultBuffer.size() > 0) {
                for (String result : resultBuffer) {
                    if (write.getWf() != null) {
                        if (write.getWf().write(result)) {
                            write.getWf().close();
                            logger.info("生成结果文件:"+outputPath + File.separator + write.getFinlName());
                            FileDealUntil.renameFile(outputPath + File.separator + write.getTmpName(), outputPath + File.separator + write.getFinlName());
                            write.setWf(null);
                            write.setFileSignCount(write.getFileSignCount() + 1);
                        }
                    } else {
                        WriteFile writeFile = new WriteFile(fileSize);
                        write.setWf(writeFile);
                        long fileCreatTime = System.currentTimeMillis();
                        write.setTime(sdf.format(new Date(fileCreatTime)));
                        write.getWf().initialize(outputPath + File.separator + write.getFile());
                        if (write.getWf().write(result)) {
                            write.getWf().close();
                            FileDealUntil.renameFile(outputPath + File.separator + write.getTmpName(), outputPath + File.separator + write.getFinlName());
                            write.setWf(null);
                            write.setFileSignCount(write.getFileSignCount() + 1);
                        }
                    }
                }
                resultBuffer.clear();
            }*/
            //排序缓冲区重置
            ReadFile.MaxCount = sortMaxBuffer;
            try {
                //5秒扫描一次文件
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //排序超时时间累加
            sortOutTimeCount += 5;
        }
    }
}
