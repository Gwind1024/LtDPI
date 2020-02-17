package com.tuoming.entity.s1mme.method;


import com.tuoming.common.FileDealUntil;
import com.tuoming.entity.s1mme.*;
import com.tuoming.writefile.MmeRelationWrite;
import com.tuoming.writefile.WriteFile;
import sort.SortEntity;
import sort.SortLinkedList;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

public class MmeMethod {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static Integer getLinkedListLength(MmeMapCommon head) {
        MmeMapCommon c;
        c = head;
        int count = 1;
        if (c == null) {
            return 0;
        }
        while ((c = c.next) != null) {
            count++;
        }
        return count;
    }

    public static void compareSplit() {
        if (Node.aftSplit == null) {
            return;
        }
        if (Node.split[MmeIndex.startTime].equals(Node.aftSplit[MmeIndex.startTime])) {
            if ("7".equals(Node.split[MmeIndex.produceType]) && "1".equals(Node.aftSplit[MmeIndex.produceType])) {
                Node.swap();
                return;
            }
            if ("41".equals(Node.split[MmeIndex.produceType]) && "12".equals(Node.aftSplit[MmeIndex.produceType])) {
                Node.swap();
                return;
            }
        }
    }

    public static boolean dealException(String[] split) {
        boolean flag = true;
        try {
            sdf.parse(split[MmeIndex.startTime].substring(0, 23)).getTime();
            sdf.parse(split[MmeIndex.endTime].substring(0, 23)).getTime();
            Integer.parseInt(split[MmeIndex.produceType]);
        } catch (Exception e) {
            flag = false;
        }
        if ("".equals(split[MmeIndex.s1apId].trim())) {
            flag = false;
        }
        return flag;

    }

    static class Node {
        public static String[] split = null;
        public static String[] aftSplit = null;

        public static void action() {
            split = aftSplit;
            aftSplit = null;
        }

        public static void swap() {
            String[] flag = null;
            flag = split;
            split = aftSplit;
            aftSplit = flag;
        }
    }

    public static void dealFinal(MmeRelationWrite write, String outputPath) {
        if (write.getWf() != null) {
            write.getWf().close();
            write.setWf(null);
            FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
        }
    }


    public static void writeToFile(MmeRelationWrite write, List<MmeCommon> relationResult, String outputPath, Integer fileSize) {
        for (MmeCommon line : relationResult) {
            if (write.getWf() != null) {
                if (write.getWf().write(line.toString())) {
                    write.getWf().close();
                    FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
                    write.setWf(null);
                    write.setFileSignCount(write.getFileSignCount() + 1);
                }
            } else {
                WriteFile writeFile = new WriteFile(fileSize);
                write.setWf(writeFile);
                String replace = null;
                try {
                    String[] split = line.toString().split("\\|");
                    if (split[0].contains(".")) {
                        replace = split[9].substring(0, 23).replace("-", "").replace(":", "").replace(".", "").replace(" ", "");
                    } else {
                        replace = split[8].substring(0, 23).replace("-", "").replace(":", "").replace(".", "").replace(" ", "");
                    }
                } catch (Exception e) {
                    System.out.println("[时间解析错误]");
                }
                write.setTime(replace);
                write.getWf().initialize(outputPath + "/" + write.getFile());
                if (write.getWf().write(line.toString())) {
                    write.getWf().close();
                    FileDealUntil.renameFile(outputPath + "/" + write.getTmpName(), outputPath + "/" + write.getFinlName());
                    write.setWf(null);
                    write.setFileSignCount(write.getFileSignCount() + 1);
                }
            }
        }
        relationResult.clear();
    }

