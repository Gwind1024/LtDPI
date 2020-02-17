package com.tuoming.user;

import com.tuoming.utils.CommonUtils;
import com.tuoming.utils.SimpleDateDeal;

import java.util.Map;

public class GenInfo extends UserInfo {
    public GenInfo(Map<String,Integer> flowMap){
        super();
        super.flowMap = flowMap;
    }
    @Override
    public void Init(String str) {
        String[] split = str.split("\\|", -1);
        if(split.length < 93){
            System.err.println("Gen话单错误!str:"+str);
            return;
        }
        Time = CommonUtils.strToLong(split[S1uGenIndex.startT_Index]);
        String msisdn = split[S1uGenIndex.msisdn_Index];
        if(msisdn.startsWith("86")){
            msisdn = msisdn.substring(2);
        }
        MSISDN = msisdn;
        IMEI = split[S1uGenIndex.imei_Index];
        IMSI = split[S1uGenIndex.imsi_Index];
        RAT = CommonUtils.strToInteger(split[S1uGenIndex.rat_Index]);
        LACorTAC = (short) (int)CommonUtils.strToShort(split[S1uGenIndex.lacTAC_Index]);
        CIorECI = CommonUtils.strToInteger(split[S1uGenIndex.cgiECI_Index]);
        String apn = split[S1uGenIndex.apn_Index];
        APN = apn.length()>16?apn.substring(0,16):apn;
//        String uri = split[S1uGenIndex.uri_Index];
//        URLorHOST = uri.length()>128?uri.substring(0,128):uri;
//        Longitude = CommonUtils.strToDouble(split[S1uGenIndex.longGPS_Index]);
//        Latitude = CommonUtils.strToDouble(split[S1uGenIndex.latiGPS_Index]);
        appType = split[S1uGenIndex.APPTYPE_Index];
        appSubType = split[S1uGenIndex.servname_Index];
        String mobileType = SimpleDateDeal.leftSupply0(appType,2)+"_"+SimpleDateDeal.leftSupply0(appSubType,4);
        FlowType = flowMap.get(mobileType)==null?-1:flowMap.get(mobileType);
//        FlowType = CommonUtils.strToInteger(split[S1uGenIndex.servname_Index]);
        gNBIP = split[S1uGenIndex.eNBSGSNIPAdd_Index];

        protocolType = CommonUtils.strToInteger(split[S1uGenIndex.ProtocolType]);

        ipServer1 = split[S1uGenIndex.ipSERV_Index];
        ipServer2 = split[S1uGenIndex.ipSERV_Index2];
    }
}
