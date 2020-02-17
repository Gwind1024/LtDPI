package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EpsAllocation extends MmeCommon {

    public String enodeb_ip = "";         //eNodeB信令面IP
    public String enodeb_port = "";       //eNodeB端口
    public String mme_ip = "";            //MME IP地址
    public String mme_port = "";          //MME端口
    public String mme_ue_s1ap_id = "";    //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";    //ENB UE S1AP ID
    public String mme_groupid = "";       //MME GroupID
    public String mme_code = "";          //MME Code
    public String m_tmsi = "";            //M-TMSI
    public String tac = "";               //TAC
    public String eci = "";               //ECI
    public String ebi = "";               //激活EBI
    public String pti = "";               //激活PTI
    public String tfa = "";               //Traffic flow aggregate
    public String lebi = "";              //Linked EPS bearer identity
    public String eps_qos = "";           //EPS QoS
    public String qos_negt = "";          //协商QoS
    public String ap_ambr = "";           //APN AMBR
    public String erab_num = "";          //ERAB数量
    public String erabid_req = "";        //请求消息的E-RAB ID
    public String rabqos_req = "";        //请求RAB中的QOS
    public String sgw_gtp_ip = "";        //SGW用户面IP
    public String sgw_gtp_teid = "";      //SGW用户面TEID
    public String erabid_rsp = "";        //响应消息的E-RAB ID
    public String rab_fillist = "";       //建立失败RAB的列表
    public String enb_gtp_ip = "";        //eNodeB用户面IP
    public String enb_gtp_teid = "";      //eNodeB用户面TEID
    public String req_time = "";          //承载资源分配消息请求时间
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


    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[EpsAllocationIndex.enodeb_ip];
        this.enodeb_port = str[EpsAllocationIndex.enodeb_port];
        this.mme_ip = str[EpsAllocationIndex.mme_ip];
        this.mme_port = str[EpsAllocationIndex.mme_port];
        this.mme_ue_s1ap_id = str[EpsAllocationIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str, AttachIndex.enb_ue_s1ap_id)];
        this.mme_groupid = str[EpsAllocationIndex.mme_groupid];
        this.mme_code = str[EpsAllocationIndex.mme_code];
        this.m_tmsi = str[EpsAllocationIndex.m_tmsi];
        this.tac = str[EpsAllocationIndex.tac];
        this.eci = str[EpsAllocationIndex.eci];
        this.tfa = "";
        this.lebi = "";
        this.erab_num = str[CommonUtils.convertIndex(str, EpsAllocationIndex.erab_num)];
        this.req_time = str[EpsAllocationIndex.req_time];
        this.rsp_time = (str[EpsAllocationIndex.msg_id] != null && "1".equals(str[EpsAllocationIndex.msg_id])) ? str[EpsAllocationIndex.rsp_time2] : "";
        String rejnum = (str[EpsAllocationIndex.msg_id] != null && "1".equals(str[EpsAllocationIndex.msg_id])) ? str[EpsAllocationIndex.rej_cause] : "";
        this.rej_cause = TypeChange.strToOx7f(rejnum);
        String uerejnum = (str[EpsAllocationIndex.msg_id] != null && "1".equals(str[EpsAllocationIndex.msg_id])) ? str[EpsAllocationIndex.ue_rej_cause] : "";
        this.ue_rej_cause = TypeChange.strToOx7f(uerejnum);

        this.res_time = "0";
        if ("0".equals(str[EpsAllocationIndex.msg_id])) {
            this.msg_id = "198";
        } else if ("1".equals(str[TauIndex.msg_id])) {
            this.msg_id = "213";
        } else if ("255".equals(str[TauIndex.msg_id])) {
            this.msg_id = "212";
        }
        this.cause_code = TypeChange.strToOx7f(str[EpsAllocationIndex.cause_code]);


    }

    //12
    private void createType12(String[] str) {
        this.rsp_time = str[EpsAllocationIndex.rsp_time1];
        this.eps_res = str[EpsAllocationIndex.eps_res];
    }

    //13
    private void createType13(String[] str) {
        Integer ebi_index = CommonUtils.numIndex(str, EpsAllocationIndex.ebi);
        if (ebi_index != null) {
            this.ebi = CommonUtils.strToInteger(str[ebi_index - 1]) > 0 ? TypeChange.strToHex(ebi_index) : "";
        }
        String ptinum = str[CommonUtils.convertIndex(str, EpsAllocationIndex.pti)];
        this.pti = TypeChange.strToHex(ptinum);

        String epsq = str[CommonUtils.convertIndex(str, EpsAllocationIndex.eps_qos)];
        this.eps_qos = TypeChange.strToHex(epsq);

        String qosn = str[CommonUtils.convertIndex(str, EpsAllocationIndex.qos_negt)];
        this.qos_negt = TypeChange.strToHex(qosn);

        String ambr = str[CommonUtils.convertIndex(str, EpsAllocationIndex.ap_ambr)];
        this.ap_ambr = TypeChange.strToHex(ambr);
        this.rsp_time = str[EpsAllocationIndex.rsp_time1];
        this.eps_res = str[EpsAllocationIndex.eps_res];
    }

    //18
    private void createType18(String[] str) {
        Integer enb_gtp_teid_index = CommonUtils.numIndex(str, EpsAllocationIndex.enb_gtp_teid);
        if (enb_gtp_teid_index != null) {
            this.enb_gtp_teid = str[enb_gtp_teid_index];
        }
    }

    //40
    private void createType40(String[] str) {

        Integer erabid_req_index = CommonUtils.numIndex(str, EpsAllocationIndex.erab_num);
        if (erabid_req_index != null) {
            this.erabid_req = str[erabid_req_index];
        }
        String rabqos = str[CommonUtils.convertIndex(str, EpsAllocationIndex.rabqos_req)];
        this.rabqos_req = TypeChange.strToHex(rabqos);

        this.sgw_gtp_ip = str[CommonUtils.convertIndex(str, EpsAllocationIndex.sgw_gtp_ip)];
        this.sgw_gtp_teid = str[CommonUtils.convertIndex(str, EpsAllocationIndex.sgw_gtp_teid)];
        this.erabid_rsp = str[CommonUtils.convertIndex(str, EpsAllocationIndex.erabid_rsp)];
        String rabfill = str[CommonUtils.convertIndex(str, EpsAllocationIndex.rab_fillist)];
        this.rab_fillist = TypeChange.strToHex(rabfill);

        this.enb_gtp_ip = str[CommonUtils.convertIndex(str, EpsAllocationIndex.enb_gtp_ip)];
        //this.enb_gtp_teid = str[CommonUtils.convertIndex(str, EpsAllocationIndex.enb_gtp_teid)];
    }

    //20
    private void createType20(String[] str) {
        this.context_rel_time = str[CommonUtils.convertIndex(str, EpsAllocationIndex.context_rel_time)];
        int reqcause = CommonUtils.convertIndex(str, EpsAllocationIndex.req_cause);
        this.req_cause = TypeChange.strToOx7f(str[reqcause]);
        this.contxt_rl_time = str[EpsAllocationIndex.contxt_rl_time];
        this.release_cause = TypeChange.strToOx7f(str[EpsAllocationIndex.release_cause]);
        this.comp_time = str[EpsAllocationIndex.comp_time];
    }

    @Override
    public boolean getMiddleProcedure(int produceType) {
        if (produceType == 12 || produceType == 13 || produceType == 18 || produceType == 40) {
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
                    case 18:
                        createType18(arr);
                        break;
                    case 40:
                        createType40(arr);
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
                .append(eci).append("|").append(ebi).append("|").append(pti).append("|").append(tfa).append("|").append(lebi).append("|").append(eps_qos).append("|")
                .append(qos_negt).append("|").append(ap_ambr).append("|").append(erab_num).append("|").append(erabid_req).append("|").append(rabqos_req).append("|")
                .append(sgw_gtp_ip).append("|").append(sgw_gtp_teid).append("|").append(erabid_rsp).append("|").append(rab_fillist).append("|").append(enb_gtp_ip).append("|")
                .append(enb_gtp_teid).append("|").append(req_time).append("|").append(rsp_time).append("|").append(rej_cause).append("|").append(eps_res).append("|")
                .append(ue_rej_cause).append("|").append(context_rel_time).append("|").append(req_cause).append("|").append(contxt_rl_time).append("|").append(release_cause).append("|")
                .append(comp_time).append("|").append(res_time).append("|").append(msg_id).append("|").append(cause_code);
        return common + sb.toString();
    }
}
