package com.tuoming;

import com.tuoming.common.FileDealUntil;
import com.tuoming.readfile.S1uDNSReadFile;
import com.tuoming.utils.RedisUntil;
import redis.clients.jedis.Jedis;

import java.util.LinkedList;

public class DnsToRedis {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("【Class    】:" + DnsToRedis.class.getName());
            System.out.println("【Parameter】:输入目录、ReidsIP、RedisPwd、mvDir");
            System.out.println("【Example  】:E:/联通dpi/版本2/gf1、192.168.2.142、123456、E:/联通dpi/版本2/gf2");
            System.exit(0);
        }

        String inputDir = args[0];
        String redisIp = args[1];
        String redisPwd = args[2];
        String mvDir = args[3];

        S1uDNSReadFile readFile = new S1uDNSReadFile();
        Jedis redis = RedisUntil.getRedis(redisIp, redisPwd);


        while (true) {
            //列出所有可用文件（.txt结尾）文件
            String[] list = FileDealUntil.fileList(inputDir);
            System.out.println("扫描文件列表" + list);
            for (String file : list) {
                readFile.read(inputDir + "/" + file);
                LinkedList<String> fileBuffer = (LinkedList<String>) readFile.getFileBuffer();
                while (fileBuffer.size() > 0) {
                    String line = fileBuffer.removeFirst();
                    String[] split = line.split("\\|", -1);
                    if (!"".equals(split[0]) && !"".equals(split[1])) {
                        RedisUntil.set(redis, split[0], split[1]);
                    }
                }
                FileDealUntil.moveFile(inputDir + "/" + file, mvDir + "/" + file);
            }
            try {
                //5秒扫描一次文件
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
