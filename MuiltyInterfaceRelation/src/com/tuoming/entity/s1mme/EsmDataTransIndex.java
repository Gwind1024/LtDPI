package com.tuoming.entity.s1mme;

public interface EsmDataTransIndex {

//    int protocol_discriminator=1;
    int eps_bearer_identity=88;
    int procedure_transaction_identity=51;
    int esm_data_transport_message_identity=88;
    int user_data_container=85;
    int release_assistance_indication=90;
    int ul_data=85;
    int dl_data=85;
    int ul_packet=71;
    int dl_packet=71;

    int mme_ue_s1ap_id=18;
    int mme_ip=28;

//    int message_type=1;
    int enb_ue_s1ap_id=42;
//    int nas_pdu=1;
    int tai=40;
    int e_utran_cgi=40;
}
