package com.tuoming.entity.s1mme;


import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

//附着 1
public class Attach extends MmeCommon {

    public String enodeb_ip = "";             //eNodeB信令面IP
    public String enodeb_port = "";           //eNodeB端口
    public String mme_ip = "";                //MME IP地址
    public String mme_port = "";              //MME端口
    public String mme_ue_s1ap_id = "";        //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";        //ENB UE S1AP ID
    private String srvtype_req = "";           //请求业务类型
    private String srvtype_rsp = "";           //响应业务类型
    private String add_uptype_req = "";        //附加更新类型（请求）
    private String add_uptype_res = "";        //附加更新类型（响应）
    public String mme_groupid = "";           //MME GroupID
    public String mme_code = "";              //MME Code
    public String m_tmsi = "";                //M-TMSI
    public String tmsi = "";                  //TMSI
    private String user_ip = "";               //IP地址
    public String apn = "";                   //APN
    private String guti_type = "";             //老GUTI类型
    public String mcc = "";                   //老国家码
    public String mnc = "";                   //老移动网络码
    private String old_guti = "";              //老GUTI
    public String tac = "";                   //TAC
    public String eci = "";                   //ECI
    private String tai_list1 = "";             //TAI 列表
    private String tai_list2 = "";             //TAI 列表
    private String tai_list3 = "";             //TAI 列表
    private String tai_list4 = "";             //TAI 列表
    public String lac = "";                   //LAC
    private String old_tac = "";               //OLD TAC
    private String old_lac = "";               //OLD LAC
    private String drx_par = "";               //DRX参数
    private String uenc = "";                  //UE网络能力
    private String vdp_uus = "";               //VDP_UUS
    private String msnc = "";                  //MS网络能力
    private String nksi = "";                  //NAS key set identifier
    private String bear_status = "";           //承载状态
    private String eps_nfs = "";               //EPS网络特性支持
    private String msc2 = "";                  //Mobile Station Classmark2
    private String msc3 = "";                  //Mobile Station Classmark3
    private String pdn_srvtype = "";           //PDN业务类型
    private String ip_versiong = "";           //IP版本
    private String pdn_cause = "";             //PDN失败原因码
    public String ebi = "";                   //激活承载标识
    public String pti = "";                   //PTI
    private String apn_req = "";               //请求APN
    public String eps_qos = "";               //EPS QoS
    public String qos_negt = "";              //协商QoS
    public String ap_ambr = "";               //APN AMBR
    public String erab_num = "";              //ERAB数量
    public String erabid_rsp = "";            //请求消息的E-RAB ID
    public String rabqos_req = "";            //请求RAB中的QOS
    public String sgw_gtp_ip = "";            //SGW用户面IP
    public String sgw_gtp_teid = "";          //SGW用户面TEID
    private String erabid_rsq = "";            //响应消息的E-RAB ID
    public String rab_fillist = "";           //建立失败RAB的列表
    public String enb_gtp_ip = "";            //eNodeB用户面IP
    public String enb_gtp_teid = "";          //eNodeB用户面TEID
    public String req_time = "";              //消息请求时间
    private String identi_time = "";           //取标识开始时间
    private String end_time = "";              //取标识结束时间
    private String auth_time = "";             //鉴权请求时间
    private String auth_failure = "";          //鉴权失败时间
    public String cause = "";                 //鉴权失败原因
    private String identity_end_time = "";     //鉴权结束时间
    private String cip_time = "";              //ciphered OptionRequest时间
    private String rsp_end_time = "";          //ciphered Option Response时间
    private String esm_info_req_time = "";     //ESM INFORMATION REQUEST消息时间
    private String esm_info_rsp_time = "";     //ESM INFORMATION RESPONSE消息时间
    private String accept_time = "";           //Initial context setup/Attach accept/acitve Default EPS bear request时间
    private String reject_cause = "";          //attach拒绝原因码
    private String initial_time = "";          //Initial context response时间
    private String erab_cause = "";            //承载建立失败原因码
    private String complete_time = "";         //attach complete/active default EPS bear accept/reject时间
    private String esm_cause = "";             //激活建立失败原因码
    public String context_rel_time = "";      //UE context release request时间
    public String req_cause = "";             //释放原因
    public String contxt_rl_time = "";        //UE context release command时间
    public String release_cause = "";         //UE context release释放原因
    public String comp_time = "";             //UE context release complete时间
    public String res_time = "0";                //结束消息类型
    public String msg_id = "0";                //结束消息
    public String cause_code = "";            //消息的结果码
    public String rand = "";                    //鉴权随机数
    public String autn = "";                    //鉴权向量
    private String ciot_support = "";            //是否支持NBIOT
    private String ce_level = "";                //覆盖增强级
    private String psm_status = "";            //省电模式状态
    private String t3324 = "";                    //T3324
    private String t3412_extended = "";        //T3412 extended value
    private String t3412 = "";                 //T3412
    private String extended_drx_status = "";  //非连续接收状态
    private String paging_time_window = "";    //寻呼时间窗
    private String edrx_value = "";            //非连续接值
    private String device_properties = "";     //是否是NAS信令低优先级
    private String t3346 = "";                 //信令拥塞控制字段
    private String req_epco = "";              //请求消息APN速率控制
    private String resp_epco = "";             //接收消息APN速率控制
    private String nr_restriction = "";        //NR限制
    private String gnb_gip_ip = "";             //gNB_IP

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public Attach() {
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }

    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[AttachIndex.enodeb_ip];
        this.enodeb_port = str[AttachIndex.enodeb_port];
        this.mme_ip = str[AttachIndex.mme_ip];
        this.mme_port = str[AttachIndex.mme_port];
        this.mme_ue_s1ap_id = str[AttachIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str, AttachIndex.enb_ue_s1ap_id)];
        this.srvtype_req = str[AttachIndex.srvtype_req];
        this.srvtype_rsp = str[AttachIndex.srvtype_rsp];

        int addIndex = CommonUtils.convertIndex(str, AttachIndex.add_uptype_req);
        if ("0".equals(str[addIndex])) {
            this.add_uptype_req = "0";
        } else if ("1".equals(str[addIndex])) {
            this.add_uptype_req = "1";
        }
        if ("1".equals(str[AttachIndex.sdrType])) {
            this.add_uptype_res = str[AttachIndex.add_uptype_res];
        }
        this.mme_groupid = str[AttachIndex.mme_groupid];
        this.mme_code = str[AttachIndex.mme_code];
        this.m_tmsi = str[AttachIndex.m_tmsi];
        this.tmsi = str[AttachIndex.tmsi];
        if (str[AttachIndex.user_ip] != null) {
            this.user_ip = str[AttachIndex.user_ip];
        } else {
            this.user_ip = str[AttachIndex.user_ip1];
        }

        this.apn = str[AttachIndex.apn];
        this.guti_type = str[CommonUtils.convertIndex(str, AttachIndex.guti_type)];
        this.mcc = str[CommonUtils.convertIndex(str, AttachIndex.mcc)];
        this.mnc = str[CommonUtils.convertIndex(str, AttachIndex.mnc)];

        String guti = str[AttachIndex.old_guti] + str[AttachIndex.old_guti + 1] + str[AttachIndex.old_guti + 2];
        try {
            this.old_guti = Long.toHexString(Long.parseLong(guti));
        } catch (NumberFormatException e) {
            this.old_guti = "";
        }
        this.tac = str[AttachIndex.tac];
        this.eci = str[AttachIndex.eci];
        this.tai_list1 = str[AttachIndex.tai_list1];
