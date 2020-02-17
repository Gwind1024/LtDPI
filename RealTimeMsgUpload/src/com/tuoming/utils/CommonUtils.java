package com.tuoming.utils;

public class CommonUtils {

    public static Integer strToInteger(String str){
        try {
            if(str!=null && str.length()>0){
                return Integer.parseInt(str);
            }
        } catch (Exception x){
            return -1;
        }
        return -1;
    }
    public static Short strToShort(String str){
        try {
            if(str!=null && str.length()>0){
                return Short.parseShort(str);
            }
        } catch (Exception x){
            return -1;
        }
        return -1;
    }
    public static Long strToLong(String str){
        try {
            if(str!=null && str.length()>0){
                return Long.parseLong(str);
            }
        } catch (Exception x){
            return -1l;
        }
        return -1l;
    }
    public static Double strToDouble(String str){
        try {
            if(str!=null && str.length()>0){
                return Double.parseDouble(str);
            }
        }catch (Exception x){
            return -1d;
        }
        return -1d;
    }
    public static String bytesToHexString(byte[] bArr) {
        StringBuffer sb = new StringBuffer(bArr.length);
        String sTmp;
        for (int i = 0; i < bArr.length; i++) {
            sTmp = Integer.toHexString(0xFF & bArr[i]);
            if (sTmp.length() < 2)
                sb.append(0);
            sb.append(sTmp.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 16进制转化为字母
     * @param hex  要转化的16进制数，用逗号隔开
     *         如：53,68,61,64,6f,77
     * @return
     */
    public static String hex2Str(String hex) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<hex.length();i+=2){
            int n = Integer.parseInt(hex.substring(i,i+2), 16);
            sb.append((char)n);
        }
        /*String[] split = hex.split(",");
        for (String str : split) {
            int i = Integer.parseInt(str, 16);
            sb.append((char)i);
        }*/
        return sb.toString();
    }


}
