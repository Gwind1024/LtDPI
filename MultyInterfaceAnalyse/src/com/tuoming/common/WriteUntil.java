package com.tuoming.common;

import com.tuoming.writefile.Write;
import com.tuoming.writefile.WriteFactory;
import com.tuoming.writefile.WriteFile;

import java.util.HashMap;
import java.util.Map;

public class WriteUntil {

    public static void dealData(CommonDecode line, Map<String, String> publicTableMap, Integer cycleTime, HashMap<String, Write> writeMap, String outputPath, Integer fileSize, Integer writeSign) {
        if (line.getMmeIp() == null || line.getEndTime() == null) {
            return;
        }
        String networkElement = publicTableMap.get(line.getMmeIp());
        if (networkElement == null) {
            networkElement = "other";
        }
        String time = FileDealUntil.timeTranform(line.getEndTime(), cycleTime);
        if (time == null) {
            return;
        }

        //判断是否达到文件生成时间，达到关闭通道，生成文件
        for (String ne : writeMap.keySet()) {
            Write write = writeMap.get(ne);
            if (FileDealUntil.longTimeToMin(time) - FileDealUntil.longTimeToMin(write.getTime()) >= cycleTime) {
                write.getWf().close();
                write.setWf(null);
                FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
            }
        }

        //写入第一条数据，绑定时间
        if (!writeMap.keySet().contains(networkElement)) {
            WriteFile writeFile = new WriteFile(fileSize);
            Write write = WriteFactory.getWrite(writeSign, time);
            String fileName = write.getFile(cycleTime, networkElement);
            writeFile.initialize(outputPath + "/" + fileName);
            if (writeFile.write(line.toString())) {
                writeFile.close();
                writeFile = null;
                FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
            }
            write.setWf(writeFile);
            writeMap.put(networkElement, write);
            //非第一条数据
        } else {
            Write write = writeMap.get(networkElement);
            if (write.getWf() != null) {
                if (write.getWf().write(line.toString())) {
                    write.getWf().close();
                    write.setWf(null);
                    FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
                }
            } else {
                write.setFileSignCount(write.getFileSignCount() + 1);
                write.setTime(time);
                String file2 = write.getFile(cycleTime, networkElement);
                WriteFile wf = new WriteFile(fileSize);
                //重新绑定文件
                wf.initialize(outputPath + "/" + file2);
                if (wf.write(line.toString())) {
                    wf.close();
                    wf = null;
                    FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
                }
                write.setWf(wf);
            }
        }
    }
}
