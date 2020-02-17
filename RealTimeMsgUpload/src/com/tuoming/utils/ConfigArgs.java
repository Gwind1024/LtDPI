package com.tuoming.utils;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;

public class ConfigArgs {
    private Logger logger = Logger.getLogger(ConfigArgs.class);

    //mcDir文件存放目录
    public String mcDir;
    //iupsDir文件存放目录
    public String iupsDir;
    //mmeDir文件存放目录
    public String mmeDir;
    //江苏配置表目录
    public String jiangsuDir;
    //南京配置表目录
    public String nanjingDir;

    //http文件存放目录
    public String httpDir;
    //gen文件存放目录
    public String genDir;
    //stream文件存放目录
    public String streamDir;

    //实时消息统计结果文件存放目录
    public String countResultDir;

    //网元信息表位置
    public String networkTable;

    //redis服务器IP地址
    public String redisAddr;
    //redis服务器连接用户名
    public String redisUser;
    //redis服务器连接密码
    public String redisPassword;
    //UDP发送端本地端口Port
    public int sendLocalPort;
    //UDP发送端发送端口Port
    public int sendPort;
    //UDP发送端发送IP地址
    public String sendIPAddr;
    //UDP接收端监听端口Port
    public int recListenPort;
    //UDP接收端缓冲区大小（字节）
    public int recBufferSize;

    public void Initialize(File f) {
        try {
            logger.info("-------------------------------------------");
            logger.info("开始读取配置文件(config.xml).......");
//            File f = new File("resources/config.xml");
            SAXReader reader = new SAXReader();
            Document doc = reader.read(f);
            Element root = doc.getRootElement();


            mcDir = root.elementText("mcDir");
            logger.info("[http文件存放目录]<->" + mcDir);

            iupsDir = root.elementText("iupsDir");
            logger.info("[http文件存放目录]<->" + iupsDir);

            mmeDir = root.elementText("mmeDir");
            logger.info("[http文件存放目录]<->" + mmeDir);

            jiangsuDir = root.elementText("jiangsuDir");
            logger.info("[江苏配置表目录]<->" + jiangsuDir);

            nanjingDir = root.elementText("nanjingDir");
            logger.info("[南京配置表目录]<->" + nanjingDir);

            httpDir = root.elementText("httpDir");
            logger.info("[http文件存放目录]<->" + httpDir);

            genDir = root.elementText("genDir");
            logger.info("[gen文件存放目录]<->" + genDir);

            streamDir = root.elementText("streamDir");
            logger.info("[stream文件存放目录]<->" + streamDir);

            countResultDir = root.elementText("countResultDir");
            logger.info("[实时消息统计结果文件存放目录]<->" + countResultDir);

            networkTable = root.elementText("networkTable");
            logger.info("[网元信息表位置]<->" + networkTable);

            redisAddr = root.elementText("redisAddr");
            logger.info("[redis服务器IP地址]<->" + redisAddr);

            redisUser = root.elementText("redisUser");
            logger.info("[redis服务器连接用户名]<->" + redisUser);

            redisPassword = root.elementText("redisPassword");
            logger.info("[redis服务器连接密码]<->" + redisPassword);

            sendLocalPort = createConf(root, "sendLocalPort", "UDP发送端本地端口Port");
            logger.info("[UDP发送端本地端口Port]<->" + sendLocalPort);

            sendPort = createConf(root, "sendPort", "UDP发送端发送端口Port");
            logger.info("[UDP发送端发送端口Port]<->" + sendPort);

            sendIPAddr = root.elementText("sendIPAddr");
            logger.info("[UDP发送端发送IP地址]<->" + sendIPAddr);

            recListenPort = createConf(root, "recListenPort", "UDP接收端监听端口Port");
            logger.info("[UDP接收端监听端口Port]<->" + recListenPort);

            recBufferSize = createConf(root, "recBufferSize", "UDP接收端缓冲区大小（字节）");
            logger.info("[UDP接收端缓冲区大小（字节）]<->" + recBufferSize);

            logger.info("配置文件读取完毕!");
            logger.info("-------------------------------------------");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("读取配置文件失败:./conf/config.xml,程序退出");
            System.exit(0);
        }
    }

    private Integer createConf(Element root, String str, String strInfo) {
        String strText = root.elementText(str);
        Integer strTextChange = CommonUtils.strToInteger(strText);
        if (strTextChange == -1) {
            logger.error("[" + strInfo + "]<->解析失败,程序退出:" + strText);
            System.exit(0);
        }
        return strTextChange;
    }

}
