package com.tuoming.entity.iups;

import com.tuoming.tools.CommonUtils;
import com.tuoming.tools.RadixDeal;
import com.tuoming.tools.RedisUntil;
import com.tuoming.tools.SimpleDateDeal;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * iups口附着话单结构
 */
public class AttachDecode extends CommonIuPS {

    private String OUT_SEPARATOR = "|";

    private String m_sIntersgsn = "";       //SGSN
    private String m_sPeerrac = "";         //对端RAC
    private String m_sPeerrat = "";         //对端/原无线接入网类型
    private String m_sDelay_Attreq = "";    //路由区更新请求时延
    private String m_sDelay_Attres = "";    //路由区更新成功时延
    private String m_sRand = "";            //鉴权随机数
    private String m_sAutn = "";            //鉴权向量


    @Override
    public void clear() {
        super.clear();
        m_sIntersgsn = "";
        m_sPeerrac = "";
        m_sPeerrat = "";
        m_sDelay_Attreq = "";
        m_sDelay_Attres = "";
        m_sRand = "";
        m_sAutn = "";
    }

    @Override
    public void setEventType() {
        this.eventType = "51";
    }

    /*@Override
    public void setSGSN(String[] xdrSplits, int index) {
        this.SGSN = RadixDeal.IP2Decimal(xdrSplits[index]);
    }*/

    @Override
    public void setRanType(String[] xdrSplits, int index) {
        if("3".equals(xdrSplits[index])){
            this.ranType = "2";
        }else{
            this.ranType = "1";
        }
    }
    @Override
    public void setCombFlag(String[] xdrSplits, int index) {
        if("2".equals(xdrSplits[index])){
            this.combFlag = "1";
        }else{
            this.combFlag = "0";
        }
    }

    @Override
    public void setSubevtType(String[] xdrSplits, int index) {
        String flag = xdrSplits[index];
        if("1".equals(flag)){
            this.subevtType = "3342337";
        }else if("3".equals(flag)){
            this.subevtType = "3342338";
        }else if("4".equals(flag)){
            this.subevtType = "3342339";
        }
    }


    public void setM_sIntersgsn() {
        this.m_sIntersgsn = "0";
    }

    public void setM_sPeerrac(String[] xdrSplits, int OMCC_index,int OMNC_index,int OLAC_index,int ORAC_index) {
        String mncXdr = xdrSplits[OMNC_index];
        if(mncXdr.length() == 1){
            mncXdr = "0"+mncXdr;
        }else if(mncXdr.length() == 0){
            mncXdr = "00";
        }
        this.m_sPeerrac = xdrSplits[OMCC_index]+ mncXdr +
                xdrSplits[OLAC_index]+xdrSplits[ORAC_index];
    }

    public void setM_sPeerrat(String[] xdrSplits, int index) {
        if("3".equals(xdrSplits[index])){
            this.m_sPeerrat = "2";
        }else if("4".equals(xdrSplits[index])){
            this.m_sPeerrat = "1";
        }
    }


    public void setM_sDelay_Attreq() {
        this.m_sDelay_Attreq = "0";
    }

    public void setM_sDelay_Attres(String[] xdrSplits, int Com_index,int End_index,int Start_index) {

        if("0".equals(xdrSplits[Com_index])){
            String endTime = xdrSplits[End_index];
            endTime = endTime.substring(0,endTime.indexOf(".")+4);
            String startTime = xdrSplits[Start_index];
            startTime = startTime.substring(0,startTime.indexOf(".")+4);
            this.m_sDelay_Attres = (SimpleDateDeal.String2TimeStamp(endTime)- SimpleDateDeal.String2TimeStamp(startTime))+"";
        }
    }

    public void setM_sRand(String[] xdrSplits, int index) {
        this.m_sRand = xdrSplits[index];
    }

    public void setM_sAutn(String[] xdrSplits, int index) {
        this.m_sAutn = xdrSplits[index];
    }

    /*
     * 总体的构造方法
     */
    public void setAttachObj(String[] xdrSplits){
        this.clear();
        setVoiceType(xdrSplits,AttachIndex.VOICETYPE);
        setStarttime(xdrSplits,AttachIndex.STARTTIME);
        //endtime 关联
        setEndtime(xdrSplits,AttachIndex.ENDTIME);
        setEventType();
        setRanType(xdrSplits,AttachIndex.RANTYPE);
        setCombFlag(xdrSplits,AttachIndex.COMBFLAG);
        setImsi(xdrSplits,AttachIndex.IMSI);
        setImei(xdrSplits,AttachIndex.IMEI);
//        setAPN(xdrSplits,AttachIndex.APN);
        setSGSN(xdrSplits,AttachIndex.SGSN);
        setRNC(xdrSplits,AttachIndex.RNC);
        setRAC(xdrSplits,AttachIndex.MCC,AttachIndex.MNC,AttachIndex.LAC,AttachIndex.RAC);
        setCell(xdrSplits,AttachIndex.MCC,AttachIndex.MNC,AttachIndex.LAC,AttachIndex.CELL);
        setRoamType();
        setResult(xdrSplits,AttachIndex.COMBFLAG,AttachIndex.RESULT);
        setErrorCode(xdrSplits,AttachIndex.COMBFLAG,AttachIndex.RESULT);
        //releasercode
        setReleaserCode(xdrSplits,AttachIndex.RESULT);
        setFailureMsg(xdrSplits,AttachIndex.COMBFLAG,AttachIndex.RESULT);
        setFailurepreMsg(xdrSplits,AttachIndex.COMBFLAG);
        setNTId(xdrSplits,AttachIndex.NTID);
        setSubevtType(xdrSplits,AttachIndex.SUBEVTTYPE);
        setTMSI(xdrSplits,AttachIndex.TMSI);
        //关联iu release
        setM_sIntersgsn();
        setM_sPeerrac(xdrSplits,AttachIndex.OLDMCC,AttachIndex.OLDMNC,AttachIndex.OLDLAC,AttachIndex.OLDRAC);
        setM_sPeerrat(xdrSplits,AttachIndex.RANTYPE);
        setM_sDelay_Attreq();
        setM_sDelay_Attres(xdrSplits,AttachIndex.COMBFLAG,AttachIndex.ENDTIME,AttachIndex.STARTTIME);
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
        setM_sRand(authXdr,AuthIndex.RAND);
        setM_sAutn(authXdr,AuthIndex.AUTN);
    }
    StringBuilder sb = new StringBuilder();
    //用于输出
    @Override
    public String toString() {
        sb.setLength(0);
//        long l = System.nanoTime();
        RedisUntil.backfill(this);

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
                append(m_sIntersgsn).append(OUT_SEPARATOR).
                append(m_sPeerrac).append(OUT_SEPARATOR).
                append(m_sPeerrat).append(OUT_SEPARATOR).
                append(m_sDelay_Attreq).append(OUT_SEPARATOR).
                append(m_sDelay_Attres).append(OUT_SEPARATOR).
                append(m_sRand).append(OUT_SEPARATOR).
                append(m_sAutn);
//        System.out.println("附着："+(System.nanoTime()-l));
        return sb.toString();
    }
}
