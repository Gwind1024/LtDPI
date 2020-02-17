package com.tuoming.utils;

import redis.clients.jedis.*;

import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * redis存储：
 * key:imsi
 * value:imei|msisdn
 */
public class RedisUntil {
    private static JedisPool jedisPool;

    /**
     * 集群方式获取一个jedis连接
     *
     * @param ips   192.168.2.223|192.168.2.225|192.168.2.226
     * @param ports 30001|30002|30003
     * @param pwd   123456
     * @return
     */
    public static JedisCluster getRedis(String ips, String ports, String pwd) {
        String[] splitIps = ips.split("\\|");
        String[] splitPorts = ports.split("\\|");
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(20);
        // 最大空闲数
        poolConfig.setMaxIdle(3);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        poolConfig.setMaxWaitMillis(3000);
        Set<HostAndPort> nodes = new LinkedHashSet<HostAndPort>();
        nodes.add(new HostAndPort(splitIps[0], Integer.parseInt(splitPorts[0])));
        nodes.add(new HostAndPort(splitIps[1], Integer.parseInt(splitPorts[0])));
        nodes.add(new HostAndPort(splitIps[2], Integer.parseInt(splitPorts[0])));

        JedisCluster cluster = new JedisCluster(nodes, 3000, 3000, 3, pwd, poolConfig);
        return cluster;

    }

    public static void main(String[] args) {

    }

    public static Jedis getRedis(String ip, String pwd) {
        if (jedisPool == null) {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            jedisPool = new JedisPool(poolConfig, ip, 6379, 3000, pwd);
        }
        return jedisPool.getResource();
    }

    /**
     * 随机填上imsi、imei、msisdn都为null的
     *
     * @param jedis
     * @return imsi|imei|msisdn
     */
    public static String randomSet(Jedis jedis) {
        String key = jedis.randomKey();
        String value = jedis.get(key);
        if (value != null) {
            return key + "|" + value;
        } else {
            //固定设置一条已知
            return "imsi|imei|msisdn";
        }
    }

    /**
     * @param jedis
     * @param key   imsi
     * @return imei|msisdn
     */
    public static String get(Jedis jedis, String key) {
        String s = jedis.get(key);
        return s;
    }

    /**
     * 批量查入数据
     *
     * @param jedis
     * @param map
     */
    public static void setBatch(Jedis jedis, Map<String, String> map) {
        for (String key : map.keySet()) {
            if (jedis.exists(key)) {
                map.remove(key);
            }
        }
        Pipeline pipelined = jedis.pipelined();
        for (String key : map.keySet()) {
            pipelined.set(key, map.get(key));
        }
        pipelined.sync();
        pipelined.close();
    }


    /**
     * @param jedis
     * @param key   imsi
     * @param value imei|msisdn
     */
    public static void set(Jedis jedis, String key, String value) {
        if (!jedis.exists(key)) {
            jedis.set(key, value);
        }
    }

    public static void setRegion(Jedis jedis, String key, String value) {
        jedis.set(key, value);
    }

    public static void close() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }
}
