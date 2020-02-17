package com.tuoming.signalling;

import com.tuoming.common.FileDealUntil;
import com.tuoming.readfile.SigReadFile;
import com.tuoming.signalling.mme.MMeInfo;
import com.tuoming.utils.UDMsgSendCount;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Queue;

public class MmeThread implements Runnable {
    private Logger logger = Logger.getLogger(MmeThread.class);
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

    @Override
    public void run() {
        Thread.currentThread().setName("Mme文件处理线程");
        logger.info(Thread.currentThread().getName()+"启动!");
        String[] split = path.split("\\|");
        String inPath = split[0];
        String outPath = split[1];
        while (true) {
            //F:\dpi\mme|F:\dpi\mme
            String[] pathArr = FileDealUntil.scanFile(inPath);
            for (String path : pathArr) {
                logger.info("["+path+"]文件开始处理!");
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
                logger.info("["+path+"]文件处理完成!准备转移目录……");
                FileDealUntil.moveFile(inPath + "/" + path, outPath + "/" + path);
                logger.info("["+path+"]文件转移完成!");
            }

            try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
