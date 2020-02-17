package com.tuoming.entity.s1mme;

import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.util.HashSet;
import java.util.Set;

public class ControlPlane extends MmeCommon {

    public String enodeb_ip = "";          //eNodeB信令面IP
    public String enodeb_port = "";        //eNodeB端口
    public String mme_ip = "";             //MME IP地址
    public String mme_port = "";           //MME端口
    public String mme_ue_s1ap_id = "";     //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";     //ENB UE S1AP ID
    private String service_type = "";       //业务请求类型
    private String active_flag = "";        //判断是否有无线承载
    private String device_properties = "";  //是否是NAS信令低优先级
    private String req_epco = "";           //请求消息APN速率控制

    public ControlPlane() {
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[ControlPlaneIndex.enodeb_ip];
        this.enodeb_port = str[ControlPlaneIndex.enodeb_port];
        this.mme_ip = str[ControlPlaneIndex.mme_ip];
        this.mme_port = str[ControlPlaneIndex.mme_port];
        this.mme_ue_s1ap_id = str[ControlPlaneIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str,AttachIndex.enb_ue_s1ap_id)];
        this.service_type = str[CommonUtils.convertIndex(str, ControlPlaneIndex.service_type)];
        this.active_flag = str[CommonUtils.convertIndex(str, ControlPlaneIndex.active_flag)];
        this.device_properties = str[CommonUtils.convertIndex(str, ControlPlaneIndex.device_properties)];
        this.req_epco = "2";

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
                .append(enb_ue_s1ap_id).append("|").append(service_type).append("|").append(active_flag).append("|").append(device_properties).append("|").append(req_epco);
        return common + sb.toString();
    }
}
