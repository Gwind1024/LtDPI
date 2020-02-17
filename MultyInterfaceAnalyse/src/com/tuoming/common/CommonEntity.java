package com.tuoming.common;

import redis.clients.jedis.Jedis;

public abstract class CommonEntity {
    protected String imsi;
    protected String imei;
    protected String msisdn;
    public static Jedis jedis;
}
