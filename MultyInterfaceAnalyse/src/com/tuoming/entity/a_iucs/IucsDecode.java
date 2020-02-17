package com.tuoming.entity.a_iucs;

import com.tuoming.common.CommonDecode;
import com.tuoming.common.RedisUntil;
import com.tuoming.tools.RadixDeal;
import redis.clients.jedis.Jedis;

public class IucsDecode extends CommonDecode {
    private String[] sortXdr;
    public static Jedis jesis;

    /**
     * 处理xdr中的带有86的手机号
     *
     * @param xdr   要处理的xdr
     * @param index 处理手机号所在的索引
     * @return 处理后的xdr
     */
    public void convertXdr(String[] xdr, int index) {

        if (xdr[index] != null && xdr[index].length() == 13 && xdr[index].startsWith("86")) {
            xdr[index] = xdr[index].substring(2, 13);

        }

    }


    @Override
    public void decode(String[] arr) {
        sortXdr = arr;
        //RedisUntil.backfill(sortXdr);
        switch (sortXdr[0]) {
            case "01":
                convertXdr(sortXdr, CallIndex.msrn_Index);
                convertXdr(sortXdr, CallIndex.rphone_Index);
                convertXdr(sortXdr, CallIndex.ocalled_Index);
                convertXdr(sortXdr, CallIndex.calledNum_Index);
                if ("0".equals(sortXdr[CallIndex.roamType_Index])) {
                    sortXdr[CallIndex.roamType_Index] = "";
                }
                break;
            case "04":
                convertXdr(sortXdr, SmsIndex.rphone_Index);
                convertXdr(sortXdr, SmsIndex.smc_GT_Index);
                convertXdr(sortXdr, SmsIndex.smc_Addr_Index);
                convertXdr(sortXdr, SmsIndex.Phone_Index);
                if ("0".equals(sortXdr[SmsIndex.roamType_Index])) {
                    sortXdr[SmsIndex.roamType_Index] = "";
                }
                break;
            case "02":
                convertXdr(sortXdr, LocationIndex.phone_Index);
                if ("0".equals(sortXdr[LocationIndex.roamType_Index])) {
                    sortXdr[LocationIndex.roamType_Index] = "";
                }
                break;
            case "05":
                convertXdr(sortXdr, SwitchIndex.rphone_Index);
                convertXdr(sortXdr, SwitchIndex.msrn_Index);
                convertXdr(sortXdr, SwitchIndex.Phone_Index);
                if ("0".equals(SwitchIndex.roamType_Index)) {
                    sortXdr[SwitchIndex.roamType_Index] = "";
                }
                break;
            case "03":
                convertXdr(sortXdr, PagingIndex.phone_Index);
                if ("0".equals(PagingIndex.roamType_Index)) {
                    sortXdr[PagingIndex.roamType_Index] = "";
                }
                break;

        }


    }

    @Override
    public String getEndTime() {
        String endTime = null;
        switch (sortXdr[0]) {
            case "01":
                endTime = sortXdr[CallIndex.endTime_Index];
                break;
            case "04":
                endTime = sortXdr[SmsIndex.endTime_Index];
                break;
            case "02":
                endTime = sortXdr[LocationIndex.endTime_Index];
                break;
            case "05":
                endTime = sortXdr[SwitchIndex.endTime_Index];
                break;
            case "03":
                endTime = sortXdr[PagingIndex.endTime_Index];
                break;
        }
        return endTime;


    }

    @Override
    public String getMmeIp() {
        String ip = null;
        switch (sortXdr[0]) {
            case "01":
                if (sortXdr.length < 126) {
                    break;
                }
                if (sortXdr[CallIndex.mscip_Index] != null) {
                    ip = RadixDeal.Decimal2IP(sortXdr[CallIndex.mscip_Index]);
                }
                break;
            case "04":
                if (sortXdr.length < 85) {
                    break;
                }
                if (sortXdr[SmsIndex.mscip_Index] != null) {
                    ip = RadixDeal.Decimal2IP(sortXdr[SmsIndex.mscip_Index]);
                }
                break;
            case "02":
                if (sortXdr.length < 78) {
                    break;
                }
                if (sortXdr[LocationIndex.mscip_Index] != null) {
                    ip = RadixDeal.Decimal2IP(sortXdr[LocationIndex.mscip_Index]);
                }
                break;
            case "05":
                if (sortXdr.length < 67) {
                    break;
                }
                if (sortXdr[SwitchIndex.mscip_Index] != null) {
                    ip = RadixDeal.Decimal2IP(sortXdr[SwitchIndex.mscip_Index]);
                }
                break;
            case "03":
                if (sortXdr.length < 68) {
                    break;
                }
                if (sortXdr[PagingIndex.mscip_Index] != null) {
                    ip = RadixDeal.Decimal2IP(sortXdr[PagingIndex.mscip_Index]);
                }
                break;
        }
        return ip;
    }


    @Override
    public String toString() {
        RedisUntil.backfill(sortXdr);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < sortXdr.length; i++) {
            if (i == sortXdr.length - 1) {
                sb.append(sortXdr[i]);
            } else {
                sb.append(sortXdr[i]).append("|");
            }
        }
        return sb.toString();
    }


}
