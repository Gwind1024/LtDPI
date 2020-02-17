package com.tuoming;

import com.tuoming.common.*;
import com.tuoming.entity.s10s11.S10_S11Decode;
import com.tuoming.entity.s10s11.S10_S11Index;
import com.tuoming.readfile.ReadFile;
import com.tuoming.readfile.S10S11ReadFile;
import com.tuoming.redis.RedisThread;
import com.tuoming.sort.SortEntity;
import com.tuoming.writefile.Write;
import com.tuoming.writefile.WriteIndex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * S10S11口结构化类
 */
public class S10S11Analyse {
    public static void main(String[] args) {
        //输入参数检查
        if (args.length < 11) {
            System.out.println("【Class    】:" + S10S11Analyse.class.getName());
            System.out.println("【Parameter】:网元表位置、时间粒度(minute)、大小粒度(k)、输入目录、备份目录、输出目录、redisIp、redisPwd、原始文件名时间索引（210000_1_LTE-S6a_20190610145320_00.csv)、排序缓冲（条）、排序超时时间（s）");
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
        CommonDecode.jedis = RedisUntil.getRedis(redisIP, redisPwd);
        //获取网元表
        ReadPublicTable publicTable = new ReadPublicTable();
        publicTable.read(publicTablePath);
        Map<String, String> publicTableMap = publicTable.getPublicTable();

        System.out.println("网元公参表" + publicTableMap);

        ReadFile readFile = new S10S11ReadFile();
        CommonDecode line = new S10_S11Decode();

        //写文件Map网元->s10s11Write
        //每个网元对应一个写通道
        HashMap<String, Write> writeMap = new HashMap<>();

        int sortOutTimeCount = 0;

        new Thread(new RedisThread()).start();

        while (true) {
            //列出所有可用文件（.csv结尾）文件
            List<String> list = FileDealUntil.fileListAndSort(inputPath, fileNameTimeIndex);
            System.out.println("扫描文件列表" + list);
            long l1 = System.currentTimeMillis();
            for (String file : list) {
                //读取一个文件
                readFile.read(inputPath + "/" + file);
                System.out.println("读入文件" + inputPath + "/" + file);
                System.out.println("文件总条数" + readFile.size());
                FileDealUntil.moveFile(inputPath + "/" + file, backupPath + "/" + file);
                System.out.println(inputPath + "/" + file + "处理完毕，移动到" + backupPath + "/" + file);
            }
            list.clear();

            long l2 = System.currentTimeMillis();
            System.out.println("处理文件总时间总时间：" + (l2 - l1) + "ms");
            list.clear();

            SortEntity sort = null;
            if (sortOutTimeCount >= sortOutTime) {
                //排序缓冲区置零，吐出所有缓冲区中数据
                ReadFile.MaxCount = 0;
            }

            while ((sort = readFile.list.getFirst()) != null) {
                sortOutTimeCount = 0;
                String[] split = sort.str.split(S10_S11Index.XdrSplit, -1);
                line.decode(split);
                WriteUntil.dealData(line, publicTableMap, cycleTime, writeMap, outputPath, fileSize, WriteIndex.s10S11Write);
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
