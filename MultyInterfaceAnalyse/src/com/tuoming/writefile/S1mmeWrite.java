package com.tuoming.writefile;

public class S1mmeWrite extends Write {

    public S1mmeWrite(String time){super(time);}

    @Override
    public String getFile(int cycleTime, String networkElement) {
        String result = "ZJ_HZ_MOBILE_CONS_Huawei_CXDR_" + networkElement + "_" + cycleTime + "_" + time + "_" + "S1MME" + "-" + getFileSign() + "_0_2.txt";
        tmpName = result + ".tmp";
        finlName = result;
        return tmpName;
    }

}
