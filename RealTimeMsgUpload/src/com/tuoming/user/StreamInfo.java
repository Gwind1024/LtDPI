package com.tuoming.user;

import com.tuoming.utils.CommonUtils;
import com.tuoming.utils.SimpleDateDeal;

import java.util.Map;

public class StreamInfo extends UserInfo {
    public StreamInfo(Map<String,Integer> flowMap){
        super();
        super.flowMap = flowMap;
    }
    @Override
    public void Init(String str) {
        String[] split = str.split("\\|", -1);
        if(split.length < 140){
            System.err.println("Stream话单错误!str:"+str);
            return;
        }
        Time = CommonUtils.strToLong(split[S1uStreamIndex.startT_Index]);
        String msisdn = split[S1uStreamIndex.msisdn_Index];
        if(msisdn.startsWith("86")){
            msisdn = msisdn.substring(2);
        }
        MSISDN = msisdn;
        IMEI = split[S1uStreamIndex.imei_Index];
        IMSI = split[S1uStreamIndex.imsi_Index];
        RAT = CommonUtils.strToInteger(split[S1uStreamIndex.rat_Index]);
        LACorTAC = (short) (int)CommonUtils.strToShort(split[S1uStreamIndex.lacTAC_Index]);
        CIorECI = CommonUtils.strToInteger(split[S1uStreamIndex.cgiECI_Index]);
        String apn = split[S1uStreamIndex.apn_Index];
        APN = apn.length()>16?apn.substring(0,16):apn;
        String uri = split[S1uStreamIndex.host_Index];
        if("".equals(uri)){
            uri = split[S1uStreamIndex.uri_Index];
        }
        URLorHOST = uri.length()>128?uri.substring(0,128):uri;
//        Longitude = CommonUtils.strToDouble(split[S1uStreamIndex.longGPS_Index]);
//        Latitude = CommonUtils.strToDouble(split[S1uStreamIndex.latiGPS_Index]);
        appType = split[S1uStreamIndex.APPTYPE_Index];
        appSubType = split[S1uStreamIndex.servname_Index];
        String mobileType = SimpleDateDeal.leftSupply0(appType,2)+"_"+SimpleDateDeal.leftSupply0(appSubType,4);
        FlowType = flowMap.get(mobileType)==null?-1:flowMap.get(mobileType);
//        FlowType = CommonUtils.strToInteger(split[S1uStreamIndex.servname_Index]);
        gNBIP = split[S1uStreamIndex.eNBSGSNIPAdd_Index];
    }
}
