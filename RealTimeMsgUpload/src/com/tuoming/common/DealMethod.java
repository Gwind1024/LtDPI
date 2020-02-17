package com.tuoming.common;

import com.tuoming.RTMsgUploadDriver;
import com.tuoming.readfile.SigReadFile;
import com.tuoming.utils.ConfigArgs;

import java.util.List;
import java.util.Map;

public class DealMethod {

    public static void setManyou(ConfigArgs configArgs, Map<String, Integer> manyou) {
        SigReadFile sig = new SigReadFile();
        sig.read(configArgs.jiangsuDir);
        List<String> js = sig.getFileBuffer();
        for (String str : js) {
            manyou.put(str, 1);
        }
        sig.clearFileBuffer();
        sig.read(configArgs.nanjingDir);
        List<String> nj = sig.getFileBuffer();
        for (String str : nj) {
            manyou.put(str, 0);
        }
    }


    public static Integer setRoamType(String msisdn) {
        if (!msisdn.startsWith("460")) {
            return 4;
        } else {
            String substring = msisdn.substring(0, 7);
            Integer flag = RTMsgUploadDriver.manyou.get(substring);
            if (flag == null) {
                return 3;
            } else if (flag == 1) {
                return 2;
            } else {
                return 1;
            }
        }
    }
}
