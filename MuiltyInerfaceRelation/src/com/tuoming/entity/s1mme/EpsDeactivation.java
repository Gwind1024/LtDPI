package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EpsDeactivation extends MmeCommon {

    public String enodeb_ip = "";         //eNodeB信令面IP
    public String enodeb_port = "";       //eNodeB端口
    public String mme_ip = "";            //MME IP地址
    public String mme_port = "";          //MME端口
    public String mme_ue_s1ap_id = "";    //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";    //ENB UE S1AP ID
    public String dereq_cause = "";       //去激活请求原因
    public String mme_groupid = "";       //MME GroupID
    public String mme_code = "";          //MME Code
    public String m_tmsi = "";            //M-TMSI
    public String tac = "";               //TAC
    public String eci = "";               //ECI
    public String ebi = "";               //去激活EBI
    public String pti = "";               //去激活PTI
    public String erab_rel = "";          //去激活的ERAB
    public String req_time = "";          //请求消息请求时间
    public String rsp_time = "";          //响应消息时间
    public String context_rel_time = "";  //UE context release request时间
    public String req_cause = "";         //请求原因
    public String contxt_rl_time = "";    //UE context release command时间
    public String release_cause = "";     //UE context release释放原因
    public String comp_time = "";         //UE context releasecomplete时间
    public String res_time = "";          //结束消息类型
    public String msg_id = "";            //结束消息
    public String cause_code = "";        //消息的结果码

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }

    public EpsDeactivation() {
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[EpsDeactivationIndex.enodeb_ip];
        this.enodeb_port = str[EpsDeactivationIndex.enodeb_port];
        this.mme_ip = str[EpsDeactivationIndex.mme_ip];
        this.mme_port = str[EpsDeactivationIndex.mme_port];
        this.mme_ue_s1ap_id = str[EpsDeactivationIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str, AttachIndex.enb_ue_s1ap_id)];
        this.dereq_cause = TypeChange.strToOx7f(str[EpsDeactivationIndex.dereq_cause]);
        this.mme_groupid = str[EpsDeactivationIndex.mme_groupid];
        this.mme_code = str[EpsDeactivationIndex.mme_code];
        this.m_tmsi = str[EpsDeactivationIndex.m_tmsi];
        this.tac = str[EpsDeactivationIndex.tac];
        this.eci = str[EpsDeactivationIndex.eci];
        Integer ebi_index = CommonUtils.numIndex(str, EpsDeactivationIndex.ebi);
        if (ebi_index != null) {
            this.ebi = TypeChange.strToHex(str[ebi_index]);
        }
        String ptinum = str[CommonUtils.convertIndex(str, EpsDeactivationIndex.pti)];
        this.pti = TypeChange.strToHex(ptinum);

        this.req_time = str[EpsDeactivationIndex.req_time];
        this.rsp_time = str[EpsDeactivationIndex.rsp_time];

        this.res_time = "0";
        switch (str[EpsDeactivationIndex.msg_id]) {
            case "0":
                this.msg_id = "202";
                break;
            case "1":
                this.msg_id = "203";
                break;
            case "255":
                this.msg_id = "201";
                break;
        }

        String causecode = str[EpsDeactivationIndex.cause_code];
        this.cause_code = TypeChange.strToOx7f(causecode);

    }

    //21
    private void createType21(String[] str) {
        String erabr = str[CommonUtils.convertIndex(str, EpsDeactivationIndex.erab_rel)];
        this.erab_rel = TypeChange.strToHex(erabr);
    }

    //20
    private void createType20(String[] str) {
        this.context_rel_time = str[CommonUtils.convertIndex(str, EpsDeactivationIndex.context_rel_time)];
        this.req_cause = str[CommonUtils.convertIndex(str, EpsDeactivationIndex.req_cause)];
        this.contxt_rl_time = str[EpsDeactivationIndex.contxt_rl_time];
        String releasecause = str[EpsDeactivationIndex.release_cause];
        this.release_cause = TypeChange.strToOx7f(releasecause);
        this.comp_time = str[EpsDeactivationIndex.comp_time];

    }

    @Override
    public boolean getMiddleProcedure(int produceType) {
        if (produceType == 21) {
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
                    case 21:
                        createType21(arr);
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
        sb.append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|").append(mme_ue_s1ap_id).append("|").append(enb_ue_s1ap_id).append("|")
                .append(dereq_cause).append("|").append(mme_groupid).append("|").append(mme_code).append("|").append(m_tmsi).append("|").append(tac).append("|").append(eci).append("|")
                .append(ebi).append("|").append(pti).append("|").append(erab_rel).append("|").append(req_time).append("|").append(rsp_time).append("|").append(context_rel_time).append("|")
                .append(req_cause).append("|").append(contxt_rl_time).append("|").append(release_cause).append("|").append(comp_time).append("|").append(res_time).append("|")
                .append(msg_id).append("|").append(cause_code);
        return common + sb.toString();
    }
}
