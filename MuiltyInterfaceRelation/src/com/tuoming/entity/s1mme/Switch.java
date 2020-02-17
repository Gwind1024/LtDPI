package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Switch extends MmeCommon {

    public String enodeb_ip = "";         //eNodeB信令面IP
    public String enodeb_port = "";       //eNodeB端口
    public String mme_ip = "";            //MME IP地址
    public String mme_port = "";          //MME端口
    public String mme_ue_s1ap_id = "";    //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";    //ENB UE S1AP ID
    private String srvtype = "";           //业务类型
    private String hdovr_cause = "";       //切换原因
    private String ho_type = "";           //切换类型
    public String mme_groupid = "";       //MME GroupID
    public String mme_code = "";          //MME Code
    private String to_mme_groupid = "";    //目的MME GroupID
    private String to_mme_code = "";       //目的MME Code
    public String m_tmsi = "";            //M-TMSI
    private String to_lac = "";            //目的TAC或LAC
    private String to_ci = "";             //目的ECI或CI
    private String from_lac = "";          //源LAC或TAC
    private String from_ci = "";           //源CI或ECI
    private String destnettype = "";       //切入网络类型
    private String sourcenettype = "";     //切出网络类型
    private String ho_srvcc = "";          //SRVCC指示
    private String ho_req_type = "";       //切换请求类型
    private String ho_s2t = "";            //源到目的无线参数
    private String ho_t2s = "";            //目的到源无线参数
    private String ho_mbr_up = "";         //UE汇聚MBR（下行）
    private String ho_mbr_down = "";       //UE汇聚MBR（上行）
    public String erab_num = "";          //ERAB数量
    public String erabid_rsp = "";        //切换后协商的E-RAB ID
    private String dl_ip = "";             //切换后协商的下行IP
    private String dl_teid = "";           //切换后协商的下行TEID
    private String up_ip = "";             //切换后协商的上行IP
    private String up_teid = "";           //切换后协商的上行TEID
    private String rab_list = "";          //切换后释放的RAB列表或切换后建立失败的列表
    public String req_time = "";          //消息请求时间
    public String rsp_time = "";          //响应时间
    public String comp_time = "";         //完成时间
    public String cause = "";             //切换失败原因
    private String canl_time = "";         //取消时间
    private String canl_cause = "";        //取消原因
    private String canl_res = "";          //取消响应时间
    public String context_rel_time = "";  //UE context release request时间
    public String req_cause = "";         //请求原因
    public String contxt_rl_time = "";    //UE context release command时间
    public String release_cause = "";     //UE context release释放原因
    private String uecomp_time = "";       //UE context releasecomplete时间
    public String res_time = "";          //结束消息类型
    public String msg_id = "";            //结束消息
    public String cause_code = "";        //消息的结果码

    public Switch() {
    }

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[SwitchIndex.enodeb_ip];
        this.enodeb_port = str[SwitchIndex.enodeb_port];
        this.mme_ip = str[SwitchIndex.mme_ip];
        this.mme_port = str[SwitchIndex.mme_port];
        this.mme_ue_s1ap_id = str[SwitchIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str, AttachIndex.enb_ue_s1ap_id)];
        this.hdovr_cause = TypeChange.strToOx7f(str[SwitchIndex.hdovr_cause]);
        this.m_tmsi = str[SwitchIndex.m_tmsi];
        this.destnettype = "6";
        this.sourcenettype = "6";
        this.ho_s2t = str[CommonUtils.convertIndex(str, SwitchIndex.ho_s2t)];
        this.ho_t2s = str[CommonUtils.convertIndex(str, SwitchIndex.ho_t2s)];
        this.ho_mbr_up = str[CommonUtils.convertIndex(str, SwitchIndex.ho_mbr_up)];
        this.ho_mbr_down = str[CommonUtils.convertIndex(str, SwitchIndex.ho_mbr_down)];
        this.erab_num = str[SwitchIndex.erab_num];
        this.erabid_rsp = str[CommonUtils.convertIndex(str, SwitchIndex.erabid_rsp)];
        this.dl_ip = str[CommonUtils.convertIndex(str, SwitchIndex.dl_ip)];
        Integer dl_teid_index = CommonUtils.numIndex(str, SwitchIndex.dl_teid);
        if (dl_teid_index != null) {
            this.dl_teid = str[dl_teid_index];
        }
        this.up_ip = str[CommonUtils.convertIndex(str, SwitchIndex.up_ip)];
        Integer up_teid_index = CommonUtils.numIndex(str, SwitchIndex.up_teid);
        if (up_teid_index != null) {
            this.up_teid = str[up_teid_index];
        }
        String rablist = str[CommonUtils.convertIndex(str, SwitchIndex.rab_list)];
        this.rab_list = TypeChange.strToHex(rablist);

        this.req_time = str[SwitchIndex.req_time];
        this.rsp_time = str[SwitchIndex.rsp_time];
        // this.context_rel_time = str[CommonUtils.convertIndex(str, SwitchIndex.context_rel_time)];
        this.res_time = "1";
        String causenum = (!"".equals(str[SwitchIndex.msg_id]) && "1".equals(str[SwitchIndex.msg_id])) ? str[SwitchIndex.cause] : "";
        this.cause = TypeChange.strToOx7f(causenum);
        if ("".equals(str[SwitchIndex.cause_code1])) {
            this.cause_code = TypeChange.strToOx7f(str[SwitchIndex.cause_code]);
        } else {
            this.cause_code = TypeChange.strToOx7f(str[SwitchIndex.cause_code1]);
        }


        switch (str[SwitchIndex.srvtype]) {
            case "14":
                this.srvtype = "";
                this.ho_type = "2";
                this.mme_groupid = str[SwitchIndex.mme_groupid14];
                this.mme_code = str[SwitchIndex.mme_code14];
                // this.to_mme_groupid = "";
                // this.to_mme_code = "";
                this.to_lac = str[SwitchIndex.to_lac14];
                this.to_ci = str[SwitchIndex.to_ci14];
                this.from_lac = str[SwitchIndex.from_lac14];
                this.from_ci = str[SwitchIndex.from_ci14];
                this.ho_srvcc = "";
                this.ho_req_type = "";
                this.comp_time = "";
                this.canl_time = "";
                this.canl_cause = "";
                this.canl_res = "";
                this.context_rel_time = "";
                this.req_cause = "";
                this.contxt_rl_time = "";
                this.release_cause = "";
                this.uecomp_time = "";
                this.msg_id = "3";
                break;
            case "15":
                this.srvtype = "1";
                this.ho_type = "1";
                this.mme_groupid = str[SwitchIndex.mme_groupid15];
                this.mme_code = str[SwitchIndex.mme_code15];
                this.to_mme_groupid = str[SwitchIndex.to_mme_groupid15];
                this.to_mme_code = str[SwitchIndex.to_mme_code15];
                this.to_lac = str[SwitchIndex.to_lac15];
                this.to_ci = str[SwitchIndex.to_ci15];
                this.from_lac = str[SwitchIndex.from_lac15];
                this.from_ci = str[SwitchIndex.from_ci15];
                this.ho_srvcc = "";
                this.ho_req_type = "keyword1";
                this.comp_time = str[SwitchIndex.comp_time];
                this.canl_time = "";
                this.canl_cause = "";
                this.canl_res = "";
                this.context_rel_time = "";
                this.req_cause = "";
                this.contxt_rl_time = "";
                this.release_cause = "";
                this.uecomp_time = "";
                if ("0".equals(str[SwitchIndex.msg_id])) {
                    this.msg_id = "2";
                } else if ("1".equals(str[SwitchIndex.msg_id])) {
                    this.msg_id = "";
                } else if ("255".equals(str[SwitchIndex.msg_id])) {
                    this.msg_id = "0";
                }
                break;
            case "16":
                this.srvtype = "2";
                this.ho_type = "1";
                this.mme_groupid = str[SwitchIndex.mme_groupid16];
                this.mme_code = str[SwitchIndex.mme_code16];
                this.to_mme_groupid = str[SwitchIndex.to_mme_groupid16];
                this.to_mme_code = str[SwitchIndex.to_mme_code16];
                this.to_lac = str[SwitchIndex.to_lac16];
                this.to_ci = str[SwitchIndex.to_ci16];
                this.from_lac = str[SwitchIndex.from_lac16];
                this.from_ci = str[SwitchIndex.from_ci16];
                this.ho_srvcc = str[SwitchIndex.ho_srvcc];
                this.ho_req_type = "";
                this.comp_time = str[SwitchIndex.comp_time];
                this.canl_time = "";
                this.canl_cause = "";
                this.canl_res = "";
                this.context_rel_time = "78";
                this.req_cause = "";
                this.contxt_rl_time = "";
                this.release_cause = "";
                this.uecomp_time = "";
                if ("0".equals(str[SwitchIndex.msg_id])) {
                    this.msg_id = "1";
                } else if ("1".equals(str[SwitchIndex.msg_id])) {
                    this.msg_id = "4";
                } else if ("255".equals(str[SwitchIndex.msg_id])) {
                    this.msg_id = "0";
                }
                break;

        }

    }


    //17
    private void createType17(String[] str) {
        if ("16".equals(str[SwitchIndex.srvtype])) {
            this.canl_time = str[SwitchIndex.canl_time];
            this.canl_cause = TypeChange.strToOx7f(str[SwitchIndex.canl_cause]);
            this.canl_res = str[SwitchIndex.canl_res];
        }

        //this.uecomp_time = str[SwitchIndex.uecomp_time];

    }


    //20
    private void createType20(String[] str) {
        if ("16".equals(str[SwitchIndex.srvtype])) {
            this.comp_time = str[SwitchIndex.comp_time];
            this.req_cause = str[CommonUtils.convertIndex(str, SwitchIndex.req_cause)];
            this.contxt_rl_time = str[SwitchIndex.contxt_rl_time];
            this.release_cause = str[SwitchIndex.release_cause];
            if ("0".equals(str[SwitchIndex.msg_id])) {
                this.msg_id = "23";
            }
        }
        this.cause_code = TypeChange.strToOx7f(str[SwitchIndex.cause_code]);
        this.uecomp_time = str[SwitchIndex.uecomp_time];


    }

    @Override
    public boolean getMiddleProcedure(int produceType) {
        if (produceType == 17) {
            return true;
        } else {
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
                    case 17:
                        createType17(arr);
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
        sb.append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|").append(mme_ue_s1ap_id).append("|").append(enb_ue_s1ap_id).append("|")
                .append(srvtype).append("|").append(hdovr_cause).append("|").append(ho_type).append("|").append(mme_groupid).append("|").append(mme_code).append("|").append(to_mme_groupid).append("|")
                .append(to_mme_code).append("|").append(m_tmsi).append("|").append(to_lac).append("|").append(to_ci).append("|").append(from_lac).append("|").append(from_ci).append("|")
                .append(destnettype).append("|").append(sourcenettype).append("|").append(ho_srvcc).append("|").append(ho_req_type).append("|").append(ho_s2t).append("|").append(ho_t2s).append("|")
                .append(ho_mbr_up).append("|").append(ho_mbr_down).append("|").append(erab_num).append("|").append(erabid_rsp).append("|").append(dl_ip).append("|").append(dl_teid).append("|")
                .append(up_ip).append("|").append(up_teid).append("|").append(rab_list).append("|").append(req_time).append("|").append(rsp_time).append("|").append(comp_time).append("|")
                .append(cause).append("|").append(canl_time).append("|").append(canl_cause).append("|").append(canl_res).append("|").append(context_rel_time).append("|").append(req_cause).append("|")
                .append(contxt_rl_time).append("|").append(release_cause).append("|").append(uecomp_time).append("|").append(res_time).append("|").append(msg_id).append("|").append(cause_code);
        return common + sb.toString();
    }
}
