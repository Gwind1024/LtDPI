package com.tuoming.signalling;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Demo {
    public volatile static Queue<byte[]> realMsgList = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) throws Exception {

//        Jedis redis = RedisUntil.getRedis("192.168.2.142", "123456");
//        UDMsgSendCount userSendCount = new UDMsgSendCount();
//        McThread iupsThread = new McThread("E:\\联通dpi\\版本2\\gf2\\mc|E:\\联通dpi\\版本2\\gf1\\mc|MC_LOCATION|MC_SWITCH", realMsgList, redis, userSendCount);
//        new Thread(iupsThread).start();
//        Thread.sleep(5000L);

//        MmeThread iupsThread = new MmeThread("E:\\联通dpi\\版本2\\gf2\\mme|E:\\联通dpi\\版本2\\gf1\\mme", realMsgList, redis, userSendCount);
//        new Thread(iupsThread).start();
//        Thread.sleep(5000L);

        LinkedList<String> list = new LinkedList<>();
        list.add("fsf");
        list.add(1, "");


    }
}
