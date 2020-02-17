package com.tuoming;

import com.tuoming.FileAnalyse.AnalysisFileThread;
import com.tuoming.UDP.SocketRecieve;
import com.tuoming.UDP.SocketSend;
import com.tuoming.common.CdrKind;
import com.tuoming.common.DealMethod;
import com.tuoming.common.ReadPublicTable;
import com.tuoming.readfile.SigReadFile;
import com.tuoming.signalling.IupsThread;
import com.tuoming.signalling.McThread;
import com.tuoming.signalling.MmeThread;
import com.tuoming.signalling.SignallingDeal;
import com.tuoming.utils.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import redis.clients.jedis.Jedis;

import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 实时消息发送驱动类
 */
public class RTMsgUploadDriver {
    private static volatile Map<Integer, UDMsgSendCount> udpMsgCountMap = new ConcurrentHashMap<>();
    private static Logger logger = Logger.getLogger(RTMsgUploadDriver.class);
    private static volatile Queue<byte[]> realMsgList = new ConcurrentLinkedQueue<>();
    private static Map<String, Integer> flowMap = new HashMap<>();
    //漫游公参表
    public static Map<String,Integer> manyou=new HashMap<>();

    public static void main(String[] args) {
        Thread.currentThread().setName("实时消息处理主线程");
        try {
            PropertyConfigurator.configure("./resources/log4j.properties");
        } catch (Exception e) {
            logger.error("读取日志配置文件失败:./resources/log4j.properties，程序退出");
            System.exit(0);
        }
        logger.info(Thread.currentThread().getName() + "启动!");
        long time = System.currentTimeMillis();
        //配置文件
        File conf = new File("resources/config.xml");
        //初始化配置参数
        ConfigArgs configArgs = new ConfigArgs();
        configArgs.Initialize(conf);
        //记录配置文件修改日期
        long modTime = conf.lastModified();
        //初始化业务对照表
        InitMapUtil.InitMap(flowMap);

        DealMethod.setManyou(configArgs,manyou);


        File httpFile = null;
        File genFile = null;
        File streamFile = null;

        boolean http_flag = false;
        boolean gen_flag = false;
        boolean stream_flag = false;

        boolean mc_flag = false;
        boolean mme_flag = false;
        boolean iups_flag = false;

        //获取网元表
        /*ReadPublicTable publicTable = new ReadPublicTable();
        publicTable.read(configArgs.networkTable);
        Map<String, String> publicTableMap = publicTable.getPublicTable();*/

        //iups目录
        String iupsPath = configArgs.iupsDir;
        //mc目录
        String mcPath = configArgs.mcDir;
        //mme目录
        String mmePath = configArgs.mmeDir;

        //http用户面输入目录
        String httpInput = configArgs.httpDir;
        //gen用户面输入目录
        String genInput = configArgs.genDir;
        //stream用户面输入目录
        String streamInput = configArgs.streamDir;
        //实时消息统计结果文件存放目录
        String countResultDir = configArgs.countResultDir;

        UDMsgSendCount userSendCount = new UDMsgSendCount();

        RealTimeMsgCountUtil msgCountUtil = new RealTimeMsgCountUtil(countResultDir, udpMsgCountMap);

        if (!(iups_flag = SignallingDeal.checkPath(iupsPath))) {
            logger.debug("iups目录不存在!");
        }
        if (!(mc_flag = SignallingDeal.checkPath(mcPath))) {
            logger.debug("mc目录不存在!");
        }
        if (!(mme_flag = SignallingDeal.checkPath(mmePath))) {
            logger.debug("mme目录不存在!");
        }

        if (!"".equals(httpInput)) {
            httpFile = new File(httpInput);
            if (httpFile.exists()) {
                http_flag = true;
            } else {
                logger.debug("http输入目录不存在!");
            }
        } else {
            logger.debug("http输入目录空值!");
        }

        if (!"".equals(genInput)) {
            genFile = new File(genInput);
            if (genFile.exists()) {
                gen_flag = true;
            } else {
                logger.debug("Gen输入目录不存在!");
            }
        } else {
            logger.debug("Gen输入目录空值!");
        }

        if (!"".equals(streamInput)) {
            streamFile = new File(streamInput);
            if (streamFile.exists()) {
                stream_flag = true;
            } else {
                logger.debug("Stream输入目录不存在!");
            }
        } else {
            logger.debug("Stream输入目录空值!");
        }

        /*if(http_flag || gen_flag || stream_flag){
            udpMsgCountMap.put(CdrKind.USER,userSendCount);
        }*/

//        Jedis redis = RedisUntil.getRedis(configArgs.redisAddr, configArgs.redisPassword);
        Jedis redis = null;
        //启动消息发送线程
        SocketSend socketSend = new SocketSend(realMsgList, configArgs.sendLocalPort, configArgs.sendPort, configArgs.sendIPAddr, udpMsgCountMap);
        Thread sochetSendThread = new Thread(socketSend);
        sochetSendThread.start();
        //启动消息接收线程
        SocketRecieve socketRecieve = new SocketRecieve(realMsgList, configArgs.recListenPort, configArgs.recBufferSize);
        Thread socketRecThread = new Thread(socketRecieve);
        socketRecThread.start();

        if (iups_flag) {
            IupsThread iupsThread = new IupsThread(iupsPath, realMsgList,userSendCount);
            new Thread(iupsThread).start();
        }

        if (mc_flag) {
            McThread mcThread = new McThread(mcPath, realMsgList, redis,userSendCount);
            new Thread(mcThread).start();
        }

        if (mme_flag) {
            MmeThread mmeThread = new MmeThread(mmePath, realMsgList, redis,userSendCount);
            new Thread(mmeThread).start();
        }

        if (http_flag) {
            AnalysisFileThread analysisFileThread = new AnalysisFileThread(httpFile, realMsgList, CdrKind.HTTP, flowMap, userSendCount);
            new Thread(analysisFileThread).start();
//            http_flag = false;
        }
        if (gen_flag) {
            AnalysisFileThread analysisFileThread = new AnalysisFileThread(genFile, realMsgList, CdrKind.GEN, flowMap, userSendCount, redis);
            new Thread(analysisFileThread).start();
//            gen_flag = false;
        }
        if (stream_flag) {
            AnalysisFileThread analysisFileThread = new AnalysisFileThread(streamFile, realMsgList, CdrKind.STREAM, flowMap, userSendCount);
            new Thread(analysisFileThread).start();
        }
        //???
        while (true) {
            //检测配置文件
            long temp = conf.lastModified();
            if (temp != modTime) {
                configArgs.Initialize(conf);
                //todo 初始化后重新启动相关

                modTime = temp;
            }
            msgCountUtil.timeGrainDealFile(time);

            time = System.currentTimeMillis();

        }
    }
}
