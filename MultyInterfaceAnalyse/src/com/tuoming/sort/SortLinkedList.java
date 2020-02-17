package com.tuoming.sort;


import com.tuoming.readfile.ReadFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class SortLinkedList {
    public LinkedList<SortEntity> result = new LinkedList<SortEntity>();
    public ArrayList<SortEntity> list = new ArrayList<>();
//    public int size = 0;
//    public Node first;
//    public Node last;

    public int size() {
        return list.size();
    }


    public SortEntity getFirst() {
        while (size() > ReadFile.MaxCount) {
            Collections.sort(list);
            int num = size() - ReadFile.MaxCount;
            int size = size();
            for (int i = 0; i < num; i++) {
                result.add(list.remove(size - 1 - i));
            }
        }
        if (result.size() > 0) {
            return result.removeFirst();
        }
        return null;
    }

//    public SortEntity removeFirst() {
//        SortEntity result = null;
//        if (first != null) {
//            result = first.entity;
//            first = first.next;
//            if (first == null) {
//                last = null;
//            } else {
//                first.pre = null;
//            }
//            size--;
//        }
//        return result;
//    }

    public void add(SortEntity entity) {
        list.add(entity);
    }


//    public void sortAdd(SortEntity entity) {
//        if (first == null && last == null) {
//            first = new Node(entity, null, null);
//            last = first;
//        } else {
//            long firstTime = first.entity.time;
//            long lastTime = last.entity.time;
//            if (entity.time <= firstTime) {
//                first.pre = new Node(entity, null, first);
//                first = first.pre;
//            } else if (entity.time >= lastTime) {
//                last.next = new Node(entity, last, null);
//                last = last.next;
//            } else {
//                long midlleTime = (firstTime + lastTime) >> 1;
//                Node flag = null;
//                if (midlleTime >= entity.time) {
//                    flag = first;
//                    while (flag != null) {
//                        Node flag2 = flag.next;
//                        if (entity.time >= flag.entity.time && entity.time < flag2.entity.time) {
//                            flag.next = new Node(entity, flag, flag2);
//                            flag2.pre = flag.next;
//                            break;
//                        } else {
//                            flag = flag.next;
//                        }
//                    }
//                } else {
//                    flag = last;
//                    while (flag != null) {
//                        Node flag2 = flag.pre;
//                        if (entity.time >= flag2.entity.time && entity.time < flag.entity.time) {
//                            flag2.next = new Node(entity, flag2, flag);
//                            flag.pre = flag2.next;
//                            break;
//                        } else {
//                            flag = flag.pre;
//                        }
//                    }
//                }
//            }
//        }
//        size++;
//        if (size() > ReadFile.MaxCount) {
//            SortEntity node;
//            if ((node = removeFirst()) != null) {
//                result.add(node);
//            }
//        }
//    }

    class Node {
        SortEntity entity;
        Node pre;
        Node next;

        Node(SortEntity entity, Node pre, Node next) {
            this.entity = entity;
            this.pre = pre;
            this.next = next;
        }
    }

//    @Override
//    public String toString() {
//        Node flag = null;
//        String str = "";
//        flag = first;
//        while (flag != null) {
//            str += flag.entity.time + "->";
//            flag = flag.next;
//        }
//        return str + "null";
//    }
}
