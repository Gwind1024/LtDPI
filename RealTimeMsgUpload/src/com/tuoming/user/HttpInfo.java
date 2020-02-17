package com.tuoming.user;

import com.tuoming.utils.CommonUtils;
import com.tuoming.utils.SimpleDateDeal;

import java.util.HashMap;
import java.util.Map;

public class HttpInfo extends UserInfo {

    public HttpInfo(Map<String,Integer> flowMap){
        super();
        super.flowMap = flowMap;
    }
    @Override
    public void Init(String str) {
        String[] split = str.split("\\|", -1);
        if(split.length < 127){
            System.err.println("HTTP话单错误!Http:"+str);
            return;
        }
        Time = CommonUtils.strToLong(split[S1uHttpIndex.startT_Index]);
        String msisdn = split[S1uHttpIndex.msisdn_Index];
        if(msisdn.startsWith("86")){
            msisdn = msisdn.substring(2);
        }
        MSISDN = msisdn;
        IMEI = split[S1uHttpIndex.imei_Index];
        IMSI = split[S1uHttpIndex.imsi_Index];
        RAT = CommonUtils.strToInteger(split[S1uHttpIndex.rat_Index]);
        LACorTAC = (short) (int)CommonUtils.strToInteger(split[S1uHttpIndex.lacTAC_Index]);
        CIorECI = CommonUtils.strToInteger(split[S1uHttpIndex.cgiECI_Index]);
        String apn = split[S1uHttpIndex.apn_Index];
        APN = apn.length()>16?apn.substring(0,16):apn;
        String uri = split[S1uHttpIndex.uri_Index];
        URLorHOST = uri.length()>128?uri.substring(0,128):uri;
        Longitude = CommonUtils.strToDouble(split[S1uHttpIndex.longGPS_Index]);
        Latitude = CommonUtils.strToDouble(split[S1uHttpIndex.latiGPS_Index]);
        appType = split[S1uHttpIndex.APPTYPE_Index];
        appSubType = split[S1uHttpIndex.servname_Index];
        String mobileType = SimpleDateDeal.leftSupply0(appType,2)+"_"+SimpleDateDeal.leftSupply0(appSubType,4);
        FlowType = flowMap.get(mobileType)==null?-1:flowMap.get(mobileType);
        gNBIP = split[S1uHttpIndex.eNBSGSNIPAdd_Index];

        transactionID = CommonUtils.strToInteger(split[S1uHttpIndex.menthod_Index]);

    }
}
