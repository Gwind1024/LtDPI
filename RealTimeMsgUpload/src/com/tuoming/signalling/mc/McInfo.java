package com.tuoming.signalling.mc;

import com.tuoming.common.TypeChange;
import redis.clients.jedis.Jedis;

public class McInfo {
    public static byte[] mcLocTobytes(String str, Jedis jedis) {
        //subevtType应该提前判断是否等于196611或者196612
        String[] split = str.split(McLocationIndex.splite, -1);
        if (split.length >= McLocationIndex.size) {
            Integer subevtType = TypeChange.strToInteger(split[McLocationIndex.subevtType]);
            if (subevtType != null && (subevtType == 196611 || subevtType == 196612)) {
                //mc开关
                McLocationInfo mcLocationInfo = new McLocationInfo();
                boolean flag = mcLocationInfo.init(split, subevtType);
                if (flag) {
                    return mcLocationInfo.toBytes();
                }
            } else {
                Mc2LocationInfo mc2LocationInfo = new Mc2LocationInfo();
                boolean flag = mc2LocationInfo.init(split, jedis);
                if (flag) {
                    return mc2LocationInfo.toBytes();
                }
            }
        }
        return null;
    }

    public static byte[] mcSwitchTobytes(String str, Jedis jedis) {
        String[] split = str.split(McSwitchIndex.splite, -1);
        if (split.length >= Mc2LocationIndex.size) {
            McSwitchInfo mcSwitchInfo = new McSwitchInfo();
            boolean flag = mcSwitchInfo.init(split, jedis);
            if (flag) {
                return mcSwitchInfo.toBytes();
            }

        }
        return null;
    }
}
