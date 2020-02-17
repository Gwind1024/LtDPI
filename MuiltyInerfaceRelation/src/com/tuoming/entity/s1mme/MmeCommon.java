package com.tuoming.entity.s1mme;

import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.RedisUntil;
import redis.clients.jedis.Jedis;

public abstract class MmeCommon {
    protected String accessType = "";    //接入网类型
    protected String interface0 = "";    //接口类型
    protected String sdrType = "";       //话单类型
    public String imsi = "";          //IMSI
    public String imei = "";          //IMEI
    public String msisdn = "";        //手机号
    protected String mcc = "460";           //国家码
    protected String mnc = "01";           //移动网络码
    protected String startTime = "";     //开始时间
    protected String endTime = "";       //结束时间
    protected String srvstat = "";       //业务状态
    protected String cdrstat = "";       //单据状态
    protected String xdrId = "";         //xDR ID

    private String produceType;

    public static Jedis jedis;


    public MmeCommon() {
    }

    public abstract String getKey();

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getProduceType() {
        return produceType;
    }

    public void decode(String[] str) {
        this.accessType = "6";
        this.interface0 = "31";

        produceType = str[AttachIndex.sdrType];
        if (str[AttachIndex.sdrType].equals("1")) {
            this.sdrType = "41";
        } else if (str[AttachIndex.sdrType].equals("2") || str[AttachIndex.sdrType].equals("3") || str[AttachIndex.sdrType].equals("4")) {
            this.sdrType = "24";
        } else if (str[AttachIndex.sdrType].equals("5")) {
            this.sdrType = "43";
        } else if (str[AttachIndex.sdrType].equals("6")) {
            this.sdrType = "42";
        } else if (str[AttachIndex.sdrType].equals("7")) {
            this.sdrType = "21";
        } else if (str[AttachIndex.sdrType].equals("8")) {
            this.sdrType = "22";
        } else if (str[AttachIndex.sdrType].equals("9")) {
            this.sdrType = "25";
        } else if (str[AttachIndex.sdrType].equals("10")) {
            this.sdrType = "26";
        } else if (str[AttachIndex.sdrType].equals("11")) {
            this.sdrType = "28";
        } else if (str[AttachIndex.sdrType].equals("12")) {
            this.sdrType = "29";
        } else if (str[AttachIndex.sdrType].equals("13")) {
            this.sdrType = "27";
        } else if (str[AttachIndex.sdrType].equals("14") || str[AttachIndex.sdrType].equals("15") || str[AttachIndex.sdrType].equals("16") || str[AttachIndex.sdrType].equals("17")) {
            this.sdrType = "33";
        } else if (str[AttachIndex.sdrType].equals("20")) {
            this.sdrType = "5";
        } else if (Integer.parseInt(str[AttachIndex.sdrType]) >= 22 && Integer.parseInt(str[AttachIndex.sdrType]) <= 28) {
            this.sdrType = "6";
        } else if (str[AttachIndex.sdrType].equals("51")) {
            this.sdrType = "50";
        } else if (str[AttachIndex.sdrType].equals("61")) {
            this.sdrType = "51";
        } else if (str[AttachIndex.sdrType].equals("62")) {
            this.sdrType = "52";
        } else if (str[AttachIndex.sdrType].equals("60")) {
            this.sdrType = "53";
        } else if (str[AttachIndex.sdrType].equals("44")) {
            this.sdrType = "54";
        } else if (str[AttachIndex.sdrType].equals("70")) {
            this.sdrType = "56";
        } else if (str[AttachIndex.sdrType].equals("71")) {
            this.sdrType = "57";
        } else {
            this.sdrType = str[AttachIndex.sdrType];
        }
        this.imsi = str[AttachIndex.imsi];
        this.imei = str[AttachIndex.imei];
        if (str[AttachIndex.msisdn].startsWith("86")) {
            this.msisdn = str[AttachIndex.msisdn].substring(2);
        } else {
            this.msisdn = str[AttachIndex.msisdn];
        }

        //  int index1 = CommonUtils.convertIndex(str, AttachIndex.imsi);
        if (!"".equals(str[AttachIndex.imsi])) {
            this.mcc = str[AttachIndex.imsi].substring(0, 3);
        } else {
            this.mcc = "460";
        }
        //int index2 = CommonUtils.convertIndex(str, AttachIndex.imsi);
        if (!"".equals(str[AttachIndex.imsi])) {
            this.mnc = str[AttachIndex.imsi].substring(3, 5);
        } else {
            this.mnc = "01";
        }
        this.startTime = str[AttachIndex.startTime];
        this.endTime = str[AttachIndex.endTime];

        if (str[AttachIndex.srvStat].equals("1")) {
            this.srvstat = "1";
        } else if (str[AttachIndex.srvStat].equals("255")) {
            this.srvstat = "0";
        } else if (str[AttachIndex.srvStat].equals("0")) {
            this.srvstat = "0";
        } else {
            this.srvstat = "2";
        }

        if (str[AttachIndex.cdrStat].equals("0")) {
            this.cdrstat = "0";
        } else if (str[AttachIndex.cdrStat].equals("255")) {
            this.cdrstat = "1";
        } else if (str[AttachIndex.cdrStat].equals("1")) {
            this.cdrstat = "1";
        }

        this.xdrId = str[AttachIndex.xdrId];
    }

    //获取中间流程的流程序号集合
    public abstract boolean getMiddleProcedure(int produceType);

    //获取结束流程的流程序号
    public abstract Integer getEndProcedure();

    //关联函数
    //1:解析成功  2:解析结束  0:不是子流程 3:解析时间抛出异常
    public abstract Integer relation(MmeMapCommon mmeMapCommon, int type, String[] arr);

    @Override
    public String toString() {
        RedisUntil.backfill(this);
        StringBuilder result = new StringBuilder();
        result.append(accessType).append("|").append(interface0).append("|").append(sdrType).append("|").append(imsi).append("|")
                .append(imei).append("|").append(msisdn).append("|").append(mcc).append("|").append(mnc).append("|").append(startTime).append("|")
                .append(endTime).append("|").append(srvstat).append("|").append(cdrstat).append("|").append(xdrId).append("|");
        return result.toString();
    }
}
