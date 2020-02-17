package com.tuoming.entity.iups;

import com.tuoming.common.CommonDecode;
import com.tuoming.tools.CommonUtils;
import com.tuoming.tools.RedisUntil;

/**
 * iups口 iu释放话单结构
 *
 */
public class IUReleaseDecode extends CommonDecode {
    private String OUT_SEPARATOR = "|";

    private String voiceType = "";

    private String RNC_IP  = "";
    private String RNC_OPC = "";
    private String SGSN_IP = "";
    private String SGSN_OPC = "";
    private String src_Local_Refer = "";
    private String dest_Local_Refer = "";
    private String clr_Req_Time = "";
    private String req_Cause = "";
    private String clr_Comd_Time = "";
    private String iu_Clr_Cause = "";
    private String clrl_Comp_Time = "";

    private String IUCode = "";
    private String RANCause = "";


    public void getAccessType() {
        this.accessType = 1;
    }

    @Override
    public void getInterface0(int a) {
        this.interface0 = a;
    }

    public void getSdrType() {
        this.sdrType = 5;
    }


    public void getSrvStat(String[] str, int Cause_Index,int Comb_Index) {
        if("11".equals(str[Cause_Index])){
            this.srvStat = 2;
        }else{
            this.srvStat = CommonUtils.strToInteger(str[Comb_Index]);
        }
    }

    public void getCdrStat(String[] str, int cdrStat_Index) {
        if("0".equals(str[cdrStat_Index])){
            this.cdrStat = "0";
        }else{
            this.cdrStat = "1";
        }
    }

    public void setVoiceType(String[] xdrSplits, int index) {
        this.voiceType = xdrSplits[index];
    }

    public void setRNC_IP(String[] xdrSplits, int index) {
        this.RNC_IP = xdrSplits[index];
    }

    public void setRNC_OPC(String[] xdrSplits, int index) {
        this.RNC_OPC = xdrSplits[index];
    }

    public void setSGSN_IP(String[] xdrSplits, int index) {
        this.SGSN_IP = xdrSplits[index];
    }

    public void setSGSN_OPC(String[] xdrSplits, int index) {
        this.SGSN_OPC = xdrSplits[index];
    }

    public void setSrc_Local_Refer(String[] xdrSplits, int index) {
        this.src_Local_Refer = xdrSplits[index];
    }

    public void setDest_Local_Refer(String[] xdrSplits, int index) {
        this.dest_Local_Refer = xdrSplits[index];
    }

    public void setClr_Req_Time(String[] xdrSplits, int PROTY_index,int STIME_index) {
        if("11".equals(xdrSplits[PROTY_index])){
            this.clr_Req_Time = xdrSplits[STIME_index];
        }
    }

    public void setReq_Cause(String[] xdrSplits, int PROTY_index,int CAUSE_index) {
        if("11".equals(xdrSplits[PROTY_index])){
            this.req_Cause = xdrSplits[CAUSE_index];
        }
    }

    public void setClr_Comd_Time(String[] xdrSplits, int PROTY_index,int STIME_index) {
        if("1".equals(xdrSplits[PROTY_index])){
            this.clr_Comd_Time = xdrSplits[STIME_index];
        }
    }

    public void setIu_Clr_Cause(String[] xdrSplits, int PROTY_index,int CAUSE_index) {
        if("1".equals(xdrSplits[PROTY_index])){
            this.iu_Clr_Cause = xdrSplits[CAUSE_index];
        }
    }

    public void setClrl_Comp_Time(String[] xdrSplits, int PROTY_index,int ETIME_index) {
        if("1".equals(xdrSplits[PROTY_index])){
            this.clrl_Comp_Time = xdrSplits[ETIME_index];
        }
    }

    public void setIUCode(String[] xdrSplits, int index) {
        this.IUCode = xdrSplits[index];
    }

    public void setRANCause(String[] xdrSplits, int index) {
        this.RANCause = xdrSplits[index];
    }

    public String getStartTime(){
        return this.startTime;
    }

    public String getEndTime(){
        return this.endTime;
    }

    public String getIUCode() {
        return IUCode;
    }

