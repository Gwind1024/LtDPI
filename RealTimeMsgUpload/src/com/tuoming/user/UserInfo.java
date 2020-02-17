package com.tuoming.user;

import com.tuoming.common.CdrKind;
import com.tuoming.utils.CommonUtils;
import com.tuoming.utils.MD5Util;
import com.tuoming.utils.ParseEvery;
import com.tuoming.utils.TBCDUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class UserInfo {
    public String Version = "412";            //3字节
    public long Time = -1l;             //8字节,毫秒数，即将标准unix时间转成毫秒，网络字节序
    public String MSISDN = "";          //16/20字节,TBCD编码
    public String IMEI = "";            //16/20字节,TBCD编码
    public String IMSI = "";           //16/20字节,TBCD编码
    public int RAT = -1;                //1字节,承载网络：1=3G, 2=2G,6=4G,9=5G
    public short LACorTAC = -1;            //2字节,2/3G对应lac，4G对应Tac，网络字节序
    public int CIorECI = -1;            //4字节,2/3G对应ci/sac，4G对应eci，网络字节序
    public String APN = "";            //16字节,超过16字节则截断为16字节
    public String URLorHOST = "";      //128字节,超过128个字节则截断为128字节
    public double Longitude = -1d;     //8字节,经度，双精度浮点，网络字节序，默认值全FF，统一转换为GPS坐标系
    public double Latitude = -1d;      //8字节,纬度，双精度浮点，网络字节序，默认值全FF，统一转换为GPS坐标系
    public Integer FlowType = -1;           //3字节,流量类型编码，网络字节序
    public int CoordinateSystem = 0;   //1字节,应用使用的坐标系,1:百度坐标系,2:Google地球坐标系,3:墨卡托坐标系,4:高德坐标系,0:其他坐标系/未知
    public String ProvinceID = "130";         //3字节,采集省份编码,默认130 江苏
    public String gNBIP = "";           //4字节,16进制IP地址

    public int transactionID = -1;      //事物类型

    public int protocolType = -1;       //协议类型

    public String appType = "";            //应用大类
    public String appSubType = "";         //应用小类

    public String ipServer1 = "";           //ip地址1
    public String ipServer2 = "";           //ip地址2

    public Map<String,Integer> flowMap;

    private byte[] ipByte =  new byte[4];
    public UserInfo(){

    }

    public abstract void Init(String str);


    public byte[] CreateSend(){
        byte[] bytes = new byte[249];
        byte[] bytes1 = Version.getBytes();
        bytes[0] = bytes1[0];
        bytes[1] = bytes1[1];
        bytes[2] = bytes1[2];
        //todo 加入每个值的空值判断和填充值
        if(Time == -1){
            for(int i = 0;i<8;i++){
                bytes[i+3] = -1;
            }
        }else {
            byte[] bytes2 = ParseEvery.longToBytes2(Time);
            for(int i = 0;i<8;i++){
                bytes[i+3] = bytes2[i];
            }
        }

        if(MSISDN.length() < 11){
            for(int i = 0;i<20;i++){
                bytes[i+11] = -1;
            }
        }else{
            String msisdnPre = MSISDN.substring(0, 7);
            byte[] bytes3 = TBCDUtil.parseTBCD(msisdnPre);
            for(int i=0;i<4;i++){
                bytes[i+11] = bytes3[i];
            }
            String msisdnTail = MSISDN.substring(7);
            byte[] md5_msisdnByte = MD5Util.getMD5_Byte(msisdnTail);
            for(int i=0;i<16;i++){
                bytes[i+15] = md5_msisdnByte[i];
            }
        }

        if(IMEI.length() < 15){
            for(int i = 0;i<20;i++){
                bytes[i+31] = -1;
            }
        }else{
            String imeiPre = IMEI.substring(0, 8);
            byte[] bytes4 = TBCDUtil.parseTBCD(imeiPre);
            for(int i=0;i<4;i++){
                bytes[i+31] = bytes4[i];
            }
            String imeiTail = IMEI.substring(8);
            byte[] md5_ImeiByte = MD5Util.getMD5_Byte(imeiTail);
            for(int i=0;i<16;i++){
                bytes[i+35] = md5_ImeiByte[i];
            }
        }

        if(IMSI.length() < 15){
            for(int i = 0;i<20;i++){
                bytes[i+51] = -1;
            }
        }else{
            String imsiPre = IMSI.substring(0, 8);
            byte[] bytes5 = TBCDUtil.parseTBCD(imsiPre);
            for(int i=0;i<4;i++){
                bytes[i+51] = bytes5[i];
            }
            String imsiTail = IMSI.substring(8);
            byte[] md5_imsiByte = MD5Util.getMD5_Byte(imsiTail);
            for(int i=0;i<16;i++){
                bytes[i+55] = md5_imsiByte[i];
            }
        }

        bytes[71] = ParseEvery.intToByte(RAT);
        if(LACorTAC == -1){
            bytes[72] = -1;
            bytes[73] = -1;
        }else{
            byte[] bytes6 = ParseEvery.shortToByteArr(LACorTAC);
            bytes[72] = bytes6[0];
            bytes[73] = bytes6[1];
        }

        if(CIorECI == -1){
            for(int i=0;i<4;i++){
                bytes[74+i] = -1;
            }
        }else{
            byte[] bytes7 = ParseEvery.intToByte4Array(CIorECI);
            for(int i=0;i<4;i++){
                bytes[74+i] = bytes7[i];
            }
        }

        int len = APN.length();
        byte[] bytes8 = APN.getBytes();
        for(int i=0;i<len;i++){
            bytes[78+i] = bytes8[i];
        }
        for(int i=len;i<16;i++){
            bytes[78+i] = -1;
        }

        int len2 = URLorHOST.length();
        byte[] bytes9 = URLorHOST.getBytes();
        for(int i=0;i<len2;i++){
            bytes[94+i] = bytes9[i];
        }
        for(int i=len2;i<128;i++){
            bytes[94+i] = -1;
        }

        //todo 经纬度坐标系问题
        if(Longitude == -1){
            for(int i=0;i<8;i++){
                bytes[222+i] = -1;
            }
        }else{
            byte[] bytes13 = ParseEvery.double2Bytes(Longitude);
            for(int i=0;i<8;i++){
                bytes[222+i] = bytes13[i];
            }
        }

        if(Latitude == -1){
            for(int i=0;i<8;i++){
                bytes[230+i] = -1;
            }
        }else{
            byte[] bytes14 = ParseEvery.double2Bytes(Latitude);
            for(int i=0;i<8;i++){
                bytes[230+i] = bytes14[i];
            }
        }

        if(FlowType == -1 || FlowType == null ){
            for(int i=0;i<3;i++){
                bytes[238+i] = -1;
            }
        }else{
            byte[] bytes10 = ParseEvery.intToByte3Array(FlowType);
            for(int i=0;i<3;i++){
                bytes[238+i] = bytes10[i];
            }
        }
        bytes[241] = ParseEvery.intToByte(CoordinateSystem);
        byte[] bytes11 = ProvinceID.getBytes();
        for(int i=0;i<3;i++){
            bytes[242+i] = bytes11[i];
        }
        parseIP(gNBIP);

        bytes[245] = ipByte[0];
        bytes[246] = ipByte[1];
        bytes[247] = ipByte[2];
        bytes[248] = ipByte[3];

        return bytes;
    }

    private void parseIP(String ipStr){
        if(ipStr == null){
            Arrays.fill(ipByte,(byte)-1);
            return;
        }
        String[] ipArr = ipStr.split("\\.", -1);
        if(ipArr.length < 4){
            System.err.println("IP类型错误！");
            Arrays.fill(ipByte,(byte)-1);
            return;
        }
        Integer integer = CommonUtils.strToInteger(ipArr[0]);
        ipByte[0] = ParseEvery.intToByte(integer);
        Integer integer1 = CommonUtils.strToInteger(ipArr[1]);
        ipByte[1] = ParseEvery.intToByte(integer1);
        Integer integer2 = CommonUtils.strToInteger(ipArr[2]);
        ipByte[2] = ParseEvery.intToByte(integer2);
        Integer integer3 = CommonUtils.strToInteger(ipArr[3]);
        ipByte[3] = ParseEvery.intToByte(integer3);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("UserInfo{");
        sb.append("Version='").append(Version).append('\'');
        sb.append(", Time=").append(Time);
        sb.append(", MSISDN='").append(MSISDN).append('\'');
        sb.append(", IMEI='").append(IMEI).append('\'');
        sb.append(", IMSI='").append(IMSI).append('\'');
        sb.append(", RAT=").append(RAT);
        sb.append(", LACorTAC=").append(LACorTAC);
        sb.append(", CIorECI=").append(CIorECI);
        sb.append(", APN='").append(APN).append('\'');
        sb.append(", URLorHOST='").append(URLorHOST).append('\'');
        sb.append(", Longitude=").append(Longitude);
        sb.append(", Latitude=").append(Latitude);
        sb.append(", FlowType=").append(FlowType);
        sb.append(", CoordinateSystem=").append(CoordinateSystem);
        sb.append(", ProvinceID='").append(ProvinceID).append('\'');
        sb.append(", gNBIP='").append(gNBIP).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
