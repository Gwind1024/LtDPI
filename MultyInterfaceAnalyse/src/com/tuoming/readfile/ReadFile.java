package com.tuoming.readfile;

import com.tuoming.sort.SortEntity;
import com.tuoming.sort.SortLinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

public abstract class ReadFile {
    //缓冲区大小
    public static Integer MaxCount = 1024;
    public SortLinkedList list = new SortLinkedList();


    protected String line = "";
    protected Reader fis;
    protected BufferedReader br;
    protected String fileName = "";


    public abstract void read(String path);


    public Integer size() {
        return list.size() + list.result.size();
    }


    public void insertSort(SortEntity entity) {
        list.add(entity);
    }


    //获取文件缓冲区
    public List getFileBuffer() {
        return null;
    }

    //清空文件缓冲区arr
    public void clearFileBuffer() {

    }

    //文件缓冲区大小
    public Integer fileBufferSize() {
        return null;
    }

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

