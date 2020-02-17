package com.tuoming.writefile;

public class MmeRelationWrite extends Write {
    @Override
    public String getFile(int cycleTime, String networkElement) {
        return null;
    }

    public String getFile() {
        finlName="MME_"+getTime()+"_"+fileSignCount + ".csv";
        tmpName=finlName+".tmp";
        return tmpName;
    }

}
