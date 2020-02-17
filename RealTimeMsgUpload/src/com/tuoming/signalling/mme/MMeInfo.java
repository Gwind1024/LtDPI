package com.tuoming.signalling.mme;

import com.tuoming.common.TypeChange;
import redis.clients.jedis.Jedis;

/**
 * 开机、关机、位置更新
 */
public class MMeInfo {
    public static byte[] mmeTobytes(String str, Jedis jedis) {
        String[] split = str.split(MmeIndex.splite, -1);
        if (split.length < MmeIndex.size) {
            return null;
        }
        Integer produceType = TypeChange.strToInteger(split[MmeIndex.produceType]);
        if (produceType == 1 || produceType == 6) {
            MmeAttachDetachInfo mmeAttachDetachInfo = new MmeAttachDetachInfo();
            boolean b = mmeAttachDetachInfo.init(split, produceType);
            if (b) {
                return mmeAttachDetachInfo.toBytes();
            }
        } else if (produceType == 5) {
            MmeTauInfo mmeTauInfo = new MmeTauInfo();
            boolean b = mmeTauInfo.init(split, jedis);
            if (b) {
                return mmeTauInfo.toBytes();
            }
        }
        return null;
    }
}
