package com.tuoming.entity.iups;

import com.tuoming.tools.RedisUntil;
import com.tuoming.tools.SimpleDateDeal;

/**
 * iups口路由更新话单结构
 */
public class RauUpdateDecode extends CommonIuPS {

    private String OUT_SEPARATOR = "|";

    public String interSgsn = "";			//SGSN
    public String peerRac = "";			    //对端RAC
    public String peerRat = "";			    //对端/原无线接入网类型
    public String delay_Raureq = "";		//路由区更新请求时延
    public String delay_Raures = "";      //路由区更新成功时延
    public String delay_Srnsctxreq = "";	//IU口SRNS上下文请求时延
    public String delay_Srnsctxres = "";	//IU口SRNS上下文请求成功时延
    public String delay_Modireq = "";	    //修改请求时延
    public String delay_Modires = "";	    //修改成功时延
    public String peerSgsn = "";			//对端SGSN
    public String peerRnc = "";			    //对端RNC
    public String rand = "";			    //鉴权随机数
    public String autn = "";			    //鉴权向量

    @Override
    public void clear() {
        super.clear();
        interSgsn = "";
        peerRac = "";
        peerRat = "";
        delay_Raureq = "";
        delay_Raures = "";
        delay_Srnsctxreq = "";
        delay_Srnsctxres = "";
        delay_Modireq = "";
        delay_Modires = "";
        peerSgsn = "";
        peerRnc = "";
        rand = "";
        autn = "";
    }

    @Override
    public void setEventType() {
        this.eventType = "54";
    }

    @Override
    public void setFailurepreMsg(String[] xdrSplits,int index) {
        if("1".equals(xdrSplits[index]) || "2".equals(xdrSplits[index])){
            this.failurepreMsg = "8";
        }
    }

    @Override
    public void setSubevtType(String[] xdrSplits, int index) {
        String flag = xdrSplits[index];
        if("0".equals(flag)){
            this.subevtType = "3538945";
        }else if("1".equals(flag)){
            this.subevtType = "3538946";
        }else if("2".equals(flag)){
            this.subevtType = "3538947";
        }else if("3".equals(flag)){
            this.subevtType = "3538948";
        }
    }

    public void setInterSgsn() {
        this.interSgsn = "0";
    }

    public void setPeerRac(String[] xdrSplits, int OMCC_index,int OMNC_index,int OLAC_index,int ORAC_index) {
        this.peerRac = xdrSplits[OMCC_index]+xdrSplits[OMNC_index]+
                xdrSplits[OLAC_index]+xdrSplits[ORAC_index];
    }

    public void setPeerRat(String[] xdrSplits, int index) {
        if("3".equals(xdrSplits[index])){
            this.peerRat = "2";
        }else if("4".equals(xdrSplits[index])){
            this.peerRat = "1";
        }
    }

    public void setDelay_Raureq() {
        this.delay_Raureq = "0";;
    }

    public void setDelay_Raures(String[] xdrSplits, int Com_index,int End_index,int Start_index) {
        if("0".equals(xdrSplits[Com_index])){
            String endTime = xdrSplits[End_index];
            endTime = endTime.substring(0,endTime.indexOf(".")+4);
            String startTime = xdrSplits[Start_index];
            startTime = startTime.substring(0,startTime.indexOf(".")+4);
            this.delay_Raures = (SimpleDateDeal.String2TimeStamp(endTime)- SimpleDateDeal.String2TimeStamp(startTime))+"";
        }
    }

    public void setDelay_Srnsctxreq(String[] xdrSplits, int index) {
        this.delay_Srnsctxreq = xdrSplits[index];
    }

    public void setDelay_Srnsctxres(String[] xdrSplits, int index) {
        this.delay_Srnsctxres = xdrSplits[index];
    }

    public void setDelay_Modireq(String[] xdrSplits, int index) {
        this.delay_Modireq = xdrSplits[index];
    }

    public void setDelay_Modires(String[] xdrSplits, int index) {
        this.delay_Modires = xdrSplits[index];
    }

    public void setPeerSgsn(String[] xdrSplits, int index) {
        this.peerSgsn = xdrSplits[index];
    }

    public void setPeerRnc(String[] xdrSplits, int index) {
        this.peerRnc = xdrSplits[index];
    }

    public void setRand(String[] xdrSplits, int index) {
        this.rand = xdrSplits[index];
    }

