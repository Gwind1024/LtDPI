package com.tuoming.entity.sms4g;

import com.tuoming.common.CommonDecode;
import com.tuoming.common.RedisUntil;

import java.io.Serializable;

public class Sms4gDecode extends CommonDecode implements Serializable {
    private String starttime;
    private String endtime;
    private String msisdn;
    private String imsi;
    private String rmsisdn;
    private String rimsi;
    private String imei;
    private String result;
    private String duration;
    private String smcAddr;
    private String procedureType;
    private String mmeIp;
    private String tai;
    private String ecgi;
    private String failuremsg;
    private String errorcode;
    private String releasercode;
    private String cpCause;
    private String rpCause;
    private String mscip;
    private String lai;
    private String delayCmreq;
    private String delayPagingreq1st;
    private String delayPagingreq2st;
    private String delayPagingreq3st;
    private String delayPagingreqMore;
    private String delayDownlink1st;
    private String delayUplink1st;
    private String delayDownlink2st;
    private String delayUplink2st;
    private String delayRelreq;
    private String callId;

    @Override
    public String getEndTime() {
        return endtime;
    }

    @Override
    public String getMmeIp() {
        return mmeIp;
    }

    public void decode(String[] arr) {
        if (arr.length >= Sms4gIndex.size) {
            starttime = arr[Sms4gIndex.starttime];
            endtime = arr[Sms4gIndex.endtime];
            msisdn = arr[Sms4gIndex.msisdn].startsWith("86") ? arr[Sms4gIndex.msisdn].substring(2) : arr[Sms4gIndex.msisdn];
            imsi = arr[Sms4gIndex.imsi];
            rmsisdn = arr[Sms4gIndex.rmsisdn];
            rimsi = arr[Sms4gIndex.rimsi];
            imei = arr[Sms4gIndex.imei];
            result = arr[Sms4gIndex.result];
            duration = arr[Sms4gIndex.duration];
            smcAddr = arr[Sms4gIndex.smcAddr];
            procedureType = arr[Sms4gIndex.procedureType];
            mmeIp = arr[Sms4gIndex.mmeIp];
            tai = arr[Sms4gIndex.tai];
            ecgi = arr[Sms4gIndex.ecgi];
            failuremsg = arr[Sms4gIndex.failuremsg];
            errorcode = arr[Sms4gIndex.errorcode];
            releasercode = arr[Sms4gIndex.releasercode];
            cpCause = arr[Sms4gIndex.cpCause];
            rpCause = arr[Sms4gIndex.rpCause];
            mscip = arr[Sms4gIndex.mscip];
            lai = arr[Sms4gIndex.lai];
            delayCmreq = arr[Sms4gIndex.delayCmreq];
            delayPagingreq1st = arr[Sms4gIndex.delayPagingreq1st];
            delayPagingreq2st = arr[Sms4gIndex.delayPagingreq2st];
            delayPagingreq3st = arr[Sms4gIndex.delayPagingreq3st];
            delayPagingreqMore = arr[Sms4gIndex.delayPagingreqMore];
            delayDownlink1st = arr[Sms4gIndex.delayDownlink1st];
            delayUplink1st = arr[Sms4gIndex.delayUplink1st];
            delayDownlink2st = arr[Sms4gIndex.delayDownlink2st];
            delayUplink2st = arr[Sms4gIndex.delayUplink2st];
            delayRelreq = arr[Sms4gIndex.delayRelreq];
            callId = arr[Sms4gIndex.callId];
        }
    }

    @Override
    public String toString() {
        RedisUntil.backfill(this);
        StringBuilder sb = new StringBuilder();
        sb.append(starttime).append("|")
                .append(endtime).append("|")
                .append(msisdn).append("|")
                .append(imsi).append("|")
                .append(rmsisdn).append("|")
                .append(rimsi).append("|")
                .append(imei).append("|")
                .append(result).append("|")
                .append(duration).append("|")
                .append(smcAddr).append("|")
                .append(procedureType).append("|")
                .append(mmeIp).append("|")
                .append(tai).append("|")
                .append(ecgi).append("|")
                .append(failuremsg).append("|")
                .append(errorcode).append("|")
                .append(releasercode).append("|")
                .append(cpCause).append("|")
                .append(rpCause).append("|")
                .append(mscip).append("|")
                .append(lai).append("|")
                .append(delayCmreq).append("|")
                .append(delayPagingreq1st).append("|")
                .append(delayPagingreq2st).append("|")
                .append(delayPagingreq3st).append("|")
                .append(delayPagingreqMore).append("|")
                .append(delayDownlink1st).append("|")
                .append(delayUplink1st).append("|")
                .append(delayDownlink2st).append("|")
                .append(delayUplink2st).append("|")
                .append(delayRelreq).append("|")
                .append(callId);
        return sb.toString();
    }
}
