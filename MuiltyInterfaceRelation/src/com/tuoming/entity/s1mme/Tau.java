package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

//跟踪区更新 5
public class Tau extends MmeCommon {


    public String enodeb_ip = "";             //eNodeB信令面IP
    public String enodeb_port = "";           //eNodeB端口
    public String mme_ip = "";                //MME IP地址
    public String mme_port = "";              //MME端口
    public String mme_ue_s1ap_id = "";        //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";        //ENB UE S1AP ID
    private String srvtype_req = "";           //业务类型（请求）
    private String srvtype_res = "";           //业务类型（响应）
    private String bear_ind = "";              //承载建立指示
    private String add_upt_req = "";           //附件更新类型(请求)
    private String add_upt_res = "";           //附件更新类型(响应)
    public String mme_groupid = "";           //MME GroupID
    public String mme_code = "";              //MME Code
    public String m_tmsi = "";                //M-TMSI
    public String tmsi = "";                  //TMSI
    private String guti_type = "";             //老GUTI类型
    public String mcc = "";                   //老国家码
    public String mnc = "";                   //老移动网络码
    private String old_guti = "";              //老GUTI
    public String tac = "";                   //TAC
    public String eci = "";                   //ECI
    private String tai_list1 = "";             //TAI 列表
    private String tai_list2 = "";             //TAI 列表
    private String tai_list3 = "";             //TAI 列表
    private String tai_list4 = "";             //TAI 列表
    public String lac = "";                   //LAC
    private String old_tac = "";               //OLD TAC
    private String old_lac = "";               //OLD LAC
    private String drx_par = "";               //DRX参数
    private String uenc = "";                  //UE网络能力
    private String vdp_uus = "";               //VDP_UUS
    private String msnc = "";                  //MS网络能力
    private String nksi = "";                  //NAS key set identifier
    private String eps_nfs = "";               //EPS网络特性支持
    private String msc2 = "";                  //Mobile Station Classmark2
    private String msc3 = "";                  //Mobile Station Classmark3
    private String bear_status = "";           //承载状态
    public String req_time = "";              //消息请求时间
    private String auth_time = "";             //鉴权请求时间
    private String auth_failure = "";          //鉴权失败时间
    public String cause = "";                 //鉴权失败原因
    private String end_time = "";              //鉴权结束时间
    private String cip_req_time = "";          //ciphered OptionRequest时间
    private String cip_res_time = "";          //ciphered Option Response时间
    private String acpt_time = "";             //TAU Accept时间或Initial UE context setup request消息时间
    private String rej_cause = "";             //TAU拒绝原因码
    private String context_set_time = "";      //Initial UE context setup response消息时间
    private String tau_comp_time = "";         //TAUComplete时间
    public String context_rel_time = "";      //UE context release request时间
    public String req_cause = "";             //请求原因
    public String contxt_rl_time = "";        //UE context release command时间
    public String release_cause = "";         //UE context release释放原因
    private String ue_comp_time = "";          //UE context releasecomplete时间
    public String res_time = "";              //结束消息类型
    public String msg_id = "";                //结束消息
    public String cause_code = "";            //消息的结果码
    public String rand = "";                  //鉴权随机数
    public String autn = "";                  //鉴权向量
    private String ciot_support = "";          //是否支持NBIOT
    private String ce_level = "";              //覆盖增强级
    private String psm_status = "";            //省电模式状态
    private String t3324 = "";                 //T3324
    private String t3412_extended = "";        //T3412 extended value
    private String t3412 = "";                 //T3412
    private String extended_drx_status = "";  //非连续接收状态
    private String paging_time_window = "";    //寻呼时间窗
    private String edrx_value = "";            //非连续接值
    private String device_properties = "";     //是否是NAS信令低优先级
    private String t3346 = "";                 //信令拥塞控制字段
    private String nr_restriction = "";        //NR限制
    private String gnb_gtp_p = "";             //GNB_GTP_IP


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");



    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[TauIndex.enodeb_ip];
        this.enodeb_port = str[TauIndex.enodeb_port];
        this.mme_ip = str[TauIndex.mme_ip];
        this.mme_port = str[TauIndex.mme_port];
        this.mme_ue_s1ap_id = str[TauIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str,AttachIndex.enb_ue_s1ap_id)];
        this.srvtype_req = str[TauIndex.srvtype_req];
        this.srvtype_res = str[TauIndex.srvtype_res];

        int bearIndex = CommonUtils.convertIndex(str, TauIndex.bear_ind);
        this.bear_ind = str[bearIndex];
        int addIndex = CommonUtils.convertIndex(str, TauIndex.add_upt_req);
        this.add_upt_req = str[addIndex];
        this.add_upt_res = str[TauIndex.add_upt_res];
        this.mme_groupid = str[TauIndex.mme_groupid];
        this.mme_code = str[TauIndex.mme_code];
        this.m_tmsi = str[TauIndex.m_tmsi];
        this.tmsi = str[TauIndex.tmsi];
        int gutiIndex = CommonUtils.convertIndex(str, TauIndex.guti_type);
        this.guti_type = str[gutiIndex];
        String oldGuti = str[TauIndex.old_guti] + str[TauIndex.old_guti + 1] + str[TauIndex.old_guti + 2];
        this.mcc = str[TauIndex.mcc] ;
        this.mnc = str[TauIndex.mnc];
        if (oldGuti != null) {
          try{
            this.old_guti = Long.toHexString(CommonUtils.strToLong(oldGuti));
        } catch (NumberFormatException e) {
            this.old_guti = "";
        }
        }
        this.tac = str[TauIndex.tac];
        this.eci = str[TauIndex.eci];
        this.tai_list1 = str[TauIndex.tai_list1];
        this.tai_list2 = "";
        this.tai_list3 = "";
        this.tai_list4 = "";
        this.lac = str[TauIndex.lac];
        this.old_tac = "";
        this.old_lac = "";
        int drxIndex = CommonUtils.convertIndex(str, TauIndex.drx_par);
        this.drx_par = TypeChange.strToHex(str[drxIndex]);
        int uencIndex = CommonUtils.convertIndex(str, TauIndex.uenc);
        this.uenc = str[uencIndex];
        this.vdp_uus = TypeChange.strToHex(str[TauIndex.vdp_uus]);
        int msncIndex = CommonUtils.convertIndex(str, TauIndex.msnc);
        this.msnc = str[msncIndex];

        int nksiIndex = CommonUtils.convertIndex(str, TauIndex.nksi);
        this.nksi = TypeChange.strToHex(str[nksiIndex]);
        int epsIndex = CommonUtils.convertIndex(str, TauIndex.eps_nfs);
        this.eps_nfs = TypeChange.strToHex(str[epsIndex]);
        this.msc2 = "";
        this.msc3 = "";
        Integer bear_status_index = CommonUtils.numIndex(str,TauIndex.bear_status);
        if (bear_status_index != null) {
            this.bear_status = TypeChange.strToHex(str[bear_status_index]);
        }
        this.req_time = str[TauIndex.req_time];
       // this.acpt_time = (str[TauIndex.msg_id].equals('0') || str[TauIndex.msg_id].equals("1")) ? str[TauIndex.acpt_time] : "";
        String rejnum = "1".equals(str[TauIndex.msg_id]) ? str[TauIndex.rej_cause] : "";
        this.rej_cause = TypeChange.strToOx7f(rejnum);

        this.tau_comp_time = str[TauIndex.tau_comp_time];
        this.res_time = "0";
        if ("0".equals(str[TauIndex.msg_id])) {
            this.msg_id = "74";
        } else if ("1".equals(str[TauIndex.msg_id])) {
            this.msg_id = "75";
        } else if ("255".equals(str[TauIndex.msg_id])) {
            this.msg_id = "72";
        }

        this.cause_code = TypeChange.strToOx7f(str[TauIndex.cause_code]);
        this.ciot_support = str[CommonUtils.convertIndex(str, TauIndex.ciot_support)];
        this.ce_level = "";
        this.psm_status = "";
        this.t3324 = str[CommonUtils.convertIndex(str, TauIndex.t3324)];
        this.t3412_extended = str[CommonUtils.convertIndex(str, TauIndex.t3412_extended)];
        this.t3412 = str[CommonUtils.convertIndex(str, TauIndex.t3412)];
        this.extended_drx_status = "";
        this.paging_time_window = str[CommonUtils.convertIndex(str, TauIndex.paging_time_window)];
        this.edrx_value = str[CommonUtils.convertIndex(str, TauIndex.edrx_value)];
        this.device_properties = str[CommonUtils.convertIndex(str, TauIndex.device_properties)];
        this.t3346 = str[CommonUtils.convertIndex(str, TauIndex.t3346)];
        this.nr_restriction = str[CommonUtils.convertIndex(str,TauIndex.nr_restriction)];
        this.gnb_gtp_p = str[CommonUtils.convertIndex(str,TauIndex.gnb_gtp_p)];

    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }

    //18
    private void createType18(String[] str) {
       // this.acpt_time = str[TauIndex.acpt_time];
        this.acpt_time = ("0".equals(str[TauIndex.msg_id]) || "1".equals(str[TauIndex.msg_id])) ? str[TauIndex.acpt_time1] : str[TauIndex.acpt_time];
        this.context_set_time = str[TauIndex.context_set_time];
    }

    //20
    private void createType20(String[] str) {
        int contextIndex = CommonUtils.convertIndex(str, TauIndex.context_rel_time);
        this.context_rel_time = str[contextIndex];
        int reqIndex = CommonUtils.convertIndex(str, TauIndex.req_cause);
        this.req_cause = TypeChange.strToOx7f(str[TauIndex.req_cause]);
      //  this.req_cause = str[reqIndex];
        this.contxt_rl_time = str[TauIndex.contxt_rl_time];
        this.release_cause = TypeChange.strToOx7f(str[TauIndex.release_cause]);
        this.ue_comp_time = str[TauIndex.ue_comp_time];
    }

    //30
    private void createType30(String[] str) {
        this.auth_time = str[TauIndex.auth_time];
        this.auth_failure = "1".equals(str[TauIndex.msg_id]) ? str[TauIndex.auth_failure] : "";
        String causenum = "1".equals(str[TauIndex.msg_id]) ? str[TauIndex.cause] : "";
        this.cause = TypeChange.strToOx7f(causenum);
        this.end_time = "0".equals(str[TauIndex.msg_id]) ? str[TauIndex.end_time] : "";
        this.rand = str[CommonUtils.convertIndex(str, TauIndex.rand)];
        this.autn = str[CommonUtils.convertIndex(str, TauIndex.autn)];
    }

    //31
    private void createType31(String[] str) {
        this.cip_req_time = str[TauIndex.cip_req_time];
        this.cip_res_time = str[TauIndex.cip_res_time];
    }

    @Override
    public boolean getMiddleProcedure(int prduceType) {
        if(prduceType==18 || prduceType==30 || prduceType==31){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Integer getEndProcedure() {
        return 20;
    }

    @Override
    public Integer relation(MmeMapCommon mmeMapCommon, int type, String[] arr) {

        long startTime = mmeMapCommon.getStartTime();
        long endTime = mmeMapCommon.getEndTime();
        Long start;
        Long end;
        try {
            start = sdf.parse(arr[MmeIndex.startTime].substring(0, 23)).getTime();
            end = sdf.parse(arr[MmeIndex.endTime].substring(0, 23)).getTime();
        } catch (ParseException e) {
            return 3;
        }
        if (getMiddleProcedure(type)) {
            if (start >= startTime && endTime >= end) {
                switch (type) {
                    case 18:
                        createType18(arr);
                        break;
                    case 30:
                        createType30(arr);
                        break;
                    case 31:
                        createType31(arr);
                        break;
                }
            }
            return 1;
        } else if (type == getEndProcedure()) {
            createType20(arr);
            return 2;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        String common = super.toString();
        StringBuilder sb = new StringBuilder();
        sb.append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|").append(mme_ue_s1ap_id).append("|")
                .append(enb_ue_s1ap_id).append("|").append(srvtype_req).append("|").append(srvtype_res).append("|").append(bear_ind).append("|").append(add_upt_req).append("|")
                .append(add_upt_res).append("|").append(mme_groupid).append("|").append(mme_code).append("|").append(m_tmsi).append("|").append(tmsi).append("|").append(guti_type).append("|")
                .append(mcc).append("|").append(mnc).append("|").append(old_guti).append("|").append(tac).append("|").append(eci).append("|").append(tai_list1).append("|").append(tai_list2).append("|")
                .append(tai_list3).append("|").append(tai_list4).append("|").append(lac).append("|").append(old_tac).append("|").append(old_lac).append("|").append(drx_par).append("|")
                .append(uenc).append("|").append(vdp_uus).append("|").append(msnc).append("|").append(nksi).append("|").append(eps_nfs).append("|").append(msc2).append("|").append(msc3).append("|")
                .append(bear_status).append("|").append(req_time).append("|").append(auth_time).append("|").append(auth_failure).append("|").append(cause).append("|").append(end_time).append("|")
                .append(cip_req_time).append("|").append(cip_res_time).append("|").append(acpt_time).append("|").append(rej_cause).append("|").append(context_set_time).append("|")
                .append(tau_comp_time).append("|").append(context_rel_time).append("|").append(req_cause).append("|").append(contxt_rl_time).append("|").append(release_cause).append("|")
                .append(ue_comp_time).append("|").append(res_time).append("|").append(msg_id).append("|").append(cause_code).append("|").append(rand).append("|").append(autn).append("|")
                .append(ciot_support).append("|").append(ce_level).append("|").append(psm_status).append("|").append(t3324).append("|").append(t3412_extended).append("|").append(t3412).append("|")
                .append(extended_drx_status).append("|").append(paging_time_window).append("|").append(edrx_value).append("|").append(device_properties).append("|").append(t3346).append("|")
                .append(nr_restriction).append("|").append(gnb_gtp_p);
        return common + sb.toString();
    }
}
