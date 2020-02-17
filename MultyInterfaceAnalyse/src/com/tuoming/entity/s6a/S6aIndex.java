package com.tuoming.entity.s6a;

public interface S6aIndex {


    /**
     * 输入样例数据
     * 352|075|6|5c9cab73347c3579|6|460016270027662|8624000402177200|8613006265602|6|2019-03-28 19:09:39.9199640|2019-03-28 19:09:39.9613370
     * |0|2001|||10.190.238.3|10.191.88.17|3868|3868|epc.mnc001.mcc460.3gppnetwork.org|epc.mnc001.mcc460.3gppnetwork.org
     * |mmec44.mmegi8d01.mme.epc.mnc001.mcc460.3gppnetwork.org|hss02fe01.nc.jx.hss.epc.mnc001.mcc460.3gppnetwork.org|16777251||
     */

    int accessType_Index = 4;//接入网类型
    //    int interface0_Index=2;//接口类型
    int sdrType_Index = 8;//话单类型
    int imsi_Index = 5;//IMSI
    int imei_Index = 6;//IMEI
    int msisdn_Index = 7;//手机号
    int mcc_Index = 00000;//国家码    //(MME)
    int mnc_Index = 00000;//移动网络码
    int startTime_Index = 9;//开始时间
    int endTime_Index = 10;//结束时间
    int srvStat_Index = 11;//业务状态
    int cdrStat_Index = 00000;//单据状态
    int xdrId_Index = 3;//xDR ID
    int mmeAddress_Index = 15;//"MME地址（S6a）iDRA设备地址（iDRA接口）"
    int mmePort_Index = 17;//"MME端口（S6a）iDRA设备端口（iDRA接口）"
    int hssAddress_Index = 16;//"HSS地址（S6a）对端IP地址（iDRA接口）"
    int hssPort_Index = 18;//"HSS端口（S6a）对端端口（iDRA接口）"
    int originRealm_Index = 19;//源REALM
    int desRealm_Index = 20;//目的REALM
    int originHost_Index = 21;//源主机名
    int desHost_Index = 22;//目的主机名
    int procType_Index = 00000;//流程类型
    int appId_Index = 23;//承载Diameter应用类型
    int sgsnNumber_Index = 00000;//SGSN Number
    int apn_Index = 00000;//APN
    int subscriberStatus_Index = 24;//用户状态
    int subData_Index = 00000;//签约数据
    int cause_Index = 12;//结束原因
    int t3412Extended_Index = 26;//T3412 extended value
    int accessRestrictionData_Index = 25;//ACCESS_RESTRICTION_DATA
    int featureListId_Index = 27;//Feature-List-ID 2

    public final static int size = 28;
    public final static String splite="\\|";


}
