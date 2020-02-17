package com.tuoming;

import com.tuoming.common.FileDealUntil;
import com.tuoming.common.ReadPublicTable;
import com.tuoming.entity.mqtt.MqttDecode;
import com.tuoming.readfile.ReadFile;
import com.tuoming.readfile.MqttReadFile;
import com.tuoming.writefile.MqttWrite;
import com.tuoming.writefile.Write;
import com.tuoming.writefile.WriteFile;
import java.io.IOException;
import java.util.*;

public class MQTTAnalyse {

    public static void main(String args[]) throws IOException {

        /**
         * 1.检查参数
         * 2.读取文件
         * 3.处理后输出
         */

        //输入参数检查
        if(args.length != 5){
            System.out.println("【Class    】:" + MQTTAnalyse.class.getName());
            System.out.println("输入参数错误！程序退出！");
            System.out.println("输入参数格式： 网元配置文件  文件时间粒度  文件大小控制粒度  输入目录  输出目录");
            System.out.println("输入参数示例： check.csv  1440  200  ./input/   output/");
            System.exit(1);
        }

        System.out.println("输入参数" + Arrays.toString(args));
        //获取网元表
        ReadPublicTable publicTable = new ReadPublicTable();
        publicTable.read(args[0]);
        Map<String, String> publicTableMap = publicTable.getPublicTable();

        System.out.println("网元公参表" + publicTableMap);

        //源文件位置文件夹
        String path = args[3];
        //输入文件目录
        String outputPath = args[4];
        //文件生成时间(分钟)
        Integer cycleTime = Integer.parseInt(args[1]);
        //文件生成大小（k）
        Integer fileSize = Integer.parseInt(args[2]);

        ReadFile readFile = new MqttReadFile();

        HashMap<String, Write> writeMap = new HashMap<>();
        while (true){
            //扫描输入文件列表
            List<String> list = FileDealUntil.fileListAndSort(path);
            System.out.println("扫描文件列表" + list);
            for (String file : list) {
                //读取一个文件
                readFile.read(path + "/" + file);
                LinkedList<MqttDecode> fileBuffer = (LinkedList<MqttDecode>) readFile.getFileBuffer();
                System.out.println("读入文件" + path + "/" + file);
                System.out.println("文件条数" + fileBuffer.size());
                while (fileBuffer.size() > 0) {
                    MqttDecode line = fileBuffer.getFirst();
                    if (line.machineIP == null || line.endTime == null)  {
                        fileBuffer.removeFirst();
                        continue;
                    }

                    System.out.println("endT:"+line.endT+";");
                    String networkElement = publicTableMap.get(line.machineIP);
                    String time = FileDealUntil.timeTranform(line.endT, cycleTime);
                    if (time == null) {
                        fileBuffer.removeFirst();
                        continue;
                    }

                    //判断是否达到文件生成时间，达到关闭通道，生成文件
                    for (String ne : writeMap.keySet()) {
                        Write MqttWrite = writeMap.get(ne);
                        if (FileDealUntil.longTimeToMin(time) - FileDealUntil.longTimeToMin(MqttWrite.getTime()) >= cycleTime) {
                            MqttWrite.getWf().close();
                            MqttWrite.setWf(null);
                            FileDealUntil.renameFile(outputPath + "/" + MqttWrite.getTmpName(), outputPath + "/" + MqttWrite.getFinlName());
                        }
                    }

                    //写入第一条数据，绑定时间
                    if (!writeMap.keySet().contains(networkElement)) {
                        WriteFile writeFile = new WriteFile(fileSize);
                        Write MqttWrite = new MqttWrite(time);
                        String fileName = MqttWrite.getFile(cycleTime, networkElement);
                        writeFile.initialize(outputPath + "/" + fileName);
                        if (writeFile.write(line.toString())) {
                            writeFile.close();
                            writeFile = null;
                            FileDealUntil.renameFile(outputPath + "/" + MqttWrite.getTmpName(), outputPath + "/" + MqttWrite.getFinlName());
                        }
                        MqttWrite.setWf(writeFile);
                        writeMap.put(networkElement, MqttWrite);
                        //非第一条数据
                    } else {
                        Write MqttWrite = writeMap.get(networkElement);
                        if (MqttWrite.getWf() != null) {
                            if (MqttWrite.getWf().write(line.toString())) {
                                MqttWrite.getWf().close();
                                MqttWrite.setWf(null);
                                FileDealUntil.renameFile(outputPath + "/" + MqttWrite.getTmpName(), outputPath + "/" + MqttWrite.getFinlName());
                            }
                        } else {
                            MqttWrite.setFileSignCount(MqttWrite.getFileSignCount() + 1);
                            MqttWrite.setTime(time);
                            String file2 = MqttWrite.getFile(cycleTime, networkElement);
                            WriteFile wf = new WriteFile(fileSize);
                            //重新绑定文件
                            wf.initialize(outputPath + "/" + file2);
                            if (wf.write(line.toString())) {
                                wf.close();
                                wf = null;
                                FileDealUntil.renameFile(outputPath + "/" + MqttWrite.getTmpName(), outputPath + "/" + MqttWrite.getFinlName());
                            }
                            MqttWrite.setWf(wf);
                        }
                    }
                    fileBuffer.removeFirst();
                }
                FileDealUntil.deleteFile(path + "/" + file);
                System.out.println(path + "/" + file + "处理完毕，删除");
            }
            list.clear();
            try {
                //5秒扫描一次文件
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
