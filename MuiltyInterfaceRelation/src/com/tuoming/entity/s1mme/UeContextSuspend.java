package com.tuoming.entity.s1mme;

import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.util.HashSet;
import java.util.Set;

public class UeContextSuspend extends MmeCommon {

    public String enodeb_ip="";            //eNodeB信令面IP
    public String enodeb_port="";          //eNodeB端口
    public String mme_ip="";               //MME IP地址
    public String mme_port="";             //MME端口
    public String mme_ue_s1ap_id="";       //MME UE S1AP ID
    public String enb_ue_s1ap_id="";       //ENB UE S1AP ID
    private String cell_recommend = "";       //寻呼优先选择小区
    private String cell_recommend_time = "";  //寻呼优先选择小区驻留时间
    private String enb_recommend = "";        //寻呼优先选择eNB
    private String tai_recommend = "";        //寻呼优先选择TAC

    public UeContextSuspend() {
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[UeContextSuspendIndex.enodeb_ip];
        this.enodeb_port = str[UeContextSuspendIndex.enodeb_port];
        this.mme_ip = str[UeContextSuspendIndex.mme_ip];
        this.mme_port = str[UeContextSuspendIndex.mme_port];
        this.mme_ue_s1ap_id = str[UeContextSuspendIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str,AttachIndex.enb_ue_s1ap_id)];
        this.cell_recommend = str[CommonUtils.convertIndex(str,UeContextSuspendIndex.cell_recommend)];
        this.cell_recommend_time = str[CommonUtils.convertIndex(str,UeContextSuspendIndex.cell_recommend_time)];
        this.enb_recommend = str[CommonUtils.convertIndex(str,UeContextSuspendIndex.enb_recommend)];
        this.tai_recommend = str[CommonUtils.convertIndex(str,UeContextSuspendIndex.tai_recommend)];

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
                .append(enb_ue_s1ap_id).append("|").append(cell_recommend).append("|").append(cell_recommend_time).append("|").append(enb_recommend).append("|")
                .append(tai_recommend);
        return common + sb.toString();
    }
}
