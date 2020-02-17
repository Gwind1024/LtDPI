package com.tuoming;


import com.tuoming.common.FileDealUntil;
import com.tuoming.entity.s1mme.MmeCommon;
import com.tuoming.entity.s1mme.method.LinedListHeader;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.entity.s1mme.method.MmeMethod;
import com.tuoming.readfile.MmeReadFile;
import com.tuoming.readfile.ReadFile;
import com.tuoming.tools.RedisUntil;
import com.tuoming.writefile.MmeRelationWrite;
import com.tuoming.writefile.WriteFile;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class MmeRelationDriver {
    private static Queue<MmeCommon> relationResult = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        if (args.length < 10) {
            System.out.println("【Class    】:" + MmeRelationDriver.class.getName());
            System.out.println("【Parameter】:输入目录、备份目录、输出目录、大小粒度(k)、关联超时时间（ms）、redisIp、redisPwd、原始文件名时间索引（210000_1_LTE-S1AP_20191117060020_00.csv)、排序缓冲（条）、排序超时时间（s）");
            System.out.println("【Example  】:F:/dataDemo/input、F:/dataDemo/backup、F:/dataDemo/output、1024、500、192.168.2.142、123456、3、1024、30");
            System.exit(0);
        }
        System.out.println("[输入参数]<->" + Arrays.toString(args));
        //源文件位置文件夹
        String inputPath = args[0];
        //备份文件位置文件夹
        String backupPath = args[1];
        FileDealUntil.pathCheck(backupPath);
        String outputPath = args[2];
        FileDealUntil.pathCheck(outputPath);
        //文件生成大小（k）
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
        MmeCommon.jedis = redis;

        MmeReadFile readFile = new MmeReadFile();

        //用户 -> 封装关联信息
        HashMap<String, MmeMapCommon> writeMap = new HashMap<>();

        LinedListHeader linedListHeader = new LinedListHeader();

        int sortOutTimeCount = 0;

//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    Untils.getMemoryAndThread() ;
//                    try {
//                        Thread.sleep(2000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//
//            }
//        }).start();

        new Thread(new Runnable() {
            MmeRelationWrite write = new MmeRelationWrite();

            @Override
            public void run() {
                Thread.currentThread().setName("文件写出线程");
                MmeCommon line = null;
                int timeOut = 0;
                while (true) {
                    if ((line = relationResult.poll()) != null) {
                        timeOut = 0;
                        if (write.getWf() != null) {
                            if (write.getWf().write(line.toString())) {
                                write.getWf().close();
                                System.out.println(outputPath + "/" + write.getFinlName());
                                FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
                                write.setWf(null);
                                write.setFileSignCount(write.getFileSignCount() + 1);
                            }
                        } else {
                            WriteFile writeFile = new WriteFile(fileSize);
                            write.setWf(writeFile);
                            String replace = null;
                            try {
                                String[] split = line.toString().split("\\|");
                                if (split[0].contains(".")) {
                                    replace = split[9].substring(0, 23).replace("-", "").replace(":", "").replace(".", "").replace(" ", "");
                                } else {
                                    replace = split[8].substring(0, 23).replace("-", "").replace(":", "").replace(".", "").replace(" ", "");
                                }
                            } catch (Exception e) {
                                System.out.println("[时间解析错误]");
                            }
                            write.setTime(replace);
                            write.getWf().initialize(outputPath + "/" + write.getFile());
                            if (write.getWf().write(line.toString())) {
                                write.getWf().close();
                                FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
                                write.setWf(null);
                                write.setFileSignCount(write.getFileSignCount() + 1);
                            }
                        }
                    } else {
                        if (timeOut >= 100) {
                            if (write.getWf() != null) {
                                write.getWf().close();
                                FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
                                write.setWf(null);
                                write.setFileSignCount(write.getFileSignCount() + 1);
                                System.out.println("文件写入完毕");
                                System.gc();
                            }
                            timeOut = 0;
                        }
                        try {
                            Thread.sleep(500L);
                            timeOut += 1;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        while (true) {
            //列出所有可用文件（.csv结尾）文件，并且排序
            List<String> list = FileDealUntil.fileListAndSort(inputPath, fileNameTimeIndex);
            System.out.println("[扫描文件列表]<->" + list);
            long l1 = System.currentTimeMillis();
            for (String file : list) {
                //读取一个文件，放到容器
                readFile.read(inputPath + "/" + file);
                System.out.println("[读入文件]<->" + inputPath + "/" + file);
                System.out.println("[文件条数]<->" + readFile.size());
                FileDealUntil.moveFile(inputPath + "/" + file, backupPath + "/" + file);
                System.out.println(inputPath + "/" + file + "处理完毕，移动到" + backupPath + "/" + file);
            }
            long l2 = System.currentTimeMillis();
            System.out.println("[排序时间为:]" + (l2 - l1));
            list.clear();

            if (sortOutTimeCount >= sortOutTime) {
                //排序缓冲区置零，吐出所有缓冲区中数据
                ReadFile.MaxCount = 0;
            }

            //算法主体流程
            MmeMethod.calc(readFile.list, writeMap, linedListHeader, relationResult, time);

            if (ReadFile.MaxCount == 0) {
                System.out.println("[输出残存在map中的数据,数据数量:]<->" + writeMap.size());
                for (String str : writeMap.keySet()) {
                    relationResult.add(writeMap.get(str).getMmeCommon());
                }
                writeMap.clear();
                linedListHeader.head = null;
                linedListHeader.thisNode = null;
                //排序缓冲区重置
                ReadFile.MaxCount = sortMaxBuffer;
                sortOutTimeCount = 0;
            }
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
