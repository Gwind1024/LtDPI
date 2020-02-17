package com.tuoming.signalling;

import com.tuoming.common.FileDealUntil;
import com.tuoming.readfile.SigReadFile;
import com.tuoming.signalling.mme.MMeInfo;
import com.tuoming.utils.UDMsgSendCount;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class MmeThread implements Runnable {
    private String path;
    private Queue<byte[]> realMsgList;
    private Jedis jedis;
    private UDMsgSendCount count;


    public MmeThread(String path, Queue<byte[]> realMsgList, Jedis jedis,UDMsgSendCount count) {
        this.path = path;
        this.realMsgList = realMsgList;
        this.jedis = jedis;
        this.count=count;
    }

    public static void main(String[] args) {
        List<byte[]> result = new ArrayList<>();
        SigReadFile sigReadFile = new SigReadFile();
        sigReadFile.read("F:\\a.csv");
        List<String> list = sigReadFile.getFileBuffer();
        for (String str : list) {
            byte[] bytes = MMeInfo.mmeTobytes(str, null);
            if (bytes != null) {
               result.add(bytes);
            }
        }
        System.out.println(result.size());
    }

    @Override
    public void run() {
        Thread.currentThread().setName("Mme线程");
        String[] split = path.split("\\|");
        String inPath = split[0];
        String outPath = split[1];
        while (true) {
            //F:\dpi\mme|F:\dpi\mme
            String[] pathArr = FileDealUntil.scanFile(inPath);
            for (String path : pathArr) {
                SigReadFile sigReadFile = new SigReadFile();
                sigReadFile.read(inPath + "/" + path);
                List<String> list = sigReadFile.getFileBuffer();
                for (String str : list) {
                    byte[] bytes = MMeInfo.mmeTobytes(str, jedis);
                    if (bytes != null) {
                        realMsgList.add(bytes);
                        count.mmeSum.getAndIncrement();
                    }
                }
                FileDealUntil.moveFile(inPath + "/" + path, outPath + "/" + path);
            }

            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
