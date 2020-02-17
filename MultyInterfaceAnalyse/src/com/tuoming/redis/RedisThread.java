package com.tuoming.redis;

import com.tuoming.common.CommonDecode;
import com.tuoming.common.RedisUntil;

public class RedisThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            RedisUntil.setBatch(CommonDecode.jedis, RedisUntil.map);
        }
    }
}
