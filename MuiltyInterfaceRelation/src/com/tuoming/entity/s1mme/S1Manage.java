package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;
import com.tuoming.tools.Untils;

import java.util.HashSet;
import java.util.Set;

public class S1Manage extends MmeCommon {

    public String enodeb_ip = "";       //eNodeB信令面IP
    public String enodeb_port = "";     //eNodeB端口
    public String mme_ip = "";          //MME IP地址
    public String mme_port = "";        //MME端口
    public String mme_ue_s1ap_id = "";  //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";  //ENB UE S1AP ID
    private String srv_type = "";        //流程类型
    private String direction = "";       //方向标识
    public String req_time = "";        //请求消息时间
    public String res_time = "";        //结束时间
    private String cause_type = "";      //原因类型
    public String cause = "";           //失败原因


    public S1Manage() {
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }



    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[S1ManageIndex.enodeb_ip];
        this.enodeb_port = str[S1ManageIndex.enodeb_port];
        this.mme_ip = str[S1ManageIndex.mme_ip];
        this.mme_port = str[S1ManageIndex.mme_port];
        this.mme_ue_s1ap_id = str[S1ManageIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str,AttachIndex.enb_ue_s1ap_id)];
        switch (str[S1ManageIndex.srv_type]) {
            case "22":
                this.srv_type = "14";
                break;
            case "23":
                this.srv_type = "15";
                break;
            case "24":
                this.srv_type = "17";
                break;
            case "25":
                this.srv_type = "29";
                break;
            case "26":
                this.srv_type = "30";
                break;
            case "27":
                this.srv_type = "34";
                break;
            case "28":
                this.srv_type = "35";
                break;
        }
        this.direction = str[CommonUtils.convertIndex(str, S1ManageIndex.direction)];
        this.req_time = str[S1ManageIndex.req_time];
        this.res_time = str[S1ManageIndex.res_time];
        int causenum;
        try {
            causenum = str[S1ManageIndex.cause] != null ? Integer.parseInt(str[S1ManageIndex.cause], 16) : 0;
        } catch (NumberFormatException e) {
           causenum=0;
        }
        byte[] result = Untils.shortToByteArr((short)causenum);

        if (Untils.byteToInt(result[0])==0){
            this.cause_type = TypeChange.strToHex(Untils.byteToInt(result[0]));
        }else {
            this.cause_type = TypeChange.strToHex(Untils.byteToInt(result[0])-1);
        }
        this.cause = TypeChange.strToHex(Untils.byteToInt(result[1]));

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
                .append(enb_ue_s1ap_id).append("|").append(srv_type).append("|").append(direction).append("|").append(req_time).append("|").append(res_time).append("|")
                .append(cause_type).append("|").append(cause);
        return common + sb.toString();
    }
}
