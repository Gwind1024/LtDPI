package com.tuoming.entity.s1mme.method;


import com.tuoming.entity.s1mme.MmeCommon;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MmeMapCommon implements Serializable {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");


    private String key;
    private Integer preduceType;
    private Long startTime;
    private Long endTime;
    private Long timeOut;
    private MmeCommon mmeCommon;

    public MmeMapCommon pre;
    public MmeMapCommon next;

    public MmeCommon getMmeCommon() {
        return mmeCommon;
    }

    public void setMmeCommon(MmeCommon mmeCommon) {
        this.mmeCommon = mmeCommon;
    }

    public String getKey() {
        return key;
    }

    public Integer getPreduceType() {
        return preduceType;
    }

    public void setPreduceType(Integer preduceType) {
        this.preduceType = preduceType;
    }

    public Long getStartTime() {
        return startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public Long getTimeOut() {
        return timeOut;
    }


    public boolean create(String[] split, MmeCommon mmeCommon) {
        try {
            this.key = mmeCommon.getKey();
            this.preduceType = Integer.parseInt(mmeCommon.getProduceType());
            this.startTime = sdf.parse(mmeCommon.getStartTime().substring(0, 23)).getTime();
            this.endTime = sdf.parse(mmeCommon.getEndTime().substring(0, 23)).getTime();
            this.timeOut = startTime;
            this.mmeCommon = mmeCommon;

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setTimeOut(String startTime) {
        try {
            timeOut = sdf.parse(startTime.substring(0, 23)).getTime();
            return true;
        } catch (ParseException e) {
            return false;
        }
    }

    public static void main(String[] args) throws Exception {

        System.out.println(sdf.parse("20121020121212.128").getTime());
    }


}