//        this.tai_list2 = str[index];
//        this.tai_list3 = str[index];
//        this.tai_list4 = str[index];
        this.lac = str[AttachIndex.lac];
//        this.old_tac = str[index];
//        this.old_lac = str[index];
        int drxIndex = CommonUtils.convertIndex(str, AttachIndex.drx_par);
        this.drx_par = str[drxIndex];
        int uencIndex = CommonUtils.convertIndex(str, AttachIndex.uenc);
        this.uenc = str[uencIndex];
        this.vdp_uus = TypeChange.strToHex(str[AttachIndex.vdp_uus]);

        int msncIndex = CommonUtils.convertIndex(str, AttachIndex.msnc);
        //msnc本来就是十六进制
//        this.msnc = Integer.toHexString(Integer.parseInt(str[msncIndex]));
        this.msnc = str[msncIndex];
        int nksiIndex = CommonUtils.convertIndex(str, AttachIndex.nksi);
        this.nksi = TypeChange.strToHex(str[nksiIndex]);


        int epsIndex = CommonUtils.convertIndex(str, AttachIndex.eps_nfs);
        this.eps_nfs = TypeChange.strToHex(str[epsIndex]);

//        this.msc2 = str[index];
//        this.msc3 = str[index];


        if (!"".equals(str[AttachIndex.ip_versiong]) && "".equals(str[AttachIndex.ip_versiong + 1])) {
            this.ip_versiong = "0";
        } else if ("".equals(str[AttachIndex.ip_versiong]) && !"".equals(str[AttachIndex.ip_versiong + 1])) {
            this.ip_versiong = "1";
        } else if (!"".equals(str[AttachIndex.ip_versiong]) && !"".equals(str[AttachIndex.ip_versiong + 1])) {
            this.ip_versiong = "2";
        }

        this.erab_num = str[AttachIndex.erab_num];
        this.req_time = str[AttachIndex.req_time];
        /*this.esm_info_req_time = str[AttachIndex.esm_info_req_time];
        this.esm_info_rsp_time = str[AttachIndex.esm_info_rsp_time];*/

        //  int contextIndex = CommonUtils.convertIndex(str, AttachIndex.context_rel_time);
        //this.context_rel_time = str[contextIndex];


        this.res_time = "0";

        if ("0".equals(str[AttachIndex.msg_id])) {
            this.msg_id = "67";
        } else if ("1".equals(str[AttachIndex.msg_id])) {
            this.msg_id = "68";
        } else if ("255".equals(str[AttachIndex.msg_id])) {
            this.msg_id = "65";
        }

        this.cause_code = TypeChange.strToOx7f(str[AttachIndex.cause_code]);

        // this.rand = str[CommonUtils.convertIndex(str, AttachIndex.rand)];
        // this.autn = str[CommonUtils.convertIndex(str, AttachIndex.autn)];
        this.ciot_support = str[CommonUtils.convertIndex(str, AttachIndex.ciot_support)];

