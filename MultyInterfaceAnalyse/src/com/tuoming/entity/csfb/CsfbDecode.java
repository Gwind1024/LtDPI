package com.tuoming.entity.csfb;


import com.tuoming.common.CommonDecode;
import com.tuoming.common.RedisUntil;
import com.tuoming.count.CountNum;

import java.io.Serializable;

public class CsfbDecode extends CommonDecode implements Serializable {
    private String procedureStarttime;
    private String procedureEndtime;
    private String procedureType;
    private String rMsisdn;
    private String rImsi;
    private String rImei;
    //    private String msisdn;
//    private String imsi;
//    private String imei;
    private String mTmsi;
    private String tmsi;
    private String mmeIpAdd;
    private String mmeUeS1apId;
    private String mmeGroupId;
    private String mmeCode;
    private String mmePort;
    private String enbIpAdd;
    private String enbPort;
    private String tac;
    private String eci;
    private String sourLai;
    private String lai;
    private String sai;
    private String endlai;
    private String endsai;
    private String endtac;
    private String endeci;
    private String ednmsc;
    private String endrnc;
    private String msc;
    private String rnc;
    private String csfbResult;
    private String failuremsg;
    private String failureCause;
    private String firstCsfailuremsg;
    private String firstCserrorcode;
    private String csmaincause;
    private String csreleasermsg;
    private String csreleasercode;
    private String callId;
    private String csrantype;
    private String endenbIpAdd;
    private String endenbPort;
    private String endmmeUeS1apId;
    private String endmmePort;
    private String endmmeGroupId;
    private String endmmeCode;
    private String oldmsc;
    private String oldlai;
    private String s1Delay;
    private String csfbDelay;
    private String delaySgspagreq;
    private String delaySgspagres;
    private String delayPagingreq;
    private String delayServreq;
    private String delayServres;
    private String delayInitreq;
    private String delayInitres;
    private String delayCsfbrel;
    private String delayCsfirstmsg;
    private String delayCslureq;
    private String delayCslures;
    private String delayCsservreq;
    private String delayCsservres;
    private String delayTime;
    private String delayCallproc;
    private String delayCsassireq;
    private String delayCsassires;
    private String delayCssri;
    private String delayCssrires;
    private String delayCsprn;
    private String delayCsprnres;
    private String delayCsiam;
    private String delayCsacm;
    private String delayCsanm;
    private String delayCsauthreq1st;
    private String delayCsauthreq2nd;
    private String delayCsauthres;
    private String delayCscipcmd;
    private String delayCscipcmp;
    private String delayCstmsicmd;
    private String delayCstmsicmp;
    private String delayCsidreq;
    private String delayCsidres;
    private String delayCsalerting;
    private String delayCsconnect;
    private String talkduration;
    private String delayCsrel;
    private String delayIteretreq;
    private String delayIteretres;
    private String delayReleasecmp;

    @Override
    public String getEndTime() {
        return procedureEndtime;
    }

    @Override
    public String getMmeIp() {
        return mmeIpAdd;
    }

