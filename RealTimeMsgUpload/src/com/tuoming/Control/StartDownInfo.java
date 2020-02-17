package com.tuoming.Control;

public class StartDownInfo {
    private int Version = 412;            //3字节
    private long Time = 0l;             //8字节,毫秒数，即将标准unix时间转成毫秒，网络字节序
    private String MSISDN = "";          //16/20字节,TBCD编码
    private String IMEI = "";            //16/20字节,TBCD编码
    private String IMSI = "";           //16/20字节,TBCD编码
    private int RAT = -1;                //1字节,承载网络：1=3G, 2=2G,6=4G,9=5G
    private int LACorTAC = -1;           //2字节,2/3G对应lac，4G对应Tac，网络字节序
    private int CIorECI = -1;            //4字节,2/3G对应ci/sac，4G对应eci，网络字节序
    private int ServiceType = -1;        //1字节,0：开机 1：关机
    private int ProvinceID = -1;         //3字节,采集省份编码
    private int UENC = -1;               //1字节,1.填写第九个字节包含DCNR的UE network capability的16进制形式 2.信令信息中没有填写全F

    private String gNBIP = "";           //4字节,16进制IP地址

    public StartDownInfo(){

    }
    public byte[] CreateSend(){
        byte[] bytes = new byte[1024];
        return bytes;
    }
}
