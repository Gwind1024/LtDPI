package com.tuoming.tools;

import com.tuoming.entity.iups.IUPSDecode;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Untils {
    public static byte[] intToByte4Array(int a) {
        return new byte[]{
                (byte) ((a >> 24) & 0xFF),
                (byte) ((a >> 16) & 0xFF),
                (byte) ((a >> 8) & 0xFF),
                (byte) (a & 0xFF)
        };
    }

    public static int count(IUPSDecode iups) {
        int i = 0;
        IUPSDecode tmp = iups;
        while (tmp != null) {
            tmp = tmp.getNextIUPSDecode();
            i++;
        }
        return i;
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

    public static void getMemoryAndThread() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.println("最大堆内存:" + heapMemoryUsage.getMax() / 1024 / 1024 + "M");
        System.out.println("已用堆内存:" + heapMemoryUsage.getUsed() / 1024 / 1024 + "M");
        Set<Thread> threads = Thread.getAllStackTraces().keySet();
        for (Thread t : threads) {
            System.out.println("线程名称:" + t.getName());
        }
        System.out.println("当前存活的线程数:" + threads.size());
    }


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.remove(1);
        System.out.println(list);

    }
}
