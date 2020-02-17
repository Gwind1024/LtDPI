package com.tuoming;


import com.tuoming.common.FileDealUntil;
import com.tuoming.count.CountNum;
import com.tuoming.count.MysqlUntil;
import com.tuoming.entity.s1mme.MmeCommon;
import com.tuoming.entity.s1mme.method.LinedListHeader;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.entity.s1mme.method.MmeMethod;
import com.tuoming.readfile.MmeReadFile;
import com.tuoming.readfile.ReadFile;
import com.tuoming.tools.RedisUntil;
import com.tuoming.writefile.MmeRelationWrite;
import redis.clients.jedis.Jedis;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

public class MmeRelationDriver {
    private static List<MmeCommon> relationResult = new ArrayList<>();

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

        MysqlUntil mysqlUntil = new MysqlUntil();
        Connection connect = mysqlUntil.connect(redisIp, redisPwd);
        mysqlUntil.createTable("mmerelcount", connect);

        LinedListHeader linedListHeader = new LinedListHeader();

        MmeRelationWrite write = new MmeRelationWrite();

        double sortOutTimeCount = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

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

        while (true) {
            CountNum count = null;
            //列出所有可用文件（.csv结尾）文件，并且排序
            List<String> list = FileDealUntil.fileListAndSort(inputPath, fileNameTimeIndex);
            System.out.println("[扫描文件列表]<->" + list);

            long rT1 = System.currentTimeMillis();
            for (String file : list) {
                //读取一个文件，放到容器
                readFile.read(inputPath + "/" + file);
                FileDealUntil.moveFile(inputPath + "/" + file, backupPath + "/" + file);
            }
            long rT2 = System.currentTimeMillis();

            if (list.size() > 0 && readFile.size() > sortMaxBuffer) {
                count = new CountNum();
                count.init();
                date.setTime(rT1);
                count.time = sdf.format(date);
                count.timeStamp = date.getTime();
                count.fileCount = list.size();
                count.intputNum = readFile.size() - sortMaxBuffer;
            }
            list.clear();

            if (sortOutTimeCount >= sortOutTime) {
                //排序缓冲区置零，吐出所有缓冲区中数据
                ReadFile.MaxCount = 0;
            }

            long dT1 = System.currentTimeMillis();
            //算法主体流程
            boolean timeFlag = MmeMethod.calc(readFile.list, writeMap, linedListHeader, relationResult, time);
            if (!timeFlag) {
                sortOutTimeCount = 0;
            }
            //残存map中数据输出
            if (ReadFile.MaxCount == 0) {
                for (String str : writeMap.keySet()) {
                    relationResult.add(writeMap.get(str).getMmeCommon());
                }
                writeMap.clear();
                linedListHeader.head = null;
                linedListHeader.thisNode = null;
            }
            MmeMethod.writeToFile(write, relationResult, outputPath, fileSize);
            long dT2 = System.currentTimeMillis();

            //关闭最后一个文件
            if (ReadFile.MaxCount == 0) {
                MmeMethod.dealFinal(write, outputPath);
                //排序缓冲区重置
                ReadFile.MaxCount = sortMaxBuffer;
            }


            if (count != null) {
                count.dealTime = (rT2 - rT1) + (dT2 - dT1);
                count.dealSpeed = Integer.parseInt(String.format("%.0f", (count.intputNum * 1.0 / count.dealTime) * 1000));
                count.backFillRatio = Double.parseDouble(String.format("%.5f", (CountNum.outputNum - CountNum.nobackFillNum) * 1.0 / CountNum.outputNum));
                mysqlUntil.insert(count, connect, "mmerelcount");
            }

            if (timeFlag) {
                try {
                    //5秒扫描一次文件
                    Thread.sleep(500L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //排序超时时间累加
                sortOutTimeCount += 0.5;
            }
        }
    }
}
