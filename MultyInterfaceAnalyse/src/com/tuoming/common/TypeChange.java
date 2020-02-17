package com.tuoming.common;

public class TypeChange {
    public static void main(String[] args) {
        System.out.println(strToDouble("23"));
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
}
