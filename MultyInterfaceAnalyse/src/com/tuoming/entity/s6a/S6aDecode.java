package com.tuoming.entity.s6a;

import com.tuoming.common.CommonDecode;
import com.tuoming.common.RedisUntil;

public class S6aDecode extends CommonDecode {


    public String mmeAddress = "";//"MME地址（S6a）iDRA设备地址（iDRA接口）"
    public String mmePort = "";//"MME端口（S6a）iDRA设备端口（iDRA接口）"
    public String hssAddress = "";//"HSS地址（S6a）对端IP地址（iDRA接口）"
    public String hssPort = "";//"HSS端口（S6a）对端端口（iDRA接口）"
    public String originRealm = "";//源REALM
    public String desRealm = "";//目的REALM
    public String originHost = "";//源主机名
    public String desHost = "";//目的主机名
    public String procType = "";//流程类型
    public String appId = "";//承载Diameter应用类型
    public String sgsnNumber = "";//SGSN Number
    public String apn = "";//APN
    public String subscriberStatus = "";//用户状态
    public String subData = "";//签约数据
    public String cause = "";//结束原因
    public String t3412Extended = "";//T3412 extended value
    public String accessRestrictionData = "";//ACCESS_RESTRICTION_DATA
    public String featureListId = "0";//Feature_list_id


    public S6aDecode() {
    }

    @Override
    public String getEndTime() {
        return endTime;
    }

    @Override
    public String getMmeIp() {
        return mmeAddress;
    }

    @Override
    public void getInterface0(int a) {
        this.interface0 = 34;
    }

    @Override
    public void getCdrStat(String[] str, int cdrStat_Index) {
        if("0".equals(str[cdrStat_Index])){
            this.cdrStat = "0";
        }else{
            this.cdrStat = "1";
        }
    }

    /*   @Override
    public void getMcc(String[] str, int mcc_Index) {
        this.mcc = null;
    }*/

  /*  @Override
    public void getMnc(String[] str, int mnc_Index) {
        this.mnc = null;
    }*/

    public void getMmeAddress(String[] str, int mmeAddress_Index) {
        this.mmeAddress = str[mmeAddress_Index];
    }

    public void getMmePort(String[] str, int mmePort_Index) {
        this.mmePort = str[mmePort_Index];
    }

    public void getHssAddress(String[] str, int hssAddress_Index) {
        this.hssAddress = str[hssAddress_Index];
    }

    public void getHssPort(String[] str, int hssPort_Index) {
        this.hssPort = str[hssPort_Index];
    }

    public void getOriginRealm(String[] str, int originRealm_Index) {
        this.originRealm = str[originRealm_Index];
    }

    public void getDesRealm(String[] str, int desRealm_Index) {
        this.desRealm = str[desRealm_Index];
    }

    public void getOriginHost(String[] str, int originHost_Index) {
        this.originHost = str[originHost_Index];
    }

    public void getDesHost(String[] str, int desHost_Index) {
        this.desHost = str[desHost_Index];
    }

//    public void getProcType(String[] str, int procType_Index) {
//        this.procType = str[procType_Index];
//    }

    public void getAppId(String[] str, int appId_Index) {
        this.appId = str[appId_Index];
    }

//    public void getSgsnNumber(String[] str, int sgsnNumber_Index) {
//        this.sgsnNumber = str[sgsnNumber_Index];
//    }

//    public void getApn(String[] str, int apn_Index) {
//        this.apn = str[apn_Index];
//    }

    public void getSubscriberStatus(String[] str, int subscriberStatus_Index) {
        this.subscriberStatus = str[subscriberStatus_Index];
    }

//    public void getSubData(String[] str, int subData_Index) {
//        this.subData = str[subData_Index];
//    }

    public void getCause(String[] str, int cause_Index) {
        this.cause = str[cause_Index];
    }

//    public void getT3412Extended(String[] str, int t3412Extended_Index) {
//        this.t3412Extended = Integer.parseInt(str[t3412Extended_Index]);
//    }

    public void getAccessRestrictionData(String[] str, int accessRestrictionData_Index) {
        this.accessRestrictionData = str[accessRestrictionData_Index];
    }

    public void getFeatureListID(String[] str, int featureListId_Index) {
        this.featureListId = str[featureListId_Index];
    }


    public void decode(String[] str) {
//        S6aDecode s6aDecode = new S6aDecode();
        getAccessType(str, S6aIndex.accessType_Index);
        getInterface0(34);
        getSdrType(str, S6aIndex.sdrType_Index);
        getImsi(str, S6aIndex.imsi_Index);
        getImei(str, S6aIndex.imei_Index);
        getMsisdn(str, S6aIndex.msisdn_Index);
        getMcc();
        getMnc();
        getStartTime(str, S6aIndex.startTime_Index);
        getEndTime(str, S6aIndex.endTime_Index);
        getSrvStat(str, S6aIndex.srvStat_Index);
        getCdrStat(str,S6aIndex.srvStat_Index);
        getXdrId(str, S6aIndex.xdrId_Index);
        getMmeAddress(str, S6aIndex.mmeAddress_Index);
        getMmePort(str, S6aIndex.mmePort_Index);
        getHssAddress(str, S6aIndex.hssAddress_Index);
        getHssPort(str, S6aIndex.hssPort_Index);
        getOriginRealm(str, S6aIndex.originRealm_Index);
        getDesRealm(str, S6aIndex.desRealm_Index);
        getOriginHost(str, S6aIndex.originHost_Index);
        getDesHost(str, S6aIndex.desHost_Index);
        getAppId(str, S6aIndex.appId_Index);
        getSubscriberStatus(str, S6aIndex.subscriberStatus_Index);
        getCause(str, S6aIndex.cause_Index);
        getAccessRestrictionData(str, S6aIndex.accessRestrictionData_Index);
        getFeatureListID(str, S6aIndex.featureListId_Index);
    }

    @Override
    public String toString() {
        RedisUntil.setToMap(this);
        /**
         * mmeAddress + "|" + mmePort + "|" + hssAddress + "|" + hssPort + "|" + originRealm + "|" + desRealm +
         *                 "|" + originHost + "|" + desHost + "|" + procType + "|" + appId + "|" + sgsnNumber + "|" + apn + "|" + subscriberStatus + "|" + subData + "|" + cause +
         *                 "||" + accessRestrictionData
         */

        StringBuilder result = new StringBuilder();
        result.append(accessType == null ? "" : accessType).append("|")
                .append(interface0).append("|")
                .append(sdrType).append("|")
                .append(imsi).append("|")
                .append(imei).append("|")
                .append(msisdn).append("|")
                .append(mcc).append("|")
                .append(mnc).append("|")
                .append(startTime).append("|")
                .append(endTime).append("|")
                .append(srvStat).append("|")
                .append(cdrStat).append("|")
                .append(xdrId).append("|")
                .append(mmeAddress).append("|")
                .append(mmePort).append("|")
                .append(hssAddress).append("|")
                .append(hssPort).append("|")
                .append(originRealm).append("|")
                .append(desRealm).append("|")
                .append(originHost).append("|")
                .append(desHost).append("|")
                .append(procType).append("|")
                .append(appId).append("|")
                .append(sgsnNumber).append("|")
                .append(apn).append("|")
                .append(subscriberStatus).append("|")
                .append(subData).append("|")
                .append(cause).append("|")
                .append(t3412Extended).append("|")
                .append(accessRestrictionData).append("|")
                .append(featureListId);
        return result.toString();


    }
}
