package com.tuoming.writefile;

public class IuPSWrite extends Write {
    public IuPSWrite(String time){
        super(time);
    }

    @Override
    public String getFile(int cycleTime, String networkElement) {
        String result = "ZJ_HZ_MOBILE_CONS_Huawei_CXDR_" + networkElement + "_" + cycleTime + "_" + time + "_" + "IuPS" + "-" + getFileSign() + "_0_2.txt";
        tmpName = result + ".tmp";
        finlName = result;
        return tmpName;
    }
}
