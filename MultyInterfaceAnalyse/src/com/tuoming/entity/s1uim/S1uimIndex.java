package com.tuoming.entity.s1uim;

public interface S1uimIndex {


    /**
     * 输入样例数据
     * 352|075|6|5c9cab73347c3579|6|460016270027662|8624000402177200|8613006265602|6|2019-03-28 19:09:39.9199640|2019-03-28 19:09:39.9613370
     * |0|2001|||10.190.238.3|10.191.88.17|3868|3868|epc.mnc001.mcc460.3gppnetwork.org|epc.mnc001.mcc460.3gppnetwork.org
     * |mmec44.mmegi8d01.mme.epc.mnc001.mcc460.3gppnetwork.org|hss02fe01.nc.jx.hss.epc.mnc001.mcc460.3gppnetwork.org|16777251||
     *
     */

    int accessType_Index=5;//接入网类型 固定是6
//    int interface0_Index=2;//接口类型
    int sdrType_Index=8;//话单类型

    int startTime_Index=9;//开始时间
    int endTime_Index=10;//结束时间
    int srvStat_Index=11;//业务状态
    int cdrStat_Index=00000;//单据状态
    int xdrId_Index=3;//xDR ID
	
	int uTYPE_Index=5;//话单类型 uTYPE值对应s1uim 固定为5
	int startT_Index=19;//开始时间
	int endT_Index=20;//结束时间
	int imsi_Index=5;//IMSI
	int msisdn_Index=7;//手机号码
	int imei_Index=6;//终端类型
	int accountID_Index=52;//APP账户
	int rac_Index=0000;//路由区编码
	int lacTAC_Index=15;//位置区或跟踪区编码
	int cgiECI_Index=16;//小区号码
	int servname_Index=0000;//流量类型
	int durition_Index=0000;//时长
	int ulTRAFF_Index=33;//上行流量（bytes）
	int dlTRAFF_Index=34;//下行流量（bytes）
	int totalTRAFF_Index=0000;//总流量（bytes）
	int rat_Index=4;//RATType
	int ipUSER_Index=26;//终端IP      USER_IPv4=27     USER_IPv6=28
	int ipUSER_Index2=27;
	int ipSERV_Index=30;//访问IP      Server IP_IPv4=31    Server IP_IPv6=32
	int ipSERV_Index2=31;
	int l4TYPE_Index=29;//传输层协议类型
	int durTCP1ST_Index=41;//TCP建链时延（第一步）
	int durTCP2ND_Index=42;//TCP建链时延（第二步）
	int apn_Index=17;//APN
	int ipENB_Index=10;//SGSN IP/eNB IP
	int ipSGW_Index=9;//GGSN IP/S-GW IP
	int mcc_Index=5;//MCC
	int mnc_Index=0000;//MNC
	int portUSER_Index=28;//源端口
	int portSERV_Index=32;//目的端口
	int feature_Index=59;//网址/特征信息
	int l7TYPE_Index=21;//应用层协议类型 应用大小类
	int appSTATUS_Index=25;//App Status
	int rttUL_Index=61;//RTT上行总时延
	int rttULNUM_Index=60;//RTT上行次数
	int rttDL_Index=63;//RTT下行总时延
	int rttDLNUM_Index=62;//RTT下行次数
	int l7UPTRANSTIME_Index=95;//上行应用传输有效时长
	int l7UPTRAFFIC_Index=33;//上行应用传输有效流量
	int l7DOWNTRANSTIME_Index=96;//下行应用传输有效时长
	int l7DOWNTRAFFIC_Index=34;//下行应用传输有效流量
	int l7TRANSTIME_Index=0000;//应用传输有效时长
	int heightGPS_Index=0000;//高度
	int accurGPS_Index=0000;//精度
	int coordi_Index=0000;//坐标系
	int tcpULRESTTRAF_Index=39;//上行TCP重传流量
	int tcpDLRESTTRAF_Index=40;//下行TCP重传流量
	int mss=48;//字段大小
	int webAPP_Index=0000;//区分网页流量/APP流量
	int actTYPE_Index=56;//业务动作
	int actSTTIME_Index=0000;//动作开始时间
	int actENDTIME_Index=0000;//动作结束时间
	int imaction_Index=24;//IM登陆标识
	int imresult_Index=0000;//IM登陆结果
	int imdelay_Index=46;//IM登陆时延
	int sgwGGSNIP_Index=10;
	int length=98;

	int Http_l7TRANSTIME_Index = 56;
	int Http_longGPS_Index=85;//GPS经度
	int Http_latiGPS_Index=86;//GPS纬度
	int Http_webAPP_Index=61;//区分网页流量/APP流量
	int Http_actType_Index=24;//App Status
	int Http_ul=33;
	int Http_dl=34;
	int size=94;

	int type=22;//业务小类
}
