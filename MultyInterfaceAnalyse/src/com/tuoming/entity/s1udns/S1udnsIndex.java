package com.tuoming.entity.s1udns;

public interface S1udnsIndex {


    /**
     * 输入样例数据
     * 352|075|6|5c9cab73347c3579|6|460016270027662|8624000402177200|8613006265602|6|2019-03-28 19:09:39.9199640|2019-03-28 19:09:39.9613370
     * |0|2001|||10.190.238.3|10.191.88.17|3868|3868|epc.mnc001.mcc460.3gppnetwork.org|epc.mnc001.mcc460.3gppnetwork.org
     * |mmec44.mmegi8d01.mme.epc.mnc001.mcc460.3gppnetwork.org|hss02fe01.nc.jx.hss.epc.mnc001.mcc460.3gppnetwork.org|16777251||
     */

    int uTYPE_Index = 0000;//DNS直接赋值3
    int starttime_Index = 19;//开始时间
    int endtime_Index = 20;//结束时间
    int cdrstat_Index = 0000;//单据状态??????
    int imsi_Index = 5;//IMSI
    int imei_Index = 6;//终端类型
    int msisdn_Index = 7;//手机号码
    int enbSGSNIP_Index = 10;//SGSN或enodeB用户面IP
    int enbSGSNGTPTEID_Index = 13;//SGSN或enodeB用户面TEID
    int sgwGGSNIP_Index = 9;//GGSN或SGW用户面IP
    int sgwGGSNGTPTEID_Index = 14;//GGSN或SGW用户面TEID
    int rac_Index = 0000;//路由区编码
    int lacTAC_Index = 15;//位置区或跟踪区编码 //需要补0 一共5位
    int cgiECI_Index = 16;//小区号码
    int rat_Index = 4;//网络类型 固定值6
    int apn_Index = 17;//APN
    int ulDURARION_Index = 60;//上行持续时长
    int dlDURARION_Index = 61;//下行持续时长
    int ulIPPACKET_Index = 35;//上行IP包数
    int dlIPPACKET_Index = 36;//下行IP包数
    int upTRAFFIC_Index = 33;//上行流量
    int downTRAFFIC_Index = 34;//下行流量
    int clientIP_Index = 26;//终端IP  27 28
    int clientIP_Index2 = 27;//终端IP  27 28
    int srcPORT_Index = 28;//终端端口
    int destIP_Index = 30;//访问IP 31 32
    int destIP_Index2 = 31;//访问IP 31 32
    int destPORT_Index = 32;//服务器端口
    int servname_Index = 23;//流量类型
    int appSTATUS_Index = 25;//App Status
    int appTYPEEXT1_Index = 0000;//应用类别扩展字段
    int appTYPEEXT2_Index = 0000;//应用类别扩展字段
    int appTYPEEXT3_Index = 0000;//应用类别扩展字段
    int l4PROTOCAL_Index = 29;//4层协议类型
    int requestQUERYDOMAIN_Index = 52;//请求查询的DNS域名
    int queryRESULTIP_Index = 53;//查询结果IP
    int responseCODE_Index = 54;//DNS响应码
    int requestTIMES_Index = 55;//DNS的请求次数
    int responseNUMBER_Index = 56;//响应数目
    int contentNUMBER_Index = 57;//授权内容数目
    int extraCONTENTNUMBER_Index = 58;//附加内容数目
    int host_Index = 52;//host回填码
    int machineIPAddtype_Index = 6;

    int size = 63;

}
