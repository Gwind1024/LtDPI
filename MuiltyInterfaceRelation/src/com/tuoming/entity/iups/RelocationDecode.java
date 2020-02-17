package com.tuoming.entity.iups;

import com.tuoming.tools.RedisUntil;
import com.tuoming.tools.SimpleDateDeal;

/**
 * iups口重定向话单结构体
 */
public class RelocationDecode extends CommonIuPS {
    private String OUT_SEPARATOR = "|";

    public String rncId = "";               //RNCID
    public String peerRncId = "";           //切入RNCID
    public String peerSgsn = "";               //对端SGSN
    public String peerRnc = "";               //对端RNC
    public String peerRac = "";            //对端RAC
    public String peerCell = "";               //对端CELL
    public String interSgsn = "";           //内部SGSN
    public String delay_Required = "";    //IU口重定位Required的时延
    public String delay_Request = "";       //IU口重定位Request的时延
    public String delay_Requestack = "";  //IU口重定位Required确认成功时延
    public String delay_Command = "";       //IU口重定位Command的时延
    public String delay_Detect = "";       //IU口重定位Detect的时延
    public String delay_Complete = "";    //IU口重定位完成的时延
    public String delay_Requiredres = ""; //重定位Required成功时延
    public String delay_Cancel = "";       //重定位取消时延
    public String delay_Failure = "";      //重定位失败时延

    @Override
    public void clear() {
        super.clear();
        rncId = "";
        peerRncId = "";
        peerSgsn = "";
        peerRnc = "";
        peerRac = "";
        peerCell = "";
        interSgsn = "";
        delay_Required = "";
        delay_Request = "";
        delay_Requestack = "";
        delay_Command = "";
        delay_Detect = "";
        delay_Complete = "";
        delay_Requiredres = "";
        delay_Cancel = "";
        delay_Failure = "";
    }

    @Override
    public void setEventType() {
        this.eventType = "56";
    }

    @Override
    public void setRanType(String[] xdrSplits, int index) {
        this.ranType = "2";
    }

    @Override
    public void setFailurepreMsg(String[] xdrSplits, int index) {
        if ("1".equals(xdrSplits[index]) || "2".equals(xdrSplits[index])) {
            this.failurepreMsg = "2";
        }
    }

    /*@Override
    public void setNTId(String[] xdrSplits, int index) {
        this.NTId = "0";
    }*/

    @Override
    public void setSubevtType(String[] xdrSplits, int index) {
        String flag = xdrSplits[index];
        if ("2".equals(flag)) {
            this.subevtType = "3670017";
        } else if ("3".equals(flag)) {
            this.subevtType = "3670018";
        }
    }

    @Override
    public void setTMSI(String[] xdrSplits, int index) {
        this.TMSI = "0";
    }

    public void setRncId(String[] xdrSplits, int Rel_index, int ORRNC_index, int DSRNC_index) {
        if ("2".equals(xdrSplits[Rel_index])) {
            this.rncId = xdrSplits[ORRNC_index];
        } else if ("3".equals(xdrSplits[Rel_index])) {
            this.rncId = xdrSplits[DSRNC_index];
        }
    }

    public void setPeerRncId(String[] xdrSplits, int Rel_index, int ORRNC_index, int DSRNC_index) {
        if ("3".equals(xdrSplits[Rel_index])) {
            this.peerRncId = xdrSplits[ORRNC_index];
        } else if ("2".equals(xdrSplits[Rel_index])) {
            this.peerRncId = xdrSplits[DSRNC_index];
        }
    }

    public void setPeerSgsn(String[] xdrSplits, int index) {
        this.peerSgsn = xdrSplits[index];
    }

    public void setPeerRnc() {
        this.peerRnc = this.rncId;
    }

    public void setPeerRac(String[] xdrSplits, int Rel_index, int RAC_index, int ORAC_index) {
        if ("2".equals(xdrSplits[Rel_index])) {
            this.peerRac = xdrSplits[RAC_index];
        } else if ("3".equals(xdrSplits[Rel_index])) {
            this.peerRac = xdrSplits[ORAC_index];
        }
    }

    public void setPeerCell(String[] xdrSplits, int Rel_index, int CELL_index, int OLDCELL_index) {
        if ("2".equals(xdrSplits[Rel_index])) {
            this.peerCell = xdrSplits[CELL_index];
        } else if ("3".equals(xdrSplits[Rel_index])) {
            this.peerCell = xdrSplits[OLDCELL_index];
        }
    }

