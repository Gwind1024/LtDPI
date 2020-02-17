package com.tuoming.common;

import com.tuoming.user.UserInfo;
import com.tuoming.utils.RedisUntil;
import com.tuoming.utils.UDMsgSendCount;
import redis.clients.jedis.Jedis;

import java.util.Arrays;
import java.util.Map;
import java.util.Queue;

public class RealTimeMsgAlg {
    //182种重点业务
    private final int[] flowtype = new int[]{304,300,330,308,2000001,412,416,2000002,407,404,631,2000003,2000004,2000005,2000006,2000007
            ,2000008,2000009,2000010,2000011,450,452,464,451,462,2000012,2000013,2000014,2000015,2000016,219,2000017,225,212
            ,209,2000018,2000019,2000020,2000021,2000022,2000023,752,754,757,758,781,780,782,2000024,2000025,761,763,2000026
            ,2000027,762,1500003,611,612,620,614,2000028,2000029,2000030,2000031,621,2000032,2000033,2000034,2000035,2000036
            ,2000037,2000038,2000039,2000040,2000041,2000042,2000043,2000044,2000045,2000046,2000047,2000048,2000049,2000050
            ,2000051,508,2000052,2000053,2000054,2000055,2000056,2000057,2000058,2000059,2000060,352,353,2000061,2000062,2000063
            ,2000064,2000065,2000066,2000067,2000068,658,2000069,2000070,2000071,2000072,2000073,2000074,2000075,2000076,2000077
            ,2000078,2000079,2000080,2000081,2000082,2000083,2000084,2000085,2000086,2000087,2000088,2000089,2000090,2000091
            ,2000092,2000093,2000094,2000095,2000096,2000097,2000098,720,721,723,724,2000099,2000100,2000101,2000102,2000103
            ,2000104,2000105,2000106,2000107,2000108,2000109,253,251,2000110,212,2000111,2000112,2000113,476,465,467,2000114
            ,2000115,2000116,2000117,2000118,2000119,2000120,2000121,2000122,2000123,2000124,2000125,2000126,2000127,2000128
            ,2000129,2000130,2000131,2000132,2000133,2000134};

//    private UserInfo userInfo = new UserInfo();
    private Jedis redis;
    private volatile UDMsgSendCount userSendCount;
    public RealTimeMsgAlg(Jedis redis,UDMsgSendCount userSendCount){
        this.redis = redis;
        this.userSendCount = userSendCount;
    }

    public void analysisMsg(String xdrInfo,UserInfo userInfo,int cdrType,Queue<byte[]> realMsgList){
        switch (cdrType){
            case CdrKind.HTTP :
                //todo http话单出实时情形判断,构建话单,并放入发送队列
                userInfo.Init(xdrInfo);
                if(jugdeImportType(userInfo.FlowType) && (judgeRequest(userInfo.transactionID)
                        || (userInfo.Longitude != -1 && userInfo.Latitude != -1) || "8".equals(userInfo.appType))){
                    realMsgList.add(userInfo.CreateSend());
//                    userSendCount.
                }
                break;
            case CdrKind.GEN :
                //todo Gen话单出实时情形判断,构建话单,并放入发送队列
                userInfo.Init(xdrInfo);
                if(jugdeImportType(userInfo.FlowType) && userInfo.protocolType == 7){
                    String key = "".equals(userInfo.ipServer1)?userInfo.ipServer2:userInfo.ipServer1;
                    String host = RedisUntil.get(redis, key);
                    if(host != null &&  !"".equals(host)){
                        host = "https://"+host;
                        userInfo.URLorHOST = host.length()>128?host.substring(0,128):host;
                    }
                    realMsgList.add(userInfo.CreateSend());
                }
                break;
            case CdrKind.STREAM :
                //todo Streaming话单出实时情形判断,构建话单,并放入发送队列
                userInfo.Init(xdrInfo);
                if(jugdeImportType(userInfo.FlowType)){
                    realMsgList.add(userInfo.CreateSend());
                }
                break;
        }
    }
    //判断当前业务是否为182种重点业务
    private boolean jugdeImportType(int type){
        for(int thisType : flowtype){
            if(thisType == type){
                return true;
            }
        }
        return false;
    }
    //判断是不是请求
    private boolean judgeRequest(int type){
        for(int i=1;i<9;i++){
            if(i == type){
                return true;
            }
        }
        return false;
    }
}
