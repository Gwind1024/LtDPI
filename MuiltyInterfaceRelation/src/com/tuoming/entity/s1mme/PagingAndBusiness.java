package com.tuoming.entity.s1mme;

import com.tuoming.common.TypeChange;
import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class PagingAndBusiness extends MmeCommon {


    public String enodeb_ip = "";            //eNodeB信令面IP
    public String enodeb_port = "";          //eNodeB端口
    public String mme_ip = "";               //MME IP地址
    public String mme_port = "";             //MME端口
    public String mme_ue_s1ap_id = "";       //MME UE S1AP ID
    public String enb_ue_s1ap_id = "";       //ENB UE S1AP ID
    private String direction = "";            //触发消息
    private String srvorig = "";              //寻呼来源
    private String pg_id_type = "";           //寻呼ID类型
    private String srvtype = "";              //业务类型
    private String csfb_res = "";             //CSFB Response
    public String mme_groupid = "";          //MME GroupID
    public String mme_code = "";             //MME Code
    public String m_tmsi = "";               //M-TMSI
    public String tac = "";                  //TAC
    public String eci = "";                  //ECI
    private String tai_list1 = "";            //TAI 列表
    private String tai_list2 = "";            //TAI 列表
    private String tai_list3 = "";            //TAI 列表
    private String tai_list4 = "";            //TAI 列表
    private String pag_drx = "";              //寻呼的DRX参数
    public String erab_num = "";             //ERAB数量
    public String erabid_req = "";           //请求消息的E-RAB ID
    public String rabqos_req = "";           //请求RAB中的QOS
    public String sgw_gtp_ip = "";           //SGW用户面IP
    public String sgw_gtp_teid = "";         //SGW用户面TEID
    private String erabid_res = "";           //响应消息的E-RAB ID
    public String rab_fillist = "";          //建立失败RAB的列表
    public String enb_gtp_ip = "";           //eNodeB用户面IP
    public String enb_gtp_teid = "";         //eNodeB用户面TEID
    private String paging_time = "";          //寻呼时间
    private String pag1st_t = "";             //第一次寻呼时刻
    private String pag2nd_t = "";             //第二次寻呼时刻
    private String pag3rd_t = "";             //第三次寻呼时刻
    public String req_time = "";             //业务请求时间
    private String rej_time = "";             //业务请求拒绝时间
    private String auth_time = "";            //鉴权请求时间
    private String auth_failure = "";         //鉴权失败时间
    public String cause = "";                //鉴权失败原因
    private String au_end_time = "";          //鉴权结束时间
    private String cip_time = "";             //ciphered OptionRequest时间
    private String cip_end_time = "";         //ciphered Option Response时间
    private String ini_set_time = "";         //initial context setup 请求时间
    public String rsp_time = "";             //initial context setup 响应时间
    private String erab_cause = "";           //承载建立失败原因
    public String context_rel_time = "";     //UE context release request时间
    public String req_cause = "";            //请求原因
    public String contxt_rl_time = "";       //UE context release command时间
    public String release_cause = "";        //UE context release释放原因
    public String comp_time = "";            //UE context release complete时间
    public String res_time = "0";             //结束消息类型
    public String msg_id = "";               //结束消息
    public String cause_code = "";           //消息的结果码
    public String rand = "";                 //鉴权随机数
    public String autn = "";                 //鉴权向量
    private String paging_time_window = "";   //寻呼时间窗
    private String edrx_value = "";           //非连续接通值
    private String adfp = "";                 //寻呼消息覆盖增强信息
    private String t3346 = "";                //信令拥塞控制字段
    private String gnb_gtp_ip = "";            //GNB_GTP_IP


    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.enodeb_ip = str[PagingIndex.enodeb_ip];
        this.enodeb_port = str[PagingIndex.enodeb_port];
        this.mme_ip = str[PagingIndex.mme_ip];
        this.mme_port = str[PagingIndex.mme_port];
        this.mme_ue_s1ap_id = str[PagingIndex.mme_ue_s1ap_id];
        this.enb_ue_s1ap_id = str[CommonUtils.convertIndex(str, AttachIndex.enb_ue_s1ap_id)];
        // this.srvorig = "";
        this.srvtype = "";
        this.csfb_res = "";
        this.mme_groupid = str[PagingIndex.mme_groupid];
        this.mme_code = str[PagingIndex.mme_code];
        this.m_tmsi = str[PagingIndex.m_tmsi];
        this.tac = str[PagingIndex.tac];
        this.eci = str[PagingIndex.eci];
        this.tai_list1 = "";
        this.tai_list2 = "";
        this.tai_list3 = "";
        this.tai_list4 = "";
        this.pag_drx = str[CommonUtils.convertIndex(str, PagingIndex.pag_drx)];
        //this.sgw_gtp_teid = "";
        // this.paging_time = "";
        this.pag1st_t = str[CommonUtils.convertIndex(str, PagingIndex.pag1st_t)];
        this.pag2nd_t = str[CommonUtils.convertIndex(str, PagingIndex.pag2nd_t)];
        this.pag3rd_t = str[CommonUtils.convertIndex(str, PagingIndex.pag3rd_t)];
        //this.res_time = "0";
        // this.msg_id = "9";
        this.paging_time_window = str[CommonUtils.convertIndex(str, PagingIndex.paging_time_window)];
        this.adfp = "";
        this.t3346 = str[CommonUtils.convertIndex(str, PagingIndex.t3346)];
        //System.out.println(str.length);
        this.gnb_gtp_ip = str[CommonUtils.convertIndex(str, PagingIndex.gnb_gtp_ip)];
        switch (str[PagingIndex.sdrType]) {
            case "2":
                this.pg_id_type = "";
                this.req_time = str[PagingIndex.paging_time];
                this.rej_time = (!"".equals(str[PagingIndex.rej_time1]) && "1".equals(str[PagingIndex.rej_time1])) ? str[PagingIndex.rej_time2] : "";
                this.cause_code = TypeChange.strToOx7f(str[PagingIndex.cause_code]);
                this.srvorig = "";
                if (!"".equals(str[PagingIndex.rej_time1]) && "255".equals(str[PagingIndex.rej_time1])) {
                    this.msg_id = "79";
                } else if (!"".equals(str[PagingIndex.rej_time1]) && "1".equals(str[PagingIndex.rej_time1])) {
                    this.msg_id = "78";
                } else {
                    this.msg_id = "9";
                }
                break;

            case "3":
                this.pg_id_type = "";
                this.srvtype = str[PagingIndex.srvtype];
                this.csfb_res = str[CommonUtils.convertIndex(str, PagingIndex.csfb_res)];
                this.req_time = str[PagingIndex.paging_time];
                this.rej_time = (str[PagingIndex.rej_time1] != null && "1".equals(str[PagingIndex.rej_time1])) ? str[PagingIndex.rej_time2] : "";
                this.cause_code = TypeChange.strToOx7f(str[PagingIndex.cause_code]);
                this.srvorig = "";
                if (!"".equals(str[PagingIndex.rej_time1]) && "255".equals(str[PagingIndex.rej_time1])) {
                    this.msg_id = "76";
                } else if (!"".equals(str[PagingIndex.rej_time1]) && "1".equals(str[PagingIndex.rej_time1])) {
                    this.msg_id = "78";
                } else {
                    this.msg_id = "9";
                }
                break;

            case "4":
                this.direction = "0";
                //this.srvorig = str[CommonUtils.convertIndex(str, PagingIndex.srvorig)];
                this.pg_id_type = str[PagingIndex.pg_id_type];
                this.tai_list1 = str[PagingIndex.tai_list1];
                this.paging_time = str[PagingIndex.paging_time];
                this.req_time = (!"".equals(str[PagingIndex.rej_time1]) && "0".equals(str[PagingIndex.rej_time1])) ? str[PagingIndex.req_time] : "";
                this.res_time = (!"".equals(str[PagingIndex.rej_time1]) && "255".equals(str[PagingIndex.rej_time1])) ? "1" : "0";
                this.edrx_value = str[CommonUtils.convertIndex(str, PagingIndex.edrx_value)];
                // this.tac = str[PagingIndex.tac];
                this.msg_id = (!"".equals(str[PagingIndex.rej_time1]) && "255".equals(str[PagingIndex.rej_time1])) ? "10" : "9";
                break;

            case "100":
                this.direction = "1";
                this.srvorig = "0";
                this.pg_id_type = str[PagingIndex.pg_id_type];
                this.tai_list1 = str[PagingIndex.tai_list1];
                this.paging_time = str[PagingIndex.paging_time];
                this.req_time = (!"".equals(str[PagingIndex.rej_time1]) && "0".equals(str[PagingIndex.rej_time1])) ? str[PagingIndex.req_time] : "";
                // this.tac = str[PagingIndex.tac];
                this.msg_id = (!"".equals(str[PagingIndex.rej_time1]) && "255".equals(str[PagingIndex.rej_time1])) ? "100" : "9";
                break;
        }
    }

    //18
    private void createType18(String[] str) {
        this.erab_num = str[PagingIndex.erab_num];
        Integer erabid_req_index = CommonUtils.numIndex(str, PagingIndex.erabid_req);
        if (erabid_req_index != null) {
            this.erabid_req = str[erabid_req_index];
        }
        String rabqos = str[CommonUtils.convertIndex(str, PagingIndex.rabqos_req)];
        this.rabqos_req = TypeChange.strToHex(rabqos);
        this.sgw_gtp_ip = str[CommonUtils.convertIndex(str, PagingIndex.sgw_gtp_ip)];
        Integer sgwteid_index = CommonUtils.numIndex(str, PagingIndex.sgw_gtp_teid);
        if (sgwteid_index != null) {
            this.sgw_gtp_teid = TypeChange.strToHex(str[sgwteid_index]);
        }
        this.erabid_res = str[CommonUtils.convertIndex(str, PagingIndex.erabid_res)];
        String rabfill = str[CommonUtils.convertIndex(str, PagingIndex.rab_fillist)];
        this.rab_fillist = TypeChange.strToHex(rabfill);
        this.enb_gtp_ip = str[CommonUtils.convertIndex(str, PagingIndex.enb_gtp_ip)];
        Integer enbteid_index = CommonUtils.numIndex(str, PagingIndex.enb_gtp_teid);
        if (enbteid_index != null) {
            this.enb_gtp_teid = TypeChange.strToHex(str[enbteid_index]);
        }
        this.ini_set_time = str[PagingIndex.ini_set_time];
        this.rsp_time = str[PagingIndex.rsp_time];
        this.erab_cause = (!"".equals(str[PagingIndex.rej_time1]) && "1".equals(str[PagingIndex.rej_time1])) ? TypeChange.strToOx7f(str[PagingIndex.erab_cause]) : "";

    }

    //19
    private void createType19(String[] str) {
        this.ini_set_time = str[PagingIndex.ini_set_time];
        this.rsp_time = str[PagingIndex.rsp_time];
    }

    //30
    private void createType30(String[] str) {
        this.auth_time = str[PagingIndex.auth_time];
        if (!"".equals(str[PagingIndex.rej_time1]) && "1".equals(str[PagingIndex.rej_time1])) {
            this.auth_failure = str[PagingIndex.auth_failure];
            this.cause = TypeChange.strToOx7f(str[PagingIndex.cause]);
        } else {
            this.auth_failure = "";
            this.cause = "";
        }
        if (!"".equals(str[PagingIndex.rej_time1]) && "0".equals(str[PagingIndex.rej_time1])) {
            this.au_end_time = str[PagingIndex.au_end_time];
        } else {
            this.au_end_time = "";
        }
        this.rand = str[CommonUtils.convertIndex(str, PagingIndex.rand)];
        this.autn = str[CommonUtils.convertIndex(str, PagingIndex.autn)];

    }

    //31
    private void createType31(String[] str) {
        this.cip_time = str[PagingIndex.cip_time];
        this.cip_end_time = str[PagingIndex.cip_end_time];

    }

    //20
    private void createType20(String[] str) {
        this.context_rel_time = str[CommonUtils.convertIndex(str, PagingIndex.context_rel_time)];
        this.req_cause = TypeChange.strToOx7f(str[CommonUtils.convertIndex(str, PagingIndex.req_cause)]);
        this.contxt_rl_time = str[PagingIndex.contxt_rl_time];
        this.release_cause = TypeChange.strToOx7f(str[PagingIndex.release_cause]);
        this.comp_time = str[PagingIndex.comp_time];

    }


    @Override
    public boolean getMiddleProcedure(int produceType) {
        if (produceType == 18 || produceType == 19 || produceType == 30 || produceType == 31) {
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
                    case 18:
                        createType18(arr);
                        break;
                    case 19:
                        createType19(arr);
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
        sb.append(enodeb_ip).append("|").append(enodeb_port).append("|").append(mme_ip).append("|").append(mme_port).append("|").append(mme_ue_s1ap_id).append("|").append(enb_ue_s1ap_id).append("|")
                .append(direction).append("|").append(srvorig).append("|").append(pg_id_type).append("|").append(srvtype).append("|").append(csfb_res).append("|").append(mme_groupid).append("|")
                .append(mme_code).append("|").append(m_tmsi).append("|").append(tac).append("|").append(eci).append("|").append(tai_list1).append("|").append(tai_list2).append("|").append(tai_list3).append("|")
                .append(tai_list4).append("|").append(pag_drx).append("|").append(erab_num).append("|").append(erabid_req).append("|").append(rabqos_req).append("|").append(sgw_gtp_ip).append("|")
                .append(sgw_gtp_teid).append("|").append(erabid_res).append("|").append(rab_fillist).append("|").append(enb_gtp_ip).append("|").append(enb_gtp_teid).append("|").append(paging_time).append("|")
                .append(pag1st_t).append("|").append(pag2nd_t).append("|").append(pag3rd_t).append("|").append(req_time).append("|").append(rej_time).append("|").append(auth_time).append("|")
                .append(auth_failure).append("|").append(cause).append("|").append(au_end_time).append("|").append(cip_time).append("|").append(cip_end_time).append("|").append(ini_set_time).append("|")
                .append(rsp_time).append("|").append(erab_cause).append("|").append(context_rel_time).append("|").append(req_cause).append("|").append(contxt_rl_time).append("|").append(release_cause).append("|")
                .append(comp_time).append("|").append(res_time).append("|").append(msg_id).append("|").append(cause_code).append("|").append(rand).append("|").append(autn).append("|")
                .append(paging_time_window).append("|").append(edrx_value).append("|").append(adfp).append("|").append(t3346).append("|").append(gnb_gtp_ip);
        return common + sb.toString();
    }
}
