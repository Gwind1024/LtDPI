package com.tuoming.entity.s1mme;

import com.tuoming.tools.CommonUtils;

public abstract class CommonDecode1 {

    public Integer accessType = -1;  //接入网类型
    public Integer interface0 = -1;  //接口类型
    public Integer sdrType = -1;     //话单类型
    public String imsi = "";         //IMSI
    public String imei = "";         //IMEI
    public String msisdn = "";       //手机号
    public String mcc = "";          //国家码
    public String mnc = "";          //移动网络码
    public String startTime = "";    //开始时间
    public String endTime = "";      //结束时间
    public String srvStat = "";      //业务状态
    public String cdrStat = "";      //单据状态
    public String xdrId = "";        //xDR ID

    public CommonDecode1() {
    }


    public void getAccessType() {
        this.accessType = 6;
    }

    public void getInterface0() {
        this.interface0 = 31;
    }

    public void getSdrType(String[] str, int sdrType_Index) {
        if(str[sdrType_Index].equals("1")){
            this.sdrType = 41;
        } else if (str[sdrType_Index].equals("2") || str[sdrType_Index].equals("3")) {
            this.sdrType = 24;
        } else if(str[sdrType_Index].equals("5")) {
            this.sdrType = 43;
        } else if (str[sdrType_Index].equals("6")) {
            this.sdrType = 42;
        } else if(str[sdrType_Index].equals("7")) {
            this.sdrType = 21;
        } else if (str[sdrType_Index].equals("8")) {
            this.sdrType = 22;
        } else if (str[sdrType_Index].equals("11")) {
            this.sdrType = 28;
        } else if (str[sdrType_Index].equals("12")) {
            this.sdrType = 29;
        } else if (str[sdrType_Index].equals("14") || str[sdrType_Index].equals("15") || str[sdrType_Index].equals("16") || str[sdrType_Index].equals("17")) {
            this.sdrType = 33;
        } else if (str[sdrType_Index].equals("20")) {
            this.sdrType = 5;
        }
    }

    public void getImsi(String[] str, int imsi_Index) {
        this.imsi = str[imsi_Index];
    }

    public void getImei(String[] str, int imei_Index) {
        this.imei = str[imei_Index];
    }

    public void getMsisdn(String[] str, int msisdn_Index) {
        if(str[msisdn_Index].startsWith("86") ){
            this.msisdn = str[msisdn_Index].substring(2);
        } else {
            this.msisdn = str[msisdn_Index];
        }
    }

    public void getMcc(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.mcc = str[index];
    }

    public void getMnc(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.mnc = str[index];
    }

    public void getStartTime(String[] str, int startTime_Index) {
        this.startTime = str[startTime_Index];
    }

    public void getEndTime(String[] str, int endTime_Index) {
        this.endTime = str[endTime_Index];
    }

    public void getSrvStat(String[] str, int srvStat_Index) {
        if(str[srvStat_Index].equals("1")){
            this.srvStat = "1";
        } else if (str[srvStat_Index].equals("255")){
            this.srvStat = "2";
        } else if (str[srvStat_Index].equals("0")){
            this.srvStat = "13";
        } else if (str[srvStat_Index].equals("258")){
            this.srvStat = "2";
        } else {
            this.srvStat = "0";
        }
    }

    public void getCdrStat(String[] str, int cdrStat_Index) {
        if(str[cdrStat_Index].equals("0")){
            this.srvStat = "0";
        } else if (str[cdrStat_Index].equals("255")){
            this.srvStat = "1";
        } else if (str[cdrStat_Index].equals("1")){
            this.srvStat = "1";
        }
    }

    public void getXdrId(String[] str, int xdrId_Index) {
        this.xdrId = str[xdrId_Index];
    }

}
