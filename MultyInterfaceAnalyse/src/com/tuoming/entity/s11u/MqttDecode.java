package com.tuoming.entity.s11u;

import com.tuoming.common.CommonDecode;
import com.tuoming.common.RedisUntil;
import com.tuoming.entity.common.typeDecode;
import com.tuoming.tools.SimpleDateDeal;

public class MqttDecode extends CommonDecode {

	public String startT="";//
	public String endTime="";//
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

    public String version="";//版本
    public String clientRole="";//客户端角色
    public String clientID="";//客户端ID
    public String userName="";//用户名
    public String password="";//密码
    public String keepAlive="";//保持连接
    public String dupFlag="";//DUP Flag
    public String qos="";//Qos
    public String protocolName="";//协议名
    public String topicName="";//主题名
    public String topic2Name="";//主题名2
    public String topic3Name="";//主题名3
    public String messageAction="";//消息类型
    public String msgActionAck="";//消息响应
    public String messageID="";//消息ID
    public String repeatcount="";//重复计数
    public String msgActionTime="";//消息时间
    public String msgActionAckTime="";//消息时间确认
    public String pubreceivedTime="";//收到时间
    public String pubreleaseTime="";//释放时间
    public String pubCompleteTime="";//完成时间
    public String status="";//状态
    public String result="";//结果
    public String retransULCount="";//上行重传次数
    public String retransDLCount="";//下行重传次数
    public String direction="";//业务数据方向

	public String machineIP="";//机器IP 拼表名用


	public void getSTARTT(String[] str,int startT_Index) {
		this.startT= SimpleDateDeal.getFormatDate(str[startT_Index]);
	}
	public void getENDT(String[] str,int endT_Index) {
		this.endTime=SimpleDateDeal.getFormatDate(str[endT_Index]);
        super.endTime=SimpleDateDeal.getFormatDate(str[endT_Index]);
	}
	public void getIMSI(String[] str,int imsi_Index) {
		this.imsi=str[imsi_Index];
	}
	public void getMSISDN(String[] str,int msisdn_Index) {
		this.msisdn=SimpleDateDeal.Except86(str[msisdn_Index]);
	}
	public void getIMEI(String[] str,int imei_Index) {
		this.imei=str[imei_Index];
	}
	public void getRAC(String[] str,int rac_Index) {
		this.rac="";
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
		this.l7TYPE="1001";
	}
	public void getL7Delay(String[] str,int l7Delay_Index) {
		this.l7Delay=str[l7Delay_Index];
	}

	public void getVersion(String[] str, int version_Index) {
		this.version = str[version_Index];
	}
	
	public void getClientRole(String[] str, int clientRole_Index) {
		this.clientRole = str[clientRole_Index];
	}
	
	public void getClientID(String[] str, int clientID_Index) {
		this.clientID = str[clientID_Index];
	}
	
	public void getUserName(String[] str, int userName_Index) {
		this.userName = str[userName_Index];
	}
	
	public void getPassword(String[] str, int password_Index) {
		this.password = str[password_Index];
	}
	
	public void getKeepAlive(String[] str, int keepAlive_Index) {
		this.keepAlive = str[keepAlive_Index];
	}
	
	public void getDUPFlag(String[] str, int dupFlag_Index) {
		this.dupFlag = str[dupFlag_Index];
	}
	
	public void getQos(String[] str, int qos_Index) {
		this.qos = str[qos_Index];
	}
	
	public void getProtocolName(String[] str, int protocolName_Index) {
		this.protocolName = str[protocolName_Index];
	}
	
	public void getTopicName(String[] str, int topicName_Index) {
		this.topicName = str[topicName_Index];
	}
	
	public void getTopic2Name(String[] str, int topic2Name_Index) {
		this.topic2Name = str[topic2Name_Index];
	}
	
	public void getTopic3Name(String[] str, int topic3Name_Index) {
		this.topic3Name = str[topic3Name_Index];
	}
	
	public void getMessageAction(String[] str, int messageAction_Index) {
		this.messageAction = str[messageAction_Index];
	}
	
	public void getMsgActionAck(String[] str, int msgActionAck_Index) {
		this.msgActionAck = str[msgActionAck_Index];
	}
	
	public void getMessageID(String[] str, int messageID_Index) {
		this.messageID = str[messageID_Index];
	}
	
	public void getrepeatcount(String[] str, int repeatcount_Index) {
		this.repeatcount = str[repeatcount_Index];
	}
	
	public void getMsgActionTime(String[] str, int msgActionTime_Index) {
		this.msgActionTime = SimpleDateDeal.getFormatDate(str[msgActionTime_Index]);
	}
	
