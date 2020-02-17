package com.tuoming.entity.s1mme;

public interface DetachIndex {

    int enodeb_ip=29;        //eNodeB信令面IP
    int enodeb_port=31;      //eNodeB端口
    int mme_ip=28;           //MME IP地址
    int mme_port=30;         //MME端口
    int mme_ue_s1ap_id=18;   //MME UE S1AP ID
    int enb_ue_s1ap_id=42;   //ENB UE S1AP ID
    int direction=15;        //方向标识
    int srvtype=14;          //业务类型
    int poweroff_ind=14;     //关机指示
    int mme_groupid=22;      //MME GroupID
    int mme_code=23;         //MME Code
    int m_tmsi=24;           //M-TMSI
    int tac=32;              //TAC
    int eci=33;              //ECI
    int detach_cause=12;     //网络去附着原因
    int req_time=9;         //消息请求时间
    int acpt_time=10;        //Dettach Accept时间
    int context_rel_time=69; //UE context release request时间
    int req_cause=70;        //请求原因
    int contxt_rl_time=9;   //UE context release command时间
    int release_cause=12;    //UE context release释放原因
    int comp_time=10;        //UE context release complete时间
//    int end_msg="";          //结束消息类型
//    int msg_id="";           //结束消息
    int cause_code=13;       //消息的结果码
    int bearer_number=37;     //承载组数量

}