    public void setInterSgsn() {
        this.interSgsn = "0";
    }

    public void setDelay_Required(String[] xdrSplits, int index) {
        if ("2".equals(xdrSplits[index])) {
            this.delay_Required = "0";
        }
    }

    public void setDelay_Request(String[] xdrSplits, int index) {
        if ("3".equals(xdrSplits[index])) {
            this.delay_Request = "0";
        }
    }

    public void setDelay_Requestack(String[] xdrSplits, int SUB_index, int End_index, int Start_index) {
        if ("3".equals(xdrSplits[SUB_index])) {
            String endTime = xdrSplits[End_index];
            endTime = endTime.substring(0, endTime.indexOf(".") + 4);
            String startTime = xdrSplits[Start_index];
            startTime = startTime.substring(0, startTime.indexOf(".") + 4);
            this.delay_Requestack = (SimpleDateDeal.String2TimeStamp(endTime) - SimpleDateDeal.String2TimeStamp(startTime)) + "";
        }
    }

    public void setDelay_Command(String[] xdrSplits, int SUB_index, int End_index, int Start_index) {
        if ("2".equals(xdrSplits[SUB_index])) {
            String endTime = xdrSplits[End_index];
            endTime = endTime.substring(0, endTime.indexOf(".") + 4);
            String startTime = xdrSplits[Start_index];
            startTime = startTime.substring(0, startTime.indexOf(".") + 4);
            this.delay_Command = (SimpleDateDeal.String2TimeStamp(endTime) - SimpleDateDeal.String2TimeStamp(startTime)) + "";
        }
    }

    public void setDelay_Detect() {
        this.delay_Detect = this.delay_Required;
    }

    public void setDelay_Complete() {
        this.delay_Complete = this.delay_Required;
    }

    //关联iu release
    public void setDelay_Requiredres(IUReleaseDecode iuReleaseDecode) {
        if ("0".equals(delay_Required)) {
            if ("1".equals(iuReleaseDecode.getIUCode()) || "11".equals(iuReleaseDecode.getIUCode())) {
                String ranStart = iuReleaseDecode.getStartTime();
                ranStart = ranStart.substring(0, ranStart.indexOf(".") + 4);
                String attachStart = this.starttime;
                attachStart = attachStart.substring(0, attachStart.indexOf(".") + 4);
                this.delay_Requiredres = (SimpleDateDeal.String2TimeStamp(ranStart) - SimpleDateDeal.String2TimeStamp(attachStart)) + "";
            }
        }
    }

    public void setDelay_Cancel(String[] xdrSplits, int Com_index, int End_index, int Start_index) {
        if ("3".equals(xdrSplits[Com_index])) {
            String endTime = xdrSplits[End_index];
            endTime = endTime.substring(0, endTime.indexOf(".") + 4);
            String startTime = xdrSplits[Start_index];
            startTime = startTime.substring(0, startTime.indexOf(".") + 4);
            this.delay_Cancel = (SimpleDateDeal.String2TimeStamp(endTime) - SimpleDateDeal.String2TimeStamp(startTime)) + "";
        }
    }

    public void setDelay_Failure(String[] xdrSplits, int Com_index, int End_index, int Start_index) {
        if ("2".equals(xdrSplits[Com_index])) {
            String endTime = xdrSplits[End_index];
            endTime = endTime.substring(0, endTime.indexOf(".") + 4);
            String startTime = xdrSplits[Start_index];
            startTime = startTime.substring(0, startTime.indexOf(".") + 4);
            this.delay_Failure = (SimpleDateDeal.String2TimeStamp(endTime) - SimpleDateDeal.String2TimeStamp(startTime)) + "";
        }
    }

