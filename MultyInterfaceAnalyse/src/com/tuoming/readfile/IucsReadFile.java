package com.tuoming.readfile;

import com.tuoming.sort.SortEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IucsReadFile extends ReadFile {

    @Override
    public void read(String path) {

    }

    //读一个文件,放入文件缓冲区arr
    public void read(String path,String filename) {
        fileName = filename;
        try {
            fis = new FileReader(path);
            br = new BufferedReader(fis);
            while ((line = br.readLine()) != null){
                SortEntity sortEntity = new SortEntity();
                if (fileName.startsWith("MC_CALL")) {
                   // ProtolSet=CALL|96|1|0|
                    boolean flag = sortEntity.createEntity("01", line, 95, 125);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //ProtolSet=PSAuthenticationEvent|22|11|0|
                } else if (fileName.startsWith("MC_LOCATION")) {
                    boolean flag = sortEntity.createEntity("02", line, 50, 77);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //ProtolSet=PAGING|44|3|0|
                }else if (fileName.startsWith("MC_PAGING")){
                    boolean flag = sortEntity.createEntity("03",line,43,67);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //ProtolSet=SMS|62|4|0|
                }else if (fileName.startsWith("MC_SMS")){
                    boolean flag = sortEntity.createEntity("04",line,61,84);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //ProtolSet=SWITCH|43|5|0
                }else if (fileName.startsWith("MC_SWITCH")){
                    boolean flag = sortEntity.createEntity("05",line,42,66);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                }

               // arr.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

}
