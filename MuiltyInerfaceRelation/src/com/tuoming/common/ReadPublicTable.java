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
            fis = new FileReader(fileName);
            br = new BufferedReader(fis);
            while ((line = br.readLine()) != null) {
                String[] split = line.split(PublicTableIndex.splite, -1);
                arr.put(split[PublicTableIndex.ip], split[PublicTableIndex.netWorkElement]);
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
