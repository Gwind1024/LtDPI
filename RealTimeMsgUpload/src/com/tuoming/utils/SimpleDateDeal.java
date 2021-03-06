package com.tuoming.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleDateDeal {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static String getFormatDate(String timeStamp){
        String micro = "000";
        if(timeStamp.length()>13){
            micro = timeStamp.substring(13);
            if(micro.length() > 3){
                micro = micro.substring(0,3);
            }else if(micro.length() < 3){
                micro = rightSupply0(micro,3);
            }
            timeStamp = timeStamp.substring(0,13);
        }
        long lTime = readLong(timeStamp);
        if(lTime == -1) return "";
        return sdf.format(new Date(lTime))+micro;
    }

    public static long String2TimeStamp(String timeStr){
        String[] splitTime = timeStr.split("\\.", -1);
        if(splitTime[1].length()>3){
            timeStr = timeStr.substring(0,splitTime[0].length()+4);
        }else if(splitTime[1].length()<3){
            timeStr = rightSupply0(timeStr,splitTime[0].length()+4);
        }
        long time = 0;
        try {
            Date date = sdf.parse(timeStr);
            time = date.getTime();
        } catch (ParseException e) {
            System.err.println("转换时间错误！");
        }
        return time;
    }

    private static long readLong(String timeStamp){
        if("".equals(timeStamp.trim()) || timeStamp == null){
            return -1;
        }
        long time = -1;
        try{
            time = Long.parseLong(timeStamp);

        }catch (Exception e){
            System.err.println("时间戳转化错误！");
            return -1;
        }
        return time;
    }

    //不够5位前面补0
    public static String leftSupply0(String s, int i)
    {
        if(s.equals("")){
            for(int j=0;j<i;j++){
                s += "0";
             }
            return s;
        }
        return String.format("%0" + i + "d", Integer.parseInt(s));
    }

    //不足n位，后面补0
    public static String rightSupply0(String s, int i)
    {
        return s+ String.format("%1$0"+(i-s.length())+"d",0);
    }

    public static String sum(String s1, String s2)
    {
        try{
            return String.valueOf(Integer.valueOf(s1)+ Integer.valueOf(s2));
        }catch(Exception e){
            System.err.println("数字转换错误！");
            return "";
        }
    }

    public static String diff(String s1, String s2)
    {
        try{
            return String.valueOf(Integer.valueOf(s1)- Integer.valueOf(s2));
        }catch(Exception e){
            System.err.println("数字转换错误！");
            return "";
        }
    }

    public static String product(String s1, String s2)
    {
        try{
            return String.valueOf(Integer.valueOf(s1)* Integer.valueOf(s2));
        }catch(Exception e){
            System.err.println("数字转换错误！");
            return "";
        }
    }

    public static String big(String s1, String s2)
    {
        try{
            if(Integer.valueOf(s1)> Integer.valueOf(s2)) {
                return String.valueOf(Integer.valueOf(s1));
            }
            else{
                return String.valueOf(Integer.valueOf(s2));
            }
        }catch(Exception e){
            System.err.println("数字转换错误！");
            return "";
        }
    }
}
