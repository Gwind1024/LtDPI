package com.tuoming.entity.iups;

import com.tuoming.tools.RadixDeal;
import com.tuoming.tools.RedisUntil;
import com.tuoming.tools.SimpleDateDeal;

/**
 * iups口PDP去激活话单结构体
 */
public class PDPDeActDecode extends CommonIuPS {
    private String OUT_SEPARATOR = "|";

    public String delay_Deactreq = "";  //去激活PDP请求和XDR的第一条消息的时间差
    public String delay_Deactres = "";  //去激活PDP响应和XDR的第一条消息的时间差
    public String cause_Deact = "";     //去激活原因
    public String result_Rabrel = "";	 //RAB释放结果
    public String cause_Rabrel = "";	 //RAB释放原因

    @Override
    public void clear() {
        super.clear();
        delay_Deactreq	=	"";
        delay_Deactres	=	"";
        cause_Deact	=	"";
        result_Rabrel	=	"";
        cause_Rabrel	=	"";

    }

    @Override
    public void setEventType() {
        this.eventType = "57";
    }

    /*@Override
    public void setSGSN(String[] xdrSplits, int index) {
        this.SGSN = RadixDeal.IP2Decimal(xdrSplits[index]);
    }*/

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
            this.failurepreMsg = "70";
        }
    }

    public void setSubevtType() {
        //网络侧
        if("22".equals(this.voiceType)){
            this.subevtType = "3735554";
        }else{
            this.subevtType = "3735553";
        }
    }

    public void setDelay_Deactreq() {
        this.delay_Deactreq = "0";
    }

    public void setDelay_Deactres(String[] xdrSplits, int Com_index,int End_index,int Start_index) {
        if("0".equals(xdrSplits[Com_index])){
            String endTime = xdrSplits[End_index];
            endTime = endTime.substring(0,endTime.indexOf(".")+4);
            String startTime = xdrSplits[Start_index];
            startTime = startTime.substring(0,startTime.indexOf(".")+4);
            this.delay_Deactres = (SimpleDateDeal.String2TimeStamp(endTime)- SimpleDateDeal.String2TimeStamp(startTime))+"";
        }
    }

    public void setCause_Deact() {
        this.cause_Deact = "0";
    }

    public void setResult_Rabrel(String[] xdrSplits, int index) {
        this.result_Rabrel = xdrSplits[index];
    }

    public void setCause_Rabrel(String[] xdrSplits, int index) {
        this.cause_Rabrel = xdrSplits[index];
    }
    
    /*
     * 总的结构体
     */
    public void setPDPDeActiveObj(String[] xdrSplits){
        clear();
        setVoiceType(xdrSplits,PDPDeActIndex.VOICETYPE);
        setStarttime(xdrSplits, PDPDeActIndex.STARTTIME);
        //endtime
        setEndtime(xdrSplits,PDPDeActIndex.ENDTIME);
        setEventType();
        setRanType(xdrSplits, PDPDeActIndex.RANTYPE);
        setCombFlag(xdrSplits, PDPDeActIndex.COMBFLAG);
        setImsi(xdrSplits, PDPDeActIndex.IMSI);
        setImei(xdrSplits, PDPDeActIndex.IMEI);
        setAPN(xdrSplits, PDPDeActIndex.APN);
        setSGSN(xdrSplits, PDPDeActIndex.SGSN);
        setRNC(xdrSplits, PDPDeActIndex.RNC);
        setRAC(xdrSplits, PDPDeActIndex.MCC, PDPDeActIndex.MNC, PDPDeActIndex.LAC, PDPDeActIndex.RAC);
        setCell(xdrSplits, PDPDeActIndex.MCC, PDPDeActIndex.MNC, PDPDeActIndex.LAC, PDPDeActIndex.CELL);
        setRoamType();
        setResult(xdrSplits, PDPDeActIndex.COMBFLAG, PDPDeActIndex.RESULT);
        setErrorCode(xdrSplits, PDPDeActIndex.COMBFLAG, PDPDeActIndex.RESULT);
        //releasercode
        setReleaserCode(xdrSplits,PDPDeActIndex.RESULT);
        setFailureMsg(xdrSplits, PDPDeActIndex.COMBFLAG, PDPDeActIndex.TRANSID);
        setFailurepreMsg(xdrSplits, PDPDeActIndex.COMBFLAG);
        setNTId(xdrSplits, PDPDeActIndex.NTID);
        setSubevtType();
        setTMSI(xdrSplits, PDPDeActIndex.TMSI);
        setDelay_Deactreq();
        setDelay_Deactres(xdrSplits,PDPDeActIndex.COMBFLAG,PDPDeActIndex.ENDTIME,PDPDeActIndex.STARTTIME);
        setCause_Deact();
    }

    //以下是关联相关的方法
    /*
     * 关联iu release话单
     */

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
                append(delay_Deactreq).append(OUT_SEPARATOR).
                append(delay_Deactres).append(OUT_SEPARATOR).
                append(cause_Deact).append(OUT_SEPARATOR).
                append(result_Rabrel).append(OUT_SEPARATOR).
                append(cause_Rabrel);
        return sb.toString();
    }
}
