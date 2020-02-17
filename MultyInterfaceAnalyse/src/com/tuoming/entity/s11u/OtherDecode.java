package com.tuoming.entity.s11u;

import com.tuoming.common.CommonDecode;
import com.tuoming.entity.common.typeDecode;
import com.tuoming.tools.SimpleDateDeal;

public class OtherDecode extends CommonDecode {

	public String startT="";//
	public String endT="";//
	public String imsi="";//
	public String msisdn="";//
	public String imei="";//
	public String rac="";//
	public String lacTAC="";//
	public String cgiECI="";//
	public String servname="";//
	public String durition="";//
	public String ulTRAFF="";//
	public String dlTRAFF="";//
	public String totalTRAFF="";//
	public String rat="";//
	public String cdrstat="";//
	public String ulDURARION="";//
	public String dlDURARION="";//
	public String ulIPPACKET="";//
	public String dlIPPACKET="";//
	public String upTCPRETRANSNUM="";//
	public String downTCPRETRANSNUM="";//
	public String ipUSER="";//
	public String ipSERV="";//
	public String l4TYPE="";//
	public String durTCP2ST="";//
	public String durTCP3ND="";//
	public String tcpULRETRNUM="";//
	public String tcpDLRETRNUM="";//
	public String l7TYPE="";//
	public String l7Delay="";//

	public String machineIP="";//机器IP 拼表名用


