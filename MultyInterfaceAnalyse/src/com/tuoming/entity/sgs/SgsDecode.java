package com.tuoming.entity.sgs;


import com.tuoming.common.CommonDecode;
import com.tuoming.common.RedisUntil;
import com.tuoming.count.CountNum;

import java.io.Serializable;

public class SgsDecode extends CommonDecode implements Serializable {
    private String accessType;
    private String interface0;
    private String sdrType;
    private String mcc;
    private String mnc;
    private String startTime;
    private String endTime;
    private String srvstat;
    private String cdrstat;
    private String xdrId;
    private String mscIp;
    private String mscPort;
    private String mmeIp;
    private String mmePort;
    private String servType;
    private String locUpType;
    private String callingImsi;
    private String callingImei;
    private String callingMsisdn;
    private String calledImsi;
    private String calledImei;
    private String calledMsisdn;
    private String tmsi;
    private String tai;
    private String cellId;
    private String newLai;
    private String oldLai;
    private String sgsCause;
    private String cpCause;
    private String rpCause;
//    private String vlrName;
//    private String mmeName;

    public String getEndTime() {
        return endTime;
    }


    public String getMmeIp() {
        return mmeIp;
    }

    public void decode(String[] arr) {
        if (arr.length >= SgsIndex.size) {
            initialize();
            accessType = arr[SgsIndex.accessType];
            interface0 = arr[SgsIndex.interface0];
            sdrType = arr[SgsIndex.sdrType];
            imsi = arr[SgsIndex.imsi];
            imei = arr[SgsIndex.imei];
            msisdn = arr[SgsIndex.msisdn].startsWith("86") ? arr[SgsIndex.msisdn].substring(2) : arr[SgsIndex.msisdn];
            mcc = "".equals(arr[SgsIndex.mcc]) ? "460" : arr[SgsIndex.mcc];
            mnc = "".equals(arr[SgsIndex.mnc]) ? "01" : arr[SgsIndex.mnc];
            startTime = arr[SgsIndex.startTime];
            endTime = arr[SgsIndex.endTime];
            srvstat = arr[SgsIndex.srvstat];
            cdrstat = arr[SgsIndex.cdrstat];
            xdrId = arr[SgsIndex.xdrId];
            mscIp = arr[SgsIndex.mscIp];
            mscPort = arr[SgsIndex.mscPort];
            mmeIp = arr[SgsIndex.mmeIp];
            mmePort = arr[SgsIndex.mmePort];
            servType = arr[SgsIndex.servType];
            locUpType = arr[SgsIndex.locUpType];
            callingImsi = arr[SgsIndex.callingImsi];
            callingImei = arr[SgsIndex.callingImei];
            callingMsisdn = arr[SgsIndex.callingMsisdn];
            calledImsi = arr[SgsIndex.calledImsi];
            calledImei = arr[SgsIndex.calledImei];
            calledMsisdn = arr[SgsIndex.calledMsisdn];
            tmsi = arr[SgsIndex.tmsi];
            tai = arr[SgsIndex.tai];
            cellId = arr[SgsIndex.cellId];
            newLai = arr[SgsIndex.newLai];
            oldLai = arr[SgsIndex.oldLai];
            sgsCause = arr[SgsIndex.sgsCause];
            cpCause = arr[SgsIndex.cpCause];
            rpCause = arr[SgsIndex.rpCause];
//            vlrName = arr[SgsIndex.vlrName];
//            mmeName = arr[SgsIndex.mmeName];
        }
    }

    public void initialize() {
        accessType = null;
        interface0 = null;
        sdrType = null;
        imsi = null;
        imei = null;
        msisdn = null;
        mcc = null;
        mnc = null;
        startTime = null;
        endTime = null;
        srvstat = null;
        cdrstat = null;
        xdrId = null;
        mscIp = null;
        mscPort = null;
        mmeIp = null;
        mmePort = null;
        servType = null;
        locUpType = null;
        callingImsi = null;
        callingImei = null;
        callingMsisdn = null;
        calledImsi = null;
        calledImei = null;
        calledMsisdn = null;
        tmsi = null;
        tai = null;
        cellId = null;
        newLai = null;
        oldLai = null;
        sgsCause = null;
        cpCause = null;
        rpCause = null;
    }


    public String toString() {
        CountNum.outputNum+=1;
        RedisUntil.backfill(this);
        StringBuilder sb = new StringBuilder();
        sb.append(accessType).append("|")
                .append(interface0).append("|")
                .append(sdrType).append("|")
                .append(imsi).append("|")
                .append(imei).append("|")
                .append(msisdn).append("|")
                .append(mcc).append("|")
                .append(mnc).append("|")
                .append(startTime).append("|")
                .append(endTime).append("|")
                .append(srvstat).append("|")
                .append(cdrstat).append("|")
                .append(xdrId).append("|")
                .append(mscIp).append("|")
                .append(mscPort).append("|")
                .append(mmeIp).append("|")
                .append(mmePort).append("|")
                .append(servType).append("|")
                .append(locUpType).append("|")
                .append(callingImsi).append("|")
                .append(callingImei).append("|")
                .append(callingMsisdn).append("|")
                .append(calledImsi).append("|")
                .append(calledImei).append("|")
                .append(calledMsisdn).append("|")
                .append(tmsi).append("|")
                .append(tai).append("|")
                .append(cellId).append("|")
                .append(newLai).append("|")
                .append(oldLai).append("|")
                .append(sgsCause).append("|")
                .append(cpCause).append("|")
                .append(rpCause);
        return sb.toString();
    }
}
