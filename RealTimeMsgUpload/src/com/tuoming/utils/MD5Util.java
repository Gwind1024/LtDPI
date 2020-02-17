package com.tuoming.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    public static String getMD5(String val)  {
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(val.getBytes());
            byte[] m = md5.digest();//加密
            return getString(m);
        }catch (Exception e){
            System.err.println("MD5加密错误!");
            return "";
        }

    }
    private static String getString(byte[] b) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            int a = b[i];
            if (a < 0)
                a += 256;
            if (a < 16)
                buf.append("0");
            buf.append(Integer.toHexString(a));

        }
        return buf.toString(); //32位
        //return buf.toString().substring(8,24)；  //16位
    }

    public static byte[] getMD5_Byte(String val) {
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(val.getBytes());
            byte[] m = md5.digest();//加密
            return m;
        }catch (Exception e){
            System.err.println("MD5加密错误!");
            return null;
        }
    }

    /*public static byte[] getMD5_16Byte(String val) {
        try{
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(val.getBytes());
            byte[] m = md5.digest();//加密
            return m;
        }catch (Exception e){
            System.err.println("MD5加密错误!");
            return null;
        }
    }*/
}
