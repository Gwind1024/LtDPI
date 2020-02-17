package com.tuoming.tools;

import com.tuoming.entity.s1mme.AttachIndex;

public class CommonUtils {


    public static Integer strToInteger(String str){
        try {
            if(str!=null && str.length()>0){
                return Integer.parseInt(str);
            }
        } catch (Exception x){
            return 0;
        }
        return 0;
    }
    public static Long strToLong(String str){
        try {
            if(str!=null && str.length()>0){
                return Long.parseLong(str);
            }
        } catch (Exception x){
            return -1L;
        }
        return null;
    }
    public static Double strToDouble(String str){
        try {
            if(str!=null && str.length()>0){
                return Double.parseDouble(str);
            }
        }catch (Exception x){
            return -1d;
        }
        return null;
    }


    /**
     * 判断取对应字段下标的方法
     * @param str
     * @param index
     * @return
     */
    public static int convertIndex(String[] str,int index){
        if("0".equals(str[AttachIndex.erab_num])){
            return index;
        }else {
            if(index>37){
                return strToInteger(str[AttachIndex.erab_num]) * 8 + index;
            }else{
                return index;
            }

        }
    }
    /**
     * 判断取对应字段下标的方法
     * @param str
     * @param index
     * @return
     */
    public static Integer numIndex(String[] str,int index){
        if(CommonUtils.strToInteger(str[AttachIndex.erab_num])>0){
            return index;
        }
        return null;
    }


    /**
     * int转byte[]
     * 该方法将一个int类型的数据转换为byte[]形式，因为int为32bit，而byte为8bit所以在进行类型转换时，只会获取低8位，
     * 丢弃高24位。通过位移的方式，将32bit的数据转换成4个8bit的数据。
     * 将想要获取的8位数据截取出来。
     * @param i 一个int数字
     * @return byte[]
     */
    public static byte[] int2ByteArray(int i){
        byte[] result=new byte[4];
        result[0]=(byte)((i >> 24)& 0xFF);
        result[1]=(byte)((i >> 16)& 0xFF);
        result[2]=(byte)((i >> 8)& 0xFF);
        result[3]=(byte)(i & 0xFF);
        return result;
    }
     /**
      * byte[]转int
      * 利用int2ByteArray方法，将一个int转为byte[]，但在解析时，需要将数据还原。同样使用移位的方式，将适当的位数进行还原，
      * 0xFF为16进制的数据，所以在其后每加上一位，就相当于二进制加上4位。同时，使用|=号拼接数据，将其还原成最终的int数据
      * @param bytes byte类型数组
      * @return int数字
      */
    public static int bytes2Int(byte[] bytes){
        int num=bytes[3] & 0xFF;
        num |=((bytes[2] <<8)& 0xFF00);
        num |=((bytes[1] <<16)& 0xFF0000);
        num |=((bytes[0] <<24)& 0xFF0000);
        return num;
    }


    /**
     * 将byte转换为一个长度为8的byte数组，数组每个值代表bit
     */
    public static byte[] getBooleanArray(byte b) {
        byte[] array = new byte[8];
        for (int i = 7; i >= 0; i--) {
            array[i] = (byte)(b & 1);
            b = (byte) (b >> 1);
        }
        return array;
    }

    /**
     * 将前五个bit位置0
     * @param b
     * @return
     */
    public static byte[] setByteArray(byte[] b){
        for(int i = 0; i < 5; i++){
            b[i] = 0;
        }
        return b;
    }

    /**
     * 把byte转为字符串的bit
     */
    public static String byteToBit(byte b) {
        return ""
                + (byte) ((b >> 7) & 0x1) + (byte) ((b >> 6) & 0x1)
                + (byte) ((b >> 5) & 0x1) + (byte) ((b >> 4) & 0x1)
                + (byte) ((b >> 3) & 0x1) + (byte) ((b >> 2) & 0x1)
                + (byte) ((b >> 1) & 0x1) + (byte) ((b >> 0) & 0x1);
    }



}
