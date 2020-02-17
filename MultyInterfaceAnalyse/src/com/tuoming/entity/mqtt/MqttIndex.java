package com.tuoming.entity.mqtt;

public interface MqttIndex {
    /**
     * 输入样例数据
     * 352|075|6|5c9cab73347c3579|6|460016270027662|8624000402177200|8613006265602|6|2019-03-28 19:09:39.9199640|2019-03-28 19:09:39.9613370
     * |0|2001|||10.190.238.3|10.191.88.17|3868|3868|epc.mnc001.mcc460.3gppnetwork.org|epc.mnc001.mcc460.3gppnetwork.org
     * |mmec44.mmegi8d01.mme.epc.mnc001.mcc460.3gppnetwork.org|hss02fe01.nc.jx.hss.epc.mnc001.mcc460.3gppnetwork.org|16777251||
     *
     */
	int startT_Index=25;//
	int endT_Index=26;//
	int imsi_Index=10;//
	int msisdn_Index=12;//
	int imei_Index=11;//
	int rac_Index=0000;//
	int lacTAC_Index=22;//
	int cgiECI_Index=23;//
	int servname_Index=34;//
	int durition_Index=0000;//
	int ulTRAFF_Index=45;//
	int dlTRAFF_Index=46;//
	int totalTRAFF_Index=0000;//
	int rat_Index=9;//
	int cdrstat_Index=36;//
	int ulDURARION_Index=49;//
	int dlDURARION_Index=50;//
	int ulIPPACKET_Index=47;//
	int dlIPPACKET_Index=48;//
	int upTCPRETRANSNUM_Index=53;//
	int downTCPRETRANSNUM_Index=54;//
	int ipUSER_Index=38;//
	int ipUSER_Index2=39;//
	int ipSERV_Index=42;//
	int ipSERV_Index2=43;//
	int l4TYPE_Index=41;//
	int durTCP2ST_Index=55;//
	int durTCP3ND_Index=56;//
	int tcpULRETRNUM_Index=53;//
	int tcpDLRETRNUM_Index=54;//
	int l7TYPE_Index=32;//
	int l7Delay_Index=60;//
	
	int version_Index=91;//版本
	int clientRole_Index=92;//客户端角色
	int clientID_Index=96;//客户端ID
	int userName_Index=93;//用户名
	int password_Index=94;//密码
	int keepAlive_Index=100;//保持连接
	int dupFlag_Index=103;//DUP Flag
	int qos_Index=102;//Qos
	int protocolName_Index=95;//协议名
	int topicName_Index=105;//主题名
	int topic2Name_Index=108;//主题名2
	int topic3Name_Index=111;//主题名3
	int messageAction_Index=81;//消息类型
	int msgActionAck_Index=82;//消息响应
	int messageID_Index=83;//4消息ID
	int repeatcount_Index=84;//重复计数
	int msgActionTime_Index=85;//消息时间
	int msgActionAckTime_Index=86;//消息时间确认
	int pubreceivedTime_Index=87;//收到时间
	int pubreleaseTime_Index=88;//释放时间
	int pubCompleteTime_Index=89;//完成时间
	int status_Index=90;//状态
	int result_Index=97;//结果
	int retransULCount_Index=53;//上行重传次数
	int retransDLCount_Index=54;//下行重传次数
	int direction_Index=119;//业务数据方向

	int machineIP_Index=13;//机器IP,拼接生成文件名用的
	int sgwIP_Index=37;//sgwIP 用来判断ipv4 =0x01   ipv6=0x02
}
