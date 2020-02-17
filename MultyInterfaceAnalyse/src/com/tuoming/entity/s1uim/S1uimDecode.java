package com.tuoming.entity.s1uim;
import com.tuoming.common.CommonDecode;
import com.tuoming.entity.http.S1uhttpIndex;
import com.tuoming.tools.SimpleDateDeal;
import com.tuoming.entity.common.*;

import java.util.*;

public class S1uimDecode extends CommonDecode {

	public String uTYPE="";//话单类型
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
	public String actTYPE="";//业务动作
	public String actSTTIME="";//动作开始时间
	public String actENDTIME="";//动作结束时间
	public String imaction="";//IM登陆标识
	public String imresult="0";//IM登陆结果
	public String imdelay="";//IM登陆时延
	public List<String> resultarr=new ArrayList<>();
	public final static String splite = "\\|";

	public String getEndTime() {
		return this.endT;
	}
	public String getMmeIp() {
		return this.ipSGW;
	}
	public static Long getEndTime(String[] arr){
		try {
			return Long.parseLong(arr[S1uimIndex.endT_Index]);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	public static String getImsi(String[] arr){return arr[S1uimIndex.imsi_Index];}

	//decode
	public void getULTRAFF(String[] str,int ulTRAFF_Index){
		this.ulTRAFF=SimpleDateDeal.sum(str[ulTRAFF_Index],this.ulTRAFF);
	}
	public void getDLTRAFF(String[] str,int dlTRAFF_Index){
		this.dlTRAFF=SimpleDateDeal.sum(str[dlTRAFF_Index],this.dlTRAFF);
	}
	public void getL7UPTRANSTIME(String[] str,int l7UPTRANSTIME_Index){
		this.l7UPTRANSTIME=SimpleDateDeal.sum(this.l7UPTRANSTIME,SimpleDateDeal.divide(str[l7UPTRANSTIME_Index],"1000"));
	}
	public void getL7UPTRAFFIC(String[] str,int l7UPTRAFFIC_Index){
		this.l7UPTRAFFIC=SimpleDateDeal.sum(this.l7UPTRAFFIC,str[l7UPTRAFFIC_Index]);
	}
	public void getL7DOWNTRANSTIME(String[] str,int l7DOWNTRANSTIME_Index){
		this.l7DOWNTRANSTIME=SimpleDateDeal.sum(this.l7DOWNTRANSTIME,SimpleDateDeal.divide(str[l7DOWNTRANSTIME_Index],"1000"));
	}
	public void getL7DOWNTRAFFIC(String[] str,int l7DOWNTRAFFIC_Index){
		this.l7DOWNTRAFFIC=SimpleDateDeal.sum(this.l7DOWNTRAFFIC,str[l7DOWNTRAFFIC_Index]);
	}
	public void getL7TRANSTIME(String[] str,int l7TRANSTIME_Index){
		this.l7TRANSTIME=SimpleDateDeal.sum(this.l7TRANSTIME,SimpleDateDeal.diff(str[20],str[19]) );
	}
	public void getTCPULRESTTRAF(String[] str,int tcpULRESTTRAF_Index,int mss){
		this.tcpULRESTTRAF=SimpleDateDeal.sum(this.tcpULRESTTRAF,
				SimpleDateDeal.product(str[tcpULRESTTRAF_Index],str[mss]));
	}
	public void getTCPDLRESTTRAF(String[] str,int tcpDLRESTTRAF_Index,int mss){
		this.tcpDLRESTTRAF=SimpleDateDeal.sum(this.tcpDLRESTTRAF,
				SimpleDateDeal.product(str[tcpDLRESTTRAF_Index],str[mss]));
	}

	public void decode(String[] str) {
	}

	public void decode(List<String[]> str){
		Map<String,List<String[]>> map = new TreeMap();

		//把5分钟粒度的数据List<String> 按用户存成map<imsi,List<String>>
		for(String[] s: str )
		{
			String imsi = s[S1uimIndex.imsi_Index];
			if(!map.containsKey(imsi))
			{
				map.put(imsi,new ArrayList<String[]>());
			}
			map.get(imsi).add(s);
		}

		//循环用户做decode解析输出toString存入resultarr
		for(List<String[]> list : map.values())
		{
			userDecode(list);
			resultarr.add(toString());
			//为下一个用户做初始化
			init();
		}
	}

	public void init()	{
		uTYPE="";//话单类型
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
		actTYPE="";//业务动作
		actSTTIME="";//动作开始时间
		actENDTIME="";//动作结束时间
		imaction="";//IM登陆标识
		//imresult="";//IM登陆结果
		imdelay="";//IM登陆时延
	}

	public void userDecode(List<String[]> list) {
	    pubDecode(list);

    	for(String[] str:list)
		{
			if(str.length == S1uimIndex.length)
			{
                imDecode(str);
			}

			else if(str.length == S1uhttpIndex.length)
			{
				httpDecode(str);
			}
			else
			{
				getL7UPTRANSTIME(str,91);
				getL7DOWNTRANSTIME(str,92);
				getL7TRANSTIME(str,S1uimIndex.l7TRANSTIME_Index);
			}
		}
		pubDecodeEnd(list);
	}

    private void pubDecode(List<String[]> list) {
        this.uTYPE=String.valueOf(S1uimIndex.uTYPE_Index);
        this.startT = list.get(0)[S1uimIndex.startT_Index];
        this.endT = list.get(list.size()-1)[S1uimIndex.endT_Index];
        this.imsi = list.get(0)[S1uimIndex.imsi_Index];
        this.msisdn = SimpleDateDeal.Except86(list.get(0)[S1uimIndex.msisdn_Index]);
        this.imei = list.get(0)[S1uimIndex.imei_Index];
        this.accountID = list.get(0)[S1uimIndex.accountID_Index];
        this.lacTAC=SimpleDateDeal.leftSupply0(list.get(0)[S1uimIndex.lacTAC_Index],5);
        this.cgiECI = list.get(0)[S1uimIndex.cgiECI_Index];
        this.durition = SimpleDateDeal.diff(this.endT,this.startT);
        this.startT = SimpleDateDeal.getFormatDate(this.startT);
        this.endT = SimpleDateDeal.getFormatDate(this.endT);
        this.rat = list.get(0)[S1uimIndex.rat_Index];
        this.ipUSER=list.get(0)[S1uimIndex.ipUSER_Index].equals("")?list.get(0)[S1uimIndex.ipUSER_Index2]:list.get(0)[S1uimIndex.ipUSER_Index];
        this.ipSERV=list.get(0)[S1uimIndex.ipSERV_Index].equals("")?list.get(0)[S1uimIndex.ipSERV_Index2]:list.get(0)[S1uimIndex.ipSERV_Index];
        this.l4TYPE = list.get(0)[S1uimIndex.l4TYPE_Index];
        this.durTCP1ST = list.get(0)[S1uimIndex.durTCP1ST_Index];
        this.durTCP2ND = list.get(0)[S1uimIndex.durTCP2ND_Index];
        this.apn = list.get(0)[S1uimIndex.apn_Index];
        this.ipENB = list.get(0)[S1uimIndex.ipENB_Index];
        this.ipSGW = list.get(0)[S1uimIndex.ipSGW_Index];
        this.mcc = list.get(0)[S1uimIndex.mcc_Index].equals("")?this.mcc="460":list.get(0)[S1uimIndex.mcc_Index].substring(0,3);
        this.portUSER = list.get(0)[S1uimIndex.portUSER_Index];
        this.portSERV = list.get(0)[S1uimIndex.portSERV_Index];
        this.l7TYPE = typeDecode.L7_TYPE(list.get(0)[S1uimIndex.imsi_Index]);
        this.appSTATUS = list.get(0)[S1uimIndex.appSTATUS_Index];
        this.rttUL = list.get(0)[S1uimIndex.rttUL_Index];
        this.rttULNUM = list.get(0)[S1uimIndex.rttULNUM_Index];
        this.rttDL = list.get(0)[S1uimIndex.rttDL_Index];
        this.rttDLNUM = list.get(0)[S1uimIndex.rttDLNUM_Index];
        this.actTYPE = typeDecode.ActType(this.actTYPE,list.get(0)[S1uimIndex.Http_actType_Index],list.get(0)[S1uimIndex.Http_ul],list.get(0)[S1uimIndex.Http_dl]);
        this.actSTTIME=this.startT;
        this.actENDTIME=this.endT;
        if(!this.imaction.equals("1") && list.get(0)[S1uimIndex.imaction_Index].equals("10"))
            this.imaction="1";
        else
            this.imaction="0";
        this.imsi = list.get(0)[S1uimIndex.imsi_Index];
        this.imsi = list.get(0)[S1uimIndex.imsi_Index];
        if(this.imdelay.equals("") && list.get(0)[S1uimIndex.imdelay_Index].equals("10"))
            this.imdelay=list.get(0)[S1uimIndex.imdelay_Index];
        else
            this.imdelay="";
    }

    private void pubDecodeEnd(List<String[]> list) {
        this.totalTRAFF=SimpleDateDeal.sum(this.ulTRAFF,this.dlTRAFF);
    }

    public void httpDecode(String[] str) 	{
		this.l7TRANSTIME=SimpleDateDeal.sum(this.l7TRANSTIME,str[56]);
		if(this.feature.equals("")) {
			this.feature = str[S1uimIndex.feature_Index];
		}
    	if(this.longGPS.equals("") && !str[S1uimIndex.Http_longGPS_Index].equals(""))
		{
			this.longGPS = str[S1uimIndex.Http_longGPS_Index];
		}
		if(this.latiGPS.equals("") && !str[S1uimIndex.Http_latiGPS_Index].equals(""))
		{
			this.latiGPS = str[S1uimIndex.Http_latiGPS_Index];
		}
		if(!this.webAPP.equals("1") )
		{
			if (!str[S1uimIndex.Http_webAPP_Index].equals("0"))
				this.webAPP = "1";
			else
				this.webAPP = "2";
		}
        getL7UPTRANSTIME(str,124);
        getL7DOWNTRANSTIME(str,125);
	}

	public void imDecode(String[] str){
		getULTRAFF(str,S1uimIndex.ulTRAFF_Index);
		getDLTRAFF(str,S1uimIndex.dlTRAFF_Index);
		getL7UPTRAFFIC(str,S1uimIndex.l7UPTRAFFIC_Index);
		getL7DOWNTRAFFIC(str,S1uimIndex.l7DOWNTRAFFIC_Index);
		getL7TRANSTIME(str,S1uimIndex.l7TRANSTIME_Index);
		getTCPULRESTTRAF(str,S1uimIndex.tcpULRESTTRAF_Index,S1uimIndex.mss);
		getTCPDLRESTTRAF(str,S1uimIndex.tcpDLRESTTRAF_Index,S1uimIndex.mss);
		getL7UPTRANSTIME(str,95);
		getL7DOWNTRANSTIME(str,96);
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
				.append(actTYPE).append("|")
				.append(actSTTIME).append("|")
				.append(actENDTIME).append("|")
				.append(imaction).append("|")
				.append(imresult).append("|")
				.append(imdelay);
        return result.toString();

    }
}
