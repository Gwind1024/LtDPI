package com.tuoming.writefile;

public class IuPSRelationWrite extends Write {
    @Override
    public String getFile(int cycleTime, String networkElement) {
        return null;
    }

    public String getFile() {
        finlName="IUPS_"+getTime()+"_"+fileSignCount + ".csv";
        tmpName=finlName+".tmp";
        return tmpName;
    }
}