    public void decode(String[] arr) {
        if (arr.length >= CsfbIndex.size) {
            initialize();
            procedureStarttime = arr[CsfbIndex.procedureStarttime];
            procedureEndtime = arr[CsfbIndex.procedureEndtime];
            procedureType = arr[CsfbIndex.procedureType];
            rMsisdn = arr[CsfbIndex.rMsisdn].startsWith("86") ? arr[CsfbIndex.rMsisdn].substring(2) : arr[CsfbIndex.rMsisdn];
            rImsi = arr[CsfbIndex.rImsi];
            rImei = arr[CsfbIndex.rImei];
            msisdn = arr[CsfbIndex.msisdn].startsWith("86") ? arr[CsfbIndex.msisdn].substring(2) : arr[CsfbIndex.msisdn];
            imsi = arr[CsfbIndex.imsi];
            imei = arr[CsfbIndex.imei];
            mTmsi = arr[CsfbIndex.mTmsi];
            tmsi = arr[CsfbIndex.tmsi];
            mmeIpAdd = arr[CsfbIndex.mmeIpAdd];
            mmeUeS1apId = arr[CsfbIndex.mmeUeS1apId];
            mmeGroupId = arr[CsfbIndex.mmeGroupId];
            mmeCode = arr[CsfbIndex.mmeCode];
            mmePort = arr[CsfbIndex.mmePort];
            enbIpAdd = arr[CsfbIndex.enbIpAdd];
            enbPort = arr[CsfbIndex.enbPort];
            tac = arr[CsfbIndex.tac];
            eci = arr[CsfbIndex.eci];
            sourLai = arr[CsfbIndex.sourLai];
            lai = arr[CsfbIndex.lai];
            sai = arr[CsfbIndex.sai];
            endlai = arr[CsfbIndex.endlai];
            endsai = arr[CsfbIndex.endsai];
            endtac = arr[CsfbIndex.endtac];
            endeci = arr[CsfbIndex.endeci];
            ednmsc = arr[CsfbIndex.ednmsc];
            endrnc = arr[CsfbIndex.endrnc];
            msc = arr[CsfbIndex.msc];
            rnc = arr[CsfbIndex.rnc];
            csfbResult = arr[CsfbIndex.csfbResult];
            failuremsg = arr[CsfbIndex.failuremsg];
            failureCause = arr[CsfbIndex.failureCause];
            firstCsfailuremsg = arr[CsfbIndex.firstCsfailuremsg];
            firstCserrorcode = arr[CsfbIndex.firstCserrorcode];
            csmaincause = arr[CsfbIndex.csmaincause];
            csreleasermsg = arr[CsfbIndex.csreleasermsg];
            csreleasercode = arr[CsfbIndex.csreleasercode];
            callId = arr[CsfbIndex.callId];
            csrantype = arr[CsfbIndex.csrantype];
            endenbIpAdd = arr[CsfbIndex.endenbIpAdd];
            endenbPort = arr[CsfbIndex.endenbPort];
            endmmeUeS1apId = arr[CsfbIndex.endmmeUeS1apId];
            endmmePort = arr[CsfbIndex.endmmePort];
            endmmeGroupId = arr[CsfbIndex.endmmeGroupId];
            endmmeCode = arr[CsfbIndex.endmmeCode];
            oldmsc = arr[CsfbIndex.oldmsc];
            oldlai = arr[CsfbIndex.oldlai];
            s1Delay = arr[CsfbIndex.s1Delay];
            csfbDelay = arr[CsfbIndex.csfbDelay];
            delaySgspagreq = arr[CsfbIndex.delaySgspagreq];
            delaySgspagres = arr[CsfbIndex.delaySgspagres];
            delayPagingreq = arr[CsfbIndex.delayPagingreq];
            delayServreq = arr[CsfbIndex.delayServreq];
            delayServres = arr[CsfbIndex.delayServres];
            delayInitreq = arr[CsfbIndex.delayInitreq];
            delayInitres = arr[CsfbIndex.delayInitres];
            delayCsfbrel = arr[CsfbIndex.delayCsfbrel];
            delayCsfirstmsg = arr[CsfbIndex.delayCsfirstmsg];
            delayCslureq = arr[CsfbIndex.delayCslureq];
            delayCslures = arr[CsfbIndex.delayCslures];
            delayCsservreq = arr[CsfbIndex.delayCsservreq];
            delayCsservres = arr[CsfbIndex.delayCsservres];
            delayTime = arr[CsfbIndex.delayTime];
            delayCallproc = arr[CsfbIndex.delayCallproc];
            delayCsassireq = arr[CsfbIndex.delayCsassireq];
            delayCsassires = arr[CsfbIndex.delayCsassires];
            delayCssri = arr[CsfbIndex.delayCssri];
            delayCssrires = arr[CsfbIndex.delayCssrires];
            delayCsprn = arr[CsfbIndex.delayCsprn];
            delayCsprnres = arr[CsfbIndex.delayCsprnres];
            delayCsiam = arr[CsfbIndex.delayCsiam];
            delayCsacm = arr[CsfbIndex.delayCsacm];
            delayCsanm = arr[CsfbIndex.delayCsanm];
            delayCsauthreq1st = arr[CsfbIndex.delayCsauthreq1st];
            delayCsauthreq2nd = arr[CsfbIndex.delayCsauthreq2nd];
            delayCsauthres = arr[CsfbIndex.delayCsauthres];
            delayCscipcmd = arr[CsfbIndex.delayCscipcmd];
            delayCscipcmp = arr[CsfbIndex.delayCscipcmp];
            delayCstmsicmd = arr[CsfbIndex.delayCstmsicmd];
            delayCstmsicmp = arr[CsfbIndex.delayCstmsicmp];
            delayCsidreq = arr[CsfbIndex.delayCsidreq];
            delayCsidres = arr[CsfbIndex.delayCsidres];
            delayCsalerting = arr[CsfbIndex.delayCsalerting];
            delayCsconnect = arr[CsfbIndex.delayCsconnect];
            talkduration = arr[CsfbIndex.talkduration];
            delayCsrel = arr[CsfbIndex.delayCsrel];
            delayIteretreq = arr[CsfbIndex.delayIteretreq];
            delayIteretres = arr[CsfbIndex.delayIteretres];
            delayReleasecmp = arr[CsfbIndex.delayReleasecmp];
        }
    }

