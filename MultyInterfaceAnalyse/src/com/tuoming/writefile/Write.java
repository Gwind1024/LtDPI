package com.tuoming.writefile;

import java.io.Serializable;

public abstract class Write implements Serializable {
    protected String time;
    protected Integer fileSignCount = 0;
    protected WriteFile wf;

    protected String tmpName;
    protected String finlName;

    public String getTmpName() {
        return tmpName;
    }

    public String getFinlName() {
        return finlName;
    }

    public Write(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getFileSignCount() {
        return fileSignCount;
    }

    public void setFileSignCount(Integer fileSignCount) {
        this.fileSignCount = fileSignCount;
    }

    public WriteFile getWf() {
        return wf;
    }

    public void setWf(WriteFile wf) {
        this.wf = wf;
    }


    public abstract String getFile(int cycleTime, String networkElement);


    protected String getFileSign() {
        String result = "";
        int i = 0;
        if (fileSignCount > 99999) {
            fileSignCount = 0;
        }
        int l = fileSignCount.toString().length();
        while (i < 5 - l) {
            result += "0";
            i++;
        }
        return result + fileSignCount;
    }
}
