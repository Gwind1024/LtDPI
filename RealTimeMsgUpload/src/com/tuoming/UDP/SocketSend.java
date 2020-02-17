package com.tuoming.UDP;

import com.tuoming.common.CdrKind;
import com.tuoming.utils.UDMsgSendCount;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Map;
import java.util.Queue;

public class SocketSend implements Runnable {

    private Logger logger = Logger.getLogger(SocketSend.class);
    private volatile Queue<byte[]> realMsgList;
    private DatagramSocket sendDatagramSocket = null;
    private volatile int port;
    private InetAddress address;
    private DatagramPacket sendPscket;
//    private volatile UDMsgSendCount userSendCount;
    private volatile Map<Integer, UDMsgSendCount> udpMsgCountMap;
    private boolean flagUser = true;
    private boolean flagOnOff = true;
    private boolean flagCalling = true;
    private boolean flagLocation = true;
    private boolean flagUser5G = true;

    public SocketSend(Queue<byte[]> realMsgList,int localPort,int toPort,String toIP,Map<Integer, UDMsgSendCount> udpMsgCountMap){
        this.realMsgList = realMsgList;
        this.port = toPort;
        try {
            this.sendDatagramSocket = new DatagramSocket(localPort);
            this.address = InetAddress.getLocalHost();
//            this.address = InetAddress.getByName(toIP);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.udpMsgCountMap = udpMsgCountMap;
    }
    @Override
    public void run() {
        Thread.currentThread().setName("UDP消息发送线程");
        logger.info(Thread.currentThread().getName()+"启动!");
        while (true){
            while(realMsgList.size() > 0){
                byte[] realMsg = realMsgList.poll();
                if(sendPscket == null){
                    sendPscket = new DatagramPacket(realMsg,realMsg.length,address,port);
                }else {
                    sendPscket.setData(realMsg);
                }
                try {
                    sendDatagramSocket.send(sendPscket);
                    switch (realMsg.length){
                        case CdrKind.USER_LEN :
                            if(flagUser){
                                UDMsgSendCount userSendCount = new UDMsgSendCount();
                                udpMsgCountMap.put(CdrKind.USER,userSendCount);
                                userSendCount.userMsgSum.getAndIncrement();
                                flagUser = false;
                            }else{
                                UDMsgSendCount userSendCount = udpMsgCountMap.get(CdrKind.USER);
                                userSendCount. userMsgSum.getAndIncrement();
                            }
                            break;
                        case CdrKind.ON_OFF_LEN :
                            if(flagOnOff){
                                UDMsgSendCount onOffSendCount = new UDMsgSendCount();
                                udpMsgCountMap.put(CdrKind.ON_OFF,onOffSendCount);
                                onOffSendCount.onOffMsgSum.getAndIncrement();
                                flagOnOff = false;
                            }else{
                                UDMsgSendCount onOffSendCount = udpMsgCountMap.get(CdrKind.ON_OFF);
                                onOffSendCount.onOffMsgSum.getAndIncrement();
                            }
                            break;
                        case CdrKind.CALLING_LEN :
                            if(flagCalling){
                                UDMsgSendCount callingSendCount = new UDMsgSendCount();
                                udpMsgCountMap.put(CdrKind.CALLING,callingSendCount);
                                callingSendCount.callingMsgSum.getAndIncrement();
                                flagCalling = false;
                            }else{
                                UDMsgSendCount callingSendCount = udpMsgCountMap.get(CdrKind.CALLING);
                                callingSendCount.callingMsgSum.getAndIncrement();
                            }
                            break;
                        case CdrKind.LOCATION_LEN :
                            if(flagLocation){
                                UDMsgSendCount locationSendCount = new UDMsgSendCount();
                                udpMsgCountMap.put(CdrKind.LOCATION,locationSendCount);
                                locationSendCount.locationMsgSum.getAndIncrement();
                                flagLocation = false;
                            }else{
                                UDMsgSendCount locationSendCount = udpMsgCountMap.get(CdrKind.LOCATION);
                                locationSendCount.locationMsgSum.getAndIncrement();
                            }
                            break;
                        case CdrKind.USER_5G_LEN :
                            if(flagUser5G){
                                UDMsgSendCount user5GSendCount = new UDMsgSendCount();
                                udpMsgCountMap.put(CdrKind.USER_5G,user5GSendCount);
                                user5GSendCount.user5gMsgSum.getAndIncrement();
                                flagUser5G = false;
                            }else{
                                UDMsgSendCount user5GSendCount = udpMsgCountMap.get(CdrKind.USER_5G);
                                user5GSendCount.user5gMsgSum.getAndIncrement();
                            }
                            break;
                        default:


                    }
                } catch (IOException e) {
                    logger.error("UDP发送失败！");
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
