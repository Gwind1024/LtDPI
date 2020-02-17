package com.tuoming;

import com.tuoming.tools.CommonUtils;
import com.tuoming.tools.RadixDeal;
import com.tuoming.tools.SimpleDateDeal;

import java.text.SimpleDateFormat;
import java.util.*;

public class Demo {
    static String[] line = new String[]{"XYF","JYJ","JGM","SKD","SXMG","SX"};
    public static void main(String[] args) {
        Random random = new Random();
        System.out.println(line[random.nextInt(100)%6]);
    }
}
