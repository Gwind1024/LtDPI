package com.tuoming.count;

public class CountNum {
    //时间
    public String time;
    //时间戳
    public long timeStamp;
    //文件数
    public int fileCount;
    //读入条数
    public int intputNum;
    //处理时间
    public long dealTime;
    //处理速度
    public int dealSpeed;
    //输出条数
    public static int outputNum;
    //回填条数
    public static int backFillNum;
    //未能回填条数
    public static int nobackFillNum;
    //回填率
    public double backFillRatio;


    public void init() {
        outputNum = 0;
        backFillNum = 0;
        nobackFillNum = 0;
    }


    @Override
    public String toString() {
        return "时间:" + time + "\n"
                + "文件数:" + fileCount + "\n"
                + "读入条数:" + intputNum + "\n"
                + "处理时间(ms):" + dealTime + "\n"
                + "处理速度(条/s):" + dealSpeed + "\n"
                + "输出条数:" + outputNum + "\n"
                + "回填条数:" + backFillNum + "\n"
                + "未能回填条数:" + nobackFillNum + "\n"
                + "回填率:" + backFillRatio;

    }

}
