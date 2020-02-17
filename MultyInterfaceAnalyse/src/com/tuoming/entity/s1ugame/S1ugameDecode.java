package com.tuoming.entity.s1ugame;
import com.tuoming.common.CommonDecode;
import com.tuoming.entity.common.typeDecode;
import com.tuoming.tools.SimpleDateDeal;
import java.util.*;

public class S1ugameDecode extends CommonDecode {

	public String uTYPE="7";//话单类型
	public String startT="";//开始时间
	public String endT="";//结束时间
	public String imsi="";//IMSI
	public String msisdn="";//手机号码
	public String imei="";//终端类型
	public String accountID="";//APP账户
	public String rac="";//路由区编码
	public String lacTAC="";//位置区或跟踪区编码
	public String cgiECI="";//小区号码
	public String servname="";//流量类型
	public String durition="";//时长
	public String ulTRAFF="";//上行流量（bytes）
	public String dlTRAFF="";//下行流量（bytes）
	public String totalTRAFF="";//总流量（bytes）
	public String rat="";//RATType
	public String ipUSER="";//终端IP
	public String ipSERV="";//访问IP
	public String l4TYPE="";//传输层协议类型
	public String durTCP1ST="";//TCP建链时延（第一步）
	public String durTCP2ND="";//TCP建链时延（第二步）
	public String apn="";//APN
	public String ipENB="";//SGSN IP/eNB IP
	public String ipSGW="";//GGSN IP/S-GW IP
	public String mcc="";//MCC
	public String mnc="01";//MNC
	public String portUSER="";//源端口
	public String portSERV="";//目的端口
	public String feature="";//网址/特征信息
	public String l7TYPE="";//应用层协议类型
	public String appSTATUS="";//App Status
	public String rttUL="";//RTT上行总时延
	public String rttULNUM="";//RTT上行次数
	public String rttDL="";//RTT下行总时延
	public String rttDLNUM="";//RTT下行次数
	public String l7UPTRANSTIME="";//上行应用传输有效时长
	public String l7UPTRAFFIC="";//上行应用传输有效流量
	public String l7DOWNTRANSTIME="";//下行应用传输有效时长
	public String l7DOWNTRAFFIC="";//下行应用传输有效流量
	public String l7TRANSTIME="";//应用传输有效时长
	public String longGPS="";//GPS经度
	public String latiGPS="";//GPS纬度
	public String heightGPS="";//高度
	public String accurGPS="";//精度
	public String coordi="0";//坐标系
	public String tcpULRESTTRAF="";//上行TCP重传流量
	public String tcpDLRESTTRAF="";//下行TCP重传流量
	public String webAPP="2";//区分网页流量/APP流量
	public String serviceREFID="";//唯一游戏局标志
	public String battleFLAG="2";//游戏属性标记
	public String avgULINTERVAL="";//上行平均时延
	public String avgDLINTERVAL="";//下行平均时延
	public String avgULJITTER="";//上行平均包间隔抖动
	public String avgDLJITTER="";//下行平均包间隔抖动
	public String maxULJITTER="";//上行最大包间隔抖动
	public String maxDLJITTER="";//下行最大包间隔抖动
	public String minULJITTER="";//上行最小包间隔抖动
	public String minDLJITTER="";//下行最小包间隔抖动
	public String dlRTTLTTH1NUM="";//下行RTT小于th1阈值的个数
	public String dlRTTTH1TH2NUM="";//下行RTT在th1和th2阈值之间的个数
	public String dlRTTTH2TH3NUM="";//下行RTT在th2和th3阈值之间的个数
	public String dlRTTTH3TH4NUM="";//下行RTT在th3和th4阈值之间的个数
	public String dlRTTGTTH4NUM="";//下行RTT大于th4阈值的个数
	public String ulRTTLTTH1NUM="";//上行RTT小于th1阈值的个数
	public String ulRTTTH1TH2NUM="";//上行RTT在th1和th2阈值之间的个数
	public String ulRTTGTTH2NUM="";//上行RTT大于th2阈值的个数
	public final static String splite = "\\|";
	public List<String> resultarr=new ArrayList<>();

    public S1ugameDecode() {
    }

	@Override
	public void decode(String[] arr) {

	}

	@Override
	public String getEndTime() {

    	return this.endT;
	}

	@Override
	public String getMmeIp() {
		return this.ipSGW;
	}

