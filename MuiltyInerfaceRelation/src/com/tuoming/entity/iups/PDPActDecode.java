package com.tuoming.entity.iups;

import com.tuoming.tools.CommonUtils;
import com.tuoming.tools.RadixDeal;
import com.tuoming.tools.RedisUntil;
import com.tuoming.tools.SimpleDateDeal;

/**
 * PDP激活话单结构体
 */
public class PDPActDecode extends CommonIuPS {
    private String OUT_SEPARATOR = "|";

    public String delay_Actireq = "";	  //激活PDP请求和XDR的第一条消息的时间差
    public String delay_Actires = "";	  //激活PDP成功时延
    public String delay_Rabreq = "";      //RAB请求时延
    public String delay_Rabres = "";      //RAB应答时延
    public String result_Rabrel = "";	  //RAB释放结果
    public String cause_Rabrel = "";     //RAB释放原因
    public String msApn = "";	       	  //MSAPN

    @Override
    public void clear() {
        super.clear();
        delay_Actireq = "";
        delay_Actires = "";
        delay_Rabreq = "";
        delay_Rabres = "";
        result_Rabrel = "";
        cause_Rabrel = "";
        msApn = "";
    }

    @Override
    public void setEventType() {
        this.eventType = "52";
    }

    /*@Override
    public void setSGSN(String[] xdrSplits, int index) {
        //网络侧
        if("21".equals(this.voiceType)||"24".equals(this.voiceType)){
            this.SGSN = xdrSplits[index];
        }else{
            this.SGSN = RadixDeal.IP2Decimal(xdrSplits[index]);
        }

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

    public void setErrorCode() {
        this.errorCode = "0";

    }

    @Override
    public void setFailurepreMsg(String[] xdrSplits,int index) {
        if("1".equals(xdrSplits[index]) || "2".equals(xdrSplits[index])){
            if("15".equals(this.voiceType) || "21".equals(this.voiceType)){
                this.failurepreMsg = "65";
            }else{
                this.failurepreMsg = "77";
            }
        }
    }

    public void setSubevtType() {
        if("15".equals(this.voiceType)){
            this.subevtType = "3407873";
        }else{
            this.subevtType = "3407874";
        }
    }

    public void setTMSI(String[] xdrSplits, int index) {
        this.TMSI = "0";
    }


    public void setDelay_Actireq() {
        this.delay_Actireq = "0";
    }

    public void setDelay_Actires(String[] xdrSplits, int Com_index,int End_index,int Start_index) {
        if("0".equals(xdrSplits[Com_index])){
            String endTime = xdrSplits[End_index];
            endTime = endTime.substring(0,endTime.indexOf(".")+4);
            String startTime = xdrSplits[Start_index];
            startTime = startTime.substring(0,startTime.indexOf(".")+4);
            this.delay_Actires = (SimpleDateDeal.String2TimeStamp(endTime)- SimpleDateDeal.String2TimeStamp(startTime))+"";
        }
    }

    public void setDelay_Rabreq(String[] xdrSplits, int index) {
        this.delay_Rabreq = xdrSplits[index];
    }

    public void setDelay_Rabres(String[] xdrSplits, int index) {
        this.delay_Rabres = xdrSplits[index];
    }

    public void setResult_Rabrel(String[] xdrSplits, int index) {
        Integer rabFlag = CommonUtils.strToInteger(xdrSplits[index]);
        if(rabFlag != null){
            if(rabFlag==83||rabFlag==11||rabFlag==41||rabFlag==16||rabFlag==40){
                this.result_Rabrel = "0";
            }else{
                this.result_Rabrel = "1";
            }
        }
    }

    public void setCause_Rabrel(String[] xdrSplits, int index) {
        this.cause_Rabrel = xdrSplits[index];
    }

    public void setMsApn(String[] xdrSplits, int index) {
        this.msApn = xdrSplits[index];
    }

    /*
     * PDP话单总构造
     */
    public void setPDPActiveObj(String[] xdrSplits){
        clear();
        setVoiceType(xdrSplits,PDPActIndex.VOICETYPE);
        //TODO 根据网络还是用户发起 取值位置不同
        //网络侧发起一次及二次激活
        if("21".equals(this.voiceType)||"24".equals(this.voiceType)){
            setStarttime(xdrSplits,PDPActIndex.NW_STARTTIME);
            setEndtime(xdrSplits,PDPActIndex.NW_ENDTIME);
            setCombFlag(xdrSplits,PDPActIndex.NW_COMBFLAG);
            setResult(xdrSplits,PDPActIndex.NW_COMBFLAG,PDPActIndex.RESULT);
            setFailureMsg(xdrSplits,PDPActIndex.NW_COMBFLAG,PDPActIndex.NW_REQCODE);
            setFailurepreMsg(xdrSplits,PDPActIndex.NW_COMBFLAG);
            setDelay_Actires(xdrSplits,PDPActIndex.NW_COMBFLAG,PDPActIndex.NW_ENDTIME,PDPActIndex.NW_STARTTIME);
        }else{//用户侧发起一次及二次激活
            setStarttime(xdrSplits,PDPActIndex.MS_STARTTIME);
            setEndtime(xdrSplits,PDPActIndex.MS_ENDTIME);
            setCombFlag(xdrSplits,PDPActIndex.MS_COMBFLAG);
            setResult(xdrSplits,PDPActIndex.MS_COMBFLAG,PDPActIndex.RESULT);
            setFailureMsg(xdrSplits,PDPActIndex.MS_COMBFLAG,PDPActIndex.MS_REQCODE);
            setFailurepreMsg(xdrSplits,PDPActIndex.MS_COMBFLAG);
            setSubevtType();
            setDelay_Actires(xdrSplits,PDPActIndex.MS_COMBFLAG,PDPActIndex.MS_ENDTIME,PDPActIndex.MS_STARTTIME);
        }

        //endtime
        setEventType();
        setRanType(xdrSplits,PDPActIndex.RANTYPE);
        setImsi(xdrSplits,PDPActIndex.IMSI);
        setImei(xdrSplits,PDPActIndex.IMEI);
        setAPN(xdrSplits,PDPActIndex.APN);
        setSGSN(xdrSplits,PDPActIndex.SGSN);
        setRNC(xdrSplits,PDPActIndex.RNC);
        setRAC(xdrSplits,PDPActIndex.MCC,PDPActIndex.MNC,PDPActIndex.LAC,PDPActIndex.RAC);
        setCell(xdrSplits,PDPActIndex.MCC,PDPActIndex.MNC,PDPActIndex.LAC,PDPActIndex.CELL);
        sethOperator();
        setRoamType();
        //TODO 存疑 待处理
        setErrorCode();
        //releasercode
        setReleaserCode(xdrSplits,PDPActIndex.RESULT);
        setNTId(xdrSplits,PDPActIndex.NTID);
        setTMSI(xdrSplits,PDPActIndex.TMSI);
        //iu release
        setDelay_Actireq();
        setMsApn(xdrSplits,PDPActIndex.APN);
    }

    //以下是关联相关的方法
    /*
     * 关联iu release话单
     */

    /*
     * 关联RAB建立或修改或释放子流程
     */
    public void relRABRelease(String[] rabXdr){
        setResult_Rabrel(rabXdr,RABIndex.RAB_Res);
        setCause_Rabrel(rabXdr,RABIndex.RAB_Res);
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
                append(delay_Actireq).append(OUT_SEPARATOR).
                append(delay_Actires).append(OUT_SEPARATOR).
                append(delay_Rabreq).append(OUT_SEPARATOR).
                append(delay_Rabres).append(OUT_SEPARATOR).
                append(result_Rabrel).append(OUT_SEPARATOR).
                append(cause_Rabrel).append(OUT_SEPARATOR).
                append(msApn);
        return sb.toString();
    }
}
