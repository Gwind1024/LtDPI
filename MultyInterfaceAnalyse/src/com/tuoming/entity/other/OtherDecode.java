package com.tuoming.entity.other;


import com.tuoming.common.CommonDecode;
import com.tuoming.entity.common.commonDecode;
import com.tuoming.entity.streaming.StreamingIndex;
import com.tuoming.tools.SimpleDateDeal;

import java.io.Serializable;
import java.math.BigDecimal;

public class OtherDecode extends CommonDecode implements Serializable {
    private String utype ;
    private String startT ;
    private String endT ;
    private String imsi ;
    private String msisdn ;
    private String imei ;
    private String rac ;
    private String lacTac ;
    private String cgiEci ;
    private String servname ;
    private String durition ;
    private String ulDurarion ;
    private String dlDurarion ;
    private String ulTraff ;
    private String dlTraff ;
    private String totalTraff ;
    private String ulIpPacket ;
    private String dlIpPacket ;
    private String rat ;
    private String ipUser ;
    private String ipServ ;
    private String l4Type ;
    private String durTcp1st ;
    private String durTcp2nd ;
    private String tcpUlretrNum ;
    private String tcpDlretrNum ;
    private String l7Type ;
    private String l7Delay ;
    private String statusCode ;
    private String userAgent ;
    private String apn ;
    private String host ;
    private String feature ;
    private String ipSgsnEnb ;
    private String ipSgw ;
    private String mcc ;
    private String mnc ;
    private String contentType ;
    private String portUser ;
    private String portServ ;
    private String ulIpFragPackets ;
    private String dlIpFragPackets ;
    private String eventT1st ;
    private String eventT2nd ;
    private String tcpWin ;
    private String tcpMss ;
    private String tcpSynnum ;
    private String tcpStatus ;
    private String appStatus ;
    private String tcpUldisord ;
    private String tcpDldisord ;
    private String rttUl ;
    private String rttUlnum ;
    private String rttDl ;
    private String rttDlnum ;
    private String l7Uptranstime ;
    private String l7Uptraffic ;
    private String l7Downtranstime ;
    private String l7Downtraffic ;
    private String l7Transtime ;
    private String longGps ;
    private String latiGps ;
    private String heightGps ;
    private String accurGps ;
    private String coordi ;
    private String tcpUldisotraf ;
    private String tcpDldisotraf ;
    private String tcpUlresttraf ;
    private String tcpDlresttraf ;
    private String webApp ;
    private String vendorSertype;

    private String protocolType ;



    private String procedureEndtime;

    private String machineIpAddType;

    private String sgwGgsnIpAdd;

    private final int S1UGEN = 94;
    //    private final int S1UDNS = 63;
//    private final int S1UHTTP = 127;
    private final int S1UMMS = 77;
    private final int S1UFTP = 70;
    private final int S1UVOIP = 66;
    private final int S1UEMAIL = 68;
    private final int S1URTSP = 69;
    //    private final int S1UIM = 98;
    private final int S1UP2P = 60;
//    private final int S1USTREAMING = 140;

//    public String getProcedureEndtime() {
//        return procedureEndtime;
//    }


    @Override
    public String getEndTime() {
        return endT;
    }

    @Override
    public String getMmeIp() {
        return ipSgsnEnb;
    }

    public String getProcedureEndtime(){
        return SimpleDateDeal.getFormatDate(procedureEndtime);
    }

    public String getMmeIpAdd() {
        return sgwGgsnIpAdd;
    }

    public static String getProtocolTypeFromArr(String[] arr){
        return arr[21];
    }


