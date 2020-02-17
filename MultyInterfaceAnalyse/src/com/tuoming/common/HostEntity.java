package com.tuoming.common;

import redis.clients.jedis.Jedis;

public abstract class HostEntity{
    protected String imsi;
    protected String host;
    public static Jedis jedis;
}
