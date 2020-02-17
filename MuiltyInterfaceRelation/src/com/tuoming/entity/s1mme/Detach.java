package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.util.HashSet;
import java.util.Set;

//去附着 6
public class Detach extends MmeCommon {

    public String enodeb_ip = "";        //eNodeB信令面IP
    public String enodeb_port = "";      //eNodeB端口
    public String mme_ip = "";           //MME IP地址
    public String mme_port = "";         //MME端口
    public String mme_ue_s1ap_id = "";   //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";   //ENB UE S1AP ID
    private String direction = "";        //方向标识
    private String srvtype = "";          //业务类型
    private String poweroff_ind = "";     //关机指示
    public String mme_groupid = "";      //MME GroupID
    public String mme_code = "";         //MME Code
    public String m_tmsi = "";           //M-TMSI
    public String tac = "";              //TAC
    public String eci = "";              //ECI
    private String detach_cause = "";     //网络去附着原因
    public String req_time = "";         //消息请求时间
    private String acpt_time = "";        //Dettach Accept时间
    public String context_rel_time = ""; //UE context release request时间
    public String req_cause = "";        //请求原因
    public String contxt_rl_time = "";   //UE context release command时间
    public String release_cause = "";    //UE context release释放原因
    public String comp_time = "";        //UE context release complete时间
    private String end_msg = "";          //结束消息类型
    public String msg_id = "";           //结束消息
    public String cause_code = "";       //消息的结果码


    public Detach() {
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }

    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[DetachIndex.enodeb_ip];
        this.enodeb_port = str[DetachIndex.enodeb_port];
        this.mme_ip = str[DetachIndex.mme_ip];
        this.mme_port = str[DetachIndex.mme_port];
        this.mme_ue_s1ap_id = str[DetachIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str,AttachIndex.enb_ue_s1ap_id)];
        this.direction = str[DetachIndex.direction];

        int srvtype = Integer.parseInt(str[DetachIndex.srvtype]);
        byte[] bytes = CommonUtils.int2ByteArray(srvtype);
        byte[] arr = CommonUtils.getBooleanArray(bytes[3]);
        if (arr[3] == 1) {
            this.poweroff_ind = "1";
        } else {
            this.poweroff_ind = "2";
        }

        byte[] arr1 = CommonUtils.setByteArray(arr);
        this.srvtype = Integer.toString(CommonUtils.bytes2Int(arr1));

        this.mme_groupid = str[DetachIndex.mme_groupid];
        this.mme_code = str[DetachIndex.mme_code];
        this.m_tmsi = str[DetachIndex.m_tmsi];
        this.tac = str[DetachIndex.tac];
        this.eci = str[DetachIndex.eci];
        this.detach_cause = TypeChange.strToOx7f(str[DetachIndex.detach_cause]);

        this.req_time = str[DetachIndex.req_time];
        this.acpt_time = str[DetachIndex.acpt_time];
        this.end_msg = "0";
        this.msg_id = "0";
        this.cause_code = str[DetachIndex.cause_code];

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
        if (type == getEndProcedure()) {
            createType20(arr);
            return 2;
        } else {
            return 0;
        }

    }


    //20：ESM INFORMATION  
    private void createType20(String[] str) {

        this.context_rel_time = str[CommonUtils.convertIndex(str,DetachIndex.context_rel_time)];
        this.req_cause = str[CommonUtils.convertIndex(str,DetachIndex.req_cause)];
        this.contxt_rl_time = str[DetachIndex.contxt_rl_time];
        this.release_cause = str[DetachIndex.release_cause];
        this.comp_time = str[DetachIndex.comp_time];
    }

    @Override
    public String toString() {
        String common = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|").append(mme_ue_s1ap_id).append("|").append(enb_ue_s1ap_id)
                .append("|").append(direction).append("|").append(srvtype).append("|").append(poweroff_ind).append("|").append(mme_groupid).append("|").append(mme_code).append("|")
                .append(m_tmsi).append("|").append(tac).append("|").append(eci).append("|").append(detach_cause).append("|").append(req_time).append("|").append(acpt_time).append("|")
                .append(context_rel_time).append("|").append(req_cause).append("|").append(contxt_rl_time).append("|").append(release_cause).append("|").append(comp_time).append("|")
                .append(end_msg).append("|").append(msg_id).append("|").append(cause_code);
        return common + sb.toString();
    }
}
