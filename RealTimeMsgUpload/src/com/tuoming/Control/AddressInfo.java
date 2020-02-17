package com.tuoming.Control;

public class AddressInfo {
    private int Version = 412;            //3字节
    private long Time = 0l;             //8字节,毫秒数，即将标准unix时间转成毫秒，网络字节序
    private String MSISDN = "";          //16/20字节,TBCD编码
    private String IMEI = "";            //16/20字节,TBCD编码
    private String IMSI = "";           //16/20字节,TBCD编码
    private int RAT = -1;                //1字节,承载网络：1=3G, 2=2G,6=4G,9=5G
    private int LACorTAC = -1;           //2字节,2/3G对应lac，4G对应Tac，网络字节序
    private int CIorECI = -1;            //4字节,2/3G对应ci/sac，4G对应eci，网络字节序
    private int ServiceType = -1;        //1字节,0：开机 1：关机
    private String MSCorVLR_GT = "";     //10字节,更新后的MSC GT，没有填FF

    private int RoamType = 0;            //1字节,见A/IuCS接口话单中的“RoamType”,1=本地用户,2=省内漫游,3=国内漫入,4=国际漫入,5=国内漫出,6=国际漫出,无法判断漫游情况则填0
    private int ProvinceID = -1;         //3字节,采集省份编码
    private int UENC = -1;                 //1字节,1.填写第九个字节包含DCNR的UE network capability的16进制形式 2.信令信息中没有填写全F
    private String gNBIP = "";           //4字节,16进制IP地址

    public AddressInfo(){

    }

    public byte[] CreateSend(){
        byte[] bytes = new byte[1024];
        return bytes;
    }
}
