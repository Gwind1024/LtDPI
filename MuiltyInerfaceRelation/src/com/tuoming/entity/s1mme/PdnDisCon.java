package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

// PDN disconnection 8
public class PdnDisCon extends MmeCommon {


    public String enodeb_ip = "";        //eNodeB信令面IP
    public String enodeb_port = "";      //eNodeB端口
    public String mme_ip = "";           //MME IP地址
    public String mme_port = "";         //MME端口
    public String mme_ue_s1ap_id = "";   //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";   //ENB UE S1AP ID
    public String mme_groupid = "";      //MME GroupID
    public String mme_code = "";         //MME Code
    public String m_tmsi = "";           //M-TMSI
    public String tac = "";              //TAC
    public String eci = "";              //ECI
    public String apn = "";              //APN
    public String ebi = "";              //去激活EBI
    public String pti = "";              //去激活PTI
    public String req_time = "";         //消息请求时间
    public String rsp_time = "";         //去激活响应响应时间或disconnect拒绝时间
    public String acpt_time = "";        //去激活上下文接受时间
    public String context_rel_time = ""; //UE context release request时间
    public String req_cause = "";        //请求原因
    public String contxt_rl_time = "";   //UE context release command时间
    public String release_cause = "";    //UE context release释放原因
    public String comp_time = "";        //UE context releasecomplete时间
    public String res_time = "";         //结束消息类型
    public String msg_id = "";           //结束消息
    public String cause_code = "";       //消息的结果码

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    public PdnDisCon() {
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[PdnDisConIndex.enodeb_ip];
        this.enodeb_port = str[PdnDisConIndex.enodeb_port];
        this.mme_ip = str[PdnDisConIndex.mme_ip];
        this.mme_port = str[PdnDisConIndex.mme_port];
        this.mme_ue_s1ap_id = str[PdnDisConIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str, AttachIndex.enb_ue_s1ap_id)];
        this.mme_groupid = str[PdnDisConIndex.mme_groupid];
        this.mme_code = str[PdnDisConIndex.mme_code];
        this.m_tmsi = str[PdnDisConIndex.m_tmsi];
        this.tac = str[PdnDisConIndex.tac];
        this.eci = str[PdnDisConIndex.eci];
        this.apn = str[PdnDisConIndex.apn];
        this.req_time = str[PdnDisConIndex.req_time];
        this.rsp_time = str[PdnDisConIndex.msg_id].endsWith("1") ? str[PdnDisConIndex.rsp_time2] : "";
        this.res_time = "0";
        if ("0".equals(str[PdnDisConIndex.msg_id])) {
            this.msg_id = "206";
        } else if ("1".equals(str[PdnDisConIndex.msg_id])) {
            this.msg_id = "211";
        } else if ("255".equals(str[PdnDisConIndex.msg_id])) {
            this.msg_id = "210";
        }
        this.cause_code = TypeChange.strToOx7f(str[PdnDisConIndex.cause_code]);
    }

    //11
    private void createType11(String[] str) {
        Integer ebi_index = CommonUtils.numIndex(str, PdnDisConIndex.ebi);
        if (ebi_index != null) {
            this.ebi = CommonUtils.strToInteger(str[PdnDisConIndex.ebi - 1]) > 0 ? TypeChange.strToHex(str[ebi_index]) : "";
        }
        this.pti = TypeChange.strToHex(str[CommonUtils.convertIndex(str, PdnDisConIndex.pti)]);
        this.rsp_time = str[PdnDisConIndex.rsp_time1];
        this.acpt_time = str[PdnDisConIndex.acpt_time];
    }

    //20
    private void createType20(String[] str) {
        this.context_rel_time = str[CommonUtils.convertIndex(str, PdnDisConIndex.context_rel_time)];
        int reqcause = CommonUtils.convertIndex(str, PdnDisConIndex.req_cause);
        this.req_cause = TypeChange.strToOx7f(str[reqcause]);
        this.contxt_rl_time = str[PdnDisConIndex.contxt_rl_time];
        this.release_cause = TypeChange.strToOx7f(str[PdnDisConIndex.release_cause]);
        this.comp_time = str[PdnDisConIndex.comp_time];
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }

    @Override
    public boolean getMiddleProcedure(int produceType) {
        if (produceType == 11) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Integer getEndProcedure() {
        return 20;
    }

    @Override
    public Integer relation(MmeMapCommon mmeMapCommon, int type, String[] arr) {
        long startTime = mmeMapCommon.getStartTime();
        long endTime = mmeMapCommon.getEndTime();
        Long start;
        Long end;
        try {
            start = sdf.parse(arr[MmeIndex.startTime].substring(0, 23)).getTime();
            end = sdf.parse(arr[MmeIndex.endTime].substring(0, 23)).getTime();
        } catch (ParseException e) {
            return 3;
        }
        if (getMiddleProcedure(type)) {
            if (start >= startTime && endTime >= end) {
                switch (type) {
                    case 11:
                        createType11(arr);
                        break;
                }
            }
            return 1;
        } else if (type == getEndProcedure()) {
            createType20(arr);
            return 2;
        } else {
            return 0;
        }

    }

    @Override
    public String toString() {
        String common = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|").append(mme_ue_s1ap_id).append("|")
                .append(enb_ue_s1ap_id).append("|").append(mme_groupid).append("|").append(mme_code).append("|").append(m_tmsi).append("|").append(tac).append("|")
                .append(eci).append("|").append(apn).append("|").append(ebi).append("|").append(pti).append("|").append(req_time).append("|").append(rsp_time).append("|")
                .append(acpt_time).append("|").append(context_rel_time).append("|").append(req_cause).append("|").append(contxt_rl_time).append("|")
                .append(release_cause).append("|").append(comp_time).append("|").append(res_time).append("|").append(msg_id).append("|").append(cause_code).append("|");
        return common + sb.toString();
    }
}

