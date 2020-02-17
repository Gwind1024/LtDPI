package com.tuoming.common;

public class TypeChange {
    public static void main(String[] args) {

      //  System.out.println(strToHex("2rtygf"));
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
    public static  String strToHex_1(String str){
        if (str!=null && str.length() > 0){

        }
        return "";

    }




    public static String strToHex(String str) {
        try {
            if (str != null && str.length() > 0) {
                return Long.toHexString(Long.parseLong(str));
            }
        } catch (Exception x) {
            return "";
        }
        return "";
    }


    public static String strToHex(Integer str) {
        try {

            return Integer.toHexString(str);

        } catch (Exception x) {
            return "";
        }
    }
    public static String strToOx7f(String str){
        try {
            if (str != null && str.length() > 0) {
                return Integer.toString(Integer.parseInt(str) & 0x7f);
            }
        } catch (Exception x) {
            return "";
        }
        return "";
    }


}
