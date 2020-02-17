package com.tuoming.common;

import com.tuoming.tools.CommonUtils;
import redis.clients.jedis.Jedis;

public abstract class CommonDecode {

    public Integer accessType = -1;  //接入网类型
    public Integer interface0 = -1;  //接口类型
    public Integer sdrType = -1;     //话单类型
    public String imsi = "";         //IMSI
    public String imei = "";         //IMEI
    public String msisdn = "";       //手机号
    public String mcc = "460";         //国家码
    public String mnc = "01";          //移动网络码
    public String startTime = "";    //开始时间
    public String endTime = "";      //结束时间
    public Integer srvStat = -1;     //业务状态
    public String cdrStat = "";     //单据状态
    public String xdrId = "";        //xDR ID
    public String machineIP = "";   // 机器IP

    public static Jedis jedis;

    public CommonDecode() {
    }

    public void clear(){
        accessType = -1;
        interface0 = -1;
        sdrType = -1;
        imsi = "";
        imei = "";
        msisdn = "";
        mcc = "460";
        mnc = "01";
        startTime = "";
        endTime = "";
        srvStat = -1;
        cdrStat = "";
        xdrId = "";
    }

    public abstract void decode(String[] arr);

    public abstract String getEndTime();

    public abstract String getMmeIp();


    public void getAccessType(String[] str, int accessType_Index) {
        this.accessType = CommonUtils.strToInteger(str[accessType_Index]);
    }

    public void getInterface0(int a) {
        this.interface0 = a;
    }

    public void getSdrType(String[] str, int sdrType_Index) {
        this.sdrType = CommonUtils.strToInteger(str[sdrType_Index]);
    }

    public void getImsi(String[] str, int imsi_Index) {
        this.imsi = str[imsi_Index];
    }

    public void getImei(String[] str, int imei_Index) {
        this.imei = str[imei_Index];
    }

    public void getMsisdn(String[] str, int msisdn_Index) {
        if (str[msisdn_Index].startsWith("86")) {
            this.msisdn = str[msisdn_Index].substring(2);
        } else {
            this.msisdn = str[msisdn_Index];
        }
    }

    public void getMcc() {
        if (!"".equals(imsi)) {
            this.mcc = imsi.substring(0, 3);
        }
    }

    public void getMnc() {
        if (!"".equals(imsi)) {
            this.mnc = imsi.substring(3, 5);
        }
    }

    public void getStartTime(String[] str, int startTime_Index) {
        this.startTime = str[startTime_Index];
    }

    public void getEndTime(String[] str, int endTime_Index) {
        this.endTime = str[endTime_Index];
    }

    public void getSrvStat(String[] str, int srvStat_Index) {
        this.srvStat = CommonUtils.strToInteger(str[srvStat_Index]);
    }

    public void getCdrStat(String[] str, int cdrStat_Index) {
        this.cdrStat = "";
    }

    public void getXdrId(String[] str, int xdrId_Index) {
        this.xdrId = str[xdrId_Index];
    }


    public String toString() {

        /**
         * accessType + "|" + interface0 + "|" + sdrType + "|" + imsi + "|" + imei + "|" + msisdn + "|" + mcc + "|" + mnc + "|" + startTime + "|" + endTime +
         *              "|" + srvStat + "|" + cdrStat + "|" + xdrId + "|"
         */

        StringBuilder result = new StringBuilder();
        result.append(accessType == null ? "" : accessType).append("|").append(interface0).append("|").append(sdrType == null ? "" : sdrType).append("|")
                .append(imsi).append("|").append(imei).append("|").append(msisdn).append("|").append(mcc == null ? "" : mcc).append("|").append(mnc).append("|")
                .append(startTime).append("|").append(endTime).append("|").append(srvStat == null ? "" : srvStat).append("|").append(cdrStat).append("|")
                .append(xdrId).append("|");

        return result.toString();
    }
}
