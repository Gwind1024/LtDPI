package com.tuoming.readfile;

import com.tuoming.entity.http.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class S1uhttpReadFile extends ReadFile {

    private List arr = new LinkedList<S1uhttpDecode>();

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
                if(split.length < 94){ // http : 127     ; https :94
                    System.out.println("数据长度异常");
                    continue;
                }

                switch(split.length)
                {
                    case 127:
                        S1uhttpDecode s1uhttpDecode = new S1uhttpDecode();
                        s1uhttpDecode.decode(split);
                        arr.add(s1uhttpDecode);
                        break;
                    default:
                        if(split[com.tuoming.entity.s1uhttps.S1uhttpsIndex.l7TYPE_Index].equals("7")){
                            S1uhttpsDecode s1uhttpsDecode = new S1uhttpsDecode();
                            s1uhttpsDecode.decode(split);
                            arr.add(s1uhttpsDecode);
                        }
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}
