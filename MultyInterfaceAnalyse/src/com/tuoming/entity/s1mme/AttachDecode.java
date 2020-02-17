package com.tuoming.entity.s1mme;

import com.tuoming.tools.CommonUtils;

public class AttachDecode extends CommonDecode1 {


    public String enodeb_ip="";             //eNodeB信令面IP
    public String enodeb_port="";           //eNodeB端口
    public String mme_ip="";                //MME IP地址
    public String mme_port="";              //MME端口
    public String mme_ue_s1ap_id="";        //MME UE S1AP ID
    public String enb_ue_s1ap_id="";        //ENB UE S1AP ID
    public String srvtype_req="";           //请求业务类型
    public String srvtype_rsp="";           //响应业务类型
    public String add_uptype_req="";        //附加更新类型（请求）
    public String add_uptype_res="";        //附加更新类型（响应）
    public String mme_groupid="";           //MME GroupID
    public String mme_code="";              //MME Code
    public String m_tmsi="";                //M-TMSI
    public String tmsi="";                  //TMSI
    public String user_ip="";               //IP地址
    public String apn="";                   //APN
    public String guti_type="";             //老GUTI类型
    public String mcc="";                   //老国家码
    public String mnc="";                   //老移动网络码
    public String old_guti="";              //老GUTI
    public String tac="";                   //TAC
    public String eci="";                   //ECI
    public String tai_list1="";             //TAI 列表
    public String tai_list2="";             //TAI 列表
    public String tai_list3="";             //TAI 列表
    public String tai_list4="";             //TAI 列表
    public String lac="";                   //LAC
    public String old_tac="";               //OLD TAC
    public String old_lac="";               //OLD LAC
    public String drx_par="";               //DRX参数
    public String uenc="";                  //UE网络能力
    public String vdp_uus="";               //VDP_UUS
    public String msnc="";                  //MS网络能力
    public String nksi="";                  //NAS key set identifier
    public String bear_status="";           //承载状态
    public String eps_nfs="";               //EPS网络特性支持
    public String msc2="";                  //Mobile Station Classmark2
    public String msc3="";                  //Mobile Station Classmark3
    public String pdn_srvtype="";           //PDN业务类型
    public String ip_versiong="";           //IP版本
    public String pdn_cause="";             //PDN失败原因码
    public String ebi="";                   //激活承载标识
    public String pti="";                   //PTI
    public String apn_req="";               //请求APN
    public String eps_qos="";               //EPS QoS
    public String qos_negt="";              //协商QoS
    public String ap_ambr="";               //APN AMBR
    public String erab_num="";              //ERAB数量
    public String erabid_rsp="";            //请求消息的E-RAB ID
    public String rabqos_req="";            //请求RAB中的QOS
    public String sgw_gtp_ip="";            //SGW用户面IP
    public String sgw_gtp_teid="";          //SGW用户面TEID
    public String erabid_rsq="";            //响应消息的E-RAB ID
    public String rab_fillist="";           //建立失败RAB的列表
    public String enb_gtp_ip="";            //eNodeB用户面IP
    public String enb_gtp_teid="";          //eNodeB用户面TEID
    public String req_time="";              //消息请求时间
    public String identi_time="";           //取标识开始时间
    public String end_time="";              //取标识结束时间
    public String auth_time="";             //鉴权请求时间
    public String auth_failure="";          //鉴权失败时间
    public String cause="";                 //鉴权失败原因
    public String identity_end_time="";     //鉴权结束时间
    public String cip_time="";              //ciphered OptionRequest时间
    public String rsp_end_time="";          //ciphered Option Response时间
    public String esm_info_req_time="";     //ESM INFORMATION REQUEST消息时间
    public String esm_info_rsp_time="";     //ESM INFORMATION RESPONSE消息时间
    public String accept_time="";           //Initial context setup/Attach accept/acitve Default EPS bear request时间
    public String reject_cause="";          //attach拒绝原因码
    public String initial_time="";          //Initial context response时间
    public String erab_cause="";            //承载建立失败原因码
    public String complete_time="";         //attach complete/active default EPS bear accept/reject时间
    public String esm_cause="";             //激活建立失败原因码
    public String context_rel_time="";      //UE context release request时间
    public String req_cause="";             //释放原因
    public String contxt_rl_time="";        //UE context release command时间
    public String release_cause="";         //UE context release释放原因
    public String comp_time="";             //UE context release complete时间
    public String res_time="0";			    //结束消息类型
    public String msg_id="0";			    //结束消息
    public String cause_code="";            //消息的结果码
    public String rand="";				    //鉴权随机数
    public String autn="";				    //鉴权向量
    public String ciot_support="";		    //是否支持NBIOT
    public String ce_level="";			    //覆盖增强级
    public String psm_status="";		    //省电模式状态
    public String t3324="";				    //T3324
    public String t3412_extended="";        //T3412 extended value
    public String t3412="";                 //T3412
    public String extended_drx_status ="";  //非连续接收状态
    public String paging_time_window="";    //寻呼时间窗
    public String edrx_value="";            //非连续接值
    public String device_properties="";     //是否是NAS信令低优先级
    public String t3346="";                 //信令拥塞控制字段
    public String req_epco="";              //请求消息APN速率控制
    public String resp_epco="";             //接收消息APN速率控制


