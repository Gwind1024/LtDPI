package com.tuoming.entity.s1mme;

import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.util.HashSet;
import java.util.Set;

public class UeContextResume extends MmeCommon {

    public String enodeb_ip = "";       //eNodeB信令面IP
    public String enodeb_port = "";     //eNodeB端口
    public String mme_ip = "";          //MME IP地址
    public String mme_port = "";        //MME端口
    public String mme_ue_s1ap_id = "";  //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";  //ENB UE S1AP ID
    private String enbid_fail = "";      //RESUME失败的eNB的id值
    private String enbcause_fail = "";   //RESUME失败的eNB的原因
    private String rrc_cause = "";       //RRC Resume Cause



    public UeContextResume() {
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }

    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[UeContextResumeIndex.enodeb_ip];
        this.enodeb_port = str[UeContextResumeIndex.enodeb_port];
        this.mme_ip = str[UeContextResumeIndex.mme_ip];
        this.mme_port = str[UeContextResumeIndex.mme_port];
        this.mme_ue_s1ap_id = str[UeContextResumeIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str,AttachIndex.enb_ue_s1ap_id)];
        if (!"".equals(str[AttachIndex.msg_id])  && "1".equals(str[AttachIndex.msg_id])) {
            this.enbid_fail = str[UeContextResumeIndex.enbid_fail];
            this.enbcause_fail = str[UeContextResumeIndex.enbcause_fail];
        }
        this.rrc_cause = str[CommonUtils.convertIndex(str, UeContextResumeIndex.rrc_cause)];

    }

    @Override
    public boolean getMiddleProcedure(int prodceType) {
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
        sb.append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|").append(mme_ue_s1ap_id).append("|")
                .append(enb_ue_s1ap_id).append("|").append(enbid_fail).append("|").append(enbcause_fail).append("|").append(rrc_cause);
        return common + sb.toString();
    }
}
