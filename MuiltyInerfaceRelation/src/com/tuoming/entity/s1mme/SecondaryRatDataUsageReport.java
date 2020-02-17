package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.util.HashSet;
import java.util.Set;

public class SecondaryRatDataUsageReport  extends MmeCommon{
    public String enodeb_ip = "";           //eNodeB信令面IP
    public String enodeb_port = "";          //eNodeB端口
    public String mme_ip = "";               //MME IP地址
    public String mme_port = "";             //MME端口
    public String mme_ue_s1ap_id = "";       // MME UE S1AP ID
    public String enb_ue_s1ap_id  = "";      //ENB UE S1AP ID
    public String second_rat_usage = "";     //Secondary RAT Usage Report list
    public String handover_flag = "" ;       //  Handover Flag


    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }

    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[SecondaryRatDataUsageReportIndex.enodeb_ip];
        this.enodeb_port = str[SecondaryRatDataUsageReportIndex.enodeb_port];
        this.mme_ip = str[SecondaryRatDataUsageReportIndex.mme_ip];
        this.mme_port = str[SecondaryRatDataUsageReportIndex.mme_port];
        this.mme_ue_s1ap_id = str[SecondaryRatDataUsageReportIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id  = str[CommonUtils.convertIndex(str,AttachIndex.enb_ue_s1ap_id)];
        int secon_rat_usage_index = CommonUtils.convertIndex(str,SecondaryRatDataUsageReportIndex.second_rat_usage);
        this.second_rat_usage = TypeChange.strToHex(str[secon_rat_usage_index]);
        this.handover_flag = str[CommonUtils.convertIndex(str,SecondaryRatDataUsageReportIndex.handover_flag)];

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
          .append(enb_ue_s1ap_id ).append("|")
          .append(second_rat_usage).append("|")
          .append(handover_flag);
        return common+sb.toString();
    }
}