    public AttachDecode() {
    }



    public void getEnodeb_ip(String[] str, int index) {
        this.enodeb_ip = str[index];
    }

    public void getEnodeb_port(String[] str, int index) {
        this.enodeb_port = str[index];
    }

    public void getMme_ip(String[] str, int index) {
        this.mme_ip = str[index];
    }

    public void getMme_port(String[] str, int index) {
        this.mme_port = str[index];
    }

    public void getMme_ue_s1ap_id(String[] str, int index) {
        this.mme_ue_s1ap_id = str[index];
    }

    public void getEnb_ue_s1ap_id(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.enb_ue_s1ap_id = str[index];
    }

    public void getSrvtype_req(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("1")){
            this.srvtype_req = str[index];
        }
    }

    public void getSrvtype_rsp(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("1")){
            this.srvtype_req = str[index];
        }
    }

    public void getAdd_uptype_req(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[index].equals("0")){
            this.add_uptype_req = "0";
        } else if(str[index].equals("1")) {
            this.add_uptype_req = "1";
        }
    }

    public void getAdd_uptype_res(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("1")){
            this.srvtype_req = str[index];
        }
    }

    public void getMme_groupid(String[] str, int index) {
        this.mme_groupid = str[index];
    }

    public void getMme_code(String[] str, int index) {
        this.mme_code = str[index];
    }

    public void getM_tmsi(String[] str, int index) {
        this.m_tmsi = str[index];
    }

    public void getTmsi(String[] str, int index) {
        this.tmsi = str[index];
    }

    public void getUser_ip(String[] str, int index ,int index1) {
        if(str[index] != null){
            this.user_ip = str[index];
        } else {
            this.user_ip = str[index1];
        }
    }

    public void getApn(String[] str, int index) {
        this.apn = str[index];
    }

    public void getGuti_type(String[] str, int index) {
        this.guti_type = str[index];
    }


    public void getOld_guti(String[] str, int index) {
        String guti = str[index] + str[index+1] + str[index+2];
        if(!guti.equals("")){
            this.old_guti = Long.toHexString(Long.parseLong(guti));
        }
    }

    public void getTac(String[] str, int index) {
        this.tac = str[index];
    }

    public void getEci(String[] str, int index) {
        this.eci = str[index];
    }

    public void getTai_list1(String[] str, int index) {
        this.tai_list1 = str[index];
    }