    public void initialize() {
        procedureStarttime = null;
        procedureEndtime = null;
        procedureType = null;
        rMsisdn = null;
        rImsi = null;
        rImei = null;
        msisdn = null;
        imsi = null;
        imei = null;
        mTmsi = null;
        tmsi = null;
        mmeIpAdd = null;
        mmeUeS1apId = null;
        mmeGroupId = null;
        mmeCode = null;
        mmePort = null;
        enbIpAdd = null;
        enbPort = null;
        tac = null;
        eci = null;
        sourLai = null;
        lai = null;
        sai = null;
        endlai = null;
        endsai = null;
        endtac = null;
        endeci = null;
        ednmsc = null;
        endrnc = null;
        msc = null;
        rnc = null;
        csfbResult = null;
        failuremsg = null;
        failureCause = null;
        firstCsfailuremsg = null;
        firstCserrorcode = null;
        csmaincause = null;
        csreleasermsg = null;
        csreleasercode = null;
        callId = null;
        csrantype = null;
        endenbIpAdd = null;
        endenbPort = null;
        endmmeUeS1apId = null;
        endmmePort = null;
        endmmeGroupId = null;
        endmmeCode = null;
        oldmsc = null;
        oldlai = null;
        s1Delay = null;
        csfbDelay = null;
        delaySgspagreq = null;
        delaySgspagres = null;
        delayPagingreq = null;
        delayServreq = null;
        delayServres = null;
        delayInitreq = null;
        delayInitres = null;
        delayCsfbrel = null;
        delayCsfirstmsg = null;
        delayCslureq = null;
        delayCslures = null;
        delayCsservreq = null;
        delayCsservres = null;
        delayTime = null;
        delayCallproc = null;
        delayCsassireq = null;
        delayCsassires = null;
        delayCssri = null;
        delayCssrires = null;
        delayCsprn = null;
        delayCsprnres = null;
        delayCsiam = null;
        delayCsacm = null;
        delayCsanm = null;
        delayCsauthreq1st = null;
        delayCsauthreq2nd = null;
        delayCsauthres = null;
        delayCscipcmd = null;
        delayCscipcmp = null;
        delayCstmsicmd = null;
        delayCstmsicmp = null;
        delayCsidreq = null;
        delayCsidres = null;
        delayCsalerting = null;
        delayCsconnect = null;
        talkduration = null;
        delayCsrel = null;
        delayIteretreq = null;
        delayIteretres = null;
        delayReleasecmp = null;
    }

