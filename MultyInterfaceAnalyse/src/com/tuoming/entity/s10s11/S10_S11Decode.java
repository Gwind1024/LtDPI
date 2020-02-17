package com.tuoming.entity.s10s11;

import com.tuoming.common.CommonDecode;
import com.tuoming.common.RedisUntil;
import com.tuoming.tools.SimpleDateDeal;

/**
 * 解析s10-s11接口数据的结构类
 */
public class S10_S11Decode extends CommonDecode {

    private String OUT_SEPARATOR = "|";

    private String m_sMME_Address = "";    //MME信令面IP地址
    private String m_sMME_Port = "";        //MME端口
    private String m_sSGW_Address = "";    //SGW信令面IP地址
    private String m_sSGW_Port = "";        //SGW端口
    private String m_sPGW_Address = "";    //PGW信令面IP地址
    private String m_iMME_TEID = "";            //MME Control TEID
    private String m_iSGW_TEID = "";            //Old MME /SGW Control TEID
    private String m_sMME_GroupID = "";        //MME GroupID
    private String m_sMME_Code = "";            //MME Code
    private String m_sM_TMSI = "";        //M-TMSI
    private String m_iTAC = "";                //TAC
    private String m_iECI = "";                //ECI
    private String m_sAPN = "";            //APN
    private String m_sUSR_IP = "";        //用户IP地址
    private String m_sEPSBearerNumber = "";    //n或空	EPS承载数
    private String m_sBearer1ID = "";        //承载的ID标识
    private String m_sBearer1Type = "";    //承载的类型
    private String m_sBearer1QCI = "";    //承载的QCI
    private String m_sBearer1Status = "";    //承载的状态
    private String m_sCC = "";                //Charging Characteristics
    private String m_sCI = "";                //Charging ID
    private String m_sBearer1eNBGTP_TEID = "";    //承载的eNB GTP-TEID
    private String m_sBearer1SGWGTP_TEID = "";    //承载的eNB GTP-U IP
    private String m_sCause = "";                //结束原因
    private String m_sS11U_MME_TEID = "";    //S11-U MME F-TEID
    private String m_sDL_Buf_Dur = "";    //DL Buffering Duration
    private String m_sDL_Buf_Sug_PacketCou = "";    //DL Buffering Suggested Packet Count
    private String m_sUP_Function_Selection_Indication_Flags = "";  //add in 20191114

    public void clear() {
        super.clear();
        m_sMME_Address = "";
        m_sMME_Port = "";
        m_sSGW_Address = "";
        m_sSGW_Port = "";
        m_sPGW_Address = "";
        m_iMME_TEID = "";
        m_iSGW_TEID = "";
        m_sMME_GroupID = "";
        m_sMME_Code = "";
        m_sM_TMSI = "";
        m_iTAC = "";
        m_iECI = "";
        m_sAPN = "";
        m_sUSR_IP = "";
        m_sEPSBearerNumber = "";
        m_sBearer1ID = "";
        m_sBearer1Type = "";
        m_sBearer1QCI = "";
        m_sBearer1Status = "";
        m_sCC = "";
        m_sCI = "";
        m_sBearer1eNBGTP_TEID = "";
        m_sBearer1SGWGTP_TEID = "";
        m_sCause = "";
        m_sS11U_MME_TEID = "";
        m_sDL_Buf_Dur = "";
        m_sDL_Buf_Sug_PacketCou = "";
        m_sUP_Function_Selection_Indication_Flags = "";
    }


    @Override
    public String getEndTime() {
        return endTime;
    }

    @Override
    public String getMmeIp() {
        return m_sMME_Address;
    }

    @Override
    public void getStartTime(String[] str, int startTime_Index) {
        this.startTime = SimpleDateDeal.getFormatDate(str[startTime_Index]);
    }

    @Override
    public void getEndTime(String[] str, int endTime_Index) {
        this.endTime = SimpleDateDeal.getFormatDate(str[endTime_Index]);
    }

    @Override
    public void getCdrStat(String[] str, int cdrStat_Index) {
        if ("1".equals(str[cdrStat_Index])) {
            this.cdrStat = "0";
        } else {
            this.cdrStat = "1";
        }
    }

    public void setMME_Address(String[] s10_s11Arr, int index) {
        this.m_sMME_Address = s10_s11Arr[index];
    }

    public void setMME_Port(String[] s10_s11Arr, int index) {
        this.m_sMME_Port = s10_s11Arr[index];
    }

    public void setSGW_Address(String[] s10_s11Arr, int index) {
        this.m_sSGW_Address = s10_s11Arr[index];
    }

    public void setSGW_Port(String[] s10_s11Arr, int index) {
        this.m_sSGW_Port = s10_s11Arr[index];
    }

    public void setPGW_Address(String[] s10_s11Arr, int index) {
        this.m_sPGW_Address = "";
    }

    public void setMME_TEID(String[] s10_s11Arr, int index) {
        this.m_iMME_TEID = s10_s11Arr[index];
    }

