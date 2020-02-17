package com.tuoming.signalling.mme;

public class MmeIndex {
    public static final int produceType = 8;
    public static final int keyword = 14;
    public static final int mmeBearerN=37;
    public static final int size = 101;
    public static final String splite = "\\|";

    public static int getMmeIndex(int index, int n) {
        if (index > 37) {
            return index + n * 8;
        } else {
            return index;
        }
    }
}
