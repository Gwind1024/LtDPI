package com.tuoming.readfile;

import com.tuoming.entity.s1ugame.*;
import com.tuoming.sort.SortEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class S1ugameReadFile extends ReadFile {


    //读一个文件,放入文件缓冲区arr
    public void read(String path) {
        fileName = path;
        try {
            fis = new FileReader(fileName);
            br = new BufferedReader(fis);
            while ((line = br.readLine()) != null) {
                SortEntity sortEntity = new SortEntity();
                if(line.split(S1ugameDecode.splite)[S1ugameIndex.type].equals("8")) {
                    boolean flag = sortEntity.createNumEntity(line, S1ugameIndex.startT_Index, S1ugameIndex.size);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }


}
