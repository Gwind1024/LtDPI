package com.tuoming.signalling;

import java.io.File;

public class SignallingDeal {

    public static boolean checkPath(String path) {
        String[] split = path.split("\\|");
        File fileIn = new File(split[0]);
        File fileOut = new File(split[1]);
        if (fileIn.exists() && fileOut.exists()) {
            return true;
        } else {
            return false;
        }
    }


}
