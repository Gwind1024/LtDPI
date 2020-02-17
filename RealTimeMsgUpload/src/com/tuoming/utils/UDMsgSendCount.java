package com.tuoming.utils;

import java.util.concurrent.atomic.AtomicInteger;

public class UDMsgSendCount {
    //用户面统计
    public AtomicInteger userMsgSum = new AtomicInteger(0);
//    public AtomicInteger msgSendSuccess = new AtomicInteger(0);
//    public AtomicInteger msgSendFail = new AtomicInteger(0);
//    public AtomicInteger msgRecieveCount = new AtomicInteger(0);

    public AtomicInteger onOffMsgSum = new AtomicInteger(0);

    public AtomicInteger callingMsgSum = new AtomicInteger(0);

    public AtomicInteger locationMsgSum = new AtomicInteger(0);

    public AtomicInteger user5gMsgSum = new AtomicInteger(0);

    public AtomicInteger iupsRauSum = new AtomicInteger(0);
    public AtomicInteger iupsLocationSum = new AtomicInteger(0);
    public AtomicInteger mcSwitchSum = new AtomicInteger(0);
    public AtomicInteger mcLocationSum = new AtomicInteger(0);
    public AtomicInteger mmeSum = new AtomicInteger(0);
}