    public static boolean calc(SortLinkedList fileBuffer, HashMap<String, MmeMapCommon> writeMap, LinedListHeader linedListHeader, List<MmeCommon> relationResult, Long time) {
        boolean runFlag = true;
        SortEntity node = null;
        //开始处理数据
        while ((node = fileBuffer.getFirst()) != null || Node.split != null) {
            Node.aftSplit = null;
            if (node != null) {
                String line = node.str;
                Node.aftSplit = line.split(MmeIndex.splite, -1);
                if (!dealException(Node.aftSplit)) {
                    continue;
                }
                if (runFlag) {
                    Node.action();
                    runFlag = false;
                    continue;
                }
            }
            compareSplit();

            String[] split = Node.split;
            Node.action();

            //System.out.println("[该条原始数据]<->" + "用户:" + split[MmeIndex.s1apId] + "  流程类型:" + split[MmeIndex.produceType] + "  起始时间:" + split[MmeIndex.startTime] + "  结束时间" + split[MmeIndex.endTime]);

            //先判断是否为S1-AP Initial UE message流程
            Integer mesFlag = null;
            try {
                mesFlag = Integer.parseInt(split[split.length - 25]);
                //System.out.println(split[split.length - 25]);
                if (mesFlag == 1) {
                    UeMessage ueMessage = new UeMessage();
                    ueMessage.decode(split);
                    relationResult.add(ueMessage);
                    //System.out.println("[该条数据为S1-AP Initial UE message流程，输出结果]");
                } else {
                    //System.out.println("[该条数据不是S1-AP Initial UE message流程，判断结束]");
                }
            } catch (Exception e) {
                //System.out.println("[Initial UE message flag字段解析错误]<->" + split[split.length - 14]);
            }


            //一般情况
            //System.out.println("[[一般流程判断]]");
            Long parse = null;
            try {
                parse = sdf.parse(split[MmeIndex.startTime].substring(0, 23)).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //判断超时
            if (linedListHeader.head != null) {
                while (parse - linedListHeader.head.getTimeOut() > time) {
                    //System.out.println("[超时输出结果<->]" + "该条数据时间:" + parse + " " + "超时时间" + linedListHeader.head.getTimeOut());
                    relationResult.add(linedListHeader.head.getMmeCommon());
                    writeMap.remove(linedListHeader.head.getKey());
                    if (linedListHeader.head.next != null) {
                        linedListHeader.head = linedListHeader.head.next;
                        linedListHeader.head.pre = null;
                        //System.out.println("[头结点改变]<->" + linedListHeader.head);
                    } else {
                        linedListHeader.head = null;
                        linedListHeader.thisNode = null;
                        //System.out.println("[链表中已不存在数据]");
                        break;
                    }
                }
            }
            MmeMapCommon thisMap = writeMap.get(split[MmeIndex.s1apId]);
            //判断该id，map上是否已经存在节点
            if (thisMap == null) {
                //System.out.println("[该用户未创建大流程]");
                Integer type = Integer.parseInt(split[MmeIndex.produceType]);
                MmeMapCommon object = createObject(type, split, relationResult);
                if (object != null) {
                    //System.out.println("[创建大流程]");
                    writeMap.put(split[MmeIndex.s1apId], object);
                } else {
                    //System.out.println("[一条数据处理结束]***************************************************************");
                    //System.out.println("");
                    continue;
                }
                //两种情况，要么都为null，要么都不为null
                //1、linedListHeader.head和linedListHeader.thisNode都为null的情况（第一条数据）
                if (linedListHeader.head == null && linedListHeader.thisNode == null) {
                    linedListHeader.head = object;
                    //System.out.println("[链表头结点创建]<->" + object);
                    linedListHeader.thisNode = object;
                    //System.out.println("[当前链表结点地址]<->" + linedListHeader.thisNode);
                    //2、linedListHeader.head和linedListHeader.thisNode不都为null的情况
                } else if (linedListHeader.head != null && linedListHeader.thisNode != null) {
                    //System.out.println("[上一个链表结点地址]<->" + linedListHeader.thisNode);
                    linedListHeader.thisNode.next = object;
                    object.pre = linedListHeader.thisNode;
                    linedListHeader.thisNode = object;
                    //System.out.println("[当前链表结点地址]<->" + linedListHeader.thisNode);
                }
                //System.out.println("[一条数据处理结束]***************************************************************");
                //System.out.println("");
            } else {
                //System.out.println("[该用户已经创建大流程]");
                //1:解析成功  2:解析结束  0:不是子流程 3:解析时间抛出异常
                //System.out.println("[开始进行关联操作]");
                Integer relation = thisMap.getMmeCommon().relation(thisMap, Integer.parseInt(split[MmeIndex.produceType]), split);
                if (relation == 1) {
                    //System.out.println("[关联子流程成功]<->" + "主流程:" + thisMap.getPreduceType() + " " + "子流程:" + split[MmeIndex.produceType]);
                    thisMap.setTimeOut(split[MmeIndex.startTime]);
                    if (thisMap.pre == null && thisMap.next != null) {
                        linedListHeader.head = linedListHeader.head.next;
                        linedListHeader.head.pre = null;
                        linedListHeader.thisNode.next = thisMap;
                        thisMap.pre = linedListHeader.thisNode;
                        thisMap.next = null;
                        linedListHeader.thisNode = thisMap;
                    } else if (thisMap.pre != null && thisMap.next != null) {
                        MmeMapCommon pre = thisMap.pre;
                        MmeMapCommon next = thisMap.next;
                        pre.next = next;
                        next.pre = pre;
                        linedListHeader.thisNode.next = thisMap;
                        thisMap.pre = linedListHeader.thisNode;
                        thisMap.next = null;
                        linedListHeader.thisNode = thisMap;
                    }
                } else if (relation == 2) {
                    //System.out.println("[关联到结束流程]<->" + "主流程类型:" + thisMap.getPreduceType() + " " + "结束流程类型:" + split[MmeIndex.produceType]);
                    relationResult.add(thisMap.getMmeCommon());
                    //System.out.println("[输出该条数据结果]");
                    writeMap.remove(split[MmeIndex.s1apId]);
                    //删除该节点
                    //1、节点为头，且链表就一个节点
                    if (thisMap.pre == null && thisMap.next == null) {
                        linedListHeader.head = null;
                        linedListHeader.thisNode = null;
                        //2、节点为头
                    } else if (thisMap.pre == null && thisMap.next != null) {
                        linedListHeader.head = linedListHeader.head.next;
                        linedListHeader.head.pre = null;
                        //叶子结点
                    } else if (thisMap.pre != null && thisMap.next == null) {
                        linedListHeader.thisNode = linedListHeader.thisNode.pre;
                        linedListHeader.thisNode.next = null;
                        //中间结点
                    } else if (thisMap.pre != null && thisMap.next != null) {
                        MmeMapCommon pre = thisMap.pre;
                        MmeMapCommon next = thisMap.next;
                        pre.next = next;
                        next.pre = pre;
                    }
                    //System.out.println("[一条数据处理结束]***************************************************************");
                    //System.out.println("");
                } else if (relation == 0) {
                    //System.out.println("[该条数据并非子流程]<->" + "主流程:" + thisMap.getPreduceType() + " " + "待关联流程:" + split[MmeIndex.produceType]);
                    //System.out.println("[对该流程进行处理]");
                    Integer type = Integer.parseInt(split[MmeIndex.produceType]);
                    MmeMapCommon object = createObject(type, split, relationResult);
                    //开启新流程
                    if (object != null) {
                        //System.out.println("[上一个大流程关闭输出]<->" + thisMap.getPreduceType());
                        relationResult.add(thisMap.getMmeCommon());
                        //System.out.println("[创建新的大流程]");
                        //map覆盖
                        writeMap.put(split[MmeIndex.s1apId], object);

                        //该节点移动到最后面
                        if (thisMap.pre == null && thisMap.next != null) {
                            linedListHeader.head = linedListHeader.head.next;
                            linedListHeader.head.pre = null;

                            linedListHeader.thisNode.next = object;
                            object.pre = linedListHeader.thisNode;
                            linedListHeader.thisNode = object;
                        } else if (thisMap.pre == null && thisMap.next == null) {
                            linedListHeader.head = object;
                            linedListHeader.thisNode = object;
                            //中间结点
                        } else if (thisMap.pre != null && thisMap.next != null) {
                            MmeMapCommon pre = thisMap.pre;
                            MmeMapCommon next = thisMap.next;
                            pre.next = next;
                            next.pre = pre;

                            linedListHeader.thisNode.next = object;
                            object.pre = linedListHeader.thisNode;
                            linedListHeader.thisNode = object;
                            //尾节点
                        } else if (thisMap.pre != null && thisMap.next == null) {
                            MmeMapCommon thisPre = thisMap.pre;
                            thisPre.next = object;
                            object.pre = thisPre;
                            linedListHeader.thisNode = object;
                        }

                    } else {
                        //System.out.println("[该条数据不是大流程或者该条数据没有子流程并且已输出]");
                        //System.out.println("[一条数据处理结束]***************************************************************");
                        //System.out.println("");
                    }
                }
            }
        }
        return runFlag;
    }

    public static MmeMapCommon createObject(int type, String[] split, List<MmeCommon> relationResult) {
        MmeMapCommon mmeMapCommon = null;
        if (type == 1) {
            MmeCommon attach = new Attach();
            attach.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, attach)) {
                return null;
            }
        } else if (type == 6) {
            MmeCommon detach = new Detach();
            detach.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, detach)) {
                return null;
            }
        } else if (type == 5) {
            MmeCommon tau = new Tau();
            tau.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, tau)) {
                return null;
            }
        } else if (type == 7) {
            MmeCommon pdnCon = new PdnCon();
            pdnCon.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, pdnCon)) {
                return null;
            }
        } else if (type == 8) {
            MmeCommon pdnDisCon = new PdnDisCon();
            pdnDisCon.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, pdnDisCon)) {
                return null;
            }
        } else if (type == 2 || type == 3 || type == 4 || type == 100) {
            MmeCommon pagingAndBusiness = new PagingAndBusiness();
            pagingAndBusiness.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, pagingAndBusiness)) {
                return null;
            }
        } else if (type == 9) {
            MmeCommon epsAllocation = new EpsAllocation();
            epsAllocation.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, epsAllocation)) {
                return null;
            }
        } else if (type == 10) {
            MmeCommon epsModify = new EpsModify();
            epsModify.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, epsModify)) {
                return null;
            }
        } else if (type == 13) {
            MmeCommon dedicatedEps = new DedicatedEps();
            dedicatedEps.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, dedicatedEps)) {
                return null;
            }
        } else if (type == 11) {
            MmeCommon epsDeactivation = new EpsDeactivation();
            epsDeactivation.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, epsDeactivation)) {
                return null;
            }
        } else if (type == 12) {
            MmeCommon epsModification = new EpsModification();
            epsModification.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, epsModification)) {
                return null;
            }
        } else if (type == 15 || type == 16 || type == 14) {
            MmeCommon aSwitch = new Switch();
            aSwitch.decode(split);
            mmeMapCommon = new MmeMapCommon();
            if (!mmeMapCommon.create(split, aSwitch)) {
                return null;
            }
        } else if (type == 20) {
            MmeCommon ueContextRelease = new UeContextRelease();
            ueContextRelease.decode(split);
            relationResult.add(ueContextRelease);
            //System.out.println("[上下文释放流程创建成功并且输出结果]");
            return null;
        } else if (type >= 22 && type <= 28) {
            MmeCommon s1Manage = new S1Manage();
            s1Manage.decode(split);
            relationResult.add(s1Manage);
            //System.out.println("[S1 管理 流程创建成功并且输出结果]");
            return null;
        } else if (type == 51) {
            MmeCommon ueCapablility = new UeCapability();
            ueCapablility.decode(split);
            relationResult.add(ueCapablility);
            //System.out.println("[UE Capablility Info Indication流程创建成功并且输出结果]");
            return null;
        } else if (type == 61) {
            MmeCommon ueContextSuspend = new UeContextSuspend();
            ueContextSuspend.decode(split);
            relationResult.add(ueContextSuspend);
            //System.out.println("[UE Context Suspend流程创建成功并且输出结果]");
            return null;
        } else if (type == 62) {
            MmeCommon ueContextResume = new UeContextResume();
            ueContextResume.decode(split);
            relationResult.add(ueContextResume);
            //System.out.println("[UE Context Resume流程创建成功并且输出结果]");
            return null;
        } else if (type == 60) {
            MmeCommon controlPlane = new ControlPlane();
            controlPlane.decode(split);
            relationResult.add(controlPlane);
            //System.out.println("[CONTROL PLANE SERVICE流程创建成功并且输出结果]");
            return null;
        } else if (type == 44) {
            MmeCommon esmDataTransport = new EsmDataTransport();
            esmDataTransport.decode(split);
            relationResult.add(esmDataTransport);
            //System.out.println("[ESM DATA TRANSPORT流程创建成功并且输出结果]");
            return null;
        } else if (type == 70) {
            MmeCommon erabModificattionIndiction = new ErabModificattionIndiction();
            erabModificattionIndiction.decode(split);
            relationResult.add(erabModificattionIndiction);
            //System.out.println("[E-RAB MODIFICATION INDICATION流程创建成功并且输出结果]");
            return null;
        } else if (type == 71) {
            MmeCommon secondaryRatDataUsageReport = new SecondaryRatDataUsageReport();
            secondaryRatDataUsageReport.decode(split);
            relationResult.add(secondaryRatDataUsageReport);
            //System.out.println("[SECONDARY RAT DATA USAGE REPORT流程创建成功并且输出结果]");
        } else {
            return null;
        }
        return mmeMapCommon;
    }
}
