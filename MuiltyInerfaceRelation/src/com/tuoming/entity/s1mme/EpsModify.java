package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EpsModify extends MmeCommon {


    public String enodeb_ip = "";         //eNodeB信令面IP
    public String enodeb_port = "";       //eNodeB端口
    public String mme_ip = "";            //MME IP地址
    public String mme_port = "";          //MME端口
    public String mme_ue_s1ap_id = "";    //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";    //ENB UE S1AP ID
    public String esm_cause = "";         //修改原因
    public String mme_groupid = "";       //MME GroupID
    public String mme_code = "";          //MME Code
    public String m_tmsi = "";            //M-TMSI
    public String tac = "";               //TAC
    public String eci = "";               //ECI
    public String ebi = "";               //激活EBI
    public String pti = "";               //激活PTI
    public String tfa = "";               //Traffic flow aggregate
    public String eps_qos = "";           //EPS QoS
    public String qos_negt = "";          //协商QoS
    public String ap_ambr = "";           //APN AMBR
    public String erab_num = "";          //ERAB数量
    public String erabid_req = "";        //请求消息的E-RAB ID
    public String rabqos_req = "";        //请求RAB中的QOS
    public String erabid_rsp = "";        //响应消息的E-RAB ID
    public String rab_fillist = "";       //建立失败RAB的列表
    public String req_time = "";          //承载资源修改请求时间
    public String rsp_time = "";          //响应时间
    public String rej_cause = "";         //核心网拒绝原因码
    public String eps_res = "";           //EPS承载上下文响应
    public String ue_rej_cause = "";      //UE拒绝原因
    public String context_rel_time = "";  //UE context release request时间
    public String req_cause = "";         //请求原因
    public String contxt_rl_time = "";    //UE context release command时间
    public String release_cause = "";     //UE context release释放原因
    public String comp_time = "";         //UE context releasecomplete时间
    public String res_time = "";          //结束消息类型
    public String msg_id = "";            //结束消息
    public String cause_code = "";        //消息的结果码

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public EpsModify() {
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[EpsModifyIndex.enodeb_ip];
        this.enodeb_port = str[EpsModifyIndex.enodeb_port];
        this.mme_ip = str[EpsModifyIndex.mme_ip];
        this.mme_port = str[EpsModifyIndex.mme_port];
        this.mme_ue_s1ap_id = str[EpsModifyIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str, AttachIndex.enb_ue_s1ap_id)];
        this.esm_cause = TypeChange.strToOx7f(str[EpsModifyIndex.esm_cause]);
        this.mme_groupid = str[EpsModifyIndex.mme_groupid];
        this.mme_code = str[EpsModifyIndex.mme_code];
        this.m_tmsi = str[EpsModifyIndex.m_tmsi];
        this.tac = str[EpsModifyIndex.tac];
        this.eci = str[EpsModifyIndex.eci];
        this.tfa = "";
        this.erab_num = str[EpsModifyIndex.erab_num];

        this.req_time = str[EpsModifyIndex.req_time];
        this.rsp_time = (str[EpsModifyIndex.msg_id] != null && "1".equals(str[EpsModifyIndex.msg_id])) ? str[EpsModifyIndex.rsp_time2] : "";
        String rej = (str[EpsModifyIndex.msg_id] != null && "1".equals(str[EpsModifyIndex.msg_id])) ? str[EpsModifyIndex.rej_cause] : "";
        this.rej_cause = TypeChange.strToOx7f(rej);

        this.res_time = "0";
        try {
            switch (str[EpsModifyIndex.msg_id]) {
                case "0":
                    this.msg_id = "202";
                    break;
                case "1":
                    this.msg_id = "215";
                    break;
                case "255":
                    this.msg_id = "214";
                    break;
            }
        } catch (Exception e) {
            this.msg_id = "";
        }
        String causecode = str[EpsModifyIndex.cause_code];
        this.cause_code = TypeChange.strToOx7f(causecode);
        Integer erabid_req_index = CommonUtils.numIndex(str, EpsModifyIndex.erabid_req);
        if (erabid_req_index != null) {
            this.erabid_req = str[erabid_req_index];
        }

    }

    //12
    private void createType12(String[] str) {
        Integer ebi_index = CommonUtils.numIndex(str, EpsModifyIndex.ebi);
        if (ebi_index != null) {
            this.ebi = TypeChange.strToHex(str[ebi_index]);
        }
        String pti = str[CommonUtils.convertIndex(str, EpsModifyIndex.pti)];
        this.pti = TypeChange.strToHex(pti);
        String ambr = str[CommonUtils.convertIndex(str, EpsModifyIndex.ap_ambr)];
        this.ap_ambr = TypeChange.strToHex(ambr);
        this.rsp_time = str[EpsModifyIndex.rsp_time1];
        this.eps_res = str[EpsModifyIndex.eps_res];
        Integer erabid_req_index = CommonUtils.numIndex(str, EpsModifyIndex.erabid_req);
        if (erabid_req_index != null) {
            this.erabid_req = str[erabid_req_index];
        }
        String uerej = (str[EpsModifyIndex.msg_id] != null && "1".equals(str[EpsModifyIndex.msg_id])) ? str[EpsModifyIndex.ue_rej_cause] : "";
        this.ue_rej_cause = TypeChange.strToOx7f(uerej);

    }

    //13
    private void createType13(String[] str) {
        this.rsp_time = str[EpsModifyIndex.rsp_time1];
        this.eps_res = str[EpsModifyIndex.eps_res];
        String uerej = (str[EpsModifyIndex.msg_id] != null && "1".equals(str[EpsModifyIndex.msg_id])) ? str[EpsModifyIndex.ue_rej_cause] : "";
        this.ue_rej_cause = TypeChange.strToOx7f(uerej);
    }

    //41
    private void createType41(String[] str) {
        //  this.erabid_req = str[CommonUtils.convertIndex(str, EpsModifyIndex.erabid_req)];
        String rabqos = str[CommonUtils.convertIndex(str, EpsModifyIndex.rabqos_req)];
        this.rabqos_req = TypeChange.strToHex(rabqos);
        Integer erabid_rsp_index = CommonUtils.numIndex(str, EpsModifyIndex.erabid_rsp);
        if (erabid_rsp_index != null) {
            this.erabid_rsp = str[erabid_rsp_index];
        }
        String rabfill = str[CommonUtils.convertIndex(str, EpsModifyIndex.rab_fillist)];
        this.rab_fillist = TypeChange.strToHex(rabfill);

    }

    //42
    private void createType42(String[] str) {
        String epsq = str[CommonUtils.convertIndex(str, EpsModifyIndex.eps_qos)];
        this.eps_qos = TypeChange.strToHex(epsq);

        String negt = str[CommonUtils.convertIndex(str, EpsModifyIndex.qos_negt)];
        this.qos_negt = TypeChange.strToHex(negt);

    }

    //20
    private void createType20(String[] str) {
        this.context_rel_time = str[CommonUtils.convertIndex(str, EpsModifyIndex.context_rel_time)];
        String reqcause = str[CommonUtils.convertIndex(str, EpsModifyIndex.req_cause)];
        this.req_cause = TypeChange.strToOx7f(reqcause);
        this.contxt_rl_time = str[EpsModifyIndex.contxt_rl_time];
        String releasecause = str[EpsModifyIndex.release_cause];
        this.release_cause = TypeChange.strToOx7f(releasecause);
        this.comp_time = str[EpsModifyIndex.comp_time];

    }

    @Override
    public boolean getMiddleProcedure(int produceType) {
        if (produceType == 18 || produceType == 41 || produceType == 42) {
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
                    case 12:
                        createType12(arr);
                        break;
                    case 13:
                        createType13(arr);
                        break;
                    case 41:
                        createType41(arr);
                        break;
                    case 42:
                        createType42(arr);
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
                .append(enb_ue_s1ap_id).append("|").append(esm_cause).append("|").append(mme_groupid).append("|").append(mme_code).append("|").append(m_tmsi).append("|")
                .append(tac).append("|").append(eci).append("|").append(ebi).append("|").append(pti).append("|").append(tfa).append("|").append(eps_qos).append("|")
                .append(qos_negt).append("|").append(ap_ambr).append("|").append(erab_num).append("|").append(erabid_req).append("|").append(rabqos_req).append("|")
                .append(erabid_rsp).append("|").append(rab_fillist).append("|").append(req_time).append("|").append(rsp_time).append("|").append(rej_cause).append("|")
                .append(eps_res).append("|").append(ue_rej_cause).append("|").append(context_rel_time).append("|").append(req_cause).append("|").append(contxt_rl_time).append("|")
                .append(release_cause).append("|").append(comp_time).append("|").append(res_time).append("|").append(msg_id).append("|").append(cause_code);
        return common + sb.toString();
    }

}