	public void getSTARTT(String[] str,int startT_Index) {
    	if(!str[startT_Index].equals(""))
		this.startT= SimpleDateDeal.getFormatDate(str[startT_Index]);
	}
	public void getENDT(String[] str,int endT_Index) {
		if(!str[endT_Index].equals(""))
		this.endT=SimpleDateDeal.getFormatDate(str[endT_Index]);
		super.endTime = this.endT;
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
		this.rac=""	;
	}
	public void getLACTAC(String[] str,int lacTAC_Index) {
		this.lacTAC=str[lacTAC_Index];
	}
	public void getCGIECI(String[] str,int cgiECI_Index) {
		this.cgiECI=str[cgiECI_Index];
	}
	public void getSERVNAME(String[] str,int servname_Index) {
		this.servname= typeDecode.SERVNAME(str[32] +"_" +str[servname_Index]) ;
	}
	public void getDURITION(String[] str,int durition_Index) {
		this.durition=SimpleDateDeal.diff(str[26],str[25]);
	}
	public void getULTRAFF(String[] str,int ulTRAFF_Index) {
		this.ulTRAFF=str[ulTRAFF_Index];
	}
	public void getDLTRAFF(String[] str,int dlTRAFF_Index) {
		this.dlTRAFF=str[dlTRAFF_Index];
	}
	public void getTOTALTRAFF(String[] str,int totalTRAFF_Index) {
		this.totalTRAFF=SimpleDateDeal.sum(str[44],str[45]);
	}
	public void getRAT(String[] str,int rat_Index) {
		this.rat="7";
	}
	public void getCDRSTAT(String[] str,int cdrstat_Index) {
		switch (str[cdrstat_Index])
		{
			case "0": this.cdrstat = "0"; break;
			case "1" : case "2": case "3": this.cdrstat = "3"; break;
			case "6": case "7": this.cdrstat = "2"; break;
			case "4": case "5": this.cdrstat = ""; break;
			default:cdrstat = ""; break;
		}
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
	public void getUPTCPRETRANSNUM(String[] str,int upTCPRETRANSNUM_Index) {
		this.upTCPRETRANSNUM=str[upTCPRETRANSNUM_Index];
	}
	public void getDOWNTCPRETRANSNUM(String[] str,int downTCPRETRANSNUM_Index) {
		this.downTCPRETRANSNUM=str[downTCPRETRANSNUM_Index];
	}
	public void getIPUSER(String[] str,int ipUSER_Index,int ipUSER_Index2,int sgwIP_Index) {
		if(str[ipUSER_Index].equals(""))this.ipUSER=str[ipUSER_Index2];
		else this.ipUSER=str[ipUSER_Index];
	}
	public void getIPSERV(String[] str,int ipSERV_Index,int ipSERV_Index2,int sgwIP_Index) {
		if(str[ipSERV_Index].equals(""))this.ipSERV=str[ipSERV_Index2];
		else this.ipSERV=str[ipSERV_Index];
	}
	public void getL4TYPE(String[] str,int l4TYPE_Index) {
		this.l4TYPE=str[l4TYPE_Index];
	}
	public void getDURTCP1ST(String[] str,int durTCP2ST_Index) {
		this.durTCP2ST=str[durTCP2ST_Index];
	}
	public void getDURTCP2ND(String[] str,int durTCP3ND_Index) {
		this.durTCP3ND=str[durTCP3ND_Index];
	}
	public void getTCPULRETRNUM(String[] str,int tcpULRETRNUM_Index) {
		this.tcpULRETRNUM=str[tcpULRETRNUM_Index];
	}
	public void getTCPDLRETRNUM(String[] str,int tcpDLRETRNUM_Index) {
		this.tcpDLRETRNUM=str[tcpDLRETRNUM_Index];
	}
	public void getL7TYPE(String[] str,int l7TYPE_Index) {
		this.l7TYPE="999";
	}
	public void getL7Delay(String[] str,int l7Delay_Index) {
		this.l7Delay=str[l7Delay_Index];
	}
	public String getEndTime() {
		return endTime;
	}
	public String getMmeIp() {
		return machineIP;
	}

    public void decode(String[] str){
		getSTARTT(str,MqttIndex.startT_Index);
		getENDT(str,MqttIndex.endT_Index);
		getIMSI(str,MqttIndex.imsi_Index);
		getMSISDN(str,MqttIndex.msisdn_Index);
		getIMEI(str,MqttIndex.imei_Index);
		getRAC(str,MqttIndex.rac_Index);
		getLACTAC(str,MqttIndex.lacTAC_Index);
		getCGIECI(str,MqttIndex.cgiECI_Index);
		getSERVNAME(str,MqttIndex.servname_Index);
		getDURITION(str,MqttIndex.durition_Index);
		getULTRAFF(str,MqttIndex.ulTRAFF_Index);
		getDLTRAFF(str,MqttIndex.dlTRAFF_Index);
		getTOTALTRAFF(str,MqttIndex.totalTRAFF_Index);
		getRAT(str,MqttIndex.rat_Index);
		getCDRSTAT(str,MqttIndex.cdrstat_Index);
		getULDURARION(str,MqttIndex.ulDURARION_Index);
		getDLDURARION(str,MqttIndex.dlDURARION_Index);
		getULIPPACKET(str,MqttIndex.ulIPPACKET_Index);
		getDLIPPACKET(str,MqttIndex.dlIPPACKET_Index);
		getUPTCPRETRANSNUM(str,MqttIndex.upTCPRETRANSNUM_Index);
		getDOWNTCPRETRANSNUM(str,MqttIndex.downTCPRETRANSNUM_Index);
		getIPUSER(str,MqttIndex.ipUSER_Index , MqttIndex.ipUSER_Index2, MqttIndex.sgwIP_Index); // ?
		getIPSERV(str,MqttIndex.ipSERV_Index , MqttIndex.ipSERV_Index2, MqttIndex.sgwIP_Index); //?
		getL4TYPE(str,MqttIndex.l4TYPE_Index);
		getDURTCP1ST(str,MqttIndex.durTCP2ST_Index);
		getDURTCP2ND(str,MqttIndex.durTCP3ND_Index);
		getTCPULRETRNUM(str,MqttIndex.tcpULRETRNUM_Index);
		getTCPDLRETRNUM(str,MqttIndex.tcpDLRETRNUM_Index);
		getL7TYPE(str,MqttIndex.l7TYPE_Index);
		getL7Delay(str,MqttIndex.l7Delay_Index);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(startT).append("|")
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
			.append(upTCPRETRANSNUM).append("|")
			.append(downTCPRETRANSNUM).append("|")
			.append(ipUSER).append("|")
			.append(ipSERV).append("|")
			.append(l4TYPE).append("|")
			.append(durTCP2ST).append("|")
			.append(durTCP3ND).append("|")
			.append(tcpULRETRNUM).append("|")
			.append(tcpDLRETRNUM).append("|")
			.append(l7TYPE).append("|")
			.append(l7Delay);
        return result.toString();
    }
}
