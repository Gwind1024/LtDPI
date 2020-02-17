package com.tuoming;

import com.tuoming.common.FileDealUntil;
import com.tuoming.common.ReadPublicTable;
import com.tuoming.entity.iups.CommonIuPSIndex;
import com.tuoming.entity.iups.IUReleaseIndex;
import com.tuoming.readfile.IuPSReadFile;
import com.tuoming.readfile.ReadFile;
import com.tuoming.sort.SortEntity;
import com.tuoming.tools.RadixDeal;
import com.tuoming.writefile.IuPSWrite;
import com.tuoming.writefile.Write;
import com.tuoming.writefile.WriteFile;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.*;

public class IuPSAnalyse {
    private static Logger logger = Logger.getLogger(IuPSAnalyse.class);
    public static void main(String[] args) {
        try {
            PropertyConfigurator.configure("./resources/log4j.properties");
        } catch (Exception e) {
            logger.error("读取日志配置文件失败:./resources/log4j.properties，程序退出");
            System.exit(0);
        }
        logger.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        if (args.length < 11) {
            logger.error("【Class    】:" + IuPSAnalyse.class.getName());
            logger.error("【Parameter】:网元表位置、时间粒度(minute)、大小粒度(k)、输入目录、备份目录、输出目录、redisIp、redisPwd、原始文件名时间索引（210000_1_LTE-S6a_20190610145320_00.csv)、排序缓冲（条）、排序超时时间（s）");
            logger.error("【Example  】:F:/dataDemo/public.txt、1440、20、F:/dataDemo/input、F:/dataDemo/backup、F:/dataDemo/output、192.168.2.142、123456、3、1024、30");
            System.exit(0);
        }
        Thread.currentThread().setName("IuPSAnalyse程序");
        logger.info(Thread.currentThread().getName()+"启动!");
        logger.info("输入参数" + Arrays.toString(args));
        //网元表位置文件
        String publicTablePath = args[0];
        //文件生成时间(分钟)
        Integer cycleTime = Integer.parseInt(args[1]);
        //文件生成大小（k）
        Integer fileSize = Integer.parseInt(args[2]);
        //输入文件位置文件夹
        String inputPath = args[3];
        //备份文件位置文件夹
        String backupPath = args[4];
        FileDealUntil.pathCheck(backupPath);
        //输出文件位置文件夹
        String outputPath = args[5];
        FileDealUntil.pathCheck(outputPath);
        //redis的ip
        String redisIP = args[6];
        //redis的pwd
        String redisPwd = args[7];
        //文件名称时间位置
        Integer fileNameTimeIndex = Integer.parseInt(args[8]);
        //排序缓冲区大小
        Integer sortMaxBuffer = Integer.parseInt(args[9]);
        //排序超时时间
        Integer sortOutTime = Integer.parseInt(args[10]);

        ReadFile.MaxCount = sortMaxBuffer;
        //获取网元表
        ReadPublicTable publicTable = new ReadPublicTable();
        publicTable.read(publicTablePath);
        Map<String, String> publicTableMap = publicTable.getPublicTable();
        logger.info("网元公参表" + publicTableMap);

        ReadFile iuPSReadFile = new IuPSReadFile();

        //写文件Map网元->iuPSWrite
        //每个网元对应一个写通道
        HashMap<String, Write> writeMap = new HashMap<>();
        int sortOutTimeCount = 0;

        while (true) {
            //列出所有可用文件（.txt结尾）文件
            List<String> list = FileDealUntil.fileListAndSort(inputPath,fileNameTimeIndex);
            logger.info("扫描文件列表" + list);
            long l1 = System.currentTimeMillis();
            for (String file : list) {
                //读取一个文件
                iuPSReadFile.read(inputPath + "/" + file);
                logger.info("读入文件" + inputPath + "/" + file);
                logger.info("文件总条数" + iuPSReadFile.size());
                FileDealUntil.moveFile(inputPath + "/" + file, backupPath + "/" + file);
                logger.info(inputPath + "/" + file + "处理完毕，移动到" + backupPath + "/" + file);
            }
            long l2 = System.currentTimeMillis();
            logger.info("处理文件总时间总时间：" + (l2 - l1) + "ms");
            list.clear();
            SortEntity sort = null;
            String line = null;
            if (sortOutTimeCount >= sortOutTime) {
                //排序缓冲区置零，吐出所有缓冲区中数据
                ReadFile.MaxCount = 0;
            }
            while ((sort = iuPSReadFile.list.getFirst()) != null) {
                sortOutTimeCount = 0;
                line = sort.str;
                try{
                    line = line.substring(line.indexOf(CommonIuPSIndex.XdrSplit)+1);

                }catch (Exception e){
                    logger.error("数据格式异常:"+line);
                    continue;
                }
                String[] xdrSplits = line.split(CommonIuPSIndex.XdrSplit,-1);
                if(xdrSplits.length < 24){
                    logger.error("数据格式异常:"+line);
                    continue;
                }
                String netAddress = null;
                String endtime = null;
                //根据第一个字段的值判断区分iu release话单与其他话单
                if(!"1".equals(xdrSplits[0])){
                    endtime = xdrSplits[CommonIuPSIndex.IUPS_ENDTIME];
                    netAddress = xdrSplits[CommonIuPSIndex.SGSN_IP];
                }else{
                    endtime = xdrSplits[IUReleaseIndex.IUPS_ENDTIME];
                    netAddress = xdrSplits[IUReleaseIndex.SGSN_IP];
                }
                if (netAddress == null || endtime == null || "".equals(endtime)) {
                    continue;
                }
                if(!netAddress.contains(".")){
                    netAddress = RadixDeal.Decimal2IP(netAddress);
                }
                String networkElement = publicTableMap.get(netAddress);
                if(networkElement == null){
                    networkElement = "other";
                }
                String time = FileDealUntil.timeTranform(endtime, cycleTime);
                if (time == null) {
                    continue;
                }
                //判断是否达到文件生成时间，达到关闭通道，生成文件
                for (String ne : writeMap.keySet()) {
                    Write iuPSWrite = writeMap.get(ne);
                    if (FileDealUntil.longTimeToMin(time) - FileDealUntil.longTimeToMin(iuPSWrite.getTime()) >= cycleTime) {
                        iuPSWrite.getWf().close();
                        iuPSWrite.setWf(null);
                        FileDealUntil.renameFile(outputPath + "/" + iuPSWrite.getTmpName(), outputPath + "/" + iuPSWrite.getFinlName());
                    }
                }
                //写入第一条数据，绑定时间
                if (!writeMap.keySet().contains(networkElement)) {
                    WriteFile writeFile = new WriteFile(fileSize);
                    Write iuPSWrite = new IuPSWrite(time);
                    String fileName = iuPSWrite.getFile(cycleTime, networkElement);
                    writeFile.initialize(outputPath + "/" + fileName);
                    if (writeFile.write(line)) {
                        writeFile.close();
                        writeFile = null;
                        FileDealUntil.renameFile(outputPath + "/" + iuPSWrite.getTmpName(), outputPath + "/" + iuPSWrite.getFinlName());
                    }
                    iuPSWrite.setWf(writeFile);
                    writeMap.put(networkElement, iuPSWrite);
                    //非第一条数据
                } else {
                    Write iuPSWrite = writeMap.get(networkElement);
                    if (iuPSWrite.getWf() != null) {
                        if (iuPSWrite.getWf().write(line)) {
                            iuPSWrite.getWf().close();
                            iuPSWrite.setWf(null);
                            FileDealUntil.renameFile(outputPath + "/" + iuPSWrite.getTmpName(), outputPath + "/" + iuPSWrite.getFinlName());
                        }
                    } else {
                        iuPSWrite.setFileSignCount(iuPSWrite.getFileSignCount() + 1);
                        iuPSWrite.setTime(time);
                        String file2 = iuPSWrite.getFile(cycleTime, networkElement);
                        WriteFile wf = new WriteFile(fileSize);
                        //重新绑定文件
                        wf.initialize(outputPath + "/" + file2);
                        if (wf.write(line)) {
                            wf.close();
                            wf = null;
                            FileDealUntil.renameFile(outputPath + "/" + iuPSWrite.getTmpName(), outputPath + "/" + iuPSWrite.getFinlName());
                        }
                        iuPSWrite.setWf(wf);
                    }
                }
            }
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