	public static Long getEndTime(String[] arr){
		try {
			return Long.parseLong(arr[S1ugameIndex.endT_Index]);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	public void getULTRAFF(String[] str,int ulTRAFF_Index) {
		this.ulTRAFF=SimpleDateDeal.sum(str[ulTRAFF_Index],this.ulTRAFF);
	}
	public void getDLTRAFF(String[] str,int dlTRAFF_Index) {
		this.dlTRAFF=SimpleDateDeal.sum(str[dlTRAFF_Index],this.dlTRAFF);
	}
	public void getL7UPTRAFFIC(String[] str,int l7UPTRAFFIC_Index) {
		this.l7UPTRAFFIC=SimpleDateDeal.sum(this.l7UPTRAFFIC, str[l7UPTRAFFIC_Index]);
	}
	public void getL7DOWNTRAFFIC(String[] str,int l7DOWNTRAFFIC_Index) {
		this.l7DOWNTRAFFIC=SimpleDateDeal.sum(this.l7DOWNTRAFFIC, str[l7DOWNTRAFFIC_Index]);
	}
	public void getL7TRANSTIME(String[] str,int l7TRANSTIME_Index) {
		this.l7TRANSTIME=SimpleDateDeal.sum(this.l7TRANSTIME,this.durition);
	}
	public void getTCPULRESTTRAF(String[] str,int tcpULRESTTRAF_Index,int mss){
		this.tcpULRESTTRAF=SimpleDateDeal.sum(this.tcpULRESTTRAF,
				SimpleDateDeal.product(str[tcpULRESTTRAF_Index],str[mss]));
	}
	public void getTCPDLRESTTRAF(String[] str,int tcpDLRESTTRAF_Index,int mss){
		this.tcpDLRESTTRAF=SimpleDateDeal.sum(this.tcpDLRESTTRAF,
				SimpleDateDeal.product(str[tcpDLRESTTRAF_Index],str[mss]));
	}

	public void getdlul(String[] str)
	{
		Long dl1 = str[63].equals("")?0:Long.valueOf(str[63]);
		Long dl2 = str[62].equals("")?0:Long.valueOf(str[62]);
		String dl3 = str[59].equals("")?"0":str[59];
		String dl4 = str[58].equals("")?"0":str[58];
		Long ul1 = str[61].equals("")?0:Long.valueOf(str[61]);
		Long ul2 = str[60].equals("")?0:Long.valueOf(str[60]);
		String ul3 = str[57].equals("")?"0":str[57];
		String ul4 = str[56].equals("")?"0":str[56];

		if(dl1<50){
			this.dlRTTLTTH1NUM = SimpleDateDeal.sum(this.dlRTTLTTH1NUM,"1");
		}
		if(dl2<50){
			this.dlRTTLTTH1NUM = SimpleDateDeal.sum(this.dlRTTLTTH1NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))<50){
			this.dlRTTLTTH1NUM = SimpleDateDeal.sum(this.dlRTTLTTH1NUM,"-2");
		}

		if(dl1>50 && dl1<=150){
			this.dlRTTTH1TH2NUM = SimpleDateDeal.sum(this.dlRTTTH1TH2NUM,"1");
		}
		if(dl2>50 && dl1<=150){
			this.dlRTTTH1TH2NUM = SimpleDateDeal.sum(this.dlRTTTH1TH2NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))>50 && Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))<=150){
			this.dlRTTTH1TH2NUM = SimpleDateDeal.sum(this.dlRTTTH1TH2NUM,"-2");
		}

		if(dl1>150 && dl1<=250){
			this.dlRTTTH2TH3NUM = SimpleDateDeal.sum(this.dlRTTTH2TH3NUM,"1");
		}
		if(dl2>150 && dl2<=250){
			this.dlRTTTH2TH3NUM = SimpleDateDeal.sum(this.dlRTTTH2TH3NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))>150 && Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))<=250){
			this.dlRTTTH2TH3NUM = SimpleDateDeal.sum(this.dlRTTTH2TH3NUM,"-2");
		}

		if(dl1>250 && dl1<=410){
			this.dlRTTTH3TH4NUM = SimpleDateDeal.sum(this.dlRTTTH3TH4NUM,"1");
		}
		if(dl2>250 && dl2<=410){
			this.dlRTTTH3TH4NUM = SimpleDateDeal.sum(this.dlRTTTH3TH4NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))>410 && Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))<=410){
			this.dlRTTTH3TH4NUM = SimpleDateDeal.sum(this.dlRTTTH3TH4NUM,"-2");
		}

		if(dl1>410){
			this.dlRTTGTTH4NUM = SimpleDateDeal.sum(this.dlRTTGTTH4NUM,"1");
		}
		if(dl2>410){
			this.dlRTTGTTH4NUM = SimpleDateDeal.sum(this.dlRTTGTTH4NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))>410){
			this.dlRTTGTTH4NUM = SimpleDateDeal.sum(this.dlRTTGTTH4NUM,"-2");
		}

		if(ul1<30){
			this.ulRTTLTTH1NUM = SimpleDateDeal.sum(this.ulRTTLTTH1NUM,"1");
		}
		if(ul2<30){
			this.ulRTTLTTH1NUM = SimpleDateDeal.sum(this.ulRTTLTTH1NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[57],str[56]))<30){
			this.ulRTTLTTH1NUM = SimpleDateDeal.sum(this.ulRTTLTTH1NUM,"-2");
		}

		if(ul1>30 && dl1<=60){
			this.ulRTTTH1TH2NUM = SimpleDateDeal.sum(this.ulRTTTH1TH2NUM,"1");
		}
		if(ul2>30 && dl2<=60){
			this.ulRTTTH1TH2NUM = SimpleDateDeal.sum(this.ulRTTTH1TH2NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[57],str[56]))>30 && Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))<=60){
			this.ulRTTTH1TH2NUM = SimpleDateDeal.sum(this.ulRTTTH1TH2NUM,"-2");
		}

		if(ul1>60){
			this.ulRTTGTTH2NUM = SimpleDateDeal.sum(this.ulRTTGTTH2NUM,"1");
		}
		if(ul2>60){
			this.ulRTTGTTH2NUM = SimpleDateDeal.sum(this.ulRTTGTTH2NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[57],str[56]))>60 ){
			this.ulRTTGTTH2NUM = SimpleDateDeal.sum(this.ulRTTGTTH2NUM,"-2");
		}
	}

	public void decode(List<String[]> str) {
		Map<String,List<String[]>> map = new TreeMap();

		for(String[] s: str )
		{
			String imsi = s[S1ugameIndex.imsi_Index];
			if(!map.containsKey(imsi))
			{
				map.put(imsi,new ArrayList<String[]>());
			}
			map.get(imsi).add(s);
		}

		for(List<String[]> list : map.values())
		{
			userDecode(list);
			resultarr.add(toString());
			init();
		}
	}

	public void userDecode(List<String[]> list){
		pubDecode(list);
		for(String[] str:list)
		{
			if(str.length == S1ugameIndex.genlength)
			{
				getS1ugameDecode(str);
			}
			else if(str.length == S1ugameIndex.httplength)
			{
				getgamehttpDecode(str);
			}
			else
			{
				getgamednsDecode(str);
			}
		}
		pubDecodeEnd(list);
	}

	public void pubDecode(List<String[]> list){
		this.startT=list.get(0)[S1ugameIndex.startT_Index];
		this.endT=list.get(0)[S1ugameIndex.endT_Index];
		this.imsi=list.get(0)[S1ugameIndex.imsi_Index];
		this.msisdn=list.get(0)[S1ugameIndex.msisdn_Index];
		this.imei=list.get(0)[S1ugameIndex.imei_Index];
		this.accountID = list.get(0)[S1ugameIndex.accountID_Index];
		this.lacTAC=SimpleDateDeal.leftSupply0(list.get(0)[S1ugameIndex.lacTAC_Index],5);
		this.cgiECI = list.get(0)[S1ugameIndex.cgiECI_Index];
		this.durition = SimpleDateDeal.diff(this.endT,this.startT);
		this.startT = SimpleDateDeal.getFormatDate(this.startT);
		this.endT = SimpleDateDeal.getFormatDate(this.endT);
		this.rat = list.get(0)[S1ugameIndex.rat_Index];
		this.ipUSER=list.get(0)[S1ugameIndex.ipUSER_Index2].equals("")?list.get(0)[S1ugameIndex.ipUSER_Index]:list.get(0)[S1ugameIndex.ipUSER_Index2];
		this.ipSERV=list.get(0)[S1ugameIndex.ipSERV_Index2].equals("")?list.get(0)[S1ugameIndex.ipSERV_Index]:list.get(0)[S1ugameIndex.ipSERV_Index2];
		this.l4TYPE = list.get(0)[S1ugameIndex.l4TYPE_Index];
		this.durTCP1ST = list.get(0)[S1ugameIndex.durTCP1ST_Index];
		this.durTCP2ND = list.get(0)[S1ugameIndex.durTCP2ND_Index];
		this.apn = list.get(0)[S1ugameIndex.apn_Index];
		this.ipENB = list.get(0)[S1ugameIndex.ipENB_Index];
		this.ipSGW = list.get(0)[S1ugameIndex.ipSGW_Index];
		this.mcc = list.get(0)[S1ugameIndex.mcc_Index].equals("")?this.mcc="460":list.get(0)[S1ugameIndex.mcc_Index].substring(0,3);
		this.portUSER = list.get(0)[S1ugameIndex.portUSER_Index];
		this.portSERV = list.get(0)[S1ugameIndex.portSERV_Index];
		this.l7TYPE = typeDecode.L7_TYPE(list.get(0)[S1ugameIndex.imsi_Index]);
		this.appSTATUS = list.get(0)[S1ugameIndex.appSTATUS_Index];
		this.serviceREFID = list.get(0)[S1ugameIndex.serviceREFID_Index];
		if(this.avgULJITTER.equals("") && list.get(0)[S1ugameIndex.avgULJITTER_Index].equals("1"))
		{
			this.avgULJITTER = SimpleDateDeal.rand(10,300);
		}
		if(this.avgDLJITTER.equals("") && list.get(0)[S1ugameIndex.avgDLJITTER_Index].equals("1"))
		{
			this.avgDLJITTER = SimpleDateDeal.rand(10,300);
		}
		if(this.maxULJITTER.equals("") && list.get(0)[S1ugameIndex.maxULJITTER_Index].equals("1"))
		{
			this.maxULJITTER = SimpleDateDeal.rand(10,300);
		}
		if(this.maxDLJITTER.equals("") && list.get(0)[S1ugameIndex.maxDLJITTER_Index].equals("1"))
		{
			this.maxDLJITTER = SimpleDateDeal.rand(10,300);
		}
		if(this.minULJITTER.equals("") && list.get(0)[S1ugameIndex.minULJITTER_Index].equals("1"))
		{
			this.minULJITTER = SimpleDateDeal.rand(10,300);
		}
		if(this.minDLJITTER.equals("") && list.get(0)[S1ugameIndex.minDLJITTER_Index].equals("1"))
		{
			this.minDLJITTER = SimpleDateDeal.rand(10,300);
		}


	}


	public void pubDecodeEnd(List<String[]> list)	{
		this.totalTRAFF=SimpleDateDeal.sum(this.ulTRAFF,this.dlTRAFF);
	}

	public void init(){
		//uTYPE="";//话单类型
		//startT="";//开始时间
		//endT="";//结束时间
		imsi="";//IMSI
		msisdn="";//手机号码
		imei="";//终端类型
		accountID="";//APP账户
		rac="";//路由区编码
		lacTAC="";//位置区或跟踪区编码
		cgiECI="";//小区号码
		servname="";//流量类型
		durition="";//时长
		ulTRAFF="";//上行流量（bytes）
		dlTRAFF="";//下行流量（bytes）
		totalTRAFF="";//总流量（bytes）
		rat="";//RATType
		ipUSER="";//终端IP
		ipSERV="";//访问IP
		l4TYPE="";//传输层协议类型
		durTCP1ST="";//TCP建链时延（第一步）
		durTCP2ND="";//TCP建链时延（第二步）
		apn="";//APN
		ipENB="";//SGSN IP/eNB IP
		ipSGW="";//GGSN IP/S-GW IP
		mcc="";//MCC
		//mnc="";//MNC
		portUSER="";//源端口
		portSERV="";//目的端口
		feature="";//网址/特征信息
		l7TYPE="";//应用层协议类型
		appSTATUS="";//App Status
		rttUL="";//RTT上行总时延
		rttULNUM="";//RTT上行次数
		rttDL="";//RTT下行总时延
		rttDLNUM="";//RTT下行次数
		l7UPTRANSTIME="";//上行应用传输有效时长
		l7UPTRAFFIC="";//上行应用传输有效流量
		l7DOWNTRANSTIME="";//下行应用传输有效时长
		l7DOWNTRAFFIC="";//下行应用传输有效流量
		l7TRANSTIME="";//应用传输有效时长
		longGPS="";//GPS经度
		latiGPS="";//GPS纬度
		heightGPS="";//高度
		accurGPS="";//精度
		//coordi="";//坐标系
		tcpULRESTTRAF="";//上行TCP重传流量
		tcpDLRESTTRAF="";//下行TCP重传流量
		//webAPP="";//区分网页流量/APP流量
		serviceREFID="";//唯一游戏局标志
		battleFLAG="";//游戏属性标记
		avgULINTERVAL="";//上行平均时延
		avgDLINTERVAL="";//下行平均时延
		avgULJITTER="";//上行平均包间隔抖动
		avgDLJITTER="";//下行平均包间隔抖动
		maxULJITTER="";//上行最大包间隔抖动
		maxDLJITTER="";//下行最大包间隔抖动
		minULJITTER="";//上行最小包间隔抖动
		minDLJITTER="";//下行最小包间隔抖动
		dlRTTLTTH1NUM="";//下行RTT小于th1阈值的个数
		dlRTTTH1TH2NUM="";//下行RTT在th1和th2阈值之间的个数
		dlRTTTH2TH3NUM="";//下行RTT在th2和th3阈值之间的个数
		dlRTTTH3TH4NUM="";//下行RTT在th3和th4阈值之间的个数
		dlRTTGTTH4NUM="";//下行RTT大于th4阈值的个数
		ulRTTLTTH1NUM="";//上行RTT小于th1阈值的个数
		ulRTTTH1TH2NUM="";//上行RTT在th1和th2阈值之间的个数
		ulRTTGTTH2NUM="";//上行RTT大于th2阈值的个数
	}

	private void getgamednsDecode(String[] str) {
		//getdlul(str);
		getULTRAFF(str,S1ugameIndex.ulTRAFF_Index);
		getDLTRAFF(str,S1ugameIndex.dlTRAFF_Index);
		getL7UPTRAFFIC(str,S1ugameIndex.l7UPTRAFFIC_Index);
		getL7DOWNTRAFFIC(str,S1ugameIndex.l7DOWNTRAFFIC_Index);
		getL7TRANSTIME(str,S1ugameIndex.l7TRANSTIME_Index);
		getTCPULRESTTRAF(str,S1ugameIndex.tcpULRESTTRAF_Index,S1ugameIndex.mss);
		getTCPDLRESTTRAF(str,S1ugameIndex.tcpDLRESTTRAF_Index,S1ugameIndex.mss);
		this.l7UPTRANSTIME=SimpleDateDeal.sum(this.l7UPTRANSTIME,SimpleDateDeal.divide(str[60],"1000"));
		this.l7DOWNTRANSTIME=SimpleDateDeal.sum(this.l7DOWNTRANSTIME,SimpleDateDeal.divide(str[61],"1000"));
	}

	private void getgamehttpDecode(String[] str) {
		getULTRAFF(str,S1ugameIndex.ulTRAFF_Index);
		getDLTRAFF(str,S1ugameIndex.dlTRAFF_Index);
		getL7UPTRAFFIC(str,S1ugameIndex.l7UPTRAFFIC_Index);
		getL7DOWNTRAFFIC(str,S1ugameIndex.l7DOWNTRAFFIC_Index);
		getL7TRANSTIME(str,S1ugameIndex.l7TRANSTIME_Index);
		getTCPULRESTTRAF(str,S1ugameIndex.tcpULRESTTRAF_Index,S1ugameIndex.mss);
		getTCPDLRESTTRAF(str,S1ugameIndex.tcpDLRESTTRAF_Index,S1ugameIndex.mss);
		this.l7UPTRANSTIME=SimpleDateDeal.sum(this.l7DOWNTRANSTIME,SimpleDateDeal.divide(str[124],"1000"));
		this.l7DOWNTRANSTIME=SimpleDateDeal.sum(this.l7DOWNTRANSTIME,SimpleDateDeal.divide(str[125],"1000"));
		this.l7TRANSTIME=SimpleDateDeal.sum(this.l7TRANSTIME,str[56]);

			if(this.avgULINTERVAL.equals(""))
			{
				this.avgULINTERVAL=SimpleDateDeal.divide( str[89],str[88]);
			}
			else
			{
				this.avgULINTERVAL=
						SimpleDateDeal.divide(
								SimpleDateDeal.sum(
										SimpleDateDeal.divide( str[89],str[88])
										,this.avgULINTERVAL)
								,"2");
			}
			if (this.avgDLINTERVAL.equals("")) {
				this.avgDLINTERVAL = SimpleDateDeal.divide(str[91], str[90]);
			} else {
				this.avgDLINTERVAL =
						SimpleDateDeal.divide(
								SimpleDateDeal.sum(
										SimpleDateDeal.divide(str[91], str[90])
										, this.avgDLINTERVAL)
								, "2");
			}

    	if(this.feature.equals("") && !str[S1ugameIndex.Http_feature_Index].equals("")){
    		this.feature=str[S1ugameIndex.Http_feature_Index];}
		if(this.rttUL.equals("") && !str[S1ugameIndex.Http_rttUL_Index].equals("")){
			this.rttUL=str[S1ugameIndex.Http_rttUL_Index];}
		if(this.rttULNUM.equals("") && !str[S1ugameIndex.Http_rttULNUM_Index].equals("")){
			this.rttULNUM=str[S1ugameIndex.Http_rttULNUM_Index];}
		if(this.rttDL.equals("") && !str[S1ugameIndex.Http_rttDL_Index].equals("")){
			this.rttDL=str[S1ugameIndex.Http_rttDL_Index];}
		if(this.rttDLNUM.equals("") && !str[S1ugameIndex.Http_rttDLNUM_Index].equals("")){
			this.rttDLNUM=str[S1ugameIndex.Http_rttDLNUM_Index];}

		if(this.longGPS.equals("") && !str[S1ugameIndex.Http_longGPS_Index].equals(""))
		{
			this.longGPS = str[S1ugameIndex.Http_longGPS_Index];
		}
		if(this.latiGPS.equals("") && !str[S1ugameIndex.Http_latiGPS_Index].equals(""))
		{
			this.latiGPS = str[S1ugameIndex.Http_latiGPS_Index];
		}
		if(!this.webAPP.equals("1") )
		{
			if(!str[S1ugameIndex.Http_webAPP_Index].equals("") )
				this.webAPP ="2";
			else
				this.webAPP ="1";
		}
		if(this.serviceREFID.equals("") && !str[S1ugameIndex.serviceREFID_Index].equals(""))
			this.serviceREFID = str[S1ugameIndex.serviceREFID_Index];

		if(this.avgULINTERVAL.equals(""))
		{
			this.avgULINTERVAL=SimpleDateDeal.divide( str[S1ugameIndex.Http_sumULINTERVAL_Index],str[S1ugameIndex.Http_countULINTERVAL_Index]);
		}
		else
		{
			this.avgULINTERVAL=
					SimpleDateDeal.divide(
							SimpleDateDeal.sum(
									SimpleDateDeal.divide( str[S1ugameIndex.Http_sumULINTERVAL_Index],str[S1ugameIndex.Http_countULINTERVAL_Index])
									,this.avgULINTERVAL)
							,"2");
		}

		if (this.avgDLINTERVAL.equals("")) {
			this.avgDLINTERVAL = SimpleDateDeal.divide(str[S1ugameIndex.Http_sumDLINTERVAL_Index], str[S1ugameIndex.Http_countDLINTERVAL_Index]);
		} else {
			this.avgDLINTERVAL =
					SimpleDateDeal.divide(
							SimpleDateDeal.sum(
									SimpleDateDeal.divide(str[S1ugameIndex.Http_sumDLINTERVAL_Index], str[S1ugameIndex.Http_countDLINTERVAL_Index])
									, this.avgDLINTERVAL)
							, "2");
		}

		Long dl1 = str[94].equals("")?0:Long.valueOf(str[94]);
		Long dl2 = str[93].equals("")?0:Long.valueOf(str[93]);
		String dl3 = str[90].equals("")?"0":str[90];
		String dl4 = str[89].equals("")?"0":str[89];
		Long ul1 = str[92].equals("")?0:Long.valueOf(str[92]);
		Long ul2 = str[91].equals("")?0:Long.valueOf(str[91]);
		String ul3 = str[88].equals("")?"0":str[88];
		String ul4 = str[87].equals("")?"0":str[87];

		if(dl1<50){
			this.dlRTTLTTH1NUM = SimpleDateDeal.sum(this.dlRTTLTTH1NUM,"1");
		}
		if(dl2<50){
			this.dlRTTLTTH1NUM = SimpleDateDeal.sum(this.dlRTTLTTH1NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(dl3,dl4))<50){
			this.dlRTTLTTH1NUM = SimpleDateDeal.sum(this.dlRTTLTTH1NUM,"-2");
		}

		if(dl1>50 && dl1<=150){
			this.dlRTTTH1TH2NUM = SimpleDateDeal.sum(this.dlRTTTH1TH2NUM,"1");
		}
		if(dl2>50 && dl1<=150){
			this.dlRTTTH1TH2NUM = SimpleDateDeal.sum(this.dlRTTTH1TH2NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(dl3,dl4))>50 && Long.valueOf(SimpleDateDeal.divide(dl3,dl4))<=150){
			this.dlRTTTH1TH2NUM = SimpleDateDeal.sum(this.dlRTTTH1TH2NUM,"-2");
		}

		if(dl1>150 && dl1<=250){
			this.dlRTTTH2TH3NUM = SimpleDateDeal.sum(this.dlRTTTH2TH3NUM,"1");
		}
		if(dl2>150 && dl2<=250){
			this.dlRTTTH2TH3NUM = SimpleDateDeal.sum(this.dlRTTTH2TH3NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(dl3,dl4))>150 && Long.valueOf(SimpleDateDeal.divide(dl3,dl4))<=250){
			this.dlRTTTH2TH3NUM = SimpleDateDeal.sum(this.dlRTTTH2TH3NUM,"-2");
		}

		if(dl1>250 && dl1<=410){
			this.dlRTTTH3TH4NUM = SimpleDateDeal.sum(this.dlRTTTH3TH4NUM,"1");
		}
		if(dl2>250 && dl2<=410){
			this.dlRTTTH3TH4NUM = SimpleDateDeal.sum(this.dlRTTTH3TH4NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(dl3,dl4))>410 && Long.valueOf(SimpleDateDeal.divide(dl3,dl4))<=410){
			this.dlRTTTH3TH4NUM = SimpleDateDeal.sum(this.dlRTTTH3TH4NUM,"-2");
		}

		if(dl1>410){
			this.dlRTTGTTH4NUM = SimpleDateDeal.sum(this.dlRTTGTTH4NUM,"1");
		}
		if(dl2>410){
			this.dlRTTGTTH4NUM = SimpleDateDeal.sum(this.dlRTTGTTH4NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(dl3,dl4))>410){
			this.dlRTTGTTH4NUM = SimpleDateDeal.sum(this.dlRTTGTTH4NUM,"-2");
		}

		if(ul1<30){
			this.ulRTTLTTH1NUM = SimpleDateDeal.sum(this.ulRTTLTTH1NUM,"1");
		}
		if(ul2<30){
			this.ulRTTLTTH1NUM = SimpleDateDeal.sum(this.ulRTTLTTH1NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(ul3,ul4))<30){
			this.ulRTTLTTH1NUM = SimpleDateDeal.sum(this.ulRTTLTTH1NUM,"-2");
		}

		if(ul1>30 && dl1<=60){
			this.ulRTTTH1TH2NUM = SimpleDateDeal.sum(this.ulRTTTH1TH2NUM,"1");
		}
		if(ul2>30 && dl2<=60){
			this.ulRTTTH1TH2NUM = SimpleDateDeal.sum(this.ulRTTTH1TH2NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(ul3,ul4))>30 && Long.valueOf(SimpleDateDeal.divide(dl3,dl4))<=60){
			this.ulRTTTH1TH2NUM = SimpleDateDeal.sum(this.ulRTTTH1TH2NUM,"-2");
		}

		if(ul1>60){
			this.ulRTTGTTH2NUM = SimpleDateDeal.sum(this.ulRTTGTTH2NUM,"1");
		}
		if(ul2>60){
			this.ulRTTGTTH2NUM = SimpleDateDeal.sum(this.ulRTTGTTH2NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(ul3,ul4))>60 ){
			this.ulRTTGTTH2NUM = SimpleDateDeal.sum(this.ulRTTGTTH2NUM,"-2");
		}
    }

	public void getS1ugameDecode(String[] str){
		getULTRAFF(str,S1ugameIndex.ulTRAFF_Index);
		getDLTRAFF(str,S1ugameIndex.dlTRAFF_Index);
		getL7UPTRAFFIC(str,S1ugameIndex.l7UPTRAFFIC_Index);
		getL7DOWNTRAFFIC(str,S1ugameIndex.l7DOWNTRAFFIC_Index);
		getL7TRANSTIME(str,S1ugameIndex.l7TRANSTIME_Index);
		getTCPULRESTTRAF(str,S1ugameIndex.tcpULRESTTRAF_Index,S1ugameIndex.mss);
		getTCPDLRESTTRAF(str,S1ugameIndex.tcpDLRESTTRAF_Index,S1ugameIndex.mss);
		this.l7UPTRANSTIME=SimpleDateDeal.sum(this.l7DOWNTRANSTIME,SimpleDateDeal.divide(str[91],"1000"));
		this.l7DOWNTRANSTIME=SimpleDateDeal.sum(this.l7DOWNTRANSTIME,SimpleDateDeal.divide(str[92],"1000"));

		Long dl1 = str[63].equals("")?0:Long.valueOf(str[63]);
		Long dl2 = str[62].equals("")?0:Long.valueOf(str[62]);
		String dl3 = str[59].equals("")?"0":str[59];
		String dl4 = str[58].equals("")?"0":str[58];
		Long ul1 = str[61].equals("")?0:Long.valueOf(str[61]);
		Long ul2 = str[60].equals("")?0:Long.valueOf(str[60]);
		String ul3 = str[57].equals("")?"0":str[57];
		String ul4 = str[56].equals("")?"0":str[56];

		if(dl1<50){
			this.dlRTTLTTH1NUM = SimpleDateDeal.sum(this.dlRTTLTTH1NUM,"1");
		}
		if(dl2<50){
			this.dlRTTLTTH1NUM = SimpleDateDeal.sum(this.dlRTTLTTH1NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))<50){
			this.dlRTTLTTH1NUM = SimpleDateDeal.sum(this.dlRTTLTTH1NUM,"-2");
		}

		if(dl1>50 && dl1<=150){
			this.dlRTTTH1TH2NUM = SimpleDateDeal.sum(this.dlRTTTH1TH2NUM,"1");
		}
		if(dl2>50 && dl1<=150){
			this.dlRTTTH1TH2NUM = SimpleDateDeal.sum(this.dlRTTTH1TH2NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))>50 && Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))<=150){
			this.dlRTTTH1TH2NUM = SimpleDateDeal.sum(this.dlRTTTH1TH2NUM,"-2");
		}

		if(dl1>150 && dl1<=250){
			this.dlRTTTH2TH3NUM = SimpleDateDeal.sum(this.dlRTTTH2TH3NUM,"1");
		}
		if(dl2>150 && dl2<=250){
			this.dlRTTTH2TH3NUM = SimpleDateDeal.sum(this.dlRTTTH2TH3NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))>150 && Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))<=250){
			this.dlRTTTH2TH3NUM = SimpleDateDeal.sum(this.dlRTTTH2TH3NUM,"-2");
		}

		if(dl1>250 && dl1<=410){
			this.dlRTTTH3TH4NUM = SimpleDateDeal.sum(this.dlRTTTH3TH4NUM,"1");
		}
		if(dl2>250 && dl2<=410){
			this.dlRTTTH3TH4NUM = SimpleDateDeal.sum(this.dlRTTTH3TH4NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))>410 && Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))<=410){
			this.dlRTTTH3TH4NUM = SimpleDateDeal.sum(this.dlRTTTH3TH4NUM,"-2");
		}

		if(dl1>410){
			this.dlRTTGTTH4NUM = SimpleDateDeal.sum(this.dlRTTGTTH4NUM,"1");
		}
		if(dl2>410){
			this.dlRTTGTTH4NUM = SimpleDateDeal.sum(this.dlRTTGTTH4NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))>410){
			this.dlRTTGTTH4NUM = SimpleDateDeal.sum(this.dlRTTGTTH4NUM,"-2");
		}

		if(ul1<30){
			this.ulRTTLTTH1NUM = SimpleDateDeal.sum(this.ulRTTLTTH1NUM,"1");
		}
		if(ul2<30){
			this.ulRTTLTTH1NUM = SimpleDateDeal.sum(this.ulRTTLTTH1NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[57],str[56]))<30){
			this.ulRTTLTTH1NUM = SimpleDateDeal.sum(this.ulRTTLTTH1NUM,"-2");
		}

		if(ul1>30 && dl1<=60){
			this.ulRTTTH1TH2NUM = SimpleDateDeal.sum(this.ulRTTTH1TH2NUM,"1");
		}
		if(ul2>30 && dl2<=60){
			this.ulRTTTH1TH2NUM = SimpleDateDeal.sum(this.ulRTTTH1TH2NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[57],str[56]))>30 && Long.valueOf(SimpleDateDeal.divide(str[59],str[58]))<=60){
			this.ulRTTTH1TH2NUM = SimpleDateDeal.sum(this.ulRTTTH1TH2NUM,"-2");
		}

		if(ul1>60){
			this.ulRTTGTTH2NUM = SimpleDateDeal.sum(this.ulRTTGTTH2NUM,"1");
		}
		if(ul2>60){
			this.ulRTTGTTH2NUM = SimpleDateDeal.sum(this.ulRTTGTTH2NUM,"1");
		}
		if(Long.valueOf(SimpleDateDeal.divide(str[57],str[56]))>60 ){
			this.ulRTTGTTH2NUM = SimpleDateDeal.sum(this.ulRTTGTTH2NUM,"-2");
		}

		if(this.avgULINTERVAL.equals(""))
		{
			this.avgULINTERVAL=SimpleDateDeal.divide( str[58],str[57]);
		}
		else
		{
			this.avgULINTERVAL=
					SimpleDateDeal.divide(
							SimpleDateDeal.sum(
									SimpleDateDeal.divide( str[58],str[57])
									,this.avgULINTERVAL)
							,"2");
		}
		if (this.avgDLINTERVAL.equals("")) {
			this.avgDLINTERVAL = SimpleDateDeal.divide(str[60], str[59]);
		} else {
			this.avgDLINTERVAL =
					SimpleDateDeal.divide(
							SimpleDateDeal.sum(
									SimpleDateDeal.divide(str[60], str[59])
									, this.avgDLINTERVAL)
							, "2");
		}
	}

    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        result.append(uTYPE).append("|")
			.append(startT).append("|")
			.append(endT).append("|")
			.append(imsi).append("|")
			.append(msisdn).append("|")
			.append(imei).append("|")
			.append(accountID).append("|")
			.append(rac).append("|")
			.append(lacTAC).append("|")
			.append(cgiECI).append("|")
			.append(servname).append("|")
			.append(durition).append("|")
			.append(ulTRAFF).append("|")
			.append(dlTRAFF).append("|")
			.append(totalTRAFF).append("|")
			.append(rat).append("|")
			.append(ipUSER).append("|")
			.append(ipSERV).append("|")
			.append(l4TYPE).append("|")
			.append(durTCP1ST).append("|")
			.append(durTCP2ND).append("|")
			.append(apn).append("|")
			.append(ipENB).append("|")
			.append(ipSGW).append("|")
			.append(mcc).append("|")
			.append(mnc).append("|")
			.append(portUSER).append("|")
			.append(portSERV).append("|")
			.append(feature).append("|")
			.append(l7TYPE).append("|")
			.append(appSTATUS).append("|")
			.append(rttUL).append("|")
			.append(rttULNUM).append("|")
			.append(rttDL).append("|")
			.append(rttDLNUM).append("|")
			.append(l7UPTRANSTIME).append("|")
			.append(l7UPTRAFFIC).append("|")
			.append(l7DOWNTRANSTIME).append("|")
			.append(l7DOWNTRAFFIC).append("|")
			.append(l7TRANSTIME).append("|")
			.append(longGPS).append("|")
			.append(latiGPS).append("|")
			.append(heightGPS).append("|")
			.append(accurGPS).append("|")
			.append(coordi).append("|")
			.append(tcpULRESTTRAF).append("|")
			.append(tcpDLRESTTRAF).append("|")
			.append(webAPP).append("|")
			.append(serviceREFID).append("|")
			.append(battleFLAG).append("|")
			.append(avgULINTERVAL).append("|")
			.append(avgDLINTERVAL).append("|")
			.append(avgULJITTER).append("|")
			.append(avgDLJITTER).append("|")
			.append(maxULJITTER).append("|")
			.append(maxDLJITTER).append("|")
			.append(minULJITTER).append("|")
			.append(minDLJITTER).append("|")
			.append(dlRTTLTTH1NUM).append("|")
			.append(dlRTTTH1TH2NUM).append("|")
			.append(dlRTTTH2TH3NUM).append("|")
			.append(dlRTTTH3TH4NUM).append("|")
			.append(dlRTTGTTH4NUM).append("|")
			.append(ulRTTLTTH1NUM).append("|")
			.append(ulRTTTH1TH2NUM).append("|")
			.append(ulRTTGTTH2NUM);
        return result.toString();


    }
}