    public void setSGW_TEID(String[] s10_s11Arr, int index) {
        this.m_iSGW_TEID = s10_s11Arr[index];
    }

    public void setMME_GroupID(String[] s10_s11Arr, int index) {
        this.m_sMME_GroupID = "";
    }

    public void setMME_Code(String[] s10_s11Arr, int index) {
        this.m_sMME_Code = "";
    }

    public void setM_TMSI(String[] s10_s11Arr, int index) {
        this.m_sM_TMSI = "";
    }

    public void setTAC(String[] s10_s11Arr, int index) {

        this.m_iTAC = SimpleDateDeal.leftSupply0(s10_s11Arr[index], 5);
    }

    public void setECI(String[] s10_s11Arr, int index) {
        this.m_iECI = s10_s11Arr[index];
    }

    public void setAPN(String[] s10_s11Arr, int index) {
        this.m_sAPN = s10_s11Arr[index];
    }

    public void setUSR_IP(String[] s10_s11Arr, int index1, int index2) {
        if (!"".equals(s10_s11Arr[index1])) {
            m_sUSR_IP = s10_s11Arr[index1];
        } else {
            m_sUSR_IP = s10_s11Arr[index2];
        }
    }

    public void setEPSBearerNumber(String[] s10_s11Arr, int index) {
        if (ReadIntStr(s10_s11Arr[index]) == 0) {
            this.m_sEPSBearerNumber = "";
        } else {
            this.m_sEPSBearerNumber = s10_s11Arr[index];
        }

    }

    public void setBearer1ID(String[] s10_s11Arr, int index) {
        if ("".equals(m_sEPSBearerNumber)) {
            this.m_sBearer1ID = "";
        } else {
            this.m_sBearer1ID = s10_s11Arr[index];
        }
    }

    public void setBearer1Type(String[] s10_s11Arr, int index) {
        if ("".equals(m_sEPSBearerNumber)) {
            this.m_sBearer1Type = "";
        } else {
            this.m_sBearer1Type = s10_s11Arr[index];
        }
    }

    public void setBearer1QCI(String[] s10_s11Arr, int index) {
        if ("".equals(m_sEPSBearerNumber)) {
            this.m_sBearer1QCI = "";
        } else {
            this.m_sBearer1QCI = s10_s11Arr[index];
        }
    }

    public void setBearer1Status(String[] s10_s11Arr, int index) {
        if ("".equals(m_sEPSBearerNumber)) {
            this.m_sBearer1Status = "";
        } else {
            this.m_sBearer1Status = s10_s11Arr[index];
        }
    }

    public void setCC(String[] s10_s11Arr, int index) {
        this.m_sCC = "";
    }

    public void setCI(String[] s10_s11Arr, int index) {
        this.m_sCI = "";
    }

    public void setBearer1eNBGTP_TEID(String[] s10_s11Arr, int index) {
        if ("".equals(m_sEPSBearerNumber)) {
            this.m_sBearer1eNBGTP_TEID = "";
        } else {
            this.m_sBearer1eNBGTP_TEID = s10_s11Arr[index];
        }
    }

    public void setBearer1SGWGTP_TEID(String[] s10_s11Arr, int index) {
        if ("".equals(m_sEPSBearerNumber)) {
            this.m_sBearer1SGWGTP_TEID = "";
        } else {
            this.m_sBearer1SGWGTP_TEID = s10_s11Arr[index];
        }
    }

    public void setCause(String[] s10_s11Arr, int index) {
        this.m_sCause = "";
    }

    public void setS11U_MME_TEID(String[] s10_s11Arr, int index) {
        this.m_sCause = "";
    }

    public void setDL_Buf_Dur(String[] s10_s11Arr, int index) {
        this.m_sDL_Buf_Dur = "";
    }

    public void setDL_Buf_Sug_PacketCou(String[] s10_s11Arr, int index) {
        this.m_sDL_Buf_Sug_PacketCou = "";
    }

    public void setM_sUP_Function_Selection_Indication_Flags(String[] s10_s11Arr, int index) {
        this.m_sUP_Function_Selection_Indication_Flags = "";
    }

