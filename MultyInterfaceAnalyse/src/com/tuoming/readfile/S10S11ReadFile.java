package com.tuoming.readfile;

import com.tuoming.entity.s10s11.S10_S11Index;
import com.tuoming.sort.SortEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class S10S11ReadFile extends ReadFile {

    @Override
    public void read(String path) {
        fileName = path;
        try {
            fis = new FileReader(fileName);
            br = new BufferedReader(fis);
            while ((line = br.readLine()) != null) {
                SortEntity sortEntity = new SortEntity();
                boolean flag = sortEntity.createNumEntity(line, S10_S11Index.EndTime, S10_S11Index.size);
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
