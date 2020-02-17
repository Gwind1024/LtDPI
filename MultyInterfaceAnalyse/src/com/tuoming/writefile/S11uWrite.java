package com.tuoming.writefile;

public class S11uWrite extends Write {

    public S11uWrite(String time) {
        super(time);
    }

    // ZJ_HZ_MOBILE_CONS_Huawei_CXDR_RNC001_1440_20150818000000_S11u-00083_1_2.tar.gz
    public String getFile(int cycleTime, String networkElement) {
        String result = "ZJ_HZ_MOBILE_CONS_Huawei_CXDR_" + networkElement + "_" + cycleTime + "_" + time + "_" + "S11u" + "-" + getFileSign() + "_0_2.txt";
        tmpName = result + ".tmp";
        finlName = result;
        return tmpName;
    }

}
