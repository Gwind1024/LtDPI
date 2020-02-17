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


}
