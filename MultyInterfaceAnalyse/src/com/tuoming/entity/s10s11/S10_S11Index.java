package com.tuoming.entity.s10s11;

/**
 * s10/s11的字段下标
 */
public interface S10_S11Index {
    String XdrSplit = "\\|";
    int Access_Type = 8;
//    int Interface = 2;
    int SdrType = 12;
    int Imsi = 9;
    int Imei = 10;
    int Msisdn = 11;
    int StartTime = 13;
    int EndTime = 14;
    int SrvStat = 15;
    int XDR_ID = 7;

    int MME_Address = 20;
    int MME_Port = 22;
    int SGW_Address = 21;
    int SGW_Port = 23;
//    int PGW_Address =
    int MME_TEID = 24;
    int SGW_TEID = 25;
//    int MME_GroupID =
//    int MME_Code =
//    int M_TMSI =
    int TAC = 27;
    int ECI = 28;
    int APN = 26;
    int USR_IP4 = 18;
    int USR_IP6 = 19;
    int DL_Buf_Dur = 31;
    int DL_Buf_Sug_PacketCou = 32;
    int EPSBearerNumber = 34;
    int Bearer1ID = 35;
    int Bearer1Type = 36;
    int Bearer1QCI = 37;
    int Bearer1Status = 38;
//    int CC =
//    int CI =
    int Bearer1eNBGTP_TEID = 39;
    int Bearer1SGWGTP_TEID = 40;
//    int Cause =
//    int S11U_MME_TEID =
//    int DL_Buf_Dur =
//    int DL_Buf_Sug_PacketCou =

    int size=39;
}
