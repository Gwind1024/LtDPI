package com.tuoming.entity.s11u;


import com.tuoming.common.CommonDecode;
import com.tuoming.entity.common.commonDecode;
import com.tuoming.tools.SimpleDateDeal;

import java.io.Serializable;
import java.math.BigDecimal;

public class CoapDecode extends CommonDecode implements Serializable {

    //nb-iot
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
    private String ulTraff ;
    private String dlTraff ;
    private String totalTraff ;
    private String rat ;
    private String cdrstat ;
    private String ulDurarion ;
    private String dlDurarion ;
    private String ulIpPacket ;
    private String dlIpPacket ;
    private String upTcpRetransNum ;
    private String downTcpRetransNum ;
    private String ipUser ;
    private String ipServ ;
    private String l4Type ;
    private String durTcp1st ;
    private String durTcp2nd ;
    private String tcpUlretrNum ;
    private String tcpDlretrNum ;
    private String l7Type ;
    private String l7Delay ;



    //coap
    private String version ;
    private String procedureType ;
    private String token ;
    private String requstAction ;
    private String responseResult ;
    private String status ;
    private String requestTime ;
    private String ackTime ;
    private String lastTime ;
    private String retransULCount ;
    private String retransDLCount ;
    private String direction ;
    private String uriPort ;
    private String destinationURI ;
    private String host ;
    private String proxyURI ;
    private String proxyScheme ;
    private String massageID ;
    private String allCount ;
    private String requestCount ;
    private String responseSuccessCount ;
    private String clientFailureCount ;
    private String serverFailureCount ;

    //ip
    private String sgwGgsnIpAdd ;
    private String procedureEndtime;


    public String getSgwGgsnIpAdd() {
        return sgwGgsnIpAdd;
    }

    public String getProcedureEndtime(){
        return endT;
    }

    public String getEndTime() {
        return endT;
    }


    public String getMmeIp() {
        return machineIP;
    }

