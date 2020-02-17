package com.tuoming.entity.s1mme;

import com.tuoming.entity.s1mme.method.MmeMapCommon;
import com.tuoming.tools.CommonUtils;

public class EsmDataTransport extends MmeCommon {

    public String protocol_discriminator = "";              //协议类别
    public String eps_bearer_identity = "";                 //EPS承载标识
    public String procedure_transaction_identity = "";      //事务标识
    public String esm_data_transport_message_identity = ""; //消息类型
    public String user_data_container = "";                 //用户数据
    public String release_assistance_indication = "";       //释放标识
    public String ul_data = "";                             //上行流量，统计CP模式下NAS传输上行用户数据字节数
    public String dl_data = "";                             //下行流量，统计CP模式下NAS传输下行用户数据字节数
    public String ul_packet = "";                           //上行包数，统计CP模式下NAS传输上行用户数据信令条数
    public String dl_packet = "";                           //下行包数，统计CP模式下NAS传输上行用户数据信令条数
    public String mme_ip = "";                              //MME_IP地址

    public String mme_ue_s1ap_id = "";


    public EsmDataTransport() {
    }

    @Override
    public String getKey() {
        return mme_ue_s1ap_id;
    }


    @Override
    public void decode(String[] str) {
        super.decode(str);
        this.mme_ue_s1ap_id = str[EsmDataTransIndex.mme_ue_s1ap_id];
        this.protocol_discriminator = "2";
        this.eps_bearer_identity = str[CommonUtils.convertIndex(str, EsmDataTransIndex.eps_bearer_identity)];
        this.procedure_transaction_identity = str[CommonUtils.convertIndex(str, EsmDataTransIndex.procedure_transaction_identity)];
        this.esm_data_transport_message_identity = str[CommonUtils.convertIndex(str,EsmDataTransIndex.esm_data_transport_message_identity)];
        this.user_data_container = str[CommonUtils.convertIndex(str,EsmDataTransIndex.user_data_container)];
        this.release_assistance_indication = str[CommonUtils.convertIndex(str, EsmDataTransIndex.release_assistance_indication)];
        String data = str[CommonUtils.convertIndex(str, EsmDataTransIndex.ul_packet)];
        if (data == null) {
            this.ul_data = "";
            this.dl_data = "";
            this.ul_packet = "";
            this.dl_packet = "";
        } else if ("1".equals(data)) {
            this.ul_data = str[CommonUtils.convertIndex(str, EsmDataTransIndex.ul_data)];
            this.ul_packet = "1";
        } else if ("2".equals(data)) {
            this.dl_data = str[CommonUtils.convertIndex(str, EsmDataTransIndex.dl_data)];
            this.dl_packet = "1";
        }
        this.mme_ip = str[EsmDataTransIndex.mme_ip];

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
        sb.append(protocol_discriminator).append("|").append(eps_bearer_identity).append("|").append(procedure_transaction_identity).append("|")
                .append(esm_data_transport_message_identity).append("|").append(user_data_container).append("|").append(release_assistance_indication).append("|")
                .append(ul_data).append("|").append(dl_data).append("|").append(ul_packet).append("|").append(dl_packet);
        return mme_ip + "|" + common + sb.toString();
    }

}
