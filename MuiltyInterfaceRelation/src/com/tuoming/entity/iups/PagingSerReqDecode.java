package com.tuoming.entity.iups;

import com.tuoming.tools.CommonUtils;
import com.tuoming.tools.RadixDeal;
import com.tuoming.tools.RedisUntil;
import com.tuoming.tools.SimpleDateDeal;

/**
 * iups口寻呼和服务请求话单结构体
 */
public class PagingSerReqDecode extends CommonIuPS {
    private String OUT_SEPARATOR = "|";

    public String delay_Pagingreq = "";    //寻呼请求时延
    public String delay_Pagingres = "";    //寻呼成功时延
    public String delay_Servreq = "";      //业务请求时延
    public String delay_Servres = "";      //业务请求成功时延
    public String delay_Rabreq = "";       //RAB指配请求时延
    public String delay_Rabres = "";       //RAB指配成功时延
    public String srcPort = "";            //触发寻呼的PDU源端口
    public String dstPort = "";            //触发寻呼的PDU目的端口
    public String host = "";               //主机名称
    public String pduType = "";            //触发寻呼的PDU类型
    public String result_Rabrel = "";      //RAB释放结果
    public String cause_Rabrel = "";       //RAB释放原因
    public String sigUpRate = "";          //签约上行速率
    public String sigDownRate = "";        //签约下行速率
    public String conUpRate = "";          //协商上行速率
    public String conDownRate = "";        //协商下行速率
    public String actUpRate = "";          //实际上行速率
    public String actDownRate = "";        //实际下行速率
    public String rand = "";               //鉴权随机数
    public String autn = "";               //鉴权向量


    @Override
    public void clear() {
        super.clear();
        delay_Pagingreq = "";
        delay_Pagingres = "";
        delay_Servreq = "";
        delay_Servres = "";
        delay_Rabreq = "";
        delay_Rabres = "";
        srcPort = "";
        dstPort = "";
        host = "";
        pduType = "";
        result_Rabrel = "";
        cause_Rabrel = "";
        sigUpRate = "";
        sigDownRate = "";
        conUpRate = "";
        conDownRate = "";
        actUpRate = "";
        actDownRate = "";
        rand = "";
        autn = "";
    }

    @Override
    public void setEventType() {
        this.eventType = "53";
    }

    @Override
    public void setRanType(String[] xdrSplits, int index) {
        this.ranType = "0";
    }

    @Override
    public void setSGSN(String[] xdrSplits, int index) {
        this.SGSN = RadixDeal.IP2Decimal(xdrSplits[index]);
    }

    @Override
    public void setResult(String[] xdrSplits, int R1_index,int R2_index) {
        if("2".equals(xdrSplits[R1_index])){
            this.result = "3";
        }else if("1".equals(xdrSplits[R1_index])){
            Integer flag = CommonUtils.strToInteger(xdrSplits[R2_index]);
            if(flag == null){
                this.result = "";
            }else if(flag>=2&&flag<=9){
                this.result = "1";
            }else{
                this.result = "2";
            }
        }
    }

    public void setResult(String[] xdrSplits, int index) {
        if("2".equals(xdrSplits[index])){
            this.result = "3";
        }
    }

    public void setErrorCode(String[] xdrSplits, int index) {
        this.errorCode = xdrSplits[index];
    }

    public void setFailurepreMsg(String[] xdrSplits,int Flag_index,int Use_index) {
        if(!"0".equals(xdrSplits[Flag_index])){
            this.failurepreMsg = xdrSplits[Use_index];
        }
    }

    public void setSubevtType() {
        this.subevtType = "3473410";
    }

    public void setDelay_Pagingreq() {
        this.delay_Pagingreq = "0";
    }
    //todo 区分寻呼和业务
    public void setDelay_Pagingres(String[] xdrSplits, int index) {
        if("0".equals(xdrSplits[index])){
            String endTime = this.endtime;
            endTime = endTime.substring(0,endTime.indexOf(".")+4);
            String startTime = this.starttime;
            startTime = startTime.substring(0,startTime.indexOf(".")+4);
            this.delay_Pagingres = (SimpleDateDeal.String2TimeStamp(endTime)-SimpleDateDeal.String2TimeStamp(startTime))+"";
        }
    }

    public void setDelay_Servreq(String[] xdrSplits, int index) {
        this.delay_Servreq = xdrSplits[index];
    }

    public void setDelay_Servres(String[] xdrSplits, int index) {
        this.delay_Servres = xdrSplits[index];
    }

    public void setDelay_Rabreq(String[] xdrSplits, int index) {
        this.delay_Rabreq = xdrSplits[index];
    }

    public void setDelay_Rabres(String[] xdrSplits, int index) {
        this.delay_Rabres = xdrSplits[index];
    }

    public void setSrcPort(String[] xdrSplits, int index) {
        this.srcPort = xdrSplits[index];
    }

    public void setDstPort(String[] xdrSplits, int index) {
        this.dstPort = xdrSplits[index];
    }

    public void setHost(String[] xdrSplits, int index) {
        this.host = xdrSplits[index];
    }

    public void setPduType(String[] xdrSplits, int index) {
        this.pduType = xdrSplits[index];
    }

    public void setResult_Rabrel() {
        this.result_Rabrel = this.result_Iurel;
    }

    public void setCause_Rabrel() {
        this.cause_Rabrel = this.cause_Iurel;
    }

    public void setSigUpRate(String[] xdrSplits, int index) {
        this.sigUpRate = xdrSplits[index];
    }

    public void setSigDownRate(String[] xdrSplits, int index) {
        this.sigDownRate = xdrSplits[index];
    }

