package com.tuoming.tools;

public class CommonUtils {

    public static Integer strToInteger(String str){
        try {
            if(str!=null && str.length()>0){
                return Integer.parseInt(str);
            }
        } catch (Exception x){
            return -1;
        }
        return null;
    }
    public static Long strToLong(String str){
        try {
            if(str!=null && str.length()>0){
                return Long.parseLong(str);
            }
        } catch (Exception x){
            return -1l;
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


}
