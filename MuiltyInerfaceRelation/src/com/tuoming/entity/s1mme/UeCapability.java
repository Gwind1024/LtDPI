package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.util.HashSet;
import java.util.Set;

public class UeCapability extends MmeCommon {

    public String enodeb_ip = "";       //eNodeB信令面IP
    public String enodeb_port = "";     //eNodeB端口
    public String mme_ip = "";          //MME IP地址
    public String mme_port = "";        //MME端口
    public String mme_ue_s1ap_id = "";  //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";  //ENB UE S1AP ID
    private String ue_rc = "";           //用户终端无线能力

    public UeCapability() {
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[UeCapabilityIndex.enodeb_ip];
        this.enodeb_port = str[UeCapabilityIndex.enodeb_port];
        this.mme_ip = str[UeCapabilityIndex.mme_ip];
        this.mme_port = str[UeCapabilityIndex.mme_port];
        this.mme_ue_s1ap_id = str[UeCapabilityIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id =  str[CommonUtils.convertIndex(str,AttachIndex.enb_ue_s1ap_id)];
        String uercnum = str[CommonUtils.convertIndex(str, UeCapabilityIndex.ue_rc)];
        this.ue_rc = TypeChange.strToHex(uercnum);

    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
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
        sb.append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|").append(mme_ue_s1ap_id).append("|")
                .append(enb_ue_s1ap_id).append("|").append(ue_rc);
        return common + sb.toString();
    }
}