//    public String getTai_list2(String[] str, int index) {
//        this.tai_list2 = str[index];
//    }
//
//    public String getTai_list3(String[] str, int index) {
//        this.tai_list3 = str[index];
//    }
//
//    public String getTai_list4(String[] str, int index) {
//        this.tai_list4 = str[index];
//    }

    public void getLac(String[] str, int index) {
        this.lac = str[index];
    }

//    public String getOld_tac(String[] str, int index) {
//        this.old_tac = str[index];
//    }
//
//    public String getOld_lac(String[] str, int index) {
//        this.old_lac = str[index];
//    }

    public void getDrx_par(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.drx_par = str[index];
    }

    public void getUenc(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.uenc = str[index];
    }

    public void getVdp_uus(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("1")){
            this.vdp_uus = Integer.toHexString(Integer.parseInt(str[index]));
        }
    }

    public void getMsnc(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("1")){
            this.vdp_uus = Integer.toHexString(Integer.parseInt(str[index]));
        }
    }

    public void getNksi(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("1")){
            this.vdp_uus = Integer.toHexString(Integer.parseInt(str[index]));
        }
    }

    public void getBear_status(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("42")){
            this.bear_status = Integer.toHexString(Integer.parseInt(str[index]));
        }
    }

    public void getEps_nfs(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.eps_nfs = Integer.toHexString(Integer.parseInt(str[index]));
    }

//    public void getMsc2(String[] str, int index) {
//        this.msc2 = str[index];
//    }
//
//    public void getMsc3(String[] str, int index) {
//        this.msc3 = str[index];
//    }

    public void getPdn_srvtype(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("7")){
            this.pdn_srvtype = str[index];
        }
    }

    public void getIp_versiong(String[] str, int index) {
        if(str[index]!=null && str[index+1]==null){
            this.ip_versiong = "0";
        } else if(str[index]==null && str[index+1]!=null){
            this.ip_versiong = "1";
        } else if(str[index]!=null && str[index+1]!=null){
            this.ip_versiong = "2";
        }

    }

    public void getPdn_cause(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("7") && str[index].equals("1")){
            this.pdn_cause = "1";
        }

    }

    public void getEbi(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("42")){
            this.ebi = str[index];
        }
    }

    public void getPti(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("42")){
            if(str[index] != null){
                this.pti = Integer.toHexString(Integer.parseInt(str[index]));
            }
        }
    }

    public void getApn_req(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("42")) {
            this.apn_req = str[index];
        }
    }

    public void getEps_qos(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("42")){
            if(str[index] != null){
                this.eps_qos = Integer.toHexString(Integer.parseInt(str[index]));
            }
        }
    }

    public void getQos_negt(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("42")){
            if(str[index] != null){
                this.qos_negt = Integer.toHexString(Integer.parseInt(str[index]));
            }
        }
    }

    public void getAp_ambr(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("42")){
            if(str[index] != null){
                this.ap_ambr = Integer.toHexString(Integer.parseInt(str[index]));
            }
        }
    }

    public void getErab_num(String[] str, int index) {
        this.erab_num = str[index];
    }

    public void getErabid_rsp(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("18")) {
            this.erabid_rsp = str[index];
        }
    }

    public void getRabqos_req(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("18")){
            if(str[index] != null){
                this.rabqos_req = Integer.toHexString(Integer.parseInt(str[index]));
            }
        }
    }

    public void getSgw_gtp_ip(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("18")) {
            this.sgw_gtp_ip = str[index];
        }
    }

    public void getSgw_gtp_teid(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("18")) {
            if(str[index] != null){
                this.sgw_gtp_teid = Integer.toHexString(Integer.parseInt(str[index]));
            }
        }
    }

    public void getErabid_rsq(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("18")) {
            this.erabid_rsq = str[index];
        }
    }

    public void getRab_fillist(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("18")) {
            if(str[index] != null){
                this.rab_fillist = Integer.toHexString(Integer.parseInt(str[index]));
            }
        }
    }

    public void getEnb_gtp_ip(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("18")) {
            this.enb_gtp_ip = str[index];
        }
    }

    public void getEnb_gtp_teid(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        if(str[AttachIndex.sdrType].equals("18")) {
            if(str[index] != null){
                this.enb_gtp_teid = Integer.toHexString(Integer.parseInt(str[index]));
            }
        }
    }

    public void getReq_time(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("1")) {
            this.req_time = str[index];
        }
    }

    public void getIdenti_time(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("29")) {
            this.identi_time = str[index];
        }
    }

    public void getEnd_time(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("29")) {
            this.end_time = str[index];
        }
    }

    public void getAuth_time(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("30")) {
            this.auth_time = str[index];
        }
    }

    public void getAuth_failure(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("30") && str[11].equals("1")) {
            this.auth_failure = str[index];
        }
    }

    public void getCause(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("30") && str[11].equals("1")) {
            this.cause = str[index];
        }
    }

    public void getIdentity_end_time(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("30") && str[11].equals("0")) {
            this.identity_end_time = str[index];
        }
    }

    public void getCip_time(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("31")) {
            this.cip_time = str[index];
        }
    }