    public void decode(String[] arr) {
        int arrLength = arr.length;
        if (arr.length >= OtherIndex.size) {
            utype = "4";
            startT = SimpleDateDeal.getFormatDate(arr[OtherIndex.startT]);
            endT = SimpleDateDeal.getFormatDate(arr[OtherIndex.endT]);
            String imsiTemp = arr[OtherIndex.imsi];
            if (imsiTemp.startsWith("+86")) {
                imsi = imsiTemp.substring(3);
            } else if (imsiTemp.startsWith("86")) {
                imsi = imsiTemp.substring(2);
            } else if (imsiTemp.startsWith("0086")) {
                imsi = imsiTemp.substring(4);
            } else {
                imsi = imsiTemp;
            }
            msisdn = arr[OtherIndex.msisdn];
            imei = arr[OtherIndex.imei];
            rac = "";
            lacTac = arr[OtherIndex.lacTac];
            lacTac = lacTac.length()>=5?lacTac:"00000".substring(0,5-lacTac.length())+lacTac;
            cgiEci = arr[OtherIndex.cgiEci];
            servname = commonDecode.SERVNAME(arr[32]+"_"+arr[33]);
            try {
                String procedureStartTime = arr[OtherIndex.procedureStartTime];
                procedureEndtime = arr[OtherIndex.procedureEndTime];
                long duritionTemp = Long.parseLong(procedureEndtime) - Long.parseLong(procedureStartTime);
                durition = duritionTemp > 0 ? duritionTemp + "" : "";
            } catch (NumberFormatException e) {
                durition = "";
            }
            switch (arrLength){
                case S1UEMAIL:
                    ulDurarion = arr[65];
                    dlDurarion = arr[66];
                    break;
                case S1UFTP:
                    ulDurarion = arr[67];
                    dlDurarion = arr[68];
                    break;
                case S1UGEN:
                    ulDurarion = arr[91];
                    dlDurarion = arr[92];
                    break;
                case S1UMMS:
                    ulDurarion = arr[74];
                    dlDurarion = arr[75];
                    break;
                case S1URTSP:
                case S1UP2P:
                    ulDurarion = arr[66];
                    dlDurarion = arr[67];
                    break;
                case S1UVOIP:
                    ulDurarion = arr[63];
                    dlDurarion = arr[64];
                    break;
                default:
                    ulDurarion = "";
                    dlDurarion = "";
            }
            ulTraff = arr[OtherIndex.ulTraff];
            dlTraff = arr[OtherIndex.dlTraff];
            try {
                totalTraff = new BigDecimal(arr[OtherIndex.ulData]).add(new BigDecimal(arr[OtherIndex.dlData])).toString();
            } catch (Exception e) {
                totalTraff = "";
            }
            ulIpPacket = arr[OtherIndex.ulIpPacket];
            dlIpPacket = arr[OtherIndex.dlIpPacket];
            rat = arr[OtherIndex.rat];
            ipUser = arr[OtherIndex.userIpv4];
            if("".equals(ipUser)) ipUser = arr[OtherIndex.userIpv6];
            ipServ = arr[OtherIndex.appServerIpIpv4];
            if("".equals(ipServ)) ipServ = arr[OtherIndex.appServerIpIpv6];
            l4Type = arr[OtherIndex.l4Type];
            durTcp1st = arr[OtherIndex.durTcp1st];
            durTcp2nd = arr[OtherIndex.durTcp2nd];
            tcpUlretrNum = arr[OtherIndex.tcpUlretrNum];
            tcpDlretrNum = arr[OtherIndex.tcpDlretrNum];
            l7Type = commonDecode.L7_TYPE(arr[31]);
            l7Delay = arr[OtherIndex.l7Delay];
            statusCode = arr[OtherIndex.statusCode];
            switch (arrLength){
                case S1UMMS : statusCode = arr[55]; break;
                case S1UFTP : statusCode = arr[52]; break;
                case S1UEMAIL : statusCode = arr[53]; break;
                default: statusCode = "";
            }
            userAgent = "";
            apn = arr[OtherIndex.apn];
            host = arr[30];
            if("".equals(host)) host = arr[31];
            feature = "";
            ipSgsnEnb = arr[OtherIndex.ipSgsnEnb];
            ipSgw = arr[OtherIndex.ipSgw];
            mcc = arr[5];
            if(mcc.length()>=3){
                mcc.substring(0,3);
            }else{
                mcc = "460";
            }
            mnc = "01";
            contentType = "";
            portUser = arr[OtherIndex.portUser];
            portServ = arr[OtherIndex.portServ];
            ulIpFragPackets = arr[OtherIndex.ulIpFragPackets];
            dlIpFragPackets = arr[OtherIndex.dlIpFragPackets];
            eventT1st = arr[OtherIndex.eventT1st];
            eventT2nd = arr[OtherIndex.eventT2nd];
            tcpWin = arr[OtherIndex.tcpWin];
            tcpMss = arr[OtherIndex.tcpMss];
            tcpSynnum = arr[OtherIndex.tcpSynnum];
            tcpStatus = arr[OtherIndex.tcpStatus];
            appStatus = arr[OtherIndex.appStatus];
            tcpUldisord = arr[OtherIndex.tcpUldisord];
            tcpDldisord = arr[OtherIndex.tcpDldisord];
            if(arrLength == S1UGEN){
                rttUl = arr[57];
                rttUlnum = arr[56];
                rttDl = arr[59];
                rttDlnum = arr[58];
            }else{
                rttUl = "";
                rttUlnum = "";
                rttDl = "";
                rttDlnum = "";
            }
            l7Uptranstime = "";
            switch (arrLength){
                case S1UEMAIL:
                    l7Uptranstime = arr[65];
                    l7Downtranstime = arr[66];
                    break;
                case S1UFTP:
                    l7Uptranstime = arr[67];
                    l7Downtranstime = arr[68];
                    break;
                case S1UGEN:
                    l7Uptranstime = arr[91];
                    l7Downtranstime = arr[92];
                    break;
                case S1UMMS:
                    l7Uptranstime = arr[74];
                    l7Downtranstime = arr[75];
                    break;
                case S1UP2P:
                case S1URTSP:
                    l7Uptranstime = arr[66];
                    l7Downtranstime = arr[67];
                    break;
                case S1UVOIP:
                    l7Uptranstime = arr[63];
                    l7Downtranstime = arr[64];
                default:
                    l7Uptranstime = "";
                    l7Downtranstime = "";
            }
            l7Uptraffic = arr[OtherIndex.l7Uptraffic];
            l7Downtraffic = arr[OtherIndex.l7Downtraffic];
            try {
                if(arrLength == S1UGEN && "0".equals(arr[29])){
                    l7Transtime = (Math.max(Long.parseLong(arr[77]),Long.parseLong(arr[78])) - Long.parseLong(arr[89])) + "";
                }else{
                    l7Transtime = (Long.parseLong(arr[OtherIndex.procedureEndTime]) - Long.parseLong(arr[OtherIndex.procedureStartTime])) + "";
                }
            } catch (Exception e) {
                l7Transtime = "";
            }
            longGps = "";
            latiGps = "";
            heightGps = "";
            accurGps = "";
            coordi = "0";
            try {
                tcpUldisotraf = new BigDecimal(arr[37]).multiply(new BigDecimal(arr[48])).toString();
            } catch (Exception e) {
                tcpUldisotraf = "";
            }
            try {
                tcpDldisotraf = new BigDecimal(arr[38]).multiply(new BigDecimal(arr[48])).toString();
            } catch (Exception e) {
                tcpDldisotraf = "";
            }
            try {
                tcpUlresttraf = new BigDecimal(arr[39]).multiply(new BigDecimal(arr[48])).toString();
            } catch (Exception e) {
                tcpUlresttraf = "";
            }
            try {
                tcpDlresttraf = new BigDecimal(arr[40]).multiply(new BigDecimal(arr[48])).toString();
            } catch (Exception e) {
                tcpDlresttraf = "";
            }
            webApp = "2";
            vendorSertype = arr[22]+"#"+arr[23];
            sgwGgsnIpAdd = arr[StreamingIndex.sgwGgsnIpAdd];
            protocolType = getProtocolTypeFromArr(arr);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(utype).append("|")
                .append(startT).append("|")
                .append(endT).append("|")
                .append(imsi).append("|")
                .append(msisdn).append("|")
                .append(imei).append("|")
                .append(rac).append("|")
                .append(lacTac).append("|")
                .append(cgiEci).append("|")
                .append(servname).append("|")
                .append(durition).append("|")
                .append(ulDurarion).append("|")
                .append(dlDurarion).append("|")
                .append(ulTraff).append("|")
                .append(dlTraff).append("|")
                .append(totalTraff).append("|")
                .append(ulIpPacket).append("|")
                .append(dlIpPacket).append("|")
                .append(rat).append("|")
                .append(ipUser).append("|")
                .append(ipServ).append("|")
                .append(l4Type).append("|")
                .append(durTcp1st).append("|")
                .append(durTcp2nd).append("|")
                .append(tcpUlretrNum).append("|")
                .append(tcpDlretrNum).append("|")
                .append(l7Type).append("|")
                .append(l7Delay).append("|")
                .append(statusCode).append("|")
                .append(userAgent).append("|")
                .append(apn).append("|")
                .append(host).append("|")
                .append(feature).append("|")
                .append(ipSgsnEnb).append("|")
                .append(ipSgw).append("|")
                .append(mcc).append("|")
                .append(mnc).append("|")
                .append(contentType).append("|")
                .append(portUser).append("|")
                .append(portServ).append("|")
                .append(ulIpFragPackets).append("|")
                .append(dlIpFragPackets).append("|")
                .append(eventT1st).append("|")
                .append(eventT2nd).append("|")
                .append(tcpWin).append("|")
                .append(tcpMss).append("|")
                .append(tcpSynnum).append("|")
                .append(tcpStatus).append("|")
                .append(appStatus).append("|")
                .append(tcpUldisord).append("|")
                .append(tcpDldisord).append("|")
                .append(rttUl).append("|")
                .append(rttUlnum).append("|")
                .append(rttDl).append("|")
                .append(rttDlnum).append("|")
                .append(l7Uptranstime).append("|")
                .append(l7Uptraffic).append("|")
                .append(l7Downtranstime).append("|")
                .append(l7Downtraffic).append("|")
                .append(l7Transtime).append("|")
                .append(longGps).append("|")
                .append(latiGps).append("|")
                .append(heightGps).append("|")
                .append(accurGps).append("|")
                .append(coordi).append("|")
                .append(tcpUldisotraf).append("|")
                .append(tcpDldisotraf).append("|")
                .append(tcpUlresttraf).append("|")
                .append(tcpDlresttraf).append("|")
                .append(webApp).append("|")
                .append(vendorSertype);
        return sb.toString();
    }
}
