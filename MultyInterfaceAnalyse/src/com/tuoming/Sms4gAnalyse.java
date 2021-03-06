package com.tuoming;


import com.tuoming.common.*;
import com.tuoming.count.CountNum;
import com.tuoming.count.MysqlUntil;
import com.tuoming.entity.sms4g.Sms4gDecode;
import com.tuoming.entity.sms4g.Sms4gIndex;
import com.tuoming.readfile.ReadFile;
import com.tuoming.readfile.Sms4gReadFile;
import com.tuoming.sort.SortEntity;
import com.tuoming.writefile.Write;
import com.tuoming.writefile.WriteIndex;

import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.*;

public class Sms4gAnalyse {
    public static void main(String[] args) {
        if (args.length < 11) {
            System.out.println("【Class    】:" + Sms4gAnalyse.class.getName());
            System.out.println("【Parameter】:网元表位置、时间粒度(minute)、大小粒度(k)、输入目录、备份目录、输出目录、redisIp、redisPwd、原始文件名时间索引（210000_1_LTE-4GSMS_20190610135320_00.csv)、排序缓冲（条）、排序超时时间（s）");
            System.out.println("【Example  】:F:/dataDemo/public.txt、1440、200、F:/dataDemo/input、F:/dataDemo/backup、F:/dataDemo/output、192.168.2.142、123456、3、1024、30");
            System.exit(0);
        }
        //F:/dataDemo/public.txt 1440 20 F:/dataDemo/input F:/dataDemo/output
        System.out.println("输入参数" + Arrays.toString(args));
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
        //redis连接赋值
        CommonDecode.jedis = RedisUntil.getRedis(redisIP, redisPwd);
        //获取网元表
        ReadPublicTable publicTable = new ReadPublicTable();
        publicTable.read(publicTablePath);
        Map<String, String> publicTableMap = publicTable.getPublicTable();

        System.out.println("网元公参表" + publicTableMap);

        ReadFile readFile = new Sms4gReadFile();

        //写文件Map网元->Sms4gWrite
        //每个网元对应一个写通道
        HashMap<String, Write> writeMap = new HashMap<>();

        MysqlUntil mysqlUntil = new MysqlUntil();
        Connection connect = mysqlUntil.connect(redisIP,redisPwd);
        mysqlUntil.createTable("sms4gcount", connect);

        CommonDecode line = new Sms4gDecode();

        double sortOutTimeCount = 0;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();

        while (true) {
            CountNum count = null;
            //列出所有可用文件（.txt结尾）文件
            List<String> list = FileDealUntil.fileListAndSort(inputPath, fileNameTimeIndex);
            System.out.println("扫描文件列表" + list);
            long rT1 = System.currentTimeMillis();
            for (String file : list) {
                //读取一个文件
                readFile.read(inputPath + "/" + file);
                FileDealUntil.moveFile(inputPath + "/" + file, backupPath + "/" + file);
            }
            long rT2 = System.currentTimeMillis();
            if (list.size() > 0 && readFile.size() > sortMaxBuffer) {
                count = new CountNum();
                count.init();
                date.setTime(rT1);
                String time = sdf.format(date);
                count.time = time;
                count.timeStamp = date.getTime();
                count.fileCount = list.size();
                count.intputNum = readFile.size()-sortMaxBuffer;
            }
            list.clear();
            SortEntity sort = null;
            if (sortOutTimeCount >= sortOutTime) {
                //排序缓冲区置零，吐出所有缓冲区中数据
                ReadFile.MaxCount = 0;
            }
            long dT1 = System.currentTimeMillis();
            boolean flag = true;
            while ((sort = readFile.list.getFirst()) != null) {
                flag = false;
                sortOutTimeCount = 0;
                String[] split = sort.str.split(Sms4gIndex.splite, -1);
                line.decode(split);
                WriteUntil.dealData(line, publicTableMap, cycleTime, writeMap, outputPath, fileSize, WriteIndex.sms4gWrite);
            }
            if (!flag) {
                sortOutTimeCount = 0;
            }
            long dT2 = System.currentTimeMillis();
            if (count != null) {
                count.dealTime = (rT2 - rT1) + (dT2 - dT1);
                count.dealSpeed = Integer.parseInt(String.format("%.0f", (count.intputNum * 1.0 / count.dealTime) * 1000));
                count.backFillRatio = Double.parseDouble(String.format("%.5f", (CountNum.outputNum - CountNum.nobackFillNum) * 1.0 / CountNum.outputNum));
                mysqlUntil.insert(count, connect, "sms4gcount");
            }

            if (ReadFile.MaxCount == 0) {
                WriteUntil.dealFinal(writeMap, outputPath);
                //排序缓冲区重置
                ReadFile.MaxCount = sortMaxBuffer;
                sortOutTimeCount = 0;
            }

            if (flag) {
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