    /*
     * 总体构造
     */
    public void setRelocationObj(String[] xdrSplits) {
        clear();
        setVoiceType(xdrSplits, RelocationIndex.VOICETYPE);
        setStarttime(xdrSplits, RelocationIndex.STARTTIME);
        //endtime
        setEndtime(xdrSplits, RelocationIndex.ENDTIME);
        setEventType();
        setRanType(xdrSplits, RelocationIndex.RANTYPE);
        setCombFlag(xdrSplits, RelocationIndex.COMBFLAG);
        setImsi(xdrSplits, RelocationIndex.IMSI);
        setImei(xdrSplits, RelocationIndex.IMEI);
        setAPN(xdrSplits, RelocationIndex.APN);
        setSGSN(xdrSplits, RelocationIndex.SGSN);
        setRNC(xdrSplits, RelocationIndex.RNC);
        setRAC(xdrSplits, RelocationIndex.MCC, RelocationIndex.MNC, RelocationIndex.LAC, RelocationIndex.RAC);
        setCell(xdrSplits, RelocationIndex.MCC, RelocationIndex.MNC, RelocationIndex.LAC, RelocationIndex.CELL);
        sethOperator();
        setRoamType();
        setResult(xdrSplits, RelocationIndex.COMBFLAG, RelocationIndex.RESULT);
        setErrorCode(xdrSplits, RelocationIndex.COMBFLAG, RelocationIndex.RESULT);
        //releasercode
        setReleaserCode(xdrSplits, RelocationIndex.RESULT);
        setFailureMsg(xdrSplits, RelocationIndex.COMBFLAG, RelocationIndex.RESULT);
        setFailurepreMsg(xdrSplits, RelocationIndex.COMBFLAG);
        //todo 存疑
        setNTId(xdrSplits, RelocationIndex.NTID);
        setSubevtType(xdrSplits, RelocationIndex.SUBEVTTYPE);
        //todo 存疑
        setTMSI(xdrSplits, RelocationIndex.TMSI);
        //关联iu release
//
        setRncId(xdrSplits, RelocationIndex.SUBEVTTYPE, RelocationIndex.ORRNC, RelocationIndex.DSRNC);
        setPeerRncId(xdrSplits, RelocationIndex.SUBEVTTYPE, RelocationIndex.ORRNC, RelocationIndex.DSRNC);
        setPeerRnc();
        setPeerRac(xdrSplits, RelocationIndex.SUBEVTTYPE, RelocationIndex.RAC, RelocationIndex.OLDRAC);
        setPeerCell(xdrSplits, RelocationIndex.SUBEVTTYPE, RelocationIndex.CELL, RelocationIndex.OLDCELL);
        setInterSgsn();
        setDelay_Required(xdrSplits, RelocationIndex.SUBEVTTYPE);
        setDelay_Request(xdrSplits, RelocationIndex.SUBEVTTYPE);
        setDelay_Requestack(xdrSplits, RelocationIndex.SUBEVTTYPE, RelocationIndex.ENDTIME, RelocationIndex.STARTTIME);
        setDelay_Command(xdrSplits, RelocationIndex.SUBEVTTYPE, RelocationIndex.ENDTIME, RelocationIndex.STARTTIME);
        setDelay_Detect();
        setDelay_Complete();
//        setDelay_Requiredres(xdrSplits,RelocationIndex.SUBEVTTYPE,RelocationIndex.ENDTIME,RelocationIndex.STARTTIME);
        setDelay_Cancel(xdrSplits, RelocationIndex.COMBFLAG, RelocationIndex.ENDTIME, RelocationIndex.STARTTIME);
        setDelay_Failure(xdrSplits, RelocationIndex.COMBFLAG, RelocationIndex.ENDTIME, RelocationIndex.STARTTIME);
        //Rand Autn
    }

    //以下是关联相关的方法
    /*
     * 关联iu release话单
     */
    public void reIURelease(IUReleaseDecode iuReleaseDecode) {
        setEndtime(iuReleaseDecode);
        setReleaserCode(iuReleaseDecode);
        setDelay_Relreq(iuReleaseDecode);
        setDelay_Relcmd(iuReleaseDecode);
        setDelay_Relcmp(iuReleaseDecode);
        setResult_Iurel(iuReleaseDecode);
        setCause_Iurel(iuReleaseDecode);
        setDelay_Requiredres(iuReleaseDecode);
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
                append(rncId).append(OUT_SEPARATOR).
                append(peerRncId).append(OUT_SEPARATOR).
                append(peerSgsn).append(OUT_SEPARATOR).
                append(peerRnc).append(OUT_SEPARATOR).
                append(peerRac).append(OUT_SEPARATOR).
                append(peerCell).append(OUT_SEPARATOR).
                append(interSgsn).append(OUT_SEPARATOR).
                append(delay_Required).append(OUT_SEPARATOR).
                append(delay_Request).append(OUT_SEPARATOR).
                append(delay_Requestack).append(OUT_SEPARATOR).
                append(delay_Command).append(OUT_SEPARATOR).
                append(delay_Detect).append(OUT_SEPARATOR).
                append(delay_Complete).append(OUT_SEPARATOR).
                append(delay_Requiredres).append(OUT_SEPARATOR).
                append(delay_Cancel).append(OUT_SEPARATOR).
                append(delay_Failure);
        return sb.toString();
    }
}
