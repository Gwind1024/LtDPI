package com.tuoming.entity.a_iucs;
/*
* 语音呼叫话单下标Call话单(流程标识1)
    * startTime 95   endTime 96   mscip 84
         8.MSRN
         10.RPhone
         13.Ocalled
         14.CalledNum
* */
public interface CallIndex {
    int msrn_Index = 8;
    int rphone_Index = 10;
    int ocalled_Index = 13;
    int calledNum_Index = 14;
    int startTime_Index = 95;
    int endTime_Index = 96;
    int mscip_Index = 84;
    int roamType_Index = 107;


}
