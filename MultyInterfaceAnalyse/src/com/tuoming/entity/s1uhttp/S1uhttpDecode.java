package com.tuoming.entity.s1uhttp;
import com.tuoming.common.CommonDecode;
import com.tuoming.tools.SimpleDateDeal;

public class S1uhttpDecode extends CommonDecode {

	public String uTYPE="";//话单类型
	public String startT="";//开始时间
	public String endT="";//结束时间
	public String imsi="";//IMSI
	public String msisdn="";//手机号码
	public String imei="";//终端类型
	public String rac="";//路由区编码
	public String lacTAC="";//位置区或跟踪区编码
	public String cgiECI="";//小区号码
	public String servname="";//流量类型
	public String durition="";//时长
	public String ulTRAFF="";//上行流量（bytes）
	public String dlTRAFF="";//下行流量（bytes）
	public String totalTRAFF="";//总流量（bytes）
	public String rat="";//RATType
	public String cdrstat="";//单据状态
	public String ulDURARION="";//上行持续时长
	public String dlDURARION="";//下行持续时长
	public String ulIPPACKET="";//上行IP包数
	public String dlIPPACKET="";//下行IP包数
	public String ipUSER="";//终端IP
	public String ipSERV="";//访问IP
	public String l4TYPE="";//传输层协议类型
	public String durTCP1ST="";//TCP建链时延（第一步）
	public String durTCP2ND="";//TCP建链时延（第二步）
	public String tcpULRETRNUM="";//TCP上行重传报文数
	public String tcpDLRETRNUM="";//TCP下行重传报文数
	public String l7TYPE="";//应用层协议类型
	public String l7Delay="";//应用层时延
	public String menthod="";//HTTP 事务类型
	public String httpCODE="";//状态码
	public String userAGENT="";//User Agent
	public String apn="";//APN
	public String ipSGSNENB="";//SGSN IP/eNB IP
	public String ipSGW="";//GGSN IP/S-GW IP
	public String mcc="";//MCC
	public String mnc="";//MNC
	public String contentTYPE="";//Content-Type
	public String portUSER="";//源端口
	public String portSERV="";//目的端口
	public String host="";//HOST
	public String uri="";//网址/特征信息
	public String ulIPFRAGPACKETS="";//UL_IP_FRAG_PACKETS
	public String dlIPFRAGPACKETS="";//DL_IP_FRAG_PACKETS
	public String eventT1ST="";//TCP建链成功到第一条事务请求的时延（ms）
	public String eventT2ND="";//第一条事务请求到其第一个响应包时延（ms）
	public String tcpWIN="";//窗口大小
	public String tcpMSS="";//MSS大小
	public String tcpSYNNUM="";//TCP建链尝试次数
	public String tcpSTATUS="";//TCP连接状态指示
	public String appSTATUS="";//App Status
	public String tcpULDISORD="";//上行TCP乱序报文数
	public String tcpDLDISORD="";//下行TCP乱序报文数
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
	public String coordi="";//坐标系
	public String tcpULDISOTRAF="";//上行TCP乱序流量
	public String tcpDLDISOTRAF="";//下行TCP乱序流量
	public String tcpULRESTTRAF="";//上行TCP重传流量
	public String tcpDLRESTTRAF="";//下行TCP重传流量
	public String httpVERS="";//HTTP版本
	public String httpLASTPACKET1STREQDELAY="";//最后一个HTTP内容包的时延(ms)
	public String httpLASTACKDELAY="";//最后一个ACK确认包的时延(ms)
	public String xonlinehost="";//HTTP X-Online-Host
	public String contentLENGTH="";//HTTPContent-Length
	public String bussBROWSER="";//浏览工具
	public String refer="";//Referer信息
	public String relocateionuri="";//重定向URI
	public String webAPP="";//区分网页流量/APP流量
	public String requestQUERYDOMAIN="";//请求查询的DNS域名
	public String sgwGGSNIPAdd="";

    public S1uhttpDecode() {
    }

	@Override
	public void decode(String[] arr) {

	}

	@Override
	public String getEndTime() {
		return null;
	}

	@Override
	public String getMmeIp() {
		return null;
	}

