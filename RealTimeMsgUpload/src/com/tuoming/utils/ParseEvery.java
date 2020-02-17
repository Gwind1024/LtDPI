package com.tuoming.utils;

import org.apache.log4j.Logger;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.nio.ByteBuffer;

public class ParseEvery {

    private static ByteBuffer buffer = ByteBuffer.allocate(8);
    //byte 数组与 long 的相互转换
    public static byte[] longToBytes(long x) {
        buffer.putLong(0, x);
        return buffer.array();
    }

    public static long bytesToLong(byte[] bytes) {
        buffer.put(bytes, 0, bytes.length);
        buffer.flip();//need flip
        return buffer.getLong();
    }

    /**
     * double To byte[]
     * @param d
     * @return
     */
    public static byte[] double2Bytes(double d) {
        long value = Double.doubleToRawLongBits(d);
        byte[] byteRet = new byte[8];
        for (int i = 0; i < 8; i++) {
            byteRet[i] = (byte) ((value >> 8 * i) & 0xff);
        }
        return byteRet;
    }
    /**
     * byte[] To double
     * @param arr
     * @return
     */
    public static double bytes2Double(byte[] arr) {
        long value = 0;
        for (int i = 0; i < 8; i++) {
            value |= ((long) (arr[i] & 0xff)) << (8 * i);
        }
        return Double.longBitsToDouble(value);
    }

    /**
     * 将String转为byte数组
     */
    public static byte[] stringToBytes(String s, int length) {
        while (s.getBytes().length < length) {
            s += " ";
        }
        return s.getBytes();
    }

    // 以下 是整型数 和 网络字节序的  byte[] 数组之间的转换
    public static byte[] longToBytes2(long n) {
        byte[] b = new byte[8];
        b[7] = (byte) (n & 0xff);
        b[6] = (byte) (n >> 8  & 0xff);
        b[5] = (byte) (n >> 16 & 0xff);
        b[4] = (byte) (n >> 24 & 0xff);
        b[3] = (byte) (n >> 32 & 0xff);
        b[2] = (byte) (n >> 40 & 0xff);
        b[1] = (byte) (n >> 48 & 0xff);
        b[0] = (byte) (n >> 56 & 0xff);
        return b;
    }

    public static void longToBytes2( long n, byte[] array, int offset ){
        array[7+offset] = (byte) (n & 0xff);
        array[6+offset] = (byte) (n >> 8 & 0xff);
        array[5+offset] = (byte) (n >> 16 & 0xff);
        array[4+offset] = (byte) (n >> 24 & 0xff);
        array[3+offset] = (byte) (n >> 32 & 0xff);
        array[2+offset] = (byte) (n >> 40 & 0xff);
        array[1+offset] = (byte) (n >> 48 & 0xff);
        array[0+offset] = (byte) (n >> 56 & 0xff);
    }

    public static long bytesToLong2( byte[] array )
    {
        return ((((long) array[ 0] & 0xff) << 56)
                | (((long) array[ 1] & 0xff) << 48)
                | (((long) array[ 2] & 0xff) << 40)
                | (((long) array[ 3] & 0xff) << 32)
                | (((long) array[ 4] & 0xff) << 24)
                | (((long) array[ 5] & 0xff) << 16)
                | (((long) array[ 6] & 0xff) << 8)
                | (((long) array[ 7] & 0xff) << 0));
    }

    public static long bytesToLong2( byte[] array, int offset )
    {
        return ((((long) array[offset + 0] & 0xff) << 56)
                | (((long) array[offset + 1] & 0xff) << 48)
                | (((long) array[offset + 2] & 0xff) << 40)
                | (((long) array[offset + 3] & 0xff) << 32)
                | (((long) array[offset + 4] & 0xff) << 24)
                | (((long) array[offset + 5] & 0xff) << 16)
                | (((long) array[offset + 6] & 0xff) << 8)
                | (((long) array[offset + 7] & 0xff) << 0));
    }

    public static byte[] intToByte4Array(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    public static byte[] intToByte3Array(int a) {
        return new byte[]{
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a  & 0xFF)
        };
    }


    public static byte[] shortToByteArr(short s) {
        byte[] targets = new byte[2];
        for (int i = 0; i < 2; i++) {
            int offset = (targets.length - 1 - i) * 8;
            targets[i] = (byte) ((s >>> offset) & 0xff);
        }
        return targets;
    }

    public static byte intToByte(int a) {
        return (byte) (a & 0xFF);
    }




    public static short byteArrToShort(byte[] b) {
        return byteArrToShort(b, 0);
    }

    public static short byteArrToShort(byte[] b, int index) {
        return (short) (((b[index + 0] << 8) | b[index + 1] & 0xff));
    }


    public static int byte4ArrayToInt(byte[] b, int index) {
        return b[index + 3] & 0xFF |
                (b[index + 2] & 0xFF) << 8 |
                (b[index + 1] & 0xFF) << 16 |
                (b[index + 0] & 0xFF) << 24;
    }

    public static int byteToInt(byte b) {
        return b & 0xFF;
    }

    public static String bytesToHexString(byte[] bArr) {
        StringBuffer sb = new StringBuffer(bArr.length);
        String sTmp;
        for (int i = 0; i < bArr.length; i++) {
            sTmp = Integer.toHexString(0xFF & bArr[i]);
            if (sTmp.length() < 2)
                sb.append(0);
            sb.append(sTmp.toUpperCase());
        }
        return sb.toString();
    }


    public static String intToHexStr(int i) {
        String s = Integer.toHexString(i);
        if (s.length() == 1) {
            return "0x0" + s + " ";
        } else {
            return "0x" + s + " ";
        }
    }

    public static String intToHexStrDelete0(int i) {
        String s = Integer.toHexString(i);
        if (s.length() == 1) {
            return "0" + s;
        } else {
            return s;
        }
    }

    public static void getMemory(Logger logger){
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        logger.debug("最大堆内存:"+heapMemoryUsage.getMax()/1024/1024+"M");
        logger.debug("已用堆内存:"+heapMemoryUsage.getUsed()/1024/1024+"M");
    }

    public static void main(String[] args) {
        long time = 1530052000254l;
        System.out.println(bytesToLong(longToBytes(time)));
        byte[] bytes = longToBytes(time);
        byte[] bytes1 = longToBytes2(time);
        int i = 412;
        double d = 25.25;
        String s = "skkd00";
        System.out.println(bytesToHexString(bytes));
        System.out.println(bytesToHexString(bytes1));
        System.out.println(bytesToHexString(intToByte3Array(i)));
        System.out.println(bytesToHexString(double2Bytes(d)));
        System.out.println(bytes2Double(double2Bytes(d)));
        System.out.println(bytesToHexString(s.getBytes()));
        System.out.println();
    }
}
