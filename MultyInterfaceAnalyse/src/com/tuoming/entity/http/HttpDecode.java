package com.tuoming.entity.http;

import com.tuoming.common.CommonDecode;
import com.tuoming.common.RedisUntil;
import redis.clients.jedis.Jedis;

public class HttpDecode extends CommonDecode {
    public static Jedis jedis;
    public String host = "";

    public static Jedis redis = RedisUntil.getRedis("192.168.2.142", "123456");


    @Override
    public void decode(String[] arr) {

    }

    @Override
    public String getEndTime() {
        return null;
    }

    @Override
    public String getMmeIp() {
        return null;
    }

    public HttpDecode() {
    }


}
