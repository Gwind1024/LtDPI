package com.tuoming.readfile;


import com.tuoming.entity.other.OtherDecode;
import com.tuoming.entity.other.OtherIndex;
import com.tuoming.sort.SortEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OtherReadFile extends ReadFile {

    //读一个文件,放入文件缓冲区arr
    public void read(String path) {
        fileName = path;
        try {
            fis = new FileReader(fileName);
            br = new BufferedReader(fis);
            while ((line = br.readLine()) != null) {
                String[] split = line.split(OtherIndex.splite, -1);
                if("7".equals(OtherDecode.getProtocolTypeFromArr(split))){
                    continue;
                }
                SortEntity sortEntity = new SortEntity();
                boolean flag = sortEntity.createNumEntity( line, OtherIndex.startT,OtherIndex.size);
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
