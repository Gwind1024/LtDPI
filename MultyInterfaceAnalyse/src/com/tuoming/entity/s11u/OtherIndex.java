package com.tuoming.entity.s11u;

public interface OtherIndex {
    /**
     * 输入样例数据
     * 352|075|6|5c9cab73347c3579|6|460016270027662|8624000402177200|8613006265602|6|2019-03-28 19:09:39.9199640|2019-03-28 19:09:39.9613370
     * |0|2001|||10.190.238.3|10.191.88.17|3868|3868|epc.mnc001.mcc460.3gppnetwork.org|epc.mnc001.mcc460.3gppnetwork.org
     * |mmec44.mmegi8d01.mme.epc.mnc001.mcc460.3gppnetwork.org|hss02fe01.nc.jx.hss.epc.mnc001.mcc460.3gppnetwork.org|16777251||
     *
     */
	int startT_Index=25;//
	int endT_Index=26;//
	int imsi_Index=9;//
	int msisdn_Index=11;//
	int imei_Index=10;//
	int rac_Index=0000;//
	int lacTAC_Index=21;//
	int cgiECI_Index=22;//
	int servname_Index=33;//
	int durition_Index=0000;//
	int ulTRAFF_Index=44;//
	int dlTRAFF_Index=45;//
	int totalTRAFF_Index=0000;//
	int rat_Index=8;//
	int cdrstat_Index=35;//
	int ulDURARION_Index=48;//
	int dlDURARION_Index=49;//
	int ulIPPACKET_Index=46;//
	int dlIPPACKET_Index=47;//
	int upTCPRETRANSNUM_Index=52;//
	int downTCPRETRANSNUM_Index=53;//
	int ipUSER_Index=37;//
	int ipUSER_Index2=38;//
	int ipSERV_Index=41;//
	int ipSERV_Index2=42;//
	int l4TYPE_Index=40;//
	int durTCP2ST_Index=54;//
	int durTCP3ND_Index=55;//
	int tcpULRETRNUM_Index=52;//
	int tcpDLRETRNUM_Index=53;//
	int l7TYPE_Index=31;//
	int l7Delay_Index=59;//
	
	
}
