package com.tuoming.signalling.iups;

public class IupsInfo {
    public static byte[] iupsRauTobytes(String str) {
        String[] split = str.split(IupsRauIndex.splite, -1);
        if (split.length >= IupsRauIndex.size) {
            IupsRauInfo iupsRauInfo = new IupsRauInfo();
            boolean flag = iupsRauInfo.init(split);
            if (flag) {
                return iupsRauInfo.toBytes();
            }
        }
        return null;
    }

    public static byte[] iupsReLocationTobytes(String str) {
        String[] split = str.split(IupsRelocationIndex.splite, -1);
        if (split.length >= IupsRelocationIndex.size) {
            IupsRelocationInfo IupsRelocationInfo = new IupsRelocationInfo();
            boolean flag = IupsRelocationInfo.init(split);
            if (flag) {
                return IupsRelocationInfo.toBytes();
            }
        }
        return null;
    }
}