    public void setAutn(String[] xdrSplits, int index) {
        this.autn = xdrSplits[index];
    }

    /*
     * 总体构造
     */
    public void setRauUpadteObj(String[] xdrSplits){
        clear();
        setVoiceType(xdrSplits,RauUpdateIndex.VOICETYPE);
        setStarttime(xdrSplits, RauUpdateIndex.STARTTIME);
        //endtime
        setEndtime(xdrSplits,RauUpdateIndex.ENDTIME);
        setEventType();
        setRanType(xdrSplits, RauUpdateIndex.RANTYPE);
        setCombFlag(xdrSplits, RauUpdateIndex.COMBFLAG);
        setImsi(xdrSplits, RauUpdateIndex.IMSI);
        setImei(xdrSplits, RauUpdateIndex.IMEI);
        setAPN(xdrSplits, RauUpdateIndex.APN);
        setSGSN(xdrSplits, RauUpdateIndex.SGSN);
        setRNC(xdrSplits, RauUpdateIndex.RNC);
        setRAC(xdrSplits, RauUpdateIndex.MCC, RauUpdateIndex.MNC, RauUpdateIndex.LAC, RauUpdateIndex.RAC);
        setCell(xdrSplits, RauUpdateIndex.MCC, RauUpdateIndex.MNC, RauUpdateIndex.LAC, RauUpdateIndex.CELL);
        setRoamType();
        setResult(xdrSplits, RauUpdateIndex.COMBFLAG, RauUpdateIndex.RESULT);
        setErrorCode(xdrSplits, RauUpdateIndex.COMBFLAG, RauUpdateIndex.RESULT);
        //releasercode
        setReleaserCode(xdrSplits,RauUpdateIndex.RESULT);
        setFailureMsg(xdrSplits, RauUpdateIndex.COMBFLAG, RauUpdateIndex.RESULT);
        setFailurepreMsg(xdrSplits, RauUpdateIndex.COMBFLAG);
        setNTId(xdrSplits, RauUpdateIndex.NTID);
        setSubevtType(xdrSplits, RauUpdateIndex.SUBEVTTYPE);
        setTMSI(xdrSplits, RauUpdateIndex.TMSI);
        //关联iu release
        setInterSgsn();
        setPeerRac(xdrSplits, RauUpdateIndex.OLDMCC, RauUpdateIndex.OLDMNC, RauUpdateIndex.OLDLAC, RauUpdateIndex.OLDRAC);
        setPeerRat(xdrSplits, RauUpdateIndex.RANTYPE);
        setDelay_Raureq();
        setDelay_Raures(xdrSplits, RauUpdateIndex.COMBFLAG, RauUpdateIndex.ENDTIME, RauUpdateIndex.STARTTIME);
        //Rand Autn

    }

    //以下是关联相关的方法
    /*
     * 关联取标识子流程
     */
    public void reIdentity(String[] identityXdr){
        setDelay_Idreq(identityXdr,IdentityIndex.StartTime);
        setDelay_Idres(identityXdr,IdentityIndex.EndTime);
        setTryIds();
    }
    /*
     * 关联鉴权子流程
     */
    public void reAuthentication(String[] authXdr){
        setDelay_Aucreq(authXdr,AuthIndex.StartTime);
        setDelay_Aucres(authXdr,AuthIndex.EndTime);
        setTryAuths(authXdr,AuthIndex.AuthReq);
        setAuthTimes(authXdr,AuthIndex.AuthReq);
        setRand(authXdr,AuthIndex.RAND);
        setAutn(authXdr,AuthIndex.AUTN);
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
                append(interSgsn).append(OUT_SEPARATOR).
                append(peerRac).append(OUT_SEPARATOR).
                append(peerRat).append(OUT_SEPARATOR).
                append(delay_Raureq).append(OUT_SEPARATOR).
                append(delay_Raures).append(OUT_SEPARATOR).
                append(delay_Srnsctxreq).append(OUT_SEPARATOR).
                append(delay_Srnsctxres).append(OUT_SEPARATOR).
                append(delay_Modireq).append(OUT_SEPARATOR).
                append(delay_Modires).append(OUT_SEPARATOR).
                append(peerSgsn).append(OUT_SEPARATOR).
                append(peerRnc).append(OUT_SEPARATOR).
                append(rand).append(OUT_SEPARATOR).
                append(autn);
        return sb.toString();
    }
}