    public String getRANCause() {
        return RANCause;
    }

    /*
     * 总的构造
     */
    public void setIUReleaseObj(String[] xdrSplits){
        clear();
        setVoiceType(xdrSplits,IUReleaseIndex.VOICETYPE);
        getAccessType();
        getInterface0(23);
        getSdrType();
        getImsi(xdrSplits,IUReleaseIndex.IMSI);
        getImei(xdrSplits,IUReleaseIndex.IMEI);
        getMcc();
        getMnc();
        getStartTime(xdrSplits,IUReleaseIndex.RAN_STARTTIME);
        getEndTime(xdrSplits,IUReleaseIndex.RAN_ENDTIME);
        getSrvStat(xdrSplits, IUReleaseIndex.RAN_CAUSE, IUReleaseIndex.RAN_COMBFALG);
        getCdrStat(xdrSplits, IUReleaseIndex.RAN_COMBFALG);
        getXdrId(xdrSplits,IUReleaseIndex.Pro_ID);
        setRNC_IP(xdrSplits,IUReleaseIndex.BSC_IP);
        setRNC_OPC(xdrSplits,IUReleaseIndex.RNC_OPC);
        setSGSN_IP(xdrSplits,IUReleaseIndex.SGSN_IP);
        setSGSN_OPC(xdrSplits,IUReleaseIndex.SGSN_OPC);
        setSrc_Local_Refer(xdrSplits,IUReleaseIndex.SRC_LOCAL);
        setDest_Local_Refer(xdrSplits,IUReleaseIndex.DEST_LOCAL);
        setClr_Req_Time(xdrSplits, IUReleaseIndex.IU_CODE, IUReleaseIndex.RAN_STARTTIME);
        setReq_Cause(xdrSplits, IUReleaseIndex.IU_CODE, IUReleaseIndex.RAN_CAUSE);
        setClr_Comd_Time(xdrSplits, IUReleaseIndex.IU_CODE, IUReleaseIndex.RAN_STARTTIME);
        setIu_Clr_Cause(xdrSplits, IUReleaseIndex.IU_CODE, IUReleaseIndex.RAN_CAUSE);
        setClrl_Comp_Time(xdrSplits, IUReleaseIndex.IU_CODE, IUReleaseIndex.RAN_ENDTIME);
        setIUCode(xdrSplits,IUReleaseIndex.IU_CODE);
        setRANCause(xdrSplits,IUReleaseIndex.RAN_CAUSE);
    }

    @Override
    public String toString() {
//        super.toString();
        RedisUntil.backfill(this);
        StringBuffer sb = new StringBuffer();
        sb.append(accessType).append(OUT_SEPARATOR).
                append(interface0).append(OUT_SEPARATOR).
                append(sdrType).append(OUT_SEPARATOR).
                append(imsi).append(OUT_SEPARATOR).
                append(imei).append(OUT_SEPARATOR).
                append(msisdn).append(OUT_SEPARATOR).
                append(mcc==null?"":mcc).append(OUT_SEPARATOR).
                append(mnc).append(OUT_SEPARATOR).
                append(startTime).append(OUT_SEPARATOR).
                append(endTime).append(OUT_SEPARATOR).
                append(srvStat==null?"":srvStat).append(OUT_SEPARATOR).
                append(cdrStat).append(OUT_SEPARATOR).
                append(xdrId).append(OUT_SEPARATOR).
                append(RNC_IP).append(OUT_SEPARATOR).
                append(RNC_OPC).append(OUT_SEPARATOR).
                append(SGSN_IP).append(OUT_SEPARATOR).
                append(SGSN_OPC).append(OUT_SEPARATOR).
                append(src_Local_Refer).append(OUT_SEPARATOR).
                append(dest_Local_Refer).append(OUT_SEPARATOR).
                append(clr_Req_Time).append(OUT_SEPARATOR).
                append(req_Cause).append(OUT_SEPARATOR).
                append(clr_Comd_Time).append(OUT_SEPARATOR).
                append(iu_Clr_Cause).append(OUT_SEPARATOR).
                append(clrl_Comp_Time);

        return sb.toString();
    }
}
