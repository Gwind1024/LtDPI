package com.tuoming.entity.s1mme;

import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

public class ErabModificattionIndiction extends MmeCommon {
    public String enodeb_ip = "";           //eNodeB信令面IP
    public String enodeb_port = "";          //eNodeB端口
    public String mme_ip = "";               //MME IP地址
    public String mme_port = "";             //MME端口
    public String mme_ue_s1ap_id = "";       // MME UE S1AP ID
    public String enb_ue_s1ap_id = "";      //ENB UE S1AP ID
    public String mme_groupid = "";          // MME GroupID
    public String mme_code = "";             // MME Code
    public String m_tmsi = "";               // M-TMSI
    public String tac = "";                  // TAC
    public String eci = "";                  //  ECI
    public String erab_num = "";             //    ERAB数量
    public String erabid_esp = "";           // 请求消息的E-RAB ID
    public String erab_filedlist = "";       //修改失败的E-RAB列表
    public String erab_release_list = "";    //E-RAB释放列表
    public String enb_mod_gtp_ip = "";       // 需修改RAN侧的用户面IP
    public String enb_notmod_gtp_ip = "";    //不需修改RAN侧的用户面IP
    public String enb_gtp_teid = "";         //      RAN侧用户面TEID
    public String res_time = "";             // 结束消息类型
    public String msg_id = "";              //    结束消息


    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }

    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[ErabModificattionIndictionIndex.enodeb_ip];
        this.enodeb_port = str[ErabModificattionIndictionIndex.enodeb_port];
        this.mme_ip = str[ErabModificattionIndictionIndex.mme_ip];
        this.mme_port = str[ErabModificattionIndictionIndex.mme_port];
        this.mme_ue_s1ap_id = str[ErabModificattionIndictionIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str, AttachIndex.enb_ue_s1ap_id)];
        this.mme_groupid = str[ErabModificattionIndictionIndex.mme_groupid];
        this.mme_code = str[ErabModificattionIndictionIndex.mme_code];
        this.m_tmsi = str[ErabModificattionIndictionIndex.m_tmsi];
        this.tac = str[ErabModificattionIndictionIndex.tac];
        this.eci = str[ErabModificattionIndictionIndex.eci];
        this.erab_num = str[CommonUtils.convertIndex(str, ErabModificattionIndictionIndex.erab_num)];
        this.erabid_esp = str[CommonUtils.convertIndex(str, ErabModificattionIndictionIndex.erabid_esp)];
        this.erab_filedlist = str[CommonUtils.convertIndex(str, ErabModificattionIndictionIndex.erab_filedlist)];
        this.erab_release_list = str[CommonUtils.convertIndex(str, ErabModificattionIndictionIndex.erab_release_list)];
        this.enb_mod_gtp_ip = str[CommonUtils.convertIndex(str, ErabModificattionIndictionIndex.enb_mod_gtp_ip)];
        this.enb_notmod_gtp_ip = str[CommonUtils.convertIndex(str, ErabModificattionIndictionIndex.enb_notmod_gtp_ip)];
        this.enb_gtp_teid = str[CommonUtils.convertIndex(str, ErabModificattionIndictionIndex.enb_gtp_teid)];
        this.res_time = "0";
        this.msg_id = str[CommonUtils.convertIndex(str, ErabModificattionIndictionIndex.msg_id)];


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
        String common = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(enodeb_ip).append("|")
                .append(enodeb_port).append("|")
                .append(mme_ip).append("|")
                .append(mme_port).append("|")
                .append(mme_ue_s1ap_id).append("|")
                .append(enb_ue_s1ap_id).append("|")
                .append(mme_groupid).append("|")
                .append(mme_code).append("|")
                .append(m_tmsi).append("|")
                .append(tac).append("|")
                .append(eci).append("|")
                .append(erab_num).append("|")
                .append(erabid_esp).append("|")
                .append(erab_filedlist).append("|")
                .append(erab_release_list).append("|")
                .append(enb_mod_gtp_ip).append("|")
                .append(enb_notmod_gtp_ip).append("|")
                .append(enb_gtp_teid).append("|")
                .append(res_time).append("|")
                .append(msg_id);

        return common + sb.toString();
    }
}