	public void getUTYPE(String[] str, int uTYPE_Index) {
		this.uTYPE="1";
	}
	public void getSTARTT(String[] str,int startT_Index) {
		this.startT=SimpleDateDeal.getFormatDate(str[startT_Index]);
	}
	public void getENDT(String[] str,int endT_Index) {
		this.endT=SimpleDateDeal.getFormatDate(str[endT_Index]);
	}
	public void getIMSI(String[] str,int imsi_Index) {
		this.imsi=str[imsi_Index];
	}
	public void getMSISDN(String[] str,int msisdn_Index) {
		this.msisdn=str[msisdn_Index];
	}
	public void getIMEI(String[] str,int imei_Index) {
		this.imei=str[imei_Index];
	}
	public void getRAC(String[] str,int rac_Index) {
		this.rac=str[rac_Index];
	}
	public void getLACTAC(String[] str,int lacTAC_Index) {
		this.lacTAC=str[lacTAC_Index];
	}
	public void getCGIECI(String[] str,int cgiECI_Index) {
		this.cgiECI=str[cgiECI_Index];
	}
	public void getSERVNAME(String[] str,int servname_Index) {
		this.servname=str[servname_Index];
	}
	public void getDURITION(String[] str,int durition_Index) {
		this.durition=str[durition_Index];
	}
	public void getULTRAFF(String[] str,int ulTRAFF_Index) {
		this.ulTRAFF=str[ulTRAFF_Index];
	}
	public void getDLTRAFF(String[] str,int dlTRAFF_Index) {
		this.dlTRAFF=str[dlTRAFF_Index];
	}
	public void getTOTALTRAFF(String[] str,int totalTRAFF_Index) {
		this.totalTRAFF=str[totalTRAFF_Index];
	}
	public void getRAT(String[] str,int rat_Index) {
		this.rat=str[rat_Index];
	}
	public void getCDRSTAT(String[] str,int cdrstat_Index) {
		this.cdrstat=str[cdrstat_Index];
	}
	public void getULDURARION(String[] str,int ulDURARION_Index) {
		this.ulDURARION=str[ulDURARION_Index];
	}
	public void getDLDURARION(String[] str,int dlDURARION_Index) {
		this.dlDURARION=str[dlDURARION_Index];
	}
	public void getULIPPACKET(String[] str,int ulIPPACKET_Index) {
		this.ulIPPACKET=str[ulIPPACKET_Index];
	}
	public void getDLIPPACKET(String[] str,int dlIPPACKET_Index) {
		this.dlIPPACKET=str[dlIPPACKET_Index];
	}
	public void getIPUSER(String[] str,int ipUSER_Index,int ipUSER_Index2,int machineIPAddtype_Index) {
		if(str[machineIPAddtype_Index].equals("0x01"))this.ipUSER=str[ipUSER_Index];
		if(str[machineIPAddtype_Index].equals("0x02"))this.ipUSER=str[ipUSER_Index2];
	}
	public void getIPSERV(String[] str,int ipSERV_Index,int ipSERV_Index2,int machineIPAddtype_Index) {
		if(str[machineIPAddtype_Index].equals("0x01"))this.ipSERV=str[ipSERV_Index];
		if(str[machineIPAddtype_Index].equals("0x02"))this.ipSERV=str[ipSERV_Index2];
	}
	public void getL4TYPE(String[] str,int l4TYPE_Index) {
		this.l4TYPE=str[l4TYPE_Index];
	}
	public void getDURTCP1ST(String[] str,int durTCP1ST_Index) {
		this.durTCP1ST=str[durTCP1ST_Index];
	}
	public void getDURTCP2ND(String[] str,int durTCP2ND_Index) {
		this.durTCP2ND=str[durTCP2ND_Index];
	}
	public void getTCPULRETRNUM(String[] str,int tcpULRETRNUM_Index) {
		this.tcpULRETRNUM=str[tcpULRETRNUM_Index];
	}
	public void getTCPDLRETRNUM(String[] str,int tcpDLRETRNUM_Index) {
		this.tcpDLRETRNUM=str[tcpDLRETRNUM_Index];
	}
	public void getL7TYPE(String[] str,int l7TYPE_Index) {
		this.l7TYPE=str[l7TYPE_Index];
	}
	public void getL7Delay(String[] str,int l7Delay_Index) {
		this.l7Delay=str[l7Delay_Index];
	}
	public void getMENTHOD(String[] str,int menthod_Index) {
		this.menthod=str[menthod_Index];
	}
	public void getHTTPCODE(String[] str,int httpCODE_Index) {
		this.httpCODE=str[httpCODE_Index];
	}
	public void getUSERAGENT(String[] str,int userAGENT_Index) {
		this.userAGENT=str[userAGENT_Index];
	}
	public void getAPN(String[] str,int apn_Index) {
		this.apn=str[apn_Index];
	}
	public void getIPSGSNENB(String[] str,int ipSGSNENB_Index) {
		this.ipSGSNENB=str[ipSGSNENB_Index];
	}
	public void getIPSGW(String[] str,int ipSGW_Index) {
		this.ipSGW=str[ipSGW_Index];
	}
	public void getMCC(String[] str,int mcc_Index) {
		this.mcc=str[mcc_Index];
	}
	public void getMNC(String[] str,int mnc_Index) {
		this.mnc=str[mnc_Index];
	}
	public void getCONTENTTYPE(String[] str,int contentTYPE_Index) {
		this.contentTYPE=str[contentTYPE_Index];
	}
	public void getPORTUSER(String[] str,int portUSER_Index) {
		this.portUSER=str[portUSER_Index];
	}
	public void getPORTSERV(String[] str,int portSERV_Index) {
		this.portSERV=str[portSERV_Index];
	}
	public void getHOST(String[] str,int host_Index) {
		this.host=str[host_Index];
	}
	public void getURI(String[] str,int uri_Index) {
		this.uri=str[uri_Index];
	}
	public void getULIPFRAGPACKETS(String[] str,int ulIPFRAGPACKETS_Index) {
		this.ulIPFRAGPACKETS=str[ulIPFRAGPACKETS_Index];
	}
	public void getDLIPFRAGPACKETS(String[] str,int dlIPFRAGPACKETS_Index) {
		this.dlIPFRAGPACKETS=str[dlIPFRAGPACKETS_Index];
	}
	public void getEVENTT1ST(String[] str,int eventT1ST_Index) {
		this.eventT1ST=str[eventT1ST_Index];
	}
	public void getEVENTT2ND(String[] str,int eventT2ND_Index) {
		this.eventT2ND=str[eventT2ND_Index];
	}
	public void getTCPWIN(String[] str,int tcpWIN_Index) {
		this.tcpWIN=str[tcpWIN_Index];
	}
	public void getTCPMSS(String[] str,int tcpMSS_Index) {
		this.tcpMSS=str[tcpMSS_Index];
	}
	public void getTCPSYNNUM(String[] str,int tcpSYNNUM_Index) {
		this.tcpSYNNUM=str[tcpSYNNUM_Index];
	}
	public void getTCPSTATUS(String[] str,int tcpSTATUS_Index) {
		this.tcpSTATUS=str[tcpSTATUS_Index];
	}
	public void getAPPSTATUS(String[] str,int appSTATUS_Index) {
		this.appSTATUS=str[appSTATUS_Index];
	}
	public void getTCPULDISORD(String[] str,int tcpULDISORD_Index) {
		this.tcpULDISORD=str[tcpULDISORD_Index];
	}
	public void getTCPDLDISORD(String[] str,int tcpDLDISORD_Index) {
		this.tcpDLDISORD=str[tcpDLDISORD_Index];
	}
	public void getRTTUL(String[] str,int rttUL_Index) {
		this.rttUL=str[rttUL_Index];
	}
	public void getRTTULNUM(String[] str,int rttULNUM_Index) {
		this.rttULNUM=str[rttULNUM_Index];
	}
	public void getRTTDL(String[] str,int rttDL_Index) {
		this.rttDL=str[rttDL_Index];
	}
	public void getRTTDLNUM(String[] str,int rttDLNUM_Index) {
		this.rttDLNUM=str[rttDLNUM_Index];
	}
	public void getL7UPTRANSTIME(String[] str,int l7UPTRANSTIME_Index) {
		this.l7UPTRANSTIME=str[l7UPTRANSTIME_Index];
	}
	public void getL7UPTRAFFIC(String[] str,int l7UPTRAFFIC_Index) {
		this.l7UPTRAFFIC=str[l7UPTRAFFIC_Index];
	}
	public void getL7DOWNTRANSTIME(String[] str,int l7DOWNTRANSTIME_Index) {
		this.l7DOWNTRANSTIME=str[l7DOWNTRANSTIME_Index];
	}
	public void getL7DOWNTRAFFIC(String[] str,int l7DOWNTRAFFIC_Index) {
		this.l7DOWNTRAFFIC=str[l7DOWNTRAFFIC_Index];
	}
	public void getL7TRANSTIME(String[] str,int l7TRANSTIME_Index) {
		this.l7TRANSTIME=str[l7TRANSTIME_Index];
	}
	public void getLONGGPS(String[] str,int longGPS_Index) {
		this.longGPS=str[longGPS_Index];
	}
	public void getLATIGPS(String[] str,int latiGPS_Index) {
		this.latiGPS=str[latiGPS_Index];
	}
	public void getHEIGHTGPS(String[] str,int heightGPS_Index) {
		this.heightGPS=str[heightGPS_Index];
	}
	public void getACCURGPS(String[] str,int accurGPS_Index) {
		this.accurGPS=str[accurGPS_Index];
	}
	public void getCOORDI(String[] str,int coordi_Index) {
		this.coordi=str[coordi_Index];
	}
	public void getTCPULDISOTRAF(String[] str,int tcpULDISOTRAF_Index) {
		this.tcpULDISOTRAF=str[tcpULDISOTRAF_Index];
	}
	public void getTCPDLDISOTRAF(String[] str,int tcpDLDISOTRAF_Index) {
		this.tcpDLDISOTRAF=str[tcpDLDISOTRAF_Index];
	}
	public void getTCPULRESTTRAF(String[] str,int tcpULRESTTRAF_Index) {
		this.tcpULRESTTRAF=str[tcpULRESTTRAF_Index];
	}
	public void getTCPDLRESTTRAF(String[] str,int tcpDLRESTTRAF_Index) {
		this.tcpDLRESTTRAF=str[tcpDLRESTTRAF_Index];
	}
	public void getHTTPVERS(String[] str,int httpVERS_Index) {
		this.httpVERS=str[httpVERS_Index];
	}
	public void getHTTPLASTPACKET1STREQDELAY(String[] str,int httpLASTPACKET1STREQDELAY_Index) {
		this.httpLASTPACKET1STREQDELAY=str[httpLASTPACKET1STREQDELAY_Index];
	}
	public void getHTTPLASTACKDELAY(String[] str,int httpLASTACKDELAY_Index) {
		this.httpLASTACKDELAY=str[httpLASTACKDELAY_Index];
	}
	public void getXONLINEHOST(String[] str,int xonlinehost_Index) {
		this.xonlinehost=str[xonlinehost_Index];
	}
	public void getCONTENTLENGTH(String[] str,int contentLENGTH_Index) {
		this.contentLENGTH=str[contentLENGTH_Index];
	}
	public void getBUSSBROWSER(String[] str,int bussBROWSER_Index) {
		this.bussBROWSER=str[bussBROWSER_Index];
	}
	public void getREFER(String[] str,int refer_Index) {
		this.refer=str[refer_Index];
	}
	public void getRELOCATEIONURI(String[] str,int relocateionuri_Index) {
		this.relocateionuri=str[relocateionuri_Index];
	}
	public void getWEBAPP(String[] str,int webAPP_Index) {
		this.webAPP=str[webAPP_Index];
	}
	public void getREQUESTQUERYDOMAIN(String[] str,int requestQUERYDOMAIN_Index) {
		this.requestQUERYDOMAIN=str[requestQUERYDOMAIN_Index];
	}
	public void getSGWGGSNIPAdd(String[] str,int sgwGGSNIPAdd_Index) {
		this.sgwGGSNIPAdd=str[sgwGGSNIPAdd_Index];
	}


