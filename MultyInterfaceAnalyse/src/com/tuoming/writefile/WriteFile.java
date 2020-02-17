package com.tuoming.writefile;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    private int MAX_COUNT;
    //字节计数器
    private int count = 0;
    private FileWriter fw;
    private BufferedWriter bw;

    //多少k刷新文件
    public WriteFile(int size) {
        MAX_COUNT = size * 1024;
    }

    //绑定文件
    public void initialize(String file) {
        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //写入一行数据并且控制大小
    public boolean write(String line) {
        try {
            bw.write(line);
            bw.newLine();
            bw.flush();
            //字符串长度和换行长度
            count += (line.length() + 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (count >= MAX_COUNT) {
            return true;
        }
        return false;
    }

    public void close() {
        if (bw != null) {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fw != null) {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
