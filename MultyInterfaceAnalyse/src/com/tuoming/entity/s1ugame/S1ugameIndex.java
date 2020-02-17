package com.tuoming.entity.s1ugame;

public interface S1ugameIndex {


    /**
     * 输入样例数据
     * 352|075|6|5c9cab73347c3579|6|460016270027662|8624000402177200|8613006265602|6|2019-03-28 19:09:39.9199640|2019-03-28 19:09:39.9613370
     * |0|2001|||10.190.238.3|10.191.88.17|3868|3868|epc.mnc001.mcc460.3gppnetwork.org|epc.mnc001.mcc460.3gppnetwork.org
     * |mmec44.mmegi8d01.mme.epc.mnc001.mcc460.3gppnetwork.org|hss02fe01.nc.jx.hss.epc.mnc001.mcc460.3gppnetwork.org|16777251||
     *
     */

	int uTYPE_Index=0000;//话单类型 game固定取值7
	int startT_Index=19;//开始时间
	int endT_Index=20;//结束时间
	int imsi_Index=5;//IMSI
	int msisdn_Index=7;//手机号码
	int imei_Index=6;//终端类型
	int accountID_Index=0000;//APP账户
	int rac_Index=0000;//路由区编码
	int lacTAC_Index=15;//位置区或跟踪区编码
	int cgiECI_Index=16;//小区号码
	int servname_Index=0000;//流量类型
	int durition_Index=0000;//时长 endt - startT
	int ulTRAFF_Index=33;//上行流量（bytes）
	int dlTRAFF_Index=34;//下行流量（bytes）
	int totalTRAFF_Index=0000;//总流量（bytes） ulTRAFF_Index + dlTRAFF_Index
	int rat_Index=4;//RATType
	int ipUSER_Index=26;//终端IP ipv4
	int ipUSER_Index2=27;//终端IP ipv6
	int ipSERV_Index=30;//访问IP ipv4
	int ipSERV_Index2=31;//访问IP ipv6
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
	int feature_Index=60;//网址/特征信息 ????
	int l7TYPE_Index=0000;//应用层协议类型
	int appSTATUS_Index=25;//App Status
	int rttUL_Index=89;//RTT上行总时延 ????
	int rttULNUM_Index=88;//RTT上行次数 ????
	int rttDL_Index=91;//RTT下行总时延 ????
	int rttDLNUM_Index=90;//RTT下行次数 ????
	int l7UPTRANSTIME_Index=91;//上行应用传输有效时长
	int l7UPTRAFFIC_Index=33;//上行应用传输有效流量
	int l7DOWNTRANSTIME_Index=93;//下行应用传输有效时长
	int l7DOWNTRAFFIC_Index=34;//下行应用传输有效流量
	int l7TRANSTIME_Index=57;//应用传输有效时长  57 or 21-20 累加
	int longGPS_Index=86;//GPS经度
	int latiGPS_Index=87;//GPS纬度
	int heightGPS_Index=0000;//高度
	int accurGPS_Index=0000;//精度
	int coordi_Index=0000;//坐标系
	int tcpULRESTTRAF_Index=39;//上行TCP重传流量
	int tcpDLRESTTRAF_Index=40;//下行TCP重传流量
	int mss=48;//字段大小
	int webAPP_Index=57;//区分网页流量/APP流量
	int serviceREFID_Index=3;//唯一游戏局标志
	int battleFLAG_Index=0000;//游戏属性标记
	int avgULINTERVAL_Index=0000;//上行平均时延
	int sumULINTERVAL_Index=57;//上行时延总量
	int countULINTERVAL_Index=56;//上行时延总数
	int avgDLINTERVAL_Index=0000;//下行平均时延
	int sumDLINTERVAL_Index=59;//下行时延总量
	int countDLINTERVAL_Index=58;//下行时延总数
	int avgULJITTER_Index=29;//上行平均包间隔抖动
	int avgDLJITTER_Index=29;//下行平均包间隔抖动
	int maxULJITTER_Index=29;//上行最大包间隔抖动
	int maxDLJITTER_Index=29;//下行最大包间隔抖动
	int minULJITTER_Index=29;//上行最小包间隔抖动
	int minDLJITTER_Index=29;//下行最小包间隔抖动
	int dlRTTLTTH1NUM_Index=0000;//下行RTT小于th1阈值的个数
	int dlRTTTH1TH2NUM_Index=0000;//下行RTT在th1和th2阈值之间的个数
	int dlRTTTH2TH3NUM_Index=0000;//下行RTT在th2和th3阈值之间的个数
	int dlRTTTH3TH4NUM_Index=0000;//下行RTT在th3和th4阈值之间的个数
	int dlRTTGTTH4NUM_Index=0000;//下行RTT大于th4阈值的个数
	int ulRTTLTTH1NUM_Index=0000;//上行RTT小于th1阈值的个数
	int ulRTTTH1TH2NUM_Index=0000;//上行RTT在th1和th2阈值之间的个数
	int ulRTTGTTH2NUM_Index=0000;//上行RTT大于th2阈值的个数
	int sgwGGSNIP_Index=10;
	int machineIPAddtype_Index = 0000;
	int genlength=94;
	int dnslength=63;
	int httplength=127;

	int Http_feature_Index = 59;
	int Http_rttUL_Index=88;//RTT上行总时延 ????
	int Http_rttULNUM_Index=87;//RTT上行次数 ????
	int Http_rttDL_Index=90;//RTT下行总时延 ????
	int Http_rttDLNUM_Index=89;//RTT下行次数 ????

	int Http_longGPS_Index=85;//GPS经度
	int Http_latiGPS_Index=86;//GPS纬度
	int Http_webAPP_Index=61;//区分网页流量/APP流量
	int Http_actType_Index=24;//App Status
	int Http_ul=33;
	int Http_dl=34;
	int Http_sumULINTERVAL_Index=88;//上行时延总量
	int Http_countULINTERVAL_Index=87;//上行时延总数
	int Http_sumDLINTERVAL_Index=90;//下行时延总量
	int Http_countDLINTERVAL_Index=89;//下行时延总数
	int size = 63;
	int type = 22;
}
