package com.tuoming.tools;

import com.tuoming.common.CommonDecode;
import com.tuoming.count.CountNum;
import com.tuoming.entity.iups.CommonIuPS;
import com.tuoming.entity.iups.IUPSDecode;
import com.tuoming.entity.s1mme.MmeCommon;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.Map;

/**
 * redis存储：
 * key:imsi
 * value:imei|msisdn
 */
public class RedisUntil {
    private static JedisPool jedisPool;

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

    public void close() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }

    /**
     * 反填三个字段,需要根据需求优化
     *
     * @param common
     */
    public static void backfill(MmeCommon common) {
        //三者都为空
        if (!"".equals(common.imsi) && ("".equals(common.imei) || "".equals(common.msisdn))) {
            String arr = RedisUntil.get(common.jedis, common.imsi);
            if (arr != null) {
                String[] split = arr.split("\\|");
                if (split.length == 2) {
                    CountNum.backFillNum += 1;
                    common.imei = split[0];
                    common.msisdn = split[1];
                } else {
                    CountNum.nobackFillNum += 1;
                }
            } else {
                CountNum.nobackFillNum += 1;
            }
        } else if ("".equals(common.imsi)) {
            CountNum.nobackFillNum += 1;
        }
    }

    /**
     * 反填三个字段,需要根据需求优化
     *
     * @param common
     */
    public static void backfill(CommonIuPS common) {
        //三者都为空
        if (!"".equals(common.imsi) && ("".equals(common.imei) || "".equals(common.phone))) {
            String arr = RedisUntil.get(IUPSDecode.jedis, common.imsi);
            if (arr != null) {
                String[] split = arr.split("\\|");
                if (split.length == 2) {
                    common.imei = split[0];
                    common.phone = split[1];
                }
            }
        }
    }

    /**
     * 反填三个字段,需要根据需求优化
     *
     * @param common
     */
    public static void backfill(CommonDecode common) {
        //三者都为空
        if (!"".equals(common.imsi) && ("".equals(common.imei) || "".equals(common.msisdn))) {
            String arr = RedisUntil.get(IUPSDecode.jedis, common.imsi);
            if (arr != null) {
                String[] split = arr.split("\\|");
                if (split.length == 2) {
                    common.imei = split[0];
                    common.msisdn = split[1];
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Jedis redis = RedisUntil.getRedis("192.168.2.142", "123456");
        long l1 = System.nanoTime();
        String s = redis.get("1300028");
        System.out.println(s);
        //  final Boolean exists = redis.exists("12243");
        long l2 = System.nanoTime();
        System.out.println(l2 - l1);

    }
}