    /*
     * S10 S11总构造
     */
    public void decode(String[] xdrSplit) {
        this.clear();
        getAccessType(xdrSplit, S10_S11Index.Access_Type);
        getInterface0(32);
        getSdrType(xdrSplit, S10_S11Index.SdrType);
        getImsi(xdrSplit, S10_S11Index.Imsi);
        getImei(xdrSplit, S10_S11Index.Imei);
        getMsisdn(xdrSplit, S10_S11Index.Msisdn);
        getMcc();
        getMnc();
        getStartTime(xdrSplit, S10_S11Index.StartTime);
        getEndTime(xdrSplit, S10_S11Index.EndTime);
        getSrvStat(xdrSplit, S10_S11Index.SrvStat);
        getCdrStat(xdrSplit, S10_S11Index.SrvStat);
        getXdrId(xdrSplit, S10_S11Index.XDR_ID);
        setMME_Address(xdrSplit, S10_S11Index.MME_Address);
        setMME_Port(xdrSplit, S10_S11Index.MME_Port);
        setSGW_Address(xdrSplit, S10_S11Index.SGW_Address);
        setSGW_Port(xdrSplit, S10_S11Index.SGW_Port);
        setMME_TEID(xdrSplit, S10_S11Index.MME_TEID);
        setSGW_TEID(xdrSplit, S10_S11Index.SGW_TEID);
        setTAC(xdrSplit, S10_S11Index.TAC);
        setECI(xdrSplit, S10_S11Index.ECI);
        setAPN(xdrSplit, S10_S11Index.APN);
        setUSR_IP(xdrSplit, S10_S11Index.USR_IP4, S10_S11Index.USR_IP6);
        setEPSBearerNumber(xdrSplit, S10_S11Index.EPSBearerNumber);
        setBearer1ID(xdrSplit, S10_S11Index.Bearer1ID);
        setBearer1Type(xdrSplit, S10_S11Index.Bearer1Type);
        setBearer1QCI(xdrSplit, S10_S11Index.Bearer1QCI);
        setBearer1Status(xdrSplit, S10_S11Index.Bearer1Status);
        setBearer1eNBGTP_TEID(xdrSplit, S10_S11Index.Bearer1eNBGTP_TEID);
        setBearer1SGWGTP_TEID(xdrSplit, S10_S11Index.Bearer1SGWGTP_TEID);
        setDL_Buf_Dur(xdrSplit, S10_S11Index.DL_Buf_Dur);
        setDL_Buf_Sug_PacketCou(xdrSplit, S10_S11Index.DL_Buf_Sug_PacketCou);
    }

    public String getMME_Address() {
        return this.m_sMME_Address;
    }

    public String getEndtime() {
        return this.endTime;
    }

    @Override
    public String toString() {
        RedisUntil.setToMap(this);
        StringBuffer sb = new StringBuffer();
        sb.append(accessType == null ? "" : accessType).append(OUT_SEPARATOR).
                append(interface0).append(OUT_SEPARATOR).
                append(sdrType == null ? "" : sdrType).append(OUT_SEPARATOR).
                append(imsi).append(OUT_SEPARATOR).
                append(imei).append(OUT_SEPARATOR).
                append(msisdn).append(OUT_SEPARATOR).
                append(mcc).append(OUT_SEPARATOR).
                append(mnc).append(OUT_SEPARATOR).
                append(startTime).append(OUT_SEPARATOR).
                append(endTime).append(OUT_SEPARATOR).
                append(srvStat == null ? "" : srvStat).append(OUT_SEPARATOR).
                append(cdrStat).append(OUT_SEPARATOR).
                append(xdrId).append(OUT_SEPARATOR).

                append(m_sMME_Address).append(OUT_SEPARATOR).
                append(m_sMME_Port).append(OUT_SEPARATOR).
                append(m_sSGW_Address).append(OUT_SEPARATOR).
                append(m_sSGW_Port).append(OUT_SEPARATOR).
                append(m_sPGW_Address).append(OUT_SEPARATOR).
                append(m_iMME_TEID).append(OUT_SEPARATOR).
                append(m_iSGW_TEID).append(OUT_SEPARATOR).
                append(m_sMME_GroupID).append(OUT_SEPARATOR).
                append(m_sMME_Code).append(OUT_SEPARATOR).
                append(m_sM_TMSI).append(OUT_SEPARATOR).
                append(m_iTAC).append(OUT_SEPARATOR).
                append(m_iECI).append(OUT_SEPARATOR).
                append(m_sAPN).append(OUT_SEPARATOR).
                append(m_sUSR_IP).append(OUT_SEPARATOR).
                append(m_sEPSBearerNumber).append(OUT_SEPARATOR).
                append(m_sBearer1ID).append(OUT_SEPARATOR).
                append(m_sBearer1Type).append(OUT_SEPARATOR).
                append(m_sBearer1QCI).append(OUT_SEPARATOR).
                append(m_sBearer1Status).append(OUT_SEPARATOR).
                append(m_sCC).append(OUT_SEPARATOR).
                append(m_sCI).append(OUT_SEPARATOR).
                append(m_sBearer1eNBGTP_TEID).append(OUT_SEPARATOR).
                append(m_sBearer1SGWGTP_TEID).append(OUT_SEPARATOR).
                append(m_sCause).append(OUT_SEPARATOR).
                append(m_sS11U_MME_TEID).append(OUT_SEPARATOR).
                append(m_sDL_Buf_Dur).append(OUT_SEPARATOR).
                append(m_sDL_Buf_Sug_PacketCou).append(OUT_SEPARATOR).
                append(m_sUP_Function_Selection_Indication_Flags);

        return sb.toString();
    }

    private int ReadIntStr(String str) {
        if (str == null || str.length() <= 0)
            return -1;
        return Integer.parseInt(str);
    }
}