    public void getS1uhttpDecode(String[] str){
		getUTYPE(str,S1uhttpIndex.uTYPE_Index);
		getSTARTT(str,S1uhttpIndex.startT_Index);
		getENDT(str,S1uhttpIndex.endT_Index);
		getIMSI(str,S1uhttpIndex.imsi_Index);
		getMSISDN(str,S1uhttpIndex.msisdn_Index);
		getIMEI(str,S1uhttpIndex.imei_Index);
		getRAC(str,S1uhttpIndex.rac_Index);
		getLACTAC(str,S1uhttpIndex.lacTAC_Index);
		getCGIECI(str,S1uhttpIndex.cgiECI_Index);
		getSERVNAME(str,S1uhttpIndex.servname_Index);
		getDURITION(str,S1uhttpIndex.durition_Index);
		getULTRAFF(str,S1uhttpIndex.ulTRAFF_Index);
		getDLTRAFF(str,S1uhttpIndex.dlTRAFF_Index);
		getTOTALTRAFF(str,S1uhttpIndex.totalTRAFF_Index);
		getRAT(str,S1uhttpIndex.rat_Index);
		getCDRSTAT(str,S1uhttpIndex.cdrstat_Index);
		getULDURARION(str,S1uhttpIndex.ulDURARION_Index);
		getDLDURARION(str,S1uhttpIndex.dlDURARION_Index);
		getULIPPACKET(str,S1uhttpIndex.ulIPPACKET_Index);
		getDLIPPACKET(str,S1uhttpIndex.dlIPPACKET_Index);
		getIPUSER(str,S1uhttpIndex.ipUSER_Index,S1uhttpIndex.ipUSER_Index2,S1uhttpIndex.machineIPAddtype_Index);
		getIPSERV(str,S1uhttpIndex.ipSERV_Index,S1uhttpIndex.ipSERV_Index2,S1uhttpIndex.machineIPAddtype_Index);
		getL4TYPE(str,S1uhttpIndex.l4TYPE_Index);
		getDURTCP1ST(str,S1uhttpIndex.durTCP1ST_Index);
		getDURTCP2ND(str,S1uhttpIndex.durTCP2ND_Index);
		getTCPULRETRNUM(str,S1uhttpIndex.tcpULRETRNUM_Index);
		getTCPDLRETRNUM(str,S1uhttpIndex.tcpDLRETRNUM_Index);
		getL7TYPE(str,S1uhttpIndex.l7TYPE_Index);
		getL7Delay(str,S1uhttpIndex.l7Delay_Index);
		getMENTHOD(str,S1uhttpIndex.menthod_Index);
		getHTTPCODE(str,S1uhttpIndex.httpCODE_Index);
		getUSERAGENT(str,S1uhttpIndex.userAGENT_Index);
		getAPN(str,S1uhttpIndex.apn_Index);
		getIPSGSNENB(str,S1uhttpIndex.ipSGSNENB_Index);
		getIPSGW(str,S1uhttpIndex.ipSGW_Index);
		getMCC(str,S1uhttpIndex.mcc_Index);
		getMNC(str,S1uhttpIndex.mnc_Index);
		getCONTENTTYPE(str,S1uhttpIndex.contentTYPE_Index);
		getPORTUSER(str,S1uhttpIndex.portUSER_Index);
		getPORTSERV(str,S1uhttpIndex.portSERV_Index);
		getHOST(str,S1uhttpIndex.host_Index);
		getURI(str,S1uhttpIndex.uri_Index);
		getULIPFRAGPACKETS(str,S1uhttpIndex.ulIPFRAGPACKETS_Index);
		getDLIPFRAGPACKETS(str,S1uhttpIndex.dlIPFRAGPACKETS_Index);
		getEVENTT1ST(str,S1uhttpIndex.eventT1ST_Index);
		getEVENTT2ND(str,S1uhttpIndex.eventT2ND_Index);
		getTCPWIN(str,S1uhttpIndex.tcpWIN_Index);
		getTCPMSS(str,S1uhttpIndex.tcpMSS_Index);
		getTCPSYNNUM(str,S1uhttpIndex.tcpSYNNUM_Index);
		getTCPSTATUS(str,S1uhttpIndex.tcpSTATUS_Index);
		getAPPSTATUS(str,S1uhttpIndex.appSTATUS_Index);
		getTCPULDISORD(str,S1uhttpIndex.tcpULDISORD_Index);
		getTCPDLDISORD(str,S1uhttpIndex.tcpDLDISORD_Index);
		getRTTUL(str,S1uhttpIndex.rttUL_Index);
		getRTTULNUM(str,S1uhttpIndex.rttULNUM_Index);
		getRTTDL(str,S1uhttpIndex.rttDL_Index);
		getRTTDLNUM(str,S1uhttpIndex.rttDLNUM_Index);
		getL7UPTRANSTIME(str,S1uhttpIndex.l7UPTRANSTIME_Index);
		getL7UPTRAFFIC(str,S1uhttpIndex.l7UPTRAFFIC_Index);
		getL7DOWNTRANSTIME(str,S1uhttpIndex.l7DOWNTRANSTIME_Index);
		getL7DOWNTRAFFIC(str,S1uhttpIndex.l7DOWNTRAFFIC_Index);
		getL7TRANSTIME(str,S1uhttpIndex.l7TRANSTIME_Index);
		getLONGGPS(str,S1uhttpIndex.longGPS_Index);
		getLATIGPS(str,S1uhttpIndex.latiGPS_Index);
		getHEIGHTGPS(str,S1uhttpIndex.heightGPS_Index);
		getACCURGPS(str,S1uhttpIndex.accurGPS_Index);
		getCOORDI(str,S1uhttpIndex.coordi_Index);
		getTCPULDISOTRAF(str,S1uhttpIndex.tcpULDISOTRAF_Index);
		getTCPDLDISOTRAF(str,S1uhttpIndex.tcpDLDISOTRAF_Index);
		getTCPULRESTTRAF(str,S1uhttpIndex.tcpULRESTTRAF_Index);
		getTCPDLRESTTRAF(str,S1uhttpIndex.tcpDLRESTTRAF_Index);
		getHTTPVERS(str,S1uhttpIndex.httpVERS_Index);
		getHTTPLASTPACKET1STREQDELAY(str,S1uhttpIndex.httpLASTPACKET1STREQDELAY_Index);
		getHTTPLASTACKDELAY(str,S1uhttpIndex.httpLASTACKDELAY_Index);
		getXONLINEHOST(str,S1uhttpIndex.xonlinehost_Index);
		getCONTENTLENGTH(str,S1uhttpIndex.contentLENGTH_Index);
		getBUSSBROWSER(str,S1uhttpIndex.bussBROWSER_Index);
		getREFER(str,S1uhttpIndex.refer_Index);
		getRELOCATEIONURI(str,S1uhttpIndex.relocateionuri_Index);
		getWEBAPP(str,S1uhttpIndex.webAPP_Index);
		getREQUESTQUERYDOMAIN(str,S1uhttpIndex.requestQUERYDOMAIN_Index);
		getSGWGGSNIPAdd(str,S1uhttpIndex.sgwGGSNIPAdd_Index);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(uTYPE).append("|")
				.append(uTYPE).append("|")
				.append(startT).append("|")
				.append(endT).append("|")
				.append(imsi).append("|")
				.append(msisdn).append("|")
				.append(imei).append("|")
				.append(rac).append("|")
				.append(lacTAC).append("|")
				.append(cgiECI).append("|")
				.append(servname).append("|")
				.append(durition).append("|")
				.append(ulTRAFF).append("|")
				.append(dlTRAFF).append("|")
				.append(totalTRAFF).append("|")
				.append(rat).append("|")
				.append(cdrstat).append("|")
				.append(ulDURARION).append("|")
				.append(dlDURARION).append("|")
				.append(ulIPPACKET).append("|")
				.append(dlIPPACKET).append("|")
				.append(ipUSER).append("|")
				.append(ipSERV).append("|")
				.append(l4TYPE).append("|")
				.append(durTCP1ST).append("|")
				.append(durTCP2ND).append("|")
				.append(tcpULRETRNUM).append("|")
				.append(tcpDLRETRNUM).append("|")
				.append(l7TYPE).append("|")
				.append(l7Delay).append("|")
				.append(menthod).append("|")
				.append(httpCODE).append("|")
				.append(userAGENT).append("|")
				.append(apn).append("|")
				.append(ipSGSNENB).append("|")
				.append(ipSGW).append("|")
				.append(mcc).append("|")
				.append(mnc).append("|")
				.append(contentTYPE).append("|")
				.append(portUSER).append("|")
				.append(portSERV).append("|")
				.append(host).append("|")
				.append(uri).append("|")
				.append(ulIPFRAGPACKETS).append("|")
				.append(dlIPFRAGPACKETS).append("|")
				.append(eventT1ST).append("|")
				.append(eventT2ND).append("|")
				.append(tcpWIN).append("|")
				.append(tcpMSS).append("|")
				.append(tcpSYNNUM).append("|")
				.append(tcpSTATUS).append("|")
				.append(appSTATUS).append("|")
				.append(tcpULDISORD).append("|")
				.append(tcpDLDISORD).append("|")
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
				.append(tcpULDISOTRAF).append("|")
				.append(tcpDLDISOTRAF).append("|")
				.append(tcpULRESTTRAF).append("|")
				.append(tcpDLRESTTRAF).append("|")
				.append(httpVERS).append("|")
				.append(httpLASTPACKET1STREQDELAY).append("|")
				.append(httpLASTACKDELAY).append("|")
				.append(xonlinehost).append("|")
				.append(contentLENGTH).append("|")
				.append(bussBROWSER).append("|")
				.append(refer).append("|")
				.append(relocateionuri).append("|")
				.append(webAPP).append("|")
				.append(requestQUERYDOMAIN);
        return result.toString();
    }
}
