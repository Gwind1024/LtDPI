package com.tuoming.readfile;

import sort.SortEntity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class IupsReadFile extends ReadFile {
    @Override
    public void read(String path) {

    }

    public void read(String path, String fileName) {
        try {
            fis = new FileReader(path);
            br = new BufferedReader(fis);
            while ((line = br.readLine()) != null) {
                SortEntity sortEntity = new SortEntity();
                if (fileName.startsWith("PSAttachEvent")) {
                    //ProtolSet=PSAttachEvent|22|10|0|
                    boolean flag = sortEntity.createEntity("10", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //ProtolSet=PSAuthenticationEvent|22|11|0|
                } else if (fileName.startsWith("PSAuthenticationEvent")) {
                    boolean flag = sortEntity.createEntity("11", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSidentityEvent|22|13|0|
                } else if (fileName.startsWith("PSidentityEvent")) {
                    boolean flag = sortEntity.createEntity("13", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSMSDetachEvent|22|14|0|
                } else if (fileName.startsWith("PSMSDetachEvent")) {
                    boolean flag = sortEntity.createEntity("14", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSMSPDPActEvent|26|15|0
                } else if (fileName.startsWith("PSMSPDPActEvent")) {
                    boolean flag = sortEntity.createEntity("15", line, 25, 26);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSMSPDPDeactEvent|22|16|0
                } else if (fileName.startsWith("PSMSPDPDeactEvent")) {
                    boolean flag = sortEntity.createEntity("16", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSMSPDPModEvent|26|17|0
                } else if (fileName.startsWith("PSMSPDPModEvent")) {
                    boolean flag = sortEntity.createEntity("17", line, 25, 26);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSMSPDPSecActEvent|26|18|0
                } else if (fileName.startsWith("PSMSPDPSecActEvent")) {
                    boolean flag = sortEntity.createEntity("18", line, 25, 26);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSMSServEvent|28|19|0
                } else if (fileName.startsWith("PSMSServEvent")) {
                    boolean flag = sortEntity.createEntity("19", line, 27, 28);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSNWDetachEvent|22|20|0
                } else if (fileName.startsWith("PSNWDetachEvent")) {
                    boolean flag = sortEntity.createEntity("20", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSNWPDPActEvent|22|21|0
                } else if (fileName.startsWith("PSNWPDPActEvent")) {
                    boolean flag = sortEntity.createEntity("21", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSNWPDPDeactEvent|22|22|0
                } else if (fileName.startsWith("PSNWPDPDeactEvent")) {
                    boolean flag = sortEntity.createEntity("22", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSNWPDPModEvent|22|23|0
                } else if (fileName.startsWith("PSNWPDPModEvent")) {
                    boolean flag = sortEntity.createEntity("23", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSNWPDPSecActEvent|26|24|0
                } else if (fileName.startsWith("PSNWPDPSecActEvent")) {
                    boolean flag = sortEntity.createEntity("24", line, 25, 26);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSNWServEvent|22|25|0
                } else if (fileName.startsWith("PSNWServEvent")) {
                    boolean flag = sortEntity.createEntity("25", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSRABEvent|22|27|0
                } else if (fileName.startsWith("PSRABEvent")) {
                    boolean flag = sortEntity.createEntity("27", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSRANAPEvent|22|28|0
                } else if (fileName.startsWith("PSRANAPEvent")) {
                    boolean flag = sortEntity.createEntity("28", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSRAUEvent|22|29|0
                } else if (fileName.startsWith("PSRAUEvent")) {
                    boolean flag = sortEntity.createEntity("29", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=PSRelocEvent|22|30|0
                } else if (fileName.startsWith("PSRelocEvent")) {
                    boolean flag = sortEntity.createEntity("30", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=BSSGP|22|12|0
                } else if (fileName.startsWith("BSSGP")) {
                    boolean flag = sortEntity.createEntity("12", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                    //                ProtolSet=Paging|22|26|0
                } else if (fileName.startsWith("Paging")) {
                    boolean flag = sortEntity.createEntity("26", line, 21, 22);
                    if (flag) {
                        insertSort(sortEntity);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close();
        }
    }
}