	public void getMsgActionAckTime(String[] str, int msgActionAckTime_Index) {
		this.msgActionAckTime = SimpleDateDeal.getFormatDate(str[msgActionAckTime_Index]);
	}
	
	public void getPubreceivedTime(String[] str, int pubreceivedTime_Index) {
		this.pubreceivedTime = str[pubreceivedTime_Index];
	}
	
	public void getPubreleaseTime(String[] str, int pubreleaseTime_Index) {
		this.pubreleaseTime = str[pubreleaseTime_Index];
	}
	
	public void getPubCompleteTime(String[] str, int pubCompleteTime_Index) {
		this.pubCompleteTime = str[pubCompleteTime_Index];
	}
	
	public void getStatus(String[] str, int status_Index) {
		this.status = str[status_Index];
	}
	
	public void getResult(String[] str, int result_Index) {
		this.result = str[result_Index];
	}
	
	public void getRetransULCount(String[] str, int retransULCount_Index) {
		this.retransULCount = str[retransULCount_Index];
	}
	
	public void getRetransDLCount(String[] str, int retransDLCount_Index) {
		this.retransDLCount = str[retransDLCount_Index];
	}
	
	public void getDirection(String[] str, int direction_Index) {
		this.direction = str[direction_Index];
		if(this.direction.equals("65535"))
		this.direction = "";
	}

	public void getMachineIP(String[] str, int machineIP_Index) {
		this.machineIP = str[machineIP_Index];
        super.machineIP = str[machineIP_Index];
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
		getVersion(str,MqttIndex.version_Index);
		getClientRole(str,MqttIndex.clientRole_Index);
		getClientID(str,MqttIndex.clientID_Index);
		getUserName(str,MqttIndex.userName_Index);
		getPassword(str,MqttIndex.password_Index);
		getKeepAlive(str,MqttIndex.keepAlive_Index);
		getDUPFlag(str,MqttIndex.dupFlag_Index);
		getQos(str,MqttIndex.qos_Index);
		getProtocolName(str,MqttIndex.protocolName_Index);
		getTopicName(str,MqttIndex.topicName_Index);
		getTopic2Name(str,MqttIndex.topic2Name_Index);
		getTopic3Name(str,MqttIndex.topic3Name_Index);
		getMessageAction(str,MqttIndex.messageAction_Index);
		getMsgActionAck(str,MqttIndex.msgActionAck_Index);
		getMessageID(str,MqttIndex.messageID_Index);
		getrepeatcount(str,MqttIndex.repeatcount_Index);
		getMsgActionTime(str,MqttIndex.msgActionTime_Index);
		getMsgActionAckTime(str,MqttIndex.msgActionAckTime_Index);
		getPubreceivedTime(str,MqttIndex.pubreceivedTime_Index);
		getPubreleaseTime(str,MqttIndex.pubreleaseTime_Index);
		getPubCompleteTime(str,MqttIndex.pubCompleteTime_Index);
		getStatus(str,MqttIndex.status_Index);
		getResult(str,MqttIndex.result_Index);
		getRetransULCount(str,MqttIndex.retransULCount_Index);
		getRetransDLCount(str,MqttIndex.retransDLCount_Index);
		getDirection(str,MqttIndex.direction_Index);
		getMachineIP(str,MqttIndex.machineIP_Index);
    }

    @Override
    public String toString() {
        StringBuilder result1 = new StringBuilder();
        result1.append(startT).append("|")
			.append(endTime).append("|")
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
			.append(l7Delay).append("|")
			.append(version).append("|")
			.append(clientRole).append("|")
			.append(clientID).append("|")
			.append(userName).append("|")
			.append(password).append("|")
			.append(keepAlive).append("|")
			.append(dupFlag).append("|")
			.append(qos).append("|")
			.append(protocolName).append("|")
			.append(topicName).append("|")
			.append(topic2Name).append("|")
			.append(topic3Name).append("|")
			.append(messageAction).append("|")
			.append(msgActionAck).append("|")
			.append(messageID).append("|")
			.append(repeatcount).append("|")
			.append(msgActionTime).append("|")
			.append(msgActionAckTime).append("|")
			.append(pubreceivedTime).append("|")
			.append(pubreleaseTime).append("|")
			.append(pubCompleteTime).append("|")
			.append(status).append("|")
			.append(result).append("|")
			.append(retransULCount).append("|")
			.append(retransDLCount).append("|")
			.append(direction);
        return result1.toString();
    }
}
