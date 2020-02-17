package com.tuoming.entity.iups;

/**
 * Iups口数据获取结束时间与IP的下标
 */
public interface CommonIuPSIndex {
    String XdrSplit = "\\|";
    int IUPS_ENDTIME = 1;
    int SGSN_IP = 13;

    int SORT_TIME = 0;
    int MIN_SIZE = 24;
}
