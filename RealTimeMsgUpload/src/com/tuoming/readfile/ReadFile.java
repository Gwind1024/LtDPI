package com.tuoming.readfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public abstract class ReadFile {
    protected String line = "";
    protected Reader fis;
    protected BufferedReader br;
    protected String fileName = "";


    public abstract void read(String path);


    //获取文件缓冲区
    public abstract List getFileBuffer();

    //清空文件缓冲区arr
    public abstract void clearFileBuffer();

    //文件缓冲区大小
    public abstract Integer fileBufferSize();

    protected void close() {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (fis != null) {
            try {
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

