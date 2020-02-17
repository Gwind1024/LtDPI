package com.tuoming.readfile;

import com.tuoming.entity.s1mme.MmeIndex;
import sort.SortEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class MmeReadFile extends ReadFile {

    @Override
    public void read(String path) {
        fileName = path;
        try {
            fis = new FileReader(fileName);
            br = new BufferedReader(fis);
            while ((line = br.readLine()) != null) {
                SortEntity sortEntity = new SortEntity();
                boolean flag = sortEntity.createEntity(line, MmeIndex.startTime, MmeIndex.size);
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
