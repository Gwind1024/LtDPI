package com.tuoming.entity.iups;

import com.tuoming.tools.RadixDeal;
import com.tuoming.tools.RedisUntil;
import com.tuoming.tools.SimpleDateDeal;

/**
 * iups口pdp修改话单结构体
 */
public class PDPModifyDecode extends CommonIuPS {
    private String OUT_SEPARATOR = "|";

    public String delay_Modirabreq = "";    //修改RAB请求时延
    public String delay_Modirabres = "";    //修改RAB成功时延
    public String iuEventType = "";         //IU事件类型
    public String iuSubevtType = "";        //IU子事件类型

    @Override
    public void clear() {
        super.clear();
        delay_Modirabreq = "";
        delay_Modirabres = "";
        iuEventType = "";
        iuSubevtType = "";
    }

    @Override
    public void setEventType() {
        this.eventType = "55";
    }

    @Override
    public void setSGSN(String[] xdrSplits, int index) {
        this.SGSN = RadixDeal.IP2Decimal(xdrSplits[index]);
    }

    @Override
    public void setResult(String[] xdrSplits, int R1_index,int R2_index) {
        if("0".equals(xdrSplits[R1_index])){
            this.result = "0";
        }else if("2".equals(xdrSplits[R1_index])){
            this.result = "3";
        }else if("1".equals(xdrSplits[R1_index])){
            String result = xdrSplits[R2_index];
            if("27".equals(result) || "66".equals(result) || "112".equals(result)){
                this.result = "1";
            }else{
                this.result = "2";
            }
        }
    }

    @Override
    public void setErrorCode(String[] xdrSplits, int R1_index,int R2_index) {
        this.errorCode = xdrSplits[R2_index];
    }

    @Override
    public void setFailurepreMsg(String[] xdrSplits,int index) {
        if("1".equals(xdrSplits[index]) || "2".equals(xdrSplits[index])){
            if("23".equals(this.voiceType)){
                this.failurepreMsg = "72";
            }else{
                this.failurepreMsg = "74";
            }
        }
    }

    public void setSubevtType() {
        if("23".equals(this.voiceType)){
            this.subevtType = "3604482";
        }else{
            this.subevtType = "3604481";
        }
    }



    public void setDelay_Modirabreq(String[] xdrSplits, int index) {
        String ranStart = xdrSplits[index];
        ranStart = ranStart.substring(0,ranStart.indexOf(".")+4);
        String modiStart = this.starttime;
        modiStart = modiStart.substring(0,modiStart.indexOf(".")+4);
        this.delay_Modirabreq = (SimpleDateDeal.String2TimeStamp(ranStart)-SimpleDateDeal.String2TimeStamp(modiStart))+"";
    }

    public void setDelay_Modirabres(String[] xdrSplits, int index) {
        String ranEnd = xdrSplits[index];
        ranEnd = ranEnd.substring(0,ranEnd.indexOf(".")+4);
        String modiStart = this.starttime;
        modiStart = modiStart.substring(0,modiStart.indexOf(".")+4);
        this.delay_Modirabres = (SimpleDateDeal.String2TimeStamp(ranEnd)-SimpleDateDeal.String2TimeStamp(modiStart))+"";
    }

    public void setIuEventType() {
        this.iuEventType = "55";
    }

    public void setIuSubevtType() {
        this.iuSubevtType = this.subevtType;
    }

    /*
     * 总的构造
     */
    public void setPDPModifyObj(String[] xdrSplits){
        clear();
        setVoiceType(xdrSplits,PDPModifyIndex.VOICETYPE);
        if("23".equals(this.voiceType)){
            setStarttime(xdrSplits,PDPModifyIndex.NW_STARTTIME);
            setEndtime(xdrSplits,PDPModifyIndex.NW_ENDTIME);
            setCombFlag(xdrSplits,PDPModifyIndex.NW_COMBFLAG);
            setResult(xdrSplits,PDPModifyIndex.NW_COMBFLAG,PDPModifyIndex.RESULT);
            setFailureMsg(xdrSplits,PDPModifyIndex.NW_COMBFLAG,PDPModifyIndex.NW_REQCODE);
            setFailurepreMsg(xdrSplits,PDPModifyIndex.NW_COMBFLAG);
        }else{
            setStarttime(xdrSplits,PDPModifyIndex.MS_STARTTIME);
            setEndtime(xdrSplits,PDPModifyIndex.MS_ENDTIME);
            setCombFlag(xdrSplits,PDPModifyIndex.MS_COMBFLAG);
            setResult(xdrSplits,PDPModifyIndex.MS_COMBFLAG,PDPModifyIndex.RESULT);
            setFailureMsg(xdrSplits,PDPModifyIndex.MS_COMBFLAG,PDPModifyIndex.MS_REQCODE);
            setFailurepreMsg(xdrSplits,PDPModifyIndex.MS_COMBFLAG);
        }
        setEventType();
        setRanType(xdrSplits,PDPModifyIndex.RANTYPE);
        setImsi(xdrSplits,PDPModifyIndex.IMSI);
        setImei(xdrSplits,PDPModifyIndex.IMEI);
        setAPN(xdrSplits,PDPModifyIndex.APN);
        setSGSN(xdrSplits,PDPModifyIndex.SGSN);
        setRNC(xdrSplits,PDPModifyIndex.RNC);
        setRAC(xdrSplits,PDPModifyIndex.MCC,PDPModifyIndex.MNC,PDPModifyIndex.LAC,PDPModifyIndex.RAC);
        setCell(xdrSplits,PDPModifyIndex.MCC,PDPModifyIndex.MNC,PDPModifyIndex.LAC,PDPModifyIndex.CELL);
        sethOperator();
        setRoamType();
        setErrorCode(xdrSplits,-1,PDPModifyIndex.RESULT);
        setReleaserCode(xdrSplits,PDPModifyIndex.RESULT);
        setNTId(xdrSplits,PDPModifyIndex.NTID);
        setSubevtType();
        setTMSI(xdrSplits,PDPModifyIndex.TMSI);
        setIuEventType();
        setIuSubevtType();
    }

    //以下是关联相关的方法

    /*
     * 关联RAB建立或修改或释放子流程
     */
    public void relRABRelease(String[] rabXdr){
        setDelay_Modirabreq(rabXdr,RABIndex.StartTime);
        setDelay_Modirabres(rabXdr,RABIndex.EndTime);
    }


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
                append(cause_Iurel).append(OUT_SEPARATOR).
                append(delay_Modirabreq).append(OUT_SEPARATOR).
                append(delay_Modirabres).append(OUT_SEPARATOR).
                append(iuEventType).append(OUT_SEPARATOR).
                append(iuSubevtType);
        return sb.toString();
    }
}
