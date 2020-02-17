package com.tuoming.entity.s1mme;

import com.tuoming.common.CommonDecode;

public class S1mmeDecode extends CommonDecode {
    private String[] mmeXdr;

    public S1mmeDecode() {
    }

    @Override
    public String getMmeIp() {
        String ip = null;
        if (mmeXdr[MmeIndex.mmeIp].contains(".")) {
            ip = mmeXdr[MmeIndex.mmeIp];
        } else if (mmeXdr[0].contains(".")) {
            ip = mmeXdr[0];
        }
        return ip;
    }

    /**
     * 得到每条xdr中的endtime
     *
     * @return
     */
    public String getEndTime() {
        if (mmeXdr[0].contains(".")) {
            return mmeXdr[MmeIndex.endTime + 1];
        } else {
            return mmeXdr[MmeIndex.endTime];
        }
    }

    public void decode(String[] arr) {
        this.mmeXdr = arr;
    }


    /**
     * 把最后两个子流程中添加的Ip去掉
     *
     * @return
     */
    public String convertXdr() {
        StringBuilder xdr = new StringBuilder();
        if (mmeXdr[0].length() > 8) {
            for (int i = 1; i < mmeXdr.length; i++) {
                if (i == mmeXdr.length - 1) {
                    xdr.append(mmeXdr[i]);
                } else {
                    xdr.append(mmeXdr[i]).append("|");
                }
            }
            return xdr.toString();
        } else {
            for (int i = 0; i < mmeXdr.length; i++) {
                if (i == mmeXdr.length - 1) {
                    xdr.append(mmeXdr[i]);
                } else {
                    xdr.append(mmeXdr[i]).append("|");
                }
            }
            return xdr.toString();
        }
    }

    @Override
    public String toString() {
        return convertXdr();
    }
}
