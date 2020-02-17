package com.tuoming.entity.s1uhttp;

public interface S1uhttpIndex {


    /**
     * 输入样例数据
     * 352|075|6|5c9cab73347c3579|6|460016270027662|8624000402177200|8613006265602|6|2019-03-28 19:09:39.9199640|2019-03-28 19:09:39.9613370
     * |0|2001|||10.190.238.3|10.191.88.17|3868|3868|epc.mnc001.mcc460.3gppnetwork.org|epc.mnc001.mcc460.3gppnetwork.org
     * |mmec44.mmegi8d01.mme.epc.mnc001.mcc460.3gppnetwork.org|hss02fe01.nc.jx.hss.epc.mnc001.mcc460.3gppnetwork.org|16777251||
     *
     */

	int uTYPE_Index=0000;//话单类型
	int startT_Index=20;//开始时间
	int endT_Index=21;//结束时间
	int imsi_Index=6;//IMSI
	int msisdn_Index=8;//手机号码
	int imei_Index=7;//终端类型
	int rac_Index=0000;//路由区编码
	int lacTAC_Index=16;//位置区或跟踪区编码
	int cgiECI_Index=17;//小区号码
	int servname_Index=24;//流量类型
	int durition_Index=0000;//时长
	int ulTRAFF_Index=34;//上行流量（bytes）
	int dlTRAFF_Index=35;//下行流量（bytes）
	int totalTRAFF_Index=0000;//总流量（bytes）
	int rat_Index=5;//RATType
	int cdrstat_Index=0000;//单据状态
	int ulDURARION_Index=125;//上行持续时长
	int dlDURARION_Index=126;//下行持续时长
	int ulIPPACKET_Index=36;//上行IP包数
	int dlIPPACKET_Index=37;//下行IP包数
	int ipUSER_Index=27;//终端IP
	int ipUSER_Index2=28;//终端IP
	int ipSERV_Index=31;//访问IP
	int ipSERV_Index2=32;//访问IP
	int l4TYPE_Index=30;//传输层协议类型
	int durTCP1ST_Index=42;//TCP建链时延（第一步）
	int durTCP2ND_Index=43;//TCP建链时延（第二步）
	int tcpULRETRNUM_Index=40;//TCP上行重传报文数
	int tcpDLRETRNUM_Index=41;//TCP下行重传报文数
	int l7TYPE_Index=22;//应用层协议类型
	int l7Delay_Index=56;//应用层时延
	int menthod_Index=54;//HTTP 事务类型
	int httpCODE_Index=55;//状态码
	int userAGENT_Index=62;//User Agent
	int apn_Index=18;//APN
	int ipSGSNENB_Index=11;//SGSN IP/eNB IP
	int ipSGW_Index=10;//GGSN IP/S-GW IP
	int mcc_Index=0000;//MCC
	int mnc_Index=0000;//MNC
	int contentTYPE_Index=63;//Content-Type
	int portUSER_Index=29;//源端口
	int portSERV_Index=33;//目的端口
	int host_Index=59;//HOST
	int uri_Index=60;//网址/特征信息
	int ulIPFRAGPACKETS_Index=44;//UL_IP_FRAG_PACKETS
	int dlIPFRAGPACKETS_Index=45;//DL_IP_FRAG_PACKETS
	int eventT1ST_Index=46;//TCP建链成功到第一条事务请求的时延（ms）
	int eventT2ND_Index=47;//第一条事务请求到其第一个响应包时延（ms）
	int tcpWIN_Index=48;//窗口大小
	int tcpMSS_Index=49;//MSS大小
	int tcpSYNNUM_Index=50;//TCP建链尝试次数
	int tcpSTATUS_Index=51;//TCP连接状态指示
	int appSTATUS_Index=26;//App Status
	int tcpULDISORD_Index=38;//上行TCP乱序报文数
	int tcpDLDISORD_Index=39;//下行TCP乱序报文数
	int rttUL_Index=89;//RTT上行总时延
	int rttULNUM_Index=88;//RTT上行次数
	int rttDL_Index=91;//RTT下行总时延
	int rttDLNUM_Index=90;//RTT下行次数
	int l7UPTRANSTIME_Index=99;//上行应用传输有效时长
	int l7UPTRAFFIC_Index=0000;//上行应用传输有效流量
	int l7DOWNTRANSTIME_Index=101;//下行应用传输有效时长
	int l7DOWNTRAFFIC_Index=0000;//下行应用传输有效流量
	int l7TRANSTIME_Index=74;//应用传输有效时长
	int longGPS_Index=86;//GPS经度
	int latiGPS_Index=87;//GPS纬度
	int heightGPS_Index=0000;//高度
	int accurGPS_Index=0000;//精度
	int coordi_Index=0000;//坐标系
	int tcpULDISOTRAF_Index=0000;//上行TCP乱序流量
	int tcpDLDISOTRAF_Index=0000;//下行TCP乱序流量
	int tcpULRESTTRAF_Index=0000;//上行TCP重传流量
	int tcpDLRESTTRAF_Index=0000;//下行TCP重传流量
	int httpVERS_Index=53;//HTTP版本
	int httpLASTPACKET1STREQDELAY_Index=57;//最后一个HTTP内容包的时延(ms)
	int httpLASTACKDELAY_Index=58;//最后一个ACK确认包的时延(ms)
	int xonlinehost_Index=61;//HTTP X-Online-Host
	int contentLENGTH_Index=66;//HTTPContent-Length
	int bussBROWSER_Index=75;//浏览工具
	int refer_Index=64;//Referer信息
	int relocateionuri_Index=0000;//重定向URI
	int webAPP_Index=0000;//区分网页流量/APP流量
	int requestQUERYDOMAIN_Index=0000;//请求查询的DNS域名
	int machineIPAddtype_Index=9;//设备IP地址类型
	int sgwGGSNIPAdd_Index=10;//GGSN的用户面IP地址

}
