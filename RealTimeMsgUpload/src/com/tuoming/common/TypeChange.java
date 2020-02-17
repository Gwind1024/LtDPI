package com.tuoming.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TypeChange {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static void main(String[] args) {
        System.out.println(add0("12312321"));
    }

    public static String add0(String str){
        if(str==null || str.length()>8){
            return null;
        }
        String result=str;
        int s=8-str.length();
        for(int i=0;i<s;i++){
            result="0"+result;
        }
        return result;
    }

    public static String getAfter5(String str){
        if(str.length()>=5){
            return str.substring(str.length()-5,str.length());
        }else {
            return null;
        }
    }


    public static Long timeChange(String str) {
        Date parse = null;
        try {
            parse = sdf.parse(str);
        } catch (ParseException e) {
            return null;
        }
        return parse.getTime();
    }

    public static Integer strToInteger(String str) {
        try {
            if (str != null && str.length() > 0) {
                return Integer.parseInt(str);
            }
        } catch (Exception x) {
            return null;
        }
        return null;
    }

    public static Short strToShort(String str) {
        try {
            if (str != null && str.length() > 0) {
                return Short.parseShort(str);
            }
        } catch (Exception x) {
            return null;
        }
        return null;
    }

    public static Long strToLong(String str) {
        try {
            if (str != null && str.length() > 0) {
                return Long.parseLong(str);
            }
        } catch (Exception x) {
            return null;
        }
        return null;
    }

    public static Double strToDouble(String str) {
        try {
            if (str != null && str.length() > 0) {
                return Double.parseDouble(str);
            }
        } catch (Exception x) {
            return null;
        }
        return null;
    }
}
