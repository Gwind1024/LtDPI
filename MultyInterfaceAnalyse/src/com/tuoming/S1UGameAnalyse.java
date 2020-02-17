package com.tuoming;

import com.tuoming.common.*;
import com.tuoming.entity.s1ugame.*;
import com.tuoming.readfile.ReadFile;
import com.tuoming.readfile.S1ugameReadFile;
import com.tuoming.sort.SortEntity;
import com.tuoming.writefile.Write;
import com.tuoming.writefile.WriteFactory;
import com.tuoming.writefile.WriteFile;
import com.tuoming.writefile.WriteIndex;
import java.io.IOException;
import java.util.*;

public class S1UGameAnalyse {

    public static void main(String args[]) throws IOException {

        /**
         * 1.检查参数
         * 2.读取文件
         * 3.处理后输出
         */

        //输入参数检查
        if(args.length <11){
            System.out.println("【Class    】:" + S1UGameAnalyse.class.getName());
            System.out.println("输入参数格式： 网元表位置、时间粒度(minute)、大小粒度(k)、输入目录、备份目录、输出目录、redisIp、redisPwd、原始文件名时间索引（210000_1_LTE-S6a_20190610135320_00.csv)、排序缓冲（条）、排序超时时间（s）");
            System.out.println("输入参数示例： F:/dataDemo/public.txt、1440、200、F:/dataDemo/input、F:/dataDemo/backup、F:/dataDemo/output、192.168.2.142、123456、4、1024、30");
            System.exit(0);
        }

        System.out.println("输入参数" + Arrays.toString(args));
        //网元表位置文件
        String publicTablePath = args[0];
        //文件生成时间(分钟)
        Integer cycleTime = Integer.parseInt(args[1]);
        //文件生成大小（k）
        Integer fileSize = Integer.parseInt(args[2]);
        //源文件位置文件夹
        String inputPath = args[3];
        //备份文件位置文件夹
        String backupPath = args[4];
        FileDealUntil.pathCheck(backupPath);
        //输入文件目录
        String outputPath = args[5];
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
        ReadFile readFile = new S1ugameReadFile();
        //写文件Map网元->SgsWrite
        //每个网元对应一个写通道
        HashMap<String, Write> writeMap = new HashMap<>();

        int sortOutTimeCount = 0;
        while (true){
            //列出所有可用文件（.csv结尾）文件
            List<String> list = FileDealUntil.fileListAndSort(inputPath,fileNameTimeIndex);
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
            long l2 = System.currentTimeMillis();
            System.out.println("处理文件总时间总时间：" + (l2 - l1) + "ms");
            list.clear();

            SortEntity sort = null;
            if (sortOutTimeCount >= sortOutTime) {
                //排序缓冲区置零，吐出所有缓冲区中数据
                ReadFile.MaxCount = 0;
            }

            long fiveminutes =0;
            List<String[]> listsplise = new ArrayList<String[]>();
            while ((sort = readFile.list.getFirst()) != null) {
                if(fiveminutes == 0)
                {
                    fiveminutes = sort.time;
                }
                else if((sort.time - fiveminutes) > 300000)
                {
                    pubWrite(listsplise,publicTableMap,cycleTime,writeMap,outputPath,fileSize);
                    fiveminutes = sort.time;

                }
                listsplise.add(sort.str.split(S1ugameDecode.splite, -1));
                sortOutTimeCount = 0;
            }

            if(listsplise.size()>0)
                pubWrite(listsplise,publicTableMap,cycleTime,writeMap,outputPath,fileSize);

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
    public static void pubWrite(List<String[]> listsplise,Map<String, String> publicTableMap,Integer cycleTime,HashMap<String, Write> writeMap,String outputPath,Integer fileSize)
    {
        S1ugameDecode line = new S1ugameDecode();
        line.decode(listsplise);
        if (line.getMmeIp() == null || line.getEndTime() == null) {
            return;
        }
        String networkElement = publicTableMap.get(line.getMmeIp());
        if (networkElement == null) {
            networkElement = "other";
        }
        String time = FileDealUntil.timeTranform(line.getEndTime(), cycleTime);
        if (time == null) {
            return;
        }

        //判断是否达到文件生成时间，达到关闭通道，生成文件
        for (String ne : writeMap.keySet()) {
            Write write = writeMap.get(ne);
            if (FileDealUntil.longTimeToMin(time) - FileDealUntil.longTimeToMin(write.getTime()) >= cycleTime) {
                write.getWf().close();
                write.setWf(null);
                FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
            }
        }

        for(String s : line.resultarr){
            //写入第一条数据，绑定时间
            if (!writeMap.keySet().contains(networkElement)) {
                WriteFile writeFile = new WriteFile(fileSize);
                Write write = WriteFactory.getWrite(WriteIndex.imWrite, time);
                String fileName = write.getFile(cycleTime, networkElement);
                writeFile.initialize(outputPath + "/" + fileName);
                if (writeFile.write(s)) {
                    writeFile.close();
                    writeFile = null;
                    FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
                }
                write.setWf(writeFile);
                writeMap.put(networkElement, write);
                //非第一条数据
            } else {
                Write write = writeMap.get(networkElement);
                if (write.getWf() != null) {
                    if (write.getWf().write(s)) {
                        write.getWf().close();
                        write.setWf(null);
                        FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
                    }
                } else {
                    write.setFileSignCount(write.getFileSignCount() + 1);
                    write.setTime(time);
                    String file2 = write.getFile(cycleTime, networkElement);
                    WriteFile wf = new WriteFile(fileSize);
                    //重新绑定文件
                    wf.initialize(outputPath + "/" + file2);
                    if (wf.write(s)) {
                        wf.close();
                        wf = null;
                        FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
                    }
                    write.setWf(wf);
                }
            }
        }
    }
}

