package com.tuoming.entity.a_iucs;
/*
* sms话单下标SMS话单(流程标识4)            startTime  61     endTime  62   mscip 50
         3.Rphone
         7.SMC_GT
         26.SMC_Addr
         69.Phone
* */
public interface SmsIndex {
    int rphone_Index = 3;
    int smc_GT_Index = 7;
    int smc_Addr_Index = 26;
    int mscip_Index = 50;
    int startTime_Index = 61;
    int endTime_Index = 62;
    int Phone_Index = 69;
    int roamType_Index = 73;
}
