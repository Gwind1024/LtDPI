package com.tuoming.entity.iups;

import com.tuoming.tools.CommonUtils;
import com.tuoming.tools.RadixDeal;
import com.tuoming.tools.RedisUntil;

/**
 * iups口去附着结构体
 */
public class DetachDecode extends CommonIuPS {
    private String OUT_SEPARATOR = "|";

    @Override
    public void setEventType() {
        this.eventType = "58";
    }

    /*@Override
    public void setSGSN(String[] xdrSplits, int index) {
        this.SGSN = RadixDeal.IP2Decimal(xdrSplits[index]);
    }*/

    @Override
    public void setFailurepreMsg(String[] xdrSplits,int index) {
        if("1".equals(xdrSplits[index]) || "2".equals(xdrSplits[index])){
            this.failurepreMsg = "5";
        }
    }

    @Override
    public void setSubevtType(String[] xdrSplits, int index) {
        String flag = xdrSplits[index];
        if("1".equals(flag)){
            this.subevtType = "3801089";
        }else if("2".equals(flag)){
            this.subevtType = "3801090";
        }else if("3".equals(flag)){
            this.subevtType = "3801091";
        }
    }

    /*
     * 总体构造
     */
    public void setDetachObj(String[] xdrSplits){
        super.clear();
        setVoiceType(xdrSplits,DetachIndex.VOICETYPE);
        setStarttime(xdrSplits, DetachIndex.STARTTIME);
        //endtime
        setEndtime(xdrSplits,DetachIndex.ENDTIME);
        setEventType();
        setRanType(xdrSplits, DetachIndex.RANTYPE);
        setCombFlag(xdrSplits, DetachIndex.COMBFLAG);
        setImsi(xdrSplits, DetachIndex.IMSI);
        setImei(xdrSplits, DetachIndex.IMEI);
        setAPN(xdrSplits, DetachIndex.APN);
        setSGSN(xdrSplits, DetachIndex.SGSN);
        setRNC(xdrSplits, DetachIndex.RNC);
        setRAC(xdrSplits, DetachIndex.MCC, DetachIndex.MNC, DetachIndex.LAC, DetachIndex.RAC);
        setCell(xdrSplits, DetachIndex.MCC, DetachIndex.MNC, DetachIndex.LAC, DetachIndex.CELL);
        setRoamType();
//        setResult(xdrSplits, DetachIndex.COMBFLAG, DetachIndex.RESULT);
        setErrorCode(xdrSplits, DetachIndex.COMBFLAG, DetachIndex.RESULT);
        //releasercode
        setReleaserCode(xdrSplits,DetachIndex.RESULT);
        setFailureMsg(xdrSplits, DetachIndex.COMBFLAG, DetachIndex.RESULT);
        setFailurepreMsg(xdrSplits, DetachIndex.COMBFLAG);
        setNTId(xdrSplits, DetachIndex.NTID);
        setSubevtType(xdrSplits, DetachIndex.SUBEVTTYPE);
        setTMSI(xdrSplits, DetachIndex.TMSI);
        //iu release
    }

    //以下是关联相关的方法


    @Override
    public String toString() {
//        super.toString();
        RedisUntil.backfill(this);
        StringBuffer sb = new StringBuffer();
        sb.append(starttime).append(OUT_SEPARATOR).
                append(endtime).append(OUT_SEPARATOR).
                append(eventType).append(OUT_SEPARATOR).
                append(ranType).append(OUT_SEPARATOR).
                append(combFlag).append(OUT_SEPARATOR).
                append(phone).append(OUT_SEPARATOR).
                append(imsi).append(OUT_SEPARATOR).
                append(imei).append(OUT_SEPARATOR).
                append(userIP).append(OUT_SEPARATOR).
                append(hCountry).append(OUT_SEPARATOR).
                append(hProvince).append(OUT_SEPARATOR).
                append(hCity).append(OUT_SEPARATOR).
                append(APN).append(OUT_SEPARATOR).
                append(SGSN).append(OUT_SEPARATOR).
                append(GGSN).append(OUT_SEPARATOR).
                append(RNC).append(OUT_SEPARATOR).
                append(RAC).append(OUT_SEPARATOR).
                append(cell).append(OUT_SEPARATOR).
                append(hOperator).append(OUT_SEPARATOR).
                append(visitProv).append(OUT_SEPARATOR).
                append(visitCity).append(OUT_SEPARATOR).
                append(roamType).append(OUT_SEPARATOR).
                append(result).append(OUT_SEPARATOR).
                append(errorCode).append(OUT_SEPARATOR).
                append(releaserCode).append(OUT_SEPARATOR).
                append(failureMsg).append(OUT_SEPARATOR).
                append(failurepreMsg).append(OUT_SEPARATOR).
                append(NTId).append(OUT_SEPARATOR).
                append(subevtType).append(OUT_SEPARATOR).
                append(TMSI).append(OUT_SEPARATOR).
                append(TLLI).append(OUT_SEPARATOR).
                append(delay_Idreq).append(OUT_SEPARATOR).
                append(delay_Idres).append(OUT_SEPARATOR).
                append(tryIds).append(OUT_SEPARATOR).
                append(delay_Aucreq).append(OUT_SEPARATOR).
                append(delay_Aucres).append(OUT_SEPARATOR).
                append(tryAuths).append(OUT_SEPARATOR).
                append(authTimes).append(OUT_SEPARATOR).
                append(delay_Secreq).append(OUT_SEPARATOR).
                append(delay_Secres).append(OUT_SEPARATOR).
                append(trySecs).append(OUT_SEPARATOR).
                append(delay_Relreq).append(OUT_SEPARATOR).
                append(delay_Relcmd).append(OUT_SEPARATOR).
                append(delay_Relcmp).append(OUT_SEPARATOR).
                append(result_Iurel).append(OUT_SEPARATOR).
                append(cause_Iurel);
        return sb.toString();
    }
}
