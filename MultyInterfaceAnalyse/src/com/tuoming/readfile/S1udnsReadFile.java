package com.tuoming.readfile;

import com.tuoming.entity.s1udns.S1udnsIndex;
import com.tuoming.entity.s6a.S6aIndex;
import com.tuoming.sort.SortEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class S1udnsReadFile extends ReadFile {
    //读一个文件,放入文件缓冲区arr
    public void read(String path) {
        fileName = path;
        try {
            fis = new FileReader(fileName);
            br = new BufferedReader(fis);
            while ((line = br.readLine()) != null) {
                SortEntity sortEntity = new SortEntity();
                boolean flag = sortEntity.createNumEntity(line, S1udnsIndex.starttime_Index, S1udnsIndex.size);
                if (flag) {
                    insertSort(sortEntity);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}