    @Override
    public String toString() {
        CountNum.outputNum+=1;
        RedisUntil.backfill(this);
        StringBuilder sb = new StringBuilder();
        sb.append(procedureStarttime).append("|")
                .append(procedureEndtime).append("|")
                .append(procedureType).append("|")
                .append(rMsisdn).append("|")
                .append(rImsi).append("|")
                .append(rImei).append("|")
                .append(msisdn).append("|")
                .append(imsi).append("|")
                .append(imei).append("|")
                .append(mTmsi).append("|")
                .append(tmsi).append("|")
                .append(mmeIpAdd).append("|")
                .append(mmeUeS1apId).append("|")
                .append(mmeGroupId).append("|")
                .append(mmeCode).append("|")
                .append(mmePort).append("|")
                .append(enbIpAdd).append("|")
                .append(enbPort).append("|")
                .append(tac).append("|")
                .append(eci).append("|")
                .append(sourLai).append("|")
                .append(lai).append("|")
                .append(sai).append("|")
                .append(endlai).append("|")
                .append(endsai).append("|")
                .append(endtac).append("|")
                .append(endeci).append("|")
                .append(ednmsc).append("|")
                .append(endrnc).append("|")
                .append(msc).append("|")
                .append(rnc).append("|")
                .append(csfbResult).append("|")
                .append(failuremsg).append("|")
                .append(failureCause).append("|")
                .append(firstCsfailuremsg).append("|")
                .append(firstCserrorcode).append("|")
                .append(csmaincause).append("|")
                .append(csreleasermsg).append("|")
                .append(csreleasercode).append("|")
                .append(callId).append("|")
                .append(csrantype).append("|")
                .append(endenbIpAdd).append("|")
                .append(endenbPort).append("|")
                .append(endmmeUeS1apId).append("|")
                .append(endmmePort).append("|")
                .append(endmmeGroupId).append("|")
                .append(endmmeCode).append("|")
                .append(oldmsc).append("|")
                .append(oldlai).append("|")
                .append(s1Delay).append("|")
                .append(csfbDelay).append("|")
                .append(delaySgspagreq).append("|")
                .append(delaySgspagres).append("|")
                .append(delayPagingreq).append("|")
                .append(delayServreq).append("|")
                .append(delayServres).append("|")
                .append(delayInitreq).append("|")
                .append(delayInitres).append("|")
                .append(delayCsfbrel).append("|")
                .append(delayCsfirstmsg).append("|")
                .append(delayCslureq).append("|")
                .append(delayCslures).append("|")
                .append(delayCsservreq).append("|")
                .append(delayCsservres).append("|")
                .append(delayTime).append("|")
                .append(delayCallproc).append("|")
                .append(delayCsassireq).append("|")
                .append(delayCsassires).append("|")
                .append(delayCssri).append("|")
                .append(delayCssrires).append("|")
                .append(delayCsprn).append("|")
                .append(delayCsprnres).append("|")
                .append(delayCsiam).append("|")
                .append(delayCsacm).append("|")
                .append(delayCsanm).append("|")
                .append(delayCsauthreq1st).append("|")
                .append(delayCsauthreq2nd).append("|")
                .append(delayCsauthres).append("|")
                .append(delayCscipcmd).append("|")
                .append(delayCscipcmp).append("|")
                .append(delayCstmsicmd).append("|")
                .append(delayCstmsicmp).append("|")
                .append(delayCsidreq).append("|")
                .append(delayCsidres).append("|")
                .append(delayCsalerting).append("|")
                .append(delayCsconnect).append("|")
                .append(talkduration).append("|")
                .append(delayCsrel).append("|")
                .append(delayIteretreq).append("|")
                .append(delayIteretres).append("|")
                .append(delayReleasecmp);
        return sb.toString();
    }
}
