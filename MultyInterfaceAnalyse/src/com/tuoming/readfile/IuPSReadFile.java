package com.tuoming.readfile;

import com.tuoming.entity.iups.CommonIuPSIndex;
import com.tuoming.sort.SortEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class IuPSReadFile extends ReadFile {
//    List<String> iupsList = new LinkedList<String>();
    @Override
    public void read(String path) {
        fileName = path;
        try {
            fis = new FileReader(fileName);
            br = new BufferedReader(fis);
            while ((line = br.readLine()) != null) {
                SortEntity sortEntity = new SortEntity();
                boolean flag = sortEntity.createEntity(line, CommonIuPSIndex.SORT_TIME, CommonIuPSIndex.MIN_SIZE);
                if(flag){
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