//    public void getCe_level(String[] str, int index) {
//        this.ce_level = str[index];
//    }
//
//    public void getPsm_status(String[] str, int index) {
//        this.psm_status = str[index];
//    }

        this.t3324 = str[CommonUtils.convertIndex(str, AttachIndex.t3324)];
        this.t3412_extended = str[CommonUtils.convertIndex(str, AttachIndex.t3412_extended)];
        this.t3412 = str[CommonUtils.convertIndex(str, AttachIndex.t3412)];
        this.paging_time_window = str[CommonUtils.convertIndex(str, AttachIndex.paging_time_window)];
        this.edrx_value = str[CommonUtils.convertIndex(str, AttachIndex.edrx_value)];
        this.device_properties = str[CommonUtils.convertIndex(str, AttachIndex.device_properties)];
        this.t3346 = str[CommonUtils.convertIndex(str, AttachIndex.t3346)];
        this.nr_restriction = str[CommonUtils.convertIndex(str, AttachIndex.nr_restriction)];
        this.gnb_gip_ip = str[CommonUtils.convertIndex(str, AttachIndex.gnb_gip_ip)];

        if ("1".equals(str[11])) {
            this.reject_cause = TypeChange.strToOx7f(str[AttachIndex.reject_cause]);
            this.esm_cause = TypeChange.strToOx7f(str[AttachIndex.esm_cause]);
        }
        if ("0".equals(str[11]) || "1".equals(str[11])) {
            this.accept_time = str[AttachIndex.accept_time2];
            this.complete_time = str[AttachIndex.complete_time2];
        }


    }


    @Override
    public boolean getMiddleProcedure(int produceType) {
        if (produceType == 7 || produceType == 9 || produceType == 18 || produceType == 29 || produceType == 30 || produceType == 31 || produceType == 42 || produceType == 50) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public Integer getEndProcedure() {
        return 20;
    }


    //关联函数
    //1:解析成功  2:解析结束  0:不是子流程 3:解析时间抛出异常
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
                    case 7:
                        createType7(arr);
                        break;
                    case 9:
                        createType9(arr);
                        break;
                    case 18:
                        createType18(arr);
                        break;
                    case 29:
                        createType29(arr);
                        break;
                    case 30:
                        createType30(arr);
                        break;
                    case 31:
                        createType31(arr);
                        break;
                    case 42:
                        createType42(arr);
                        break;
                    case 50:
                        createType50(arr);
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

    //7： PDN connectivity
    //xdr切分得到arr ，这个arr[produceType]=7
    private void createType7(String[] str) {

        if (str[AttachIndex.pdn_cause].equals("1")) {
            this.pdn_cause = "1";
        }
        Integer erabid_rsq_index = CommonUtils.numIndex(str, AttachIndex.erabid_rsp7_42);
        if (erabid_rsq_index != null) {
            this.erabid_rsq = str[erabid_rsq_index];
        }
        int pdnIndex = CommonUtils.convertIndex(str, AttachIndex.pdn_srvtype);
        this.pdn_srvtype = str[pdnIndex];

    }

    //9： EPS bearer resource allocation
    private void createType9(String[] str) {

    }

    //18：Initial context setup
    private void createType18(String[] str) {
        Integer erabid_rsp_index = CommonUtils.numIndex(str, AttachIndex.erabid_rsp);
        if (erabid_rsp_index != null) {
            this.erabid_rsp = str[erabid_rsp_index];
        }
        int rabIndex = CommonUtils.convertIndex(str, AttachIndex.rabqos_req);
        if (!"".equals(str[rabIndex])) {
            this.rabqos_req = TypeChange.strToHex(str[rabIndex]);
        }

        this.sgw_gtp_ip = str[CommonUtils.convertIndex(str, AttachIndex.sgw_gtp_ip)];
        Integer sgw_gtp_teid_index = CommonUtils.numIndex(str, AttachIndex.sgw_gtp_teid);
        if (sgw_gtp_teid_index != null) {
            this.sgw_gtp_teid = TypeChange.strToHex(str[sgw_gtp_teid_index]);
        }
        int rabfillIndex = CommonUtils.convertIndex(str, AttachIndex.rab_fillist);
        this.rab_fillist = TypeChange.strToHex(str[rabfillIndex]);
        this.enb_gtp_ip = str[CommonUtils.convertIndex(str, AttachIndex.enb_gtp_ip)];
        Integer enbgtpIndex = CommonUtils.numIndex(str, AttachIndex.enb_gtp_teid);
        if (enbgtpIndex != null) {
            this.enb_gtp_teid = TypeChange.strToHex(str[enbgtpIndex]);
        }
        //this.accept_time = str[AttachIndex.accept_time];
        this.accept_time = str[AttachIndex.accept_time];
        this.complete_time = str[AttachIndex.complete_time1];
        this.initial_time = str[AttachIndex.initial_time];
        // this.complete_time = str[AttachIndex.complete_time1];
        if ("1".equals(str[11])) {
            this.erab_cause = TypeChange.strToOx7f(str[AttachIndex.erab_cause]);
        }

    }

    //29：Identity Acquisition
    private void createType29(String[] str) {
        this.identi_time = str[AttachIndex.identi_time];
        this.end_time = str[AttachIndex.end_time];
    }

    //30：Authentication
    private void createType30(String[] str) {
        this.auth_time = str[AttachIndex.auth_time];

        if ("1".equals(str[11])) {
            this.cause = TypeChange.strToOx7f(str[AttachIndex.cause]);
            this.auth_failure = str[AttachIndex.auth_failure];
        }
        if ("0".equals(str[11])) {
            this.identity_end_time = str[AttachIndex.identity_end_time];
        }
        this.rand = str[CommonUtils.convertIndex(str, AttachIndex.rand)];
        this.autn = str[CommonUtils.convertIndex(str, AttachIndex.autn)];

    }

    //31：Security Activation
    private void createType31(String[] str) {
        this.cip_time = str[AttachIndex.cip_time];
        this.rsp_end_time = str[AttachIndex.rsp_end_time];
    }

    //42：Default EPS bearer context activation
    private void createType42(String[] str) {
        // int bearIndex = CommonUtils.convertIndex(str, AttachIndex.bear_status);
        this.bear_status = TypeChange.strToHex(str[AttachIndex.bear_status]);
        Integer ebi_index = CommonUtils.numIndex(str, AttachIndex.ebi);
        if (ebi_index != null) {
            this.ebi = str[ebi_index];
        }
        int ptiIndex = CommonUtils.convertIndex(str, AttachIndex.pti);
        this.pti = TypeChange.strToHex(str[ptiIndex]);

        int epsIndex = CommonUtils.convertIndex(str, AttachIndex.eps_qos);
        this.eps_qos = TypeChange.strToHex(str[epsIndex]);
        int qosIndex = CommonUtils.convertIndex(str, AttachIndex.qos_negt);
        this.qos_negt = TypeChange.strToHex(str[qosIndex]);
        int apIndex = CommonUtils.convertIndex(str, AttachIndex.ap_ambr);
        this.ap_ambr = TypeChange.strToHex(str[apIndex]);
        Integer erabid_rsq_index = CommonUtils.numIndex(str, AttachIndex.erabid_rsp7_42);
        if (erabid_rsq_index != null) {
            this.erabid_rsq = str[erabid_rsq_index];
        }
    }

    //50：ESM INFORMATION  
    private void createType50(String[] str) {
        this.esm_info_req_time = str[AttachIndex.esm_info_req_time];
        this.esm_info_rsp_time = str[AttachIndex.esm_info_rsp_time];
        this.apn_req = str[AttachIndex.apn_req];

    }

    //20：ESM INFORMATION  
    private void createType20(String[] str) {
        this.release_cause = str[AttachIndex.release_cause];
        this.comp_time = str[AttachIndex.comp_time];
        int contextIndex = CommonUtils.convertIndex(str, AttachIndex.context_rel_time);
        this.context_rel_time = str[contextIndex];
        int contxtIndex = AttachIndex.contxt_rl_time;
        this.contxt_rl_time = str[contxtIndex];
        int reqIndex = CommonUtils.convertIndex(str, AttachIndex.req_cause);
        this.req_cause = str[reqIndex];
    }

    @Override
    public String toString() {
        String common = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|").append(mme_ue_s1ap_id).append("|")
                .append(enb_ue_s1ap_id).append("|").append(srvtype_req).append("|").append(srvtype_rsp).append("|").append(add_uptype_req).append("|")
                .append(add_uptype_res).append("|").append(mme_groupid).append("|").append(mme_code).append("|").append(m_tmsi).append("|").append(tmsi).append("|")
                .append(user_ip).append("|").append(apn).append("|").append(guti_type).append("|").append(mcc).append("|").append(mnc).append("|").append(old_guti).append("|")
                .append(tac).append("|").append(eci).append("|").append(tai_list1).append("|").append(tai_list2).append("|").append(tai_list3).append("|").append(tai_list4).append("|")
                .append(lac).append("|").append(old_tac).append("|").append(old_lac).append("|").append(drx_par).append("|").append(uenc).append("|").append(vdp_uus).append("|")
                .append(msnc).append("|").append(nksi).append("|").append(bear_status).append("|").append(eps_nfs).append("|").append(msc2).append("|").append(msc3).append("|")
                .append(pdn_srvtype).append("|").append(ip_versiong).append("|").append(pdn_cause).append("|").append(ebi).append("|").append(pti).append("|").append(apn_req).append("|")
                .append(eps_qos).append("|").append(qos_negt).append("|").append(ap_ambr).append("|").append(erab_num).append("|").append(erabid_rsp).append("|").append(rabqos_req).append("|")
                .append(sgw_gtp_ip).append("|").append(sgw_gtp_teid).append("|").append(erabid_rsq).append("|").append(rab_fillist).append("|").append(enb_gtp_ip).append("|")
                .append(enb_gtp_teid).append("|").append(req_time).append("|").append(identi_time).append("|").append(end_time).append("|").append(auth_time).append("|").append(auth_failure).append("|")
                .append(cause).append("|").append(identity_end_time).append("|").append(cip_time).append("|").append(rsp_end_time).append("|").append(esm_info_req_time).append("|")
                .append(esm_info_rsp_time).append("|").append(accept_time).append("|").append(reject_cause).append("|").append(initial_time).append("|").append(erab_cause).append("|")
                .append(complete_time).append("|").append(esm_cause).append("|").append(context_rel_time).append("|").append(req_cause).append("|").append(contxt_rl_time).append("|")
                .append(release_cause).append("|").append(comp_time).append("|").append(res_time).append("|").append(msg_id).append("|").append(cause_code).append("|").append(rand).append("|")
                .append(autn).append("|").append(ciot_support).append("|").append(ce_level).append("|").append(psm_status).append("|").append(t3324).append("|").append(t3412_extended).append("|")
                .append(t3412).append("|").append(extended_drx_status).append("|").append(paging_time_window).append("|").append(edrx_value).append("|").append(device_properties).append("|")
                .append(t3346).append("|").append(req_epco).append("|").append(resp_epco).append("|").append(nr_restriction).append("|").append(gnb_gip_ip);
        return common + sb.toString();
    }
}
