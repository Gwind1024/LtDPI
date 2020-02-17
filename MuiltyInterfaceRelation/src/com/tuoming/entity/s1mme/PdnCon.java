package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

// PDN connectivity 7
public class PdnCon extends MmeCommon {

    public String enodeb_ip = "";       //eNodeB信令面IP
    public String enodeb_port = "";     //eNodeB端口
    public String mme_ip = "";          //MME IP地址
    public String mme_port = "";        //MME端口
    public String mme_ue_s1ap_id = "";  //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";  //ENB UE S1AP ID
    public String srvtype = "";         //业务类型
    public String mme_groupid = "";     //MME GroupID
    public String mme_code = "";        //MME Code
    public String m_tmsi = "";          //M-TMSI
    public String tac = "";             //TAC
    public String eci = "";             //ECI
    public String ip_versiong = "";     //PDN请求IP版本
    public String user_ip = "";         //IP地址
    public String apn = "";             //APN
    public String ebi = "";             //激活承载标识
    public String pti = "";             //PTI
    private String apn_req = "";         //请求APN
    public String eps_qos = "";         //EPS QoS
    public String qos_negt = "";        //协商QoS
    public String ap_ambr = "";         //APN AMBR
    public String erab_num = "";        //ERAB数量
    public String erabid_req = "";      //请求消息的E-RAB ID
    public String rabqos_req = "";      //请求RAB中的QOS
    public String sgw_gtp_ip = "";      //SGW用户面IP
    public String sgw_gtp_teid = "";    //SGW用户面TEID
    public String erabid_rsp = "";      //响应消息的E-RAB ID
    public String rab_fillist = "";     //建立失败RAB的列表
    public String enb_gtp_ip = "";      //eNodeB用户面IP
    public String enb_gtp_teid = "";    //eNodeB用户面TEID
    public String req_time = "";        //消息请求时间
    private String bear_act_time = "";   //激活默认承载上下文请求消息时间
    private String pdn_cause = "";       //PDN失败原因码
    private String eear = "";            //激活默认承载上下文响应消息时间
    private String esm_cause = "";       //激活建立失败原因码
    public String context_rel_time = "";//UE context release request时间
    public String req_cause = "";       //请求原因
    public String contxt_rl_time = "";  //UE context release command时间
    public String release_cause = "";   //UE context release释放原因
    public String comp_time = "";       //UE context releasecomplete时间
    public String res_time = "";        //结束消息类型
    public String msg_id = "";          //结束消息
    public String cause_code = "";      //消息的结果码

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    public PdnCon() {
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[PdnConIndex.enodeb_ip];
        this.enodeb_port = str[PdnConIndex.enodeb_port];
        this.mme_ip = str[PdnConIndex.mme_ip];
        this.mme_port = str[PdnConIndex.mme_port];
        this.mme_ue_s1ap_id = str[PdnConIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str, AttachIndex.enb_ue_s1ap_id)];
        this.srvtype = str[PdnConIndex.srvtype];
        this.mme_groupid = str[PdnConIndex.mme_groupid];
        this.mme_code = str[PdnConIndex.mme_code];
        this.m_tmsi = str[PdnConIndex.m_tmsi];
        this.tac = str[PdnConIndex.tac];
        this.eci = str[PdnConIndex.eci];
        if ("".equals(str[PdnConIndex.ip_versiong]) && "".equals(str[PdnConIndex.user_ip])) {
            this.ip_versiong = "";
            //this.user_ip = "";
        } else if (!"".equals(str[PdnConIndex.ip_versiong]) && "".equals(str[PdnConIndex.user_ip])) {
            this.ip_versiong = "0";
            // this.user_ip = str[PdnConIndex.ip_versiong];
        } else if ("".equals(str[PdnConIndex.ip_versiong]) && !"".equals(str[PdnConIndex.user_ip])) {
            this.ip_versiong = "1";
            //this.user_ip = str[PdnConIndex.user_ip];
        } else {
            this.ip_versiong = "2";
            // this.user_ip = str[PdnConIndex.ip_versiong];
        }

        this.apn_req = str[PdnConIndex.apn_req];
        this.erab_num = str[PdnConIndex.erab_num];
        this.req_time = str[PdnConIndex.req_time];
        this.bear_act_time = str[PdnConIndex.bear_act_time];
        String pdnCause = "1".equals(str[PdnConIndex.msg_id]) ? str[PdnConIndex.pdn_cause] : "";
        this.pdn_cause = TypeChange.strToOx7f(pdnCause);
        this.eear = str[PdnConIndex.eear];
        String esmCause = str[PdnConIndex.msg_id].equals("1") ? str[PdnConIndex.esm_cause] : "";
        this.esm_cause = TypeChange.strToOx7f(esmCause);


        this.res_time = "0";
        switch (str[PdnConIndex.msg_id]) {
            case "0":
                this.msg_id = "194";
                break;
            case "1":
                this.msg_id = "209";
                break;
            case "255":
                this.msg_id = "208";
                break;
        }

