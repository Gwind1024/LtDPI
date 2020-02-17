package com.tuoming.tools;

/**
 * 用于IP进制转化的工具类
 */
public class RadixDeal {
    /*
     * ip字符串转网络字节序10进制字符串
     */
    public static String IP2Decimal(String ipString){
        String[] splits = ipString.split("\\.", -1);
        if(splits.length != 4){
            return "";
        }
        long result = 0l;
        for(int i=0;i<splits.length;i++){
            result= result | (Long.parseLong(splits[i]) << 8*i);
        }
        return ""+result;
    }

    /*
     * ip字符串转主机字节序10进制字符串
     */
    public static String IP2Decimal2(String ipString){
        String[] splits = ipString.split("\\.", -1);
        if(splits.length != 4){
            return "";
        }
        long result = 0l;//生成的主机字节序
        for(int i=0;i<splits.length;i++){
            result= result | (Long.parseLong(splits[i]) << 8*(3-i));
        }
        return ""+result;
    }

    /*
     * 10进制字符串转ip字符串
     */
    public static String Decimal2IP(String decimalString){
        Long decimalIP = CommonUtils.strToLong(decimalString);
        if(decimalIP == null || decimalIP == -1){
            return  "";
        }
        String hexString = Long.toHexString(decimalIP);
        int len = hexString.length();
        for(int i=0;i<8-len;i++){
            hexString = "0"+hexString;
        }
        String sub1 = parse2Decimal(hexString,0);
        String sub2 = parse2Decimal(hexString,2);
        String sub3 = parse2Decimal(hexString,4);
        String sub4 = parse2Decimal(hexString,6);

        return sub4+"."+sub3+"."+sub2+"."+sub1;
    }

    private static String parse2Decimal(String hexString,int index){
        String sub = hexString.substring(index, index+2);
        return Long.valueOf(sub, 16).toString();
    }

    private static String parse2Hex(String ips){
        String ipDecimal = "";
        long ip = Long.parseLong(ips);
        if(ip < 16){
            ipDecimal = "0" + Long.toHexString(ip);
        }else{
            ipDecimal = Long.toHexString(ip);
        }
        return ipDecimal;
    }
}
