package com.tuoming.entity.s1mme;

import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

public class UeContextRelease extends MmeCommon {

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
    public String context_rel_time = "";  //UE context release request时间
    public String req_cause = "";         //请求原因
    public String contxt_rl_time = "";    //UE context release command时间
    public String release_cause = "";     //UE context release释放原因
    public String comp_time = "";         //UE context releasecomplete时间

    public UeContextRelease() {
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[UeContextReleaseIndex.enodeb_ip];
        this.enodeb_port = str[UeContextReleaseIndex.enodeb_port];
        this.mme_ip = str[UeContextReleaseIndex.mme_ip];
        this.mme_port = str[UeContextReleaseIndex.mme_port];
        this.mme_ue_s1ap_id = str[UeContextReleaseIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str, AttachIndex.enb_ue_s1ap_id)];
        this.mme_groupid = str[UeContextReleaseIndex.mme_groupid];
        this.mme_code = str[UeContextReleaseIndex.mme_code];
        this.m_tmsi = str[UeContextReleaseIndex.m_tmsi];
        this.tac = str[UeContextReleaseIndex.tac];
        this.eci = str[UeContextReleaseIndex.eci];
        this.context_rel_time = str[CommonUtils.convertIndex(str, UeContextReleaseIndex.context_rel_time)];
        this.req_cause = str[CommonUtils.convertIndex(str, UeContextReleaseIndex.req_cause)];
        this.contxt_rl_time = str[UeContextReleaseIndex.contxt_rl_time];
        this.release_cause = str[UeContextReleaseIndex.release_cause];
        this.comp_time = str[UeContextReleaseIndex.comp_time];

    }

    //20
    private void createType20(String[] str) {

    }

    @Override
    public boolean getMiddleProcedure(int produceType) {
        return false;
    }

    @Override
    public Integer getEndProcedure() {
        return 20;
    }

    @Override
    public Integer relation(MmeMapCommon mmeMapCommon, int type, String[] arr) {
        return 2;
    }

    @Override
    public String toString() {
        String common = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|").append(mme_ue_s1ap_id).append("|")
                .append(enb_ue_s1ap_id).append("|").append(mme_groupid).append("|").append(mme_code).append("|").append(m_tmsi).append("|").append(tac).append("|")
                .append(eci).append("|").append(context_rel_time).append("|").append(req_cause).append("|").append(contxt_rl_time).append("|").append(release_cause).append("|")
                .append(comp_time);
        return common + sb.toString();
    }
}
