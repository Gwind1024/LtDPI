package com.tuoming.writeFile;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteCountResult {
    private BufferedWriter bufferedWriter;
    private FileWriter fileWriter;
    private int count;

    public void Init(File file){
        try {
            fileWriter = new FileWriter(file,true);
            bufferedWriter = new BufferedWriter(fileWriter);
        } catch (IOException e) {

        }
    }

    public void write(String resultStr){

        try {
            bufferedWriter.write(resultStr);
            bufferedWriter.newLine();
        }catch (Exception e){

        }
    }
    public void close(){
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedWriter != null){
                bufferedWriter = null;
            }
        }
    }
    public String getFileName(String time){
        String fileName = "ZJ_OTHER_MOBILE_CONS_Huawei_CXDR_OTHER_900_"+ time + "_REALTIMELOG-00000_0_0.txt.tmp";
        return fileName;
    }
    public void renameFile(File file){
        String fileNameOrg = file.getName();
        String fileNameNew = file.getParent() +File.separator + fileNameOrg.substring(0, fileNameOrg.lastIndexOf("."));
//        System.out.println(fileNameNew);
        File file1 = new File(fileNameNew);
        file.renameTo(file1);
    }

}