        this.cause_code = TypeChange.strToOx7f(str[PdnConIndex.cause_code]);

    }

    //20：ESM INFORMATION  
    private void createType20(String[] str) {
        this.context_rel_time = str[CommonUtils.convertIndex(str, PdnConIndex.context_rel_time)];
        this.req_cause = str[CommonUtils.convertIndex(str, PdnConIndex.req_cause)];
        this.contxt_rl_time = str[PdnConIndex.contxt_rl_time];
        this.release_cause = TypeChange.strToOx7f(str[PdnConIndex.release_cause]);
        this.comp_time = str[PdnConIndex.comp_time];
    }

    //40：ESM INFORMATION  
    private void createType40(String[] str) {
       /* try {
            this.erabid_req = str[CommonUtils.convertIndex(str, PdnConIndex.erabid_req)] == null ? "" : Integer.toHexString(Integer.parseInt(str[CommonUtils.convertIndex(str, PdnConIndex.erabid_req)]));
        } catch (NumberFormatException e) {
            this.erabid_req = "";
        }*/
        Integer erabid_req_index = CommonUtils.numIndex(str, PdnConIndex.erabid_req);
        if (erabid_req_index != null) {
            this.erabid_req = str[erabid_req_index];
        }
        this.rabqos_req = str[PdnConIndex.rabqos_req] == null ? "" : TypeChange.strToHex(str[CommonUtils.convertIndex(str, PdnConIndex.rabqos_req)]);
        this.sgw_gtp_ip = str[CommonUtils.convertIndex(str, PdnConIndex.sgw_gtp_ip)];
        Integer sgw_gtp_teid_index = CommonUtils.numIndex(str, PdnConIndex.sgw_gtp_teid);
        if (sgw_gtp_teid_index != null) {
            this.sgw_gtp_teid = str[sgw_gtp_teid_index];
        }
        //this.erabid_rsp = str[CommonUtils.convertIndex(str, PdnConIndex.erabid_rsp)];
        this.rab_fillist = str[CommonUtils.convertIndex(str, PdnConIndex.rab_fillist)];
        this.enb_gtp_ip = str[CommonUtils.convertIndex(str, PdnConIndex.enb_gtp_ip)];
        this.enb_gtp_teid = str[CommonUtils.convertIndex(str, PdnConIndex.enb_gtp_teid)];
    }

    private void createType18(String[] str) {
        this.erabid_rsp = str[CommonUtils.convertIndex(str, PdnConIndex.erabid_rsp)];
    }

    //42：ESM INFORMATION  
    private void createType42(String[] str) {
        this.apn = str[PdnConIndex.apn];
        this.user_ip = str[PdnConIndex.ip_versiong];
        Integer ebi_index = CommonUtils.numIndex(str, PdnConIndex.ebi);
        if (ebi_index != null) {
            this.ebi = TypeChange.strToHex(str[ebi_index]);
        }
        this.pti = TypeChange.strToHex(str[CommonUtils.convertIndex(str, PdnConIndex.pti)]);
        this.eps_qos = TypeChange.strToHex(str[CommonUtils.convertIndex(str, PdnConIndex.eps_qos)]);
        this.qos_negt = TypeChange.strToHex(str[CommonUtils.convertIndex(str, PdnConIndex.qos_negt)]);
        this.ap_ambr = TypeChange.strToHex(str[CommonUtils.convertIndex(str, PdnConIndex.ap_ambr)]);
        this.bear_act_time = str[PdnConIndex.bear_act_time];
    }


    @Override
    public boolean getMiddleProcedure(int produceType) {
        if (produceType == 18 || produceType == 40 || produceType == 42) {
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
//        //处理pdn在attach之前的情况
//        try {
//            if (arr[MmeIndex.startTime].equals(mmeMapCommon.getMmeCommon().startTime) && arr[MmeIndex.endTime].equals(mmeMapCommon.getMmeCommon().endTime)) {
//                if (type == 1) {
//                    //创建attach
//                    Attach attach = new Attach();
//                    attach.decode(arr);
//                    mmeMapCommon.setPreduceType(1);
//                    mmeMapCommon.setMmeCommon(attach);
//                    attach.relation(mmeMapCommon, 7, mmeMapCommon.getSplit());
//                    System.out.println("[关联子流程成功]<->主流程:1 子流程:7,下条日志不予理会");
//                    return 1;
//                }
//            }
//        } catch (Exception e) {
//            return 3;
//        }
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
                    case 18:
                        createType18(arr);
                        break;
                    case 40:
                        createType40(arr);
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
        sb.append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|").append(mme_ue_s1ap_id).append("|").append(enb_ue_s1ap_id).append("|")
                .append(srvtype).append("|").append(mme_groupid).append("|").append(mme_code).append("|").append(m_tmsi).append("|").append(tac).append("|").append(eci).append("|")
                .append(ip_versiong).append("|").append(user_ip).append("|").append(apn).append("|").append(ebi).append("|").append(pti).append("|").append(apn_req).append("|").append(eps_qos).append("|")
                .append(qos_negt).append("|").append(ap_ambr).append("|").append(erab_num).append("|").append(erabid_req).append("|").append(rabqos_req).append("|").append(sgw_gtp_ip).append("|")
                .append(sgw_gtp_teid).append("|").append(erabid_rsp).append("|").append(rab_fillist).append("|").append(enb_gtp_ip).append("|").append(enb_gtp_teid).append("|").append(req_time).append("|")
                .append(bear_act_time).append("|").append(pdn_cause).append("|").append(eear).append("|").append(esm_cause).append("|").append(context_rel_time).append("|").append(req_cause).append("|")
                .append(contxt_rl_time).append("|").append(release_cause).append("|").append(comp_time).append("|").append(res_time).append("|").append(msg_id).append("|").append(cause_code);
        return common + sb.toString();
    }
}
