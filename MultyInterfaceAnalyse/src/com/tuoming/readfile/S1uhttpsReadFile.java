package com.tuoming.readfile;

import com.tuoming.entity.s1uhttps.S1uhttpsDecode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class S1uhttpsReadFile extends ReadFile {

    private List arr = new LinkedList<S1uhttpsDecode>();

    //获取文件缓冲区
    public List getFileBuffer() {
        return arr;
    }

    //清空文件缓冲区arr
    public void clearFileBuffer() {
        arr.clear();
    }

    //文件缓冲区大小
    public Integer fileBufferSize() {
        return arr.size();
    }

    //读一个文件,放入文件缓冲区arr
    public void read(String path) {
        fileName = path;
        try {
            fis = new FileReader(fileName);
            br = new BufferedReader(fis);
            while ((line = br.readLine()) != null) {
                String[] split = line.split("\\|", -1);
                if(split.length < 63){
                    System.out.println("数据长度异常");
                    continue;
                }
                S1uhttpsDecode S1uhttpsDecode = new S1uhttpsDecode();
                S1uhttpsDecode.getS1uhttpsDecode(split);
                arr.add(S1uhttpsDecode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}
