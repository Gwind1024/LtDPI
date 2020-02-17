package com.tuoming.UDP;

import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Queue;

public class SocketRecieve implements Runnable{
    private Logger logger = Logger.getLogger(SocketRecieve.class);
    private volatile Queue<byte[]> realMsgList;
    private DatagramSocket revDatagramSocket = null;
    private volatile int listenPort;
//    private InetAddress address;
    private byte[] msgByte;
    private DatagramPacket revPscket;

    public SocketRecieve(Queue<byte[]> realMsgList,int listenPort,int msgCap){
        this.realMsgList = realMsgList;
        this.msgByte = new byte[msgCap];
        try {
            this.revDatagramSocket = new DatagramSocket(listenPort);
            this.revPscket = new DatagramPacket(msgByte,msgCap);
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        Thread.currentThread().setName("UDP消息接收线程");
        logger.info(Thread.currentThread().getName()+"启动!");
        while(true){
            try {
                revDatagramSocket.receive(revPscket);
                int len = revPscket.getLength();
                byte[] data = revPscket.getData();
                byte[] bytes = Arrays.copyOf(data, len);

                logger.info("接收一条UDP消息存入队列,消息大小:"+len);
                realMsgList.add(bytes);
            } catch (IOException e) {
                e.printStackTrace();
                logger.info("接收UDP消息失败!");
            }
        }
    }
}
