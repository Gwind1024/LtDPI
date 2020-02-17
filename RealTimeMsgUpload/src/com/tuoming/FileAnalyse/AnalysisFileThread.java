package com.tuoming.FileAnalyse;

import com.tuoming.common.CdrKind;
import com.tuoming.common.RealTimeMsgAlg;
import com.tuoming.user.GenInfo;
import com.tuoming.user.HttpInfo;
import com.tuoming.user.StreamInfo;
import com.tuoming.user.UserInfo;
import com.tuoming.utils.UDMsgSendCount;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;

import java.io.*;
import java.util.Map;
import java.util.Queue;

public class AnalysisFileThread implements Runnable{
    private Logger logger = Logger.getLogger(AnalysisFileThread.class);
    private File s1uFile = null;
    private volatile Queue<byte[]> realMsgList;
    private int flag = -1;
    private UserInfo userInfo;
    private RealTimeMsgAlg mgAlg;
    private String removePath;
    private File removeDir;
    private Map<String,Integer> flowMap;

    public AnalysisFileThread(File s1uFile,Queue<byte[]> realMsgList,int cdrFlag,Map<String,Integer> flowMap,UDMsgSendCount userSendCount,Jedis redis){
        this.s1uFile = s1uFile;
        this.realMsgList = realMsgList;
        this.mgAlg = new RealTimeMsgAlg(redis,userSendCount);
        this.flag = cdrFlag;
        this.flowMap = flowMap;
//        this.udpMsgCountMap = udpMsgCountMap;
    }
    public AnalysisFileThread(File s1uFile,Queue<byte[]> realMsgList,int cdrFlag,Map<String,Integer> flowMap,UDMsgSendCount userSendCount){
        this.s1uFile = s1uFile;
        this.realMsgList = realMsgList;
        this.mgAlg = new RealTimeMsgAlg(null,userSendCount);
        this.flag = cdrFlag;
        this.flowMap = flowMap;
//        this.udpMsgCountMap = udpMsgCountMap;
    }
    @Override
    public void run() {
//        String s1uPath = s1uFile.getName().toLowerCase();
        String parent = s1uFile.getParent();
        if(flag == CdrKind.HTTP){
            userInfo = new HttpInfo(flowMap);
            removePath = parent+File.separator+"http_bak";
            Thread.currentThread().setName("HTTP文件处理线程");
        }else if(flag == CdrKind.GEN){
            userInfo = new GenInfo(flowMap);
            removePath = parent+File.separator+"gen_bak";
            Thread.currentThread().setName("GENERAL文件处理线程");
        }else if(flag == CdrKind.STREAM){
            userInfo = new StreamInfo(flowMap);
            removePath = parent+File.separator+"stream_bak";
            Thread.currentThread().setName("STREAM文件处理线程");
        }else{
            logger.error("ERROR FILE TYPE");
            return;
        }
        logger.info(Thread.currentThread().getName()+"启动!");
        logger.info("转移目录:"+removePath);
        String line;
        while(true){

            if(removeDir == null || !removeDir.exists()){
                removeDir = new File(removePath);
                removeDir.mkdirs();
            }

            File[] fileList = s1uFile.listFiles(new FileFilter() {
                @Override
                public boolean accept(File pathname) {
                    if (pathname.isFile() && pathname.getName().endsWith("csv"))
                        return true;
                    return false;
                }
            });
            for(File file : fileList){
                logger.info("["+file.getName()+"]文件开始处理!");
                try(
                        BufferedReader br = new BufferedReader(new FileReader(file))
                ) {
                    while((line = br.readLine()) != null){
                        mgAlg.analysisMsg(line,userInfo,flag,realMsgList);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                logger.info("["+file.getName()+"]文件处理完成!准备转移目录……");
                File removeFile = new File(removePath + File.separator + file.getName());
                if(removeFile.exists()){
                    file.delete();
                }else{
                    file.renameTo(removeFile);
                }
                logger.info("["+file.getName()+"]文件转移成功!");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
