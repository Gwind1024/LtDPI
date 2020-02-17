package com.tuoming.common;

import com.tuoming.count.CountNum;
import com.tuoming.entity.a_iucs.IucsDecode;
import com.tuoming.entity.a_iucs.IucsIndex;
import com.tuoming.entity.http.HttpDecode;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.HashMap;
import java.util.Map;

/**
 * redis存储：
 * key:imsi
 * value:imei|msisdn
 */
public class RedisUntil {
    private static JedisPool jedisPool;
    public static Map map = new HashMap<String, String>();

    public static Jedis getRedis(String ip, String pwd) {
        if (jedisPool == null) {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            jedisPool = new JedisPool(poolConfig, ip, 6379, 3000, pwd);
        }
        return jedisPool.getResource();
    }

    public static void setToMap(CommonDecode common) {
        synchronized (RedisUntil.class) {
            if (!"".equals(common.imsi) && !"".equals(common.imei) && !"".equals(common.msisdn)) {
                map.put(common.imsi, common.imei + "|" + common.msisdn);
            }
        }
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
        Pipeline pipelined = jedis.pipelined();
        synchronized (RedisUntil.class) {
            for (String key : map.keySet()) {
                pipelined.set(key, map.get(key));
            }
            map.clear();
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
        jedis.set(key, value);
    }

    public static void close() {
        if (jedisPool != null) {
            jedisPool.close();
        }
    }


    public static void setRedis(CommonDecode common) {
        if (!"".equals(common.imsi) && !"".equals(common.imei) && !"".equals(common.msisdn)) {
            set(common.jedis, common.imsi, common.imei + "|" + common.msisdn);
        }
    }


    public static void backfill(CommonDecode common) {
        //三者都为空
        if (!"".equals(common.imsi) && ("".equals(common.imei) || "".equals(common.msisdn))) {
            String arr = RedisUntil.get(common.jedis, common.imsi);
            if (arr != null) {
                String[] split = arr.split("\\|");
                if (split.length == 2) {
                    CountNum.backFillNum+=1;
                    common.imei = split[0];
                    common.msisdn = split[1];
                }else {
                    CountNum.nobackFillNum+=1;
                }
            }else{
                CountNum.nobackFillNum+=1;
            }
        }else if("".equals(common.imsi)){
            CountNum.nobackFillNum+=1;
        }
    }

    public static void backfill(String[] common) {
        //三者都为空
        if (!"".equals(common[IucsIndex.imsi]) && ("".equals(common[IucsIndex.imei]) || "".equals(common[IucsIndex.msisdn]))) {
            String arr = RedisUntil.get(IucsDecode.jedis, common[IucsIndex.imsi]);
            if (arr != null) {
                String[] split = arr.split("\\|");
                if (split.length == 2) {
                    common[IucsIndex.imei] = split[0];
                    common[IucsIndex.msisdn] = split[1];
                }
            }
        }
    }


    /**
     * 反填host
     *
     * @param common
     */
    public static void backfillhost(HttpDecode common) {
        if (!"".equals(common.imsi)) {
            String arr = RedisUntil.get(common.jedis, common.imsi);
            if (arr != null) {
                common.host = arr;
            }
        }
    }


    public static void main(String[] args) throws Exception {
        Jedis redis = RedisUntil.getRedis("192.168.2.142", "123456");
        final String s = redis.get("460012873072156");
        System.out.println(s.split("\\|").length);
        RedisUntil.close();

    }
}
