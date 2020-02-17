package com.tuoming.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class receiveDemo {
    public static void main(String[] args) {
        DatagramSocket sendDatagramSocket = null;
        try {
            Scanner sc = new Scanner(System.in);
            String string = "";
            //创建DatagramSocket 对象，并指定该程序的通信端口为8080
            sendDatagramSocket = new DatagramSocket(8080);
            //转成字符数组类型
            byte[] by = string.getBytes();
            //确定要发送的端口
            int port = 10000;
            //确定要发送的地址
            InetAddress ip = InetAddress.getLocalHost();//如果是自己电脑测试可以这样得到本机地址，也可以自己查自己的ip地址

            //InetAddress ip = InetAddress.getByName("192.168.32.1");
            //创建发送类型的数据包，这个数据包包含了要发往的ip地址和端口
//            DatagramPacket sendPscket = new DatagramPacket(by, by.length, ip,port);
            //创建接收类型的数据包
            //创建接收缓冲区
            byte[] bt = new byte[1024];
            //DatagramPacket(byte[] buf, int length)构造 DatagramPacket，用来接收长度为 length 的数据包

            DatagramPacket receivePacket = new DatagramPacket(bt, bt.length);

            while(true){
                System.out.println("等待接收数据……");
                //通过DatagramSocket 的receive方法接收数据
                sendDatagramSocket.receive(receivePacket);
                //打印---数据包
                String daString = new String(receivePacket.getData(),0,receivePacket.getLength());//getData()方法是返回数据缓冲区
                System.out.println("接收数据："+daString);
                /*System.out.println("输入回复数据……");
                string = sc.nextLine();
                sendPscket.setData(string.getBytes());
                System.out.println("发送数据:"+string);
                //通过DatagramSocket 的send方法发送数据
                sendDatagramSocket.send(sendPscket);
                System.out.println("发送成功！");*/
            }


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }finally {
            sendDatagramSocket.close();
        }
    }
}
