package com.tuoming.writefile;

public class S1ugameWrite extends Write {

    public S1ugameWrite(String time) {
        super(time);
    }

    // ZJ_HZ_MOBILE_CONS_Huawei_CXDR_RNC001_1440_20150818000000_S1ugame-00083_1_2.tar.gz
    public String getFile(int cycleTime, String networkElement) {
        String result = "ZJ_HZ_MOBILE_CONS_Huawei_CXDR_" + networkElement + "_" + cycleTime + "_" + time + "_" + "S1ugame" + "-" + getFileSign() + "_0_2.txt";
        tmpName = result + ".tmp";
        finlName = result;
        return tmpName;
    }

}
