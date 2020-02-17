package com.tuoming.tools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
    public static String leftSupply0(String s,int i)
    {
        if(s.equals("")) return "00000";
        return String.format("%0" + i + "d", Integer.parseInt(s));
    }

    //不足n位，后面补0
    public static String rightSupply0(String s,int i)
    {
        return s+String.format("%1$0"+(i-s.length())+"d",0);
    }

    public static String sum(String s1,String s2)
    {
        if(s1.equals(""))s1="0";
        if(s2.equals(""))s2="0";
        try{
            return String.valueOf(Long.valueOf(s1)+Long.valueOf(s2));
        }catch(Exception e){
            System.err.println("数字转换错误！s1="+s1+";s2="+s2);
            return "";
        }
    }

    public static String diff(String s1,String s2)
    {
        if(s1.equals(""))s1="0";
        if(s2.equals(""))s2="0";
        try{
            return String.valueOf( new Double(Double.valueOf(s1)-Double.valueOf(s2)).intValue());
        }catch(Exception e){
            System.err.println("数字转换错误！s1="+s1+";s2="+s2);
            return "";
        }
    }

    public static String product(String s1,String s2)
    {
        if(s1.equals(""))s1="0";
        if(s2.equals(""))s2="0";
        try{
            return String.valueOf(Integer.valueOf(s1)*Integer.valueOf(s2));
        }catch(Exception e){
            System.err.println("数字转换错误！s1="+s1+";s2="+s2);
            return "";
        }
    }

    public static String divide(String s1,String s2)
    {
        if(s1.equals(""))s1="0";
        if(s2.equals(""))s2="0";
        try{
            if(s1.equals("") || s2.equals("0") ) return "0";
            return String.valueOf(Integer.valueOf(s1)/Integer.valueOf(s2));
        }catch(Exception e){
            System.err.println("数字转换错误！s1="+s1+";s2="+s2);
            return "0";
        }
    }

    public static String big(String s1,String s2)
    {
        if(s1.equals(""))s1="0";
        if(s2.equals(""))s2="0";
        try{
            if(Long.valueOf(s1)>Long.valueOf(s2)) {
                return s1;
            }
            else{
                return s2;
            }
        }catch(Exception e){
            System.err.println("数字转换错误！s1="+s1+";s2="+s2);
            return "";
        }
    }

    public static String small(String s1,String s2)
    {
        if(s1.equals(""))s1="0";
        if(s2.equals(""))s2="0";
        try{
            if(Integer.valueOf(s1)<Integer.valueOf(s2)) {
                return String.valueOf(Integer.valueOf(s1));
            }
            else{
                return String.valueOf(Integer.valueOf(s2));
            }
        }catch(Exception e){
            System.err.println("数字转换错误！s1="+s1+";s2="+s2);
            return "";
        }
    }

    public static String rand(int i1,int i2)
    {
        ThreadLocalRandom rand = ThreadLocalRandom.current();
        int r1 = rand.nextInt(i1,i2);
        return String.valueOf(r1);
    }


    public static String Except86(String Temp)
    {
        String ret = "";
        if (Temp == null) {
            ret = "";
        } else if (Temp.startsWith("+86")) {
            ret = Temp.substring(3);
        } else if (Temp.startsWith("86")) {
            ret = Temp.substring(2);
        } else if (Temp.startsWith("0086")) {
            ret = Temp.substring(4);
        } else {
            ret = Temp;
        }
        return ret;
    }
}
