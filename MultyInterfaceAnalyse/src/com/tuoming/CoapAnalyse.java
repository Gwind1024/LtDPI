package com.tuoming;

import com.tuoming.common.FileDealUntil;
import com.tuoming.common.ReadPublicTable;
import com.tuoming.entity.coap.CoapDecode;
import com.tuoming.readfile.CoapReadFile;
import com.tuoming.readfile.ReadFile;
import com.tuoming.writefile.CoapWrite;
import com.tuoming.writefile.Write;
import com.tuoming.writefile.WriteFile;

import java.util.*;

public class CoapAnalyse {
    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("【Class    】:" + CoapAnalyse.class.getName());
            System.out.println("【Parameter】:网元表位置、时间粒度(minute)、大小粒度(k)、输入目录、输出目录");
            System.out.println("【Example  】:F:/dataDemo/public.txt、1440、20、F:/dataDemo/input、F:/dataDemo/output");
            System.exit(0);
        }

        //      F:/dataDemo/public.txt 1440 20 F:/dataDemo/input F:/dataDemo/output
        System.out.println("输入参数" + Arrays.toString(args));
        //源文件位置文件夹
        String path = args[3];
        //网元表位置文件
        String publicTablePath = args[0];
        String outputPath = args[4];
        //文件生成时间(分钟)
        Integer cycleTime = Integer.parseInt(args[1]);
        //文件生成大小（k）
        Integer fileSize = Integer.parseInt(args[2]);
        //获取网元表
        ReadPublicTable publicTable = new ReadPublicTable();
        publicTable.read(publicTablePath);
        Map<String, String> publicTableMap = publicTable.getPublicTable();
        System.out.println("网元公参表" + publicTableMap);

        ReadFile readFile = new CoapReadFile();

        //写文件Map网元->csfbWrite
        //每个网元对应一个写通道
        HashMap<String, Write> writeMap = new HashMap<>();

        while (true) {
            //列出所有可用文件（.txt结尾）文件
            List<String> list = FileDealUntil.fileListAndSort(path);
            System.out.println("扫描文件列表" + list);
            for (String file : list) {
                //读取一个文件
                readFile.read(path + "/" + file);
                LinkedList<CoapDecode> fileBuffer = (LinkedList<CoapDecode>) readFile.getFileBuffer();
                System.out.println("读入文件" + path + "/" + file);
                System.out.println("文件条数" + fileBuffer.size());
                while (fileBuffer.size() > 0) {
                    CoapDecode line = fileBuffer.getFirst();
                    if (line.getSgwGgsnIpAdd() == null || line.getProcedureEndtime() == null) {
                        fileBuffer.removeFirst();
                        continue;
                    }
                    String networkElement = publicTableMap.get(line.getSgwGgsnIpAdd());
                    String time = FileDealUntil.timeTranform(line.getProcedureEndtime(), cycleTime);
                    if (time == null) {
                        fileBuffer.removeFirst();
                        continue;
                    }

                    //判断是否达到文件生成时间，达到关闭通道，生成文件
                    for (String ne : writeMap.keySet()) {
                        Write coapWrite = writeMap.get(ne);
                        if (FileDealUntil.longTimeToMin(time) - FileDealUntil.longTimeToMin(coapWrite.getTime()) >= cycleTime) {
                            coapWrite.getWf().close();
                            coapWrite.setWf(null);
                            FileDealUntil.renameFile(outputPath + "/" + coapWrite.getTmpName(), outputPath + "/" + coapWrite.getFinlName());
                        }
                    }

                    //写入第一条数据，绑定时间
                    if (!writeMap.keySet().contains(networkElement)) {
                        WriteFile writeFile = new WriteFile(fileSize);
                        Write csfbWrite = new CoapWrite(time);
                        String fileName = csfbWrite.getFile(cycleTime, networkElement);
                        writeFile.initialize(outputPath + "/" + fileName);
                        if (writeFile.write(line.toString())) {
                            writeFile.close();
                            writeFile = null;
                            FileDealUntil.renameFile(outputPath + "/" + csfbWrite.getTmpName(), outputPath + "/" + csfbWrite.getFinlName());
                        }
                        csfbWrite.setWf(writeFile);
                        writeMap.put(networkElement, csfbWrite);
                        //非第一条数据
                    } else {
                        Write csfbWrite = writeMap.get(networkElement);
                        if (csfbWrite.getWf() != null) {
                            if (csfbWrite.getWf().write(line.toString())) {
                                csfbWrite.getWf().close();
                                csfbWrite.setWf(null);
                                FileDealUntil.renameFile(outputPath + "/" + csfbWrite.getTmpName(), outputPath + "/" + csfbWrite.getFinlName());
                            }
                        } else {
                            csfbWrite.setFileSignCount(csfbWrite.getFileSignCount() + 1);
                            csfbWrite.setTime(time);
                            String file2 = csfbWrite.getFile(cycleTime, networkElement);
                            WriteFile wf = new WriteFile(fileSize);
                            //重新绑定文件
                            wf.initialize(outputPath + "/" + file2);
                            if (wf.write(line.toString())) {
                                wf.close();
                                wf = null;
                                FileDealUntil.renameFile(outputPath + "/" + csfbWrite.getTmpName(), outputPath + "/" + csfbWrite.getFinlName());
                            }
                            csfbWrite.setWf(wf);
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