    public void setConUpRate(String[] xdrSplits, int index) {
        this.conUpRate = xdrSplits[index];
    }

    public void setConDownRate(String[] xdrSplits, int index) {
        this.conDownRate = xdrSplits[index];
    }

    public void setActUpRate(String[] xdrSplits, int index) {
        this.actUpRate = xdrSplits[index];
    }

    public void setActDownRate(String[] xdrSplits, int index) {
        this.actDownRate = xdrSplits[index];
    }

    public void setRand(String[] xdrSplits, int index) {
        this.rand = xdrSplits[index];
    }

    public void setAutn(String[] xdrSplits, int index) {
        this.autn = xdrSplits[index];
    }

    /*
     * 总的构造
     */
    public void setPagingSerReqObj(String[] xdrSplits){
        clear();
        setVoiceType(xdrSplits,PagingSerReqIndex.VOICETYPE);
        if("19".equals(voiceType)){
            setStarttime(xdrSplits,PagingSerReqIndex.SERVICE_STARTTIME);
            setEndtime(xdrSplits,PagingSerReqIndex.SERVICE_ENDTIME);
            setCombFlag(xdrSplits,PagingSerReqIndex.SERVICE_COMBFLAG);
            setResult(xdrSplits,PagingSerReqIndex.SERVICE_COMBFLAG,PagingSerReqIndex.SERVICE_RESULT);
            setErrorCode(xdrSplits,PagingSerReqIndex.SERVICE_RESULT);
            setReleaserCode(xdrSplits,PagingSerReqIndex.SERVICE_RESULT);
            setFailureMsg(xdrSplits,PagingSerReqIndex.SERVICE_COMBFLAG,PagingSerReqIndex.SERVICE_RESULT);
            setFailurepreMsg(xdrSplits,PagingSerReqIndex.SERVICE_COMBFLAG,PagingSerReqIndex.SERVICE_CODE);
            setDelay_Pagingres(xdrSplits,PagingSerReqIndex.SERVICE_COMBFLAG);
        }else{
            setStarttime(xdrSplits,PagingSerReqIndex.PAGING_STARTTIME);
            setEndtime(xdrSplits,PagingSerReqIndex.PAGING_ENDTIME);
            setCombFlag(xdrSplits,PagingSerReqIndex.PAGING_COMBFLAG);
            setResult(xdrSplits,PagingSerReqIndex.PAGING_COMBFLAG);
            setFailurepreMsg(xdrSplits,PagingSerReqIndex.PAGING_COMBFLAG,PagingSerReqIndex.PAGING_CODE);
            setDelay_Pagingres(xdrSplits,PagingSerReqIndex.PAGING_COMBFLAG);
        }

        setEventType();
        setRanType(xdrSplits,PagingSerReqIndex.RANTYPE);
        setImsi(xdrSplits,PagingSerReqIndex.IMSI);
        setImei(xdrSplits,PagingSerReqIndex.IMEI);
        setAPN(xdrSplits,PagingSerReqIndex.APN);
        setSGSN(xdrSplits,PagingSerReqIndex.SGSN);
        setRNC(xdrSplits,PagingSerReqIndex.RNC);
        setRAC(xdrSplits,PagingSerReqIndex.MCC,PagingSerReqIndex.MNC,PagingSerReqIndex.LAC,PagingSerReqIndex.RAC);
        setCell(xdrSplits,PagingSerReqIndex.MCC,PagingSerReqIndex.MNC,PagingSerReqIndex.LAC,PagingSerReqIndex.CELL);
        sethOperator();
        setRoamType();

        setNTId(xdrSplits,PagingSerReqIndex.NTID);
        setSubevtType();
        setTMSI(xdrSplits,PagingSerReqIndex.TMSI);
        setDelay_Pagingreq();
    }

    //以下是关联相关的方法
    /*
     * 关联iu release话单(只有service业务话单才有iu关联)
     */
    public void reIURelease(IUReleaseDecode iuReleaseDecode){
//        setEndtime(iuReleaseDecode);
        setReleaserCode(iuReleaseDecode);
        setDelay_Relreq(iuReleaseDecode);
        setDelay_Relcmd(iuReleaseDecode);
        setDelay_Relcmp(iuReleaseDecode);
        setResult_Iurel(iuReleaseDecode);
        setCause_Iurel(iuReleaseDecode);
        setResult_Rabrel();
        setCause_Rabrel();
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
                append(delay_Pagingreq).append(OUT_SEPARATOR).
                append(delay_Pagingres).append(OUT_SEPARATOR).
                append(delay_Servreq).append(OUT_SEPARATOR).
                append(delay_Servres).append(OUT_SEPARATOR).
                append(delay_Rabreq).append(OUT_SEPARATOR).
                append(delay_Rabres).append(OUT_SEPARATOR).
                append(srcPort).append(OUT_SEPARATOR).
                append(dstPort).append(OUT_SEPARATOR).
                append(host).append(OUT_SEPARATOR).
                append(pduType).append(OUT_SEPARATOR).
                append(result_Rabrel).append(OUT_SEPARATOR).
                append(cause_Rabrel).append(OUT_SEPARATOR).
                append(sigUpRate).append(OUT_SEPARATOR).
                append(sigDownRate).append(OUT_SEPARATOR).
                append(conUpRate).append(OUT_SEPARATOR).
                append(conDownRate).append(OUT_SEPARATOR).
                append(actUpRate).append(OUT_SEPARATOR).
                append(actDownRate).append(OUT_SEPARATOR).
                append(rand).append(OUT_SEPARATOR).
                append(autn);
        return sb.toString();
    }
}
