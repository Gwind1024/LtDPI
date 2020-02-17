package com.tuoming;

import com.tuoming.common.RealTimeMsgAlg;
import com.tuoming.utils.CommonUtils;
import com.tuoming.utils.MD5Util;
import com.tuoming.utils.ParseEvery;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Demo {
    public static void main(String[] args) throws ParseException {
        SimpleDateFormat sdf2 = new SimpleDateFormat("mm");
        long l = System.currentTimeMillis();
        System.out.println(l);
        String format = sdf2.format(new Date(l));
        System.out.println(CommonUtils.strToInteger(format));

    }
}
