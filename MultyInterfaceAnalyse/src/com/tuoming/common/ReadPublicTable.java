package com.tuoming.common;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class ReadPublicTable {
    private Map arr = new HashMap<String, String>();
    private String line = "";
    private Reader fis;
    private BufferedReader br;
    private String fileName = "";


    //读取工参表
    public void read(String path) {
        fileName = path;
        try {
            fis = new FileReader(fileName);//在给定从中读取数据的文件名的情况下创建一个新 FileReader  按字符读取流中数据
            br = new BufferedReader(fis);//创建一个使用默认大小输入缓冲区的缓冲字符输入流
            while ((line = br.readLine()) != null) {//读取输入流的中的文本行
                String[] split = line.split(PublicTableIndex.splite, -1);//最大分割次数分割，将字符串分割成数组
                arr.put(split[PublicTableIndex.ip], split[PublicTableIndex.netWorkElement]);//将指定值与此映射中的指定键关联
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    //获取工参表
    public Map<String, String> getPublicTable() {
        return arr;
    }

    private void close() {
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
