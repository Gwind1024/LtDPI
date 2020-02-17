package com.tuoming.readfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SigReadFile extends ReadFile {
    private List arr = new ArrayList<String>();

    @Override
    public void read(String path) {
        fileName = path;
        try {
            fis = new FileReader(fileName);
            br = new BufferedReader(fis);
            while ((line = br.readLine()) != null) {
                arr.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }

    @Override
    public List<String> getFileBuffer() {
        return arr;
    }

    @Override
    public void clearFileBuffer() {
        arr.clear();
    }

    @Override
    public Integer fileBufferSize() {
        return arr.size();
    }
}