    public void decode(String[] arr) {
        if (arr.length >= CoapIndex.size) {
            //nb-iot
            startT = SimpleDateDeal.getFormatDate(arr[CoapIndex.startT]);
            endT = SimpleDateDeal.getFormatDate(arr[CoapIndex.endT]);
            imsi = arr[CoapIndex.imsi];
            String msisdnTmp = arr[CoapIndex.msisdn];
            if (msisdnTmp == null) {
                msisdn = "";
            } else if (msisdnTmp.startsWith("+86")) {
                msisdn = msisdnTmp.substring(3);
            } else if (msisdnTmp.startsWith("86")) {
                msisdn = msisdnTmp.substring(2);
            } else if (msisdnTmp.startsWith("0086")) {
                msisdn = msisdnTmp.substring(4);
            } else {
                msisdn = msisdnTmp;
            }
            imei = arr[CoapIndex.imei];
            rac = "";
            lacTac = arr[CoapIndex.lacTac];
            cgiEci = arr[CoapIndex.cgiEci];
            servname = commonDecode.SERVNAME(arr[32]+"_"+arr[33]);
            String procedureStartTime = arr[CoapIndex.procedureStartTime];
            String procedureEndTime = arr[CoapIndex.procedureEndTime];
            durition = "";
            try {
                long duritionTemp = Long.parseLong(procedureEndTime) - Long.parseLong(procedureStartTime);
                durition = duritionTemp >= 0 ? duritionTemp + "" : "";
            } catch (NumberFormatException e) {
                durition = "";
            }
            ulTraff = arr[CoapIndex.ulTraff];
            dlTraff = arr[CoapIndex.dlTraff];
            try {
                totalTraff = new BigDecimal(arr[CoapIndex.ulData]).add(new BigDecimal(arr[CoapIndex.dlData])).toString();
            } catch (Exception e) {
                totalTraff = "";
            }
            rat = arr[CoapIndex.rat];
            String appStatus = arr[CoapIndex.appStatus];
            if(appStatus == null || "255".equals(appStatus) || "65535".equals(appStatus)){
                cdrstat = "";
            }else if("0".equals(appStatus)){
                cdrstat = "0";
            }else if("1".equals(appStatus) || "2".equals(appStatus) || "3".equals(appStatus)){
                cdrstat = "3";
            }else if("6".equals(appStatus) || "7".equals(appStatus)){
                cdrstat = "2";
            }else{
                cdrstat = "";
            }
            ulDurarion = arr[CoapIndex.ulDurarion];
            dlDurarion = arr[CoapIndex.dlDurarion];
            ulIpPacket = arr[CoapIndex.ulIpPacket];
            dlIpPacket = arr[CoapIndex.dlIpPacket];
            upTcpRetransNum = arr[CoapIndex.upTcpRetransNum];
            downTcpRetransNum = arr[CoapIndex.downTcpRetransNum];
            String machineIpAddType = arr[CoapIndex.machineIpAddType];
            if("1".equals(machineIpAddType)){
                ipUser = arr[CoapIndex.userIpv4];
            }else if("2".equals(machineIpAddType)){
                ipUser = arr[CoapIndex.userIpv6];
            }else{
                ipUser = "";
            }
            String ipAddressType = arr[CoapIndex.ipAddressType];
            if("0".equals(ipAddressType)){
                ipServ = arr[CoapIndex.appServerIpIpv4];
            }else if("1".equals(ipAddressType)){
                ipServ = arr[CoapIndex.appServerIpIpv6];
            }else{
                ipServ = "";
            }
            l4Type = arr[CoapIndex.l4Type];
            durTcp1st = arr[CoapIndex.durTcp1st];
            durTcp2nd = arr[CoapIndex.durTcp2nd];
            tcpUlretrNum = arr[CoapIndex.tcpUlretrNum];
            tcpDlretrNum = arr[CoapIndex.tcpDlretrNum];
            l7Type = "1000";
            l7Delay = arr[CoapIndex.l7Delay];



            //coap
            version = "1";
            String procedureTypeTemp = arr[CoapIndex.procedureType];
            if("0".equals(procedureTypeTemp)){
                procedureType = "1";
            }else if("1".equals(procedureTypeTemp)){
                procedureType = "2";
            }else{
                procedureType = "";
            }
            token = arr[CoapIndex.token];
            requstAction = arr[CoapIndex.requstAction];
            if("255".equals(requstAction) || "65535".equals(requstAction))requstAction = "";
            responseResult = arr[CoapIndex.responseResult];
            if("255".equals(responseResult) || "65535".equals(responseResult))responseResult = "";
            String transReplycode = arr[CoapIndex.transReplycode];
            status = "";
            if(transReplycode != null || !"65535".equals(transReplycode)){
                status = String.valueOf(Integer.parseInt(transReplycode)>>5);
            }
            requestTime = arr[CoapIndex.requestTime];
            ackTime = arr[CoapIndex.ackTime];
            lastTime = SimpleDateDeal.getFormatDate(arr[CoapIndex.lastTime]);
            retransULCount = arr[CoapIndex.retransULCount];
            retransDLCount = arr[CoapIndex.retransDLCount];
            direction = arr[CoapIndex.direction];
            uriPort = arr[CoapIndex.uriPort];
            if("65535".equals(uriPort)) uriPort = "";
            destinationURI = arr[CoapIndex.destinationURI];
            host = arr[CoapIndex.host];
            proxyURI = "";
            proxyScheme = "";
            massageID = arr[CoapIndex.massageID];
            allCount = "";
            String msgType = arr[CoapIndex.msgType];
            if("1".equals(msgType)){
                allCount = "1";
            }else{
                allCount = "0";
            }
            requestCount = "0";
            responseSuccessCount = "0";
            clientFailureCount = "0";
            serverFailureCount = "0";
            if("1".equals(msgType)){
                if("0".equals(status)){
                    requestCount = "1";
                }else if("2".equals(status)){
                    responseSuccessCount = "1";
                }else if("4".equals(status)){
                    clientFailureCount = "1";
                }else if("5".equals(status)){
                    serverFailureCount = "1";
                }
            }
            sgwGgsnIpAdd = arr[CoapIndex.sgwGgsnIpAdd];
            procedureEndtime = arr[CoapIndex.endT];
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //nb-iot
        sb.append(startT).append("|")
                .append(endT).append("|")
                .append(imsi).append("|")
                .append(msisdn).append("|")
                .append(imei).append("|")
                .append(rac).append("|")
                .append(lacTac).append("|")
                .append(cgiEci).append("|")
                .append(servname).append("|")
                .append(durition).append("|")
                .append(ulTraff).append("|")
                .append(dlTraff).append("|")
                .append(totalTraff).append("|")
                .append(rat).append("|")
                .append(cdrstat).append("|")
                .append(ulDurarion).append("|")
                .append(dlDurarion).append("|")
                .append(ulIpPacket).append("|")
                .append(dlIpPacket).append("|")
                .append(upTcpRetransNum).append("|")
                .append(downTcpRetransNum).append("|")
                .append(ipUser).append("|")
                .append(ipServ).append("|")
                .append(l4Type).append("|")
                .append(durTcp1st).append("|")
                .append(durTcp2nd).append("|")
                .append(tcpUlretrNum).append("|")
                .append(tcpDlretrNum).append("|")
                .append(l7Type).append("|")
                .append(l7Delay).append("|")

                //coap
                .append(version).append("|")
                .append(procedureType).append("|")
                .append(token).append("|")
                .append(requstAction).append("|")
                .append(responseResult).append("|")
                .append(status).append("|")
                .append(requestTime).append("|")
                .append(ackTime).append("|")
                .append(lastTime).append("|")
                .append(retransULCount).append("|")
                .append(retransDLCount).append("|")
                .append(direction).append("|")
                .append(uriPort).append("|")
                .append(destinationURI).append("|")
                .append(host).append("|")
                .append(proxyURI).append("|")
                .append(proxyScheme).append("|")
                .append(massageID).append("|")
                .append(allCount).append("|")
                .append(requestCount).append("|")
                .append(responseSuccessCount).append("|")
                .append(clientFailureCount).append("|")
                .append(serverFailureCount);
        return sb.toString();
    }
}