//todo 字段解释
    public void getRsp_end_time(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("30") && str[11].equals("1")) {
            this.rsp_end_time = str[index];
        }
    }

    public void getEsm_info_req_time(String[] str, int index) {
        this.esm_info_req_time = str[index];
    }

    public void getEsm_info_rsp_time(String[] str, int index) {
        this.esm_info_rsp_time = str[index];
    }

    public void getAccept_time(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("18")) {
            this.accept_time = str[index];
        }
    }

    public void getReject_cause(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("30") && str[11].equals("1")) {
            this.reject_cause = str[index];
        }
    }

    public void getInitial_time(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("18")) {
            this.initial_time = str[index];
        }
    }

    public void getErab_cause(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("30") && str[11].equals("1")) {
            this.erab_cause = str[index];
        }
    }

    public void getComplete_time(String[] str, int index, int index1) {
        if(str[AttachIndex.sdrType].equals("18")) {
            this.complete_time = str[index];
        } else if(str[AttachIndex.sdrType].equals("30") && (str[11].equals("1") || str[11].equals("0"))) {
            this.complete_time = str[index1];
        }
    }

    public void getEsm_cause(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("30") && str[11].equals("1")) {
            if(str[index] != null){
                this.esm_cause = Integer.toString(Integer.parseInt(str[index]) & 0x7f);
            }
        }
    }

    public void getContext_rel_time(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.context_rel_time = str[index];
    }

    public void getReq_cause(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.req_cause = str[index];
    }

    public void getContxt_rl_time(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.contxt_rl_time = str[index];
    }

    public void getRelease_cause(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("20")) {
            this.release_cause = str[index];
        }
    }

    public void getComp_time(String[] str, int index) {
        if(str[AttachIndex.sdrType].equals("20")) {
            this.comp_time = str[index];
        }
    }

    public void getRes_time() {
        this.res_time = "0";
    }

    public void getMsg_id(String[] str, int index) {
        if(str[index].equals("0")){
            this.msg_id = "67";
        } else if (str[index].equals("1")){
            this.msg_id = "68";
        } else if (str[index].equals("255")){
            this.msg_id = "65";
        }

    }

    public void getCause_code(String[] str, int index) {
        this.cause_code = str[index];
    }

    public void getRand(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
            this.rand = str[index];
    }

    public void getAutn(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
            this.autn = str[index];
    }

    public void getCiot_support(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.ciot_support = str[index];
    }

//    public void getCe_level(String[] str, int index) {
//        this.ce_level = str[index];
//    }
//
//    public void getPsm_status(String[] str, int index) {
//        this.psm_status = str[index];
//    }

    public void getT3324(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.t3324 = str[index];
    }

    public void getT3412_extended(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.t3412_extended = str[index];
    }

    public void getT3412(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.t3412 = str[index];
    }

