package com.tuoming.entity.streaming;


import com.tuoming.common.CommonDecode;
import com.tuoming.tools.SimpleDateDeal;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class StreamingDecode extends CommonDecode implements Serializable {
    private String utype = "6";
    private String startT = "";
    private String endT = "";
    private String imsi = "";
    private String msisdn = "";
    private String imei = "";
    private String accountId = "";
    private String rac = "";
    private String lacTac = "";
    private String cgiEci = "";
    private String servname = "";
    private String durition = "";
    private String ulTraff = "";
    private String dlTraff = "";
    private String totalTraff = "";
    private String rat = "";
    private String ipUser = "";
    private String ipServ = "";
    private String l4Type = "";
    private String durTcp1st = "";
    private String durTcp2nd = "";
    private String apn = "";
    private String ipMme = "";
    private String ipSgw = "";
    private String mcc = "";
    private String mnc = "01";
    private String portUser = "";
    private String portServ = "";
    private String l7Type = "";
    private String appStatus = "";
    private String rttUl ;
    private String rttUlnum ;
    private String rttDl = "";
    private String rttDlnum = "";
    private String l7Uptranstime = "";
    private String l7Uptraffic = "";
    private String l7Downtranstime = "";
    private String l7Downtraffic = "";
    private String l7Transtime = "";
    private String longGps = "";
    private String latiGps = "";
    private String heightGps = "";
    private String accurGps = "";
    private String coordi = "0";
    private String tcpUlresttraf = "";
    private String tcpDlresttraf = "";
    private String webApp = "";
    private String videoType = "";
    private String actType = "2";
    private String actStTime = "";
    private String actEndTime = "";
    private String buffertime = "";
    private String realplaytime = "";
    private String coderate = "";
    private String videosize = "";
    private String videoblocknum = "";
    private String videoblockUrarion = "";
    private String videofailcause = "";
    private String videotime = "";
    private String playtime = "";
    private String videlqual = "";
    private String videoreso = "";




    private String procedureEndtime;

    private String sgwGgsnIpAdd;

    private Long minStartTime;
    private Long maxEndTime;
    private long actStTimeIndex = 1;
    private long actEndTimeIndex = 1;
    private long durTcp1stIndex = 0;
    private long durTcp2ndIndex = 0;
    private long buffertimeIndex = 0;
    private long realplaytimeIndex = 0;
    private long videosizeIndex = 0;
    private long videoblocknumIndex = 0;
    private long videoblockUrarionIndex = 0;

    private boolean alreadyToString = false;

    @Override
    public String getEndTime() {
        return endT;
    }

    @Override
    public String getMmeIp() {
        return ipMme;
    }

    public static Long getStartTime(String[] arr){
        try {
            return Long.parseLong(arr[StreamingIndex.startT]);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Long getEndTime(String[] arr){
        try {
            return Long.parseLong(arr[StreamingIndex.endT]);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    public static String getImsi(String[] arr){return arr[StreamingIndex.imsi];}

    public  String getProcedureEndtime() {
        return SimpleDateDeal.getFormatDate(maxEndTime.toString());
    }

    public String getMmeIpAdd() {
        return sgwGgsnIpAdd;
    }

    public void decode(String[] arr) {
        if (arr.length >= StreamingIndex.size) {
            utype = "6";
            Long startTime = StreamingDecode.getStartTime(arr);
            if(minStartTime == null){
                minStartTime = startTime;
                startT = SimpleDateDeal.getFormatDate(minStartTime.toString());
            }else if(minStartTime != null && startTime != null && startTime < minStartTime){
                minStartTime = startTime;
                startT = SimpleDateDeal.getFormatDate(minStartTime.toString());
            }
            Long endTime = StreamingDecode.getEndTime(arr);
            if(maxEndTime == null){
                maxEndTime = endTime;
                procedureEndtime = arr[StreamingIndex.endT];
                endT = SimpleDateDeal.getFormatDate(maxEndTime.toString());
            }else if(maxEndTime != null && endTime != null && endTime > maxEndTime){
                maxEndTime = endTime;
                procedureEndtime = arr[StreamingIndex.endT];
                endT = SimpleDateDeal.getFormatDate(maxEndTime.toString());
            }
            if(isNull(imsi)){
                String imsiTmp = arr[StreamingIndex.imsi];
                String msisdnTmp = arr[StreamingIndex.msisdn];
                if (msisdnTmp.startsWith("+86")) {
                    msisdnTmp = msisdnTmp.substring(3);
                } else if (msisdnTmp.startsWith("86")) {
                    msisdnTmp = msisdnTmp.substring(2);
                } else if (msisdnTmp.startsWith("0086")) {
                    msisdnTmp = msisdnTmp.substring(4);
                }
                String imeiTmp = arr[StreamingIndex.imei];
                if(isNotNull(imeiTmp) && isNotNull(msisdnTmp) && isNotNull(imeiTmp)){
                    imsi = imsiTmp;
                    msisdn = msisdnTmp;
                    imei = imeiTmp;
                }
            }
            if(isNull(lacTac))
                lacTac = SimpleDateDeal.leftSupply0(arr[StreamingIndex.lacTac],5);
            if(isNull(cgiEci))
                cgiEci = arr[StreamingIndex.cgiEci];
            ulTraff = strAdd(ulTraff,arr[StreamingIndex.ulTraff]);
            dlTraff = strAdd(dlTraff,arr[StreamingIndex.dlTraff]);
            totalTraff = strAdd(ulTraff,dlTraff);
            if(isNull(rat))
                rat = arr[StreamingIndex.rat];
            if(isNull(ipUser))
                ipUser = arr[StreamingIndex.userIpv4];
            if(isNull(ipUser))
                ipUser = arr[StreamingIndex.userIpv6];
            if(isNull(ipServ))
                ipServ = arr[StreamingIndex.appServerIpIpv4];
            if(isNull(ipServ))
                ipServ = arr[StreamingIndex.appServerIpIpv6];
            if(isNull(l4Type))
                l4Type = arr[StreamingIndex.l4Type];
            String durTcp1stTmp = arr[StreamingIndex.durTcp1st];
            if(isNotNull(durTcp1stTmp)){
                durTcp1st = strAdd(durTcp1st,durTcp1stTmp);
                durTcp1stIndex++;
            }
            String durTcp2ndTmp = arr[StreamingIndex.durTcp2nd];
            if(isNotNull(durTcp2nd)){
                durTcp2nd = strAdd(durTcp2nd,durTcp2ndTmp);
                durTcp2ndIndex++;
            }
            if(isNull(apn))
                apn = arr[StreamingIndex.apn];
            if(isNull(ipMme))
                ipMme = arr[StreamingIndex.ipMme];
            if(isNull(ipSgw))
                ipSgw = arr[StreamingIndex.ipSgw];
//            mcc = "";
//            if(isNotNull(imsi) && imsi.length()>=3)
//                mcc = imsi.substring(0,3);
            if(isNull(portUser))
                portUser = arr[StreamingIndex.portUser];
            if(isNull(portServ))
                portServ = arr[StreamingIndex.portServ];
            if(isNull(appStatus))
                appStatus = arr[StreamingIndex.appStatus];
            rttUl = strAdd(rttUl,arr[StreamingIndex.rttUl]);
            rttUlnum = strAdd(rttUlnum,arr[StreamingIndex.rttUlnum]);
            rttDl = strAdd(rttDl,arr[StreamingIndex.rttDl]);
            rttDlnum = strAdd(rttDlnum,arr[StreamingIndex.rttDlnum]);
            l7Uptranstime = strAdd(l7Uptranstime,arr[StreamingIndex.l7Uptranstime]);
            l7Uptraffic = strAdd(l7Uptraffic,arr[StreamingIndex.l7Uptraffic]);
            l7Downtranstime = strAdd(l7Downtranstime,arr[StreamingIndex.l7Downtranstime]);
            l7Downtraffic = strAdd(l7Downtraffic,arr[StreamingIndex.l7Downtraffic]);
            l7Transtime = strAdd(l7Transtime,arr[StreamingIndex.l7Transtime]);
            tcpUlresttraf = strAdd(tcpUlresttraf,strMultiply(arr[39],arr[48]));
            tcpDlresttraf = strAdd(tcpDlresttraf,strMultiply(arr[40],arr[48]));
            if(isNull(webApp))
                webApp = "0".equals(arr[84])? "2": "1";
            if(actStTimeIndex <= 2 && isNotNull(arr[19])){
                actStTime = arr[19];
                actStTimeIndex++;
            }
            if(actEndTimeIndex <= 2 && isNotNull(arr[20])){
                actEndTime = arr[20];
                actEndTimeIndex++;
            }
            String buffertimeTmp = getBuffertime(arr);
            if(isNotNull(buffertimeTmp)){
                buffertime = strAdd(buffertime,buffertimeTmp);
                buffertimeIndex++;
            }
            String realplaytimeTmp = arr[StreamingIndex.realplaytime];
            if(isNotNull(realplaytimeTmp)){
                realplaytime = strAdd(realplaytime,realplaytimeTmp);
                realplaytimeIndex++;
            }
            if(isNull(coderate))
                coderate = arr[StreamingIndex.coderate];
            String videosizeTmp = arr[StreamingIndex.videosize];
            if(isNotNull(videosizeTmp)){
                videosize = strAdd(videosize,videosizeTmp);
                videosizeIndex++;
            }
            String videoblocknumTmp = arr[StreamingIndex.videoblocknum];
            if(isNotNull(videoblocknumTmp)) {
                videoblocknum = strAdd(videoblocknum,videoblocknumTmp);
                videoblocknumIndex++;
            }
            String videoblockUrarionTmp = strMultiply(arr[57],arr[58]);
            if(isNotNull(videoblockUrarionTmp)){
                videoblockUrarion = strAdd(videoblockUrarion,videoblockUrarionTmp);
                videoblockUrarionIndex++;
            }
            String playSuccess = arr[55];
            if(isNull(videofailcause) && isNotNull(playSuccess) ){
                if("0".equals(playSuccess)){
                    videofailcause = arr[64];
                }else{
                    videofailcause = "";
                }
            }
            videotime = strAdd(videotime,arr[StreamingIndex.videotime]);
            if(isNull(videoreso))
                videoreso = arr[StreamingIndex.videoreso];


            sgwGgsnIpAdd = arr[StreamingIndex.sgwGgsnIpAdd];

        }
    }
    private boolean isNull(String str){
        return str == null || "".equals(str);
    }
    private boolean isNotNull(String str){
        return str != null && !"".equals(str);
    }
    private void decodeDurition(){
        try {
            durition = String.valueOf(maxEndTime - minStartTime);
        } catch (Exception e) {
        }
    }
    private String strAdd(String a, String b){
        if(isNull(a)){
            return b;
        }
        if(isNull(b)){
            return a;
        }
        try {
            return new BigDecimal(a).add(new BigDecimal(b)).toString();
        } catch (Exception e) {
            return a;
        }
    }
    private String strMultiply(String a,String b){
        if(isNull(a)){
            return b;
        }
        if(isNull(b)){
            return a;
        }
        try {
            return new BigDecimal(a).multiply(new BigDecimal(b)).toString();
        } catch (Exception e) {
            return a;
        }
    }
    private String getBuffertime(String[] arr){
        try {
            return new BigDecimal(arr[54]).multiply(new BigDecimal(5000)).divide(new BigDecimal(arr[34]),3,RoundingMode.HALF_UP).divide(new BigDecimal(arr[66]),3,RoundingMode.HALF_UP).toString();
        } catch (Exception e) {
            return "";
        }
    }
    @Override
    public String toString() {
        if(!alreadyToString) {
            if (durTcp1stIndex != 0) {
                try {
                    durTcp1st = new BigDecimal(durTcp1st).divide(new BigDecimal(durTcp1stIndex), 0, RoundingMode.DOWN).toString();
                } catch (Exception e) {
                    durTcp1st = "";
                }
            }
            if (durTcp2ndIndex != 0) {
                try {
                    durTcp2nd = new BigDecimal(durTcp2nd).divide(new BigDecimal(durTcp2ndIndex), 0, RoundingMode.DOWN).toString();
                } catch (Exception e) {
                    durTcp2nd = "";
                }
            }
            if (buffertimeIndex != 0) {
                try {
                    buffertime = new BigDecimal(buffertime).divide(new BigDecimal(buffertimeIndex), 0, RoundingMode.DOWN).toString();
                } catch (Exception e) {
                    buffertime = "";
                }
            }
            if (realplaytimeIndex != 0) {
                try {
                    realplaytime = new BigDecimal(realplaytime).divide(new BigDecimal(realplaytimeIndex), 0, RoundingMode.DOWN).toString();
                } catch (Exception e) {
                    realplaytime = "";
                }
            }
            if (videosizeIndex != 0) {
                try {
                    videosize = new BigDecimal(videosize).divide(new BigDecimal(videosizeIndex), 0, RoundingMode.DOWN).toString();
                } catch (Exception e) {
                    videosize = "";
                }
            }
            if (videoblocknumIndex != 0) {
                try {
                    videoblocknum = new BigDecimal(videoblocknum).divide(new BigDecimal(videoblocknumIndex), 0, RoundingMode.DOWN).toString();
                } catch (Exception e) {
                    videoblocknum = "";
                }
            }
            if (videoblockUrarionIndex != 0) {
                try {
                    videoblockUrarion = new BigDecimal(videoblockUrarion).divide(new BigDecimal(videoblockUrarionIndex), 0, RoundingMode.DOWN).toString();
                } catch (Exception e) {
                    videoblockUrarion = "";
                }
            }
            if(isNotNull(l7Uptranstime)){
                try {
                    l7Uptranstime = new BigDecimal(l7Uptranstime).divide(new BigDecimal("1000"),0,RoundingMode.DOWN).toString();
                } catch (Exception e) {
                    l7Uptranstime = "";
                }
            }
            if(isNotNull(l7Downtranstime)){
                try {
                    l7Downtranstime = new BigDecimal(l7Downtranstime).divide(new BigDecimal("1000"),0,RoundingMode.DOWN).toString();
                } catch (Exception e) {
                    l7Downtranstime = "";
                }
            }
        }
        decodeDurition();



        StringBuilder sb = new StringBuilder();
        sb.append(utype).append("|")
                .append(startT).append("|")
                .append(endT).append("|")
                .append(imsi).append("|")
                .append(msisdn).append("|")
                .append(imei).append("|")
                .append(accountId).append("|")
                .append(rac).append("|")
                .append(lacTac).append("|")
                .append(cgiEci).append("|")
                .append(servname).append("|")
                .append(durition).append("|")
                .append(ulTraff).append("|")
                .append(dlTraff).append("|")
                .append(totalTraff).append("|")
                .append(rat).append("|")
                .append(ipUser).append("|")
                .append(ipServ).append("|")
                .append(l4Type).append("|")
                .append(durTcp1st).append("|")
                .append(durTcp2nd).append("|")
                .append(apn).append("|")
                .append(ipMme).append("|")
                .append(ipSgw).append("|")
                .append(isNull(mcc)?"460":mcc).append("|")
                .append(mnc).append("|")
                .append(portUser).append("|")
                .append(portServ).append("|")
                .append(l7Type).append("|")
                .append(appStatus).append("|")
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
                .append(tcpUlresttraf).append("|")
                .append(tcpDlresttraf).append("|")
                .append(webApp).append("|")
                .append(videoType).append("|")
                .append(actType).append("|")
                .append(SimpleDateDeal.getFormatDate(actStTime)).append("|")
                .append(SimpleDateDeal.getFormatDate(actEndTime)).append("|")
                .append(buffertime).append("|")
                .append(realplaytime).append("|")
                .append(coderate).append("|")
                .append(videosize).append("|")
                .append(videoblocknum).append("|")
                .append(videoblockUrarion).append("|")
                .append(videofailcause).append("|")
                .append(videotime).append("|")
                .append(playtime).append("|")
                .append(videlqual).append("|")
                .append(videoreso);
        return sb.toString();
    }
}
