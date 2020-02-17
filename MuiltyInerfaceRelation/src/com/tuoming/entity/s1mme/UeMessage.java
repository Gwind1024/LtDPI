package com.tuoming.entity.s1mme;

import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

public class UeMessage extends MmeCommon {
    public String sdrType = "55";
    private String message_type = "";    //消息类型
    public String enb_ue_s1ap_id = "";  //eNB UE S1AP ID
    private String nas_pdu = "";         //NAS-PDU
    private String tai = "";             //TAI
    private String e_utran_cgi = "";     //E-UTRAN CGI
    public String mme_ip = "";          //MME_IP地址

    public String mme_ue_s1ap_id = "";

    public UeMessage() {
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.mme_ue_s1ap_id = str[UeMessageIndex.mme_ue_s1ap_id];
        this.message_type = "2";
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str, AttachIndex.enb_ue_s1ap_id)];
        this.nas_pdu = "";
        if (str[CommonUtils.convertIndex(str, UeMessageIndex.tai2)].length() == 1) {
            this.tai = str[CommonUtils.convertIndex(str, UeMessageIndex.tai1)] + "0"
                    + str[CommonUtils.convertIndex(str, UeMessageIndex.tai2)] + str[UeMessageIndex.tai3];
        } else {
            this.tai = str[CommonUtils.convertIndex(str, UeMessageIndex.tai1)]
                    + str[CommonUtils.convertIndex(str, UeMessageIndex.tai2)] + str[UeMessageIndex.tai3];
        }
        if (str[CommonUtils.convertIndex(str, UeMessageIndex.e_utran_cgi2)].length() == 1) {
            this.e_utran_cgi = str[CommonUtils.convertIndex(str, UeMessageIndex.e_utran_cgi1)] + "0"
                    + str[CommonUtils.convertIndex(str, UeMessageIndex.e_utran_cgi2)] + str[UeMessageIndex.e_utran_cgi3];
        } else {
            this.e_utran_cgi = str[CommonUtils.convertIndex(str, UeMessageIndex.e_utran_cgi1)]
                    + str[CommonUtils.convertIndex(str, UeMessageIndex.e_utran_cgi2)] + str[UeMessageIndex.e_utran_cgi3];
        }
        this.mme_ip = str[EsmDataTransIndex.mme_ip];

    }

    @Override
    public boolean getMiddleProcedure(int produceType) {
        return false;
    }

    @Override
    public Integer getEndProcedure() {
        return null;
    }

    @Override
    public Integer relation(MmeMapCommon mmeMapCommon, int type, String[] arr) {
        return 2;
    }

    @Override
    public String toString() {
        // String common = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(accessType).append("|").append(interface0).append("|").append(sdrType).append("|").append(imsi).append("|")
                .append(imei).append("|").append(msisdn).append("|").append(mcc).append("|").append(mnc).append("|").append(startTime).append("|")
                .append(endTime).append("|").append(srvstat).append("|").append(cdrstat).append("|").append(xdrId).append("|").append(message_type).append("|").append(enb_ue_s1ap_id).append("|").append(nas_pdu).append("|")
                .append(tai).append("|").append(e_utran_cgi);
        return mme_ip + "|" + sb.toString();
    }
}