//    public void getExtended_drx_status(String[] str, int index) {
//        this.extended_drx_status = str[index];
//    }

    public void getPaging_time_window(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.paging_time_window = str[index];
    }

    public void getEdrx_value(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.edrx_value = str[index];
    }

    public void getDevice_properties(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.device_properties = str[index];
    }

    public void getT3346(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.t3346 = str[index];
    }

    public void getReq_epco(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.req_epco = str[index];
    }

    public void getResp_epco(String[] str, int index) {
        index = str[AttachIndex.erab_num]==null ? index : CommonUtils.strToInteger(str[AttachIndex.erab_num]) * 8 + index;
        this.resp_epco = str[index];
    }


    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        result.append(accessType==null ? "" : accessType).append("|").append(interface0).append("|").append(sdrType == null ? "" : sdrType).append("|")
                .append(imsi).append("|").append(imei).append("|").append(msisdn).append("|").append(mcc).append("|").append(mnc).append("|")
                .append(startTime).append("|").append(endTime).append("|").append(srvStat).append("|").append(cdrStat).append("|")
                .append(xdrId).append("|").append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|")
                .append(mme_ue_s1ap_id).append("|").append(enb_ue_s1ap_id).append("|").append(srvtype_req).append("|").append(srvtype_rsp).append("|")
                .append(add_uptype_req).append("|").append(add_uptype_res).append("|").append(mme_groupid).append("|").append(mme_code).append("|")
                .append(m_tmsi).append("|").append(tmsi).append("|").append(user_ip).append("|").append(apn).append("|").append(guti_type).append("|")
                .append(mcc).append("|").append(mnc).append("|").append(old_guti).append("|").append(tac).append("|").append(eci).append("|")
                .append(tai_list1).append("|").append(tai_list2).append("|").append(tai_list3).append("|").append(tai_list4).append("|").append(lac).append("|")
                .append(old_tac).append("|").append(old_lac).append("|").append(drx_par).append("|").append(uenc).append("|").append(vdp_uus).append("|")
                .append(msnc).append("|").append(nksi).append("|").append(bear_status).append("|").append(eps_nfs).append("|").append(msc2).append("|")
                .append(msc3).append("|").append(pdn_srvtype).append("|").append(ip_versiong).append("|").append(pdn_cause).append("|").append(ebi).append("|")
                .append(pti ).append("|").append(apn_req).append("|").append(eps_qos).append("|").append(qos_negt).append("|").append(ap_ambr).append("|")
                .append(erab_num).append("|").append(erabid_rsp).append("|").append(rabqos_req).append("|").append(sgw_gtp_ip).append("|").append(sgw_gtp_teid).append("|")
                .append(erabid_rsq).append("|").append(rab_fillist).append("|").append(enb_gtp_ip).append("|").append(enb_gtp_teid).append("|").append(req_time).append("|")
                .append(identi_time).append("|").append(end_time).append("|").append(auth_time).append("|").append(auth_failure).append("|").append(cause).append("|")
                .append(identity_end_time).append("|").append(cip_time).append("|").append(rsp_end_time).append("|").append(esm_info_req_time).append("|").append(esm_info_rsp_time).append("|")
                .append(accept_time).append("|").append(reject_cause).append("|").append(initial_time).append("|").append(erab_cause).append("|").append(complete_time).append("|")
                .append(esm_cause).append("|").append(context_rel_time).append("|").append(req_cause).append("|").append(contxt_rl_time).append("|").append(release_cause).append("|")
                .append(comp_time).append("|").append(res_time).append("|").append(msg_id).append("|").append(cause_code).append("|").append(rand).append("|")
                .append(autn).append("|").append(ciot_support).append("|").append(ce_level).append("|").append(psm_status).append("|").append(t3324).append("|")
                .append(t3412_extended).append("|").append(t3412).append("|").append(extended_drx_status).append("|").append(paging_time_window).append("|")
                .append(edrx_value).append("|").append(device_properties).append("|").append(t3346).append("|").append(req_epco).append("|").append(resp_epco);

        return result.toString();


    }
}
