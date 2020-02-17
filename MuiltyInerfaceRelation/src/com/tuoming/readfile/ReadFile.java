package com.tuoming.readfile;

import sort.SortEntity;
import sort.SortLinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
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
//        list.sortAdd(entity);
        list.add(entity);
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

