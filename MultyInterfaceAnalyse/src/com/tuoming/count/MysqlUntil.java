package com.tuoming.count;

import java.sql.*;

public class MysqlUntil {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/count?characterEncoding=utf-8";
    String username = "root";
    String password = "123456";

    public Connection connect(String redisIP, String redisPwd) {
        url = "jdbc:mysql://" + redisIP + ":3306/count?characterEncoding=utf-8";
        password = redisPwd;
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public CountNum[] query(String startTime, String endTime, String tableName, Connection connection) {
        String sql = "SELECT * FROM " + tableName + " WHERE time>='" + startTime + "' AND time<='" + endTime + "';";
        System.out.println(sql);
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insert(CountNum countNum, Connection connection, String tableName) {
        String sql = "INSERT into " + tableName + " VALUES('" + countNum.time + "'," + countNum.timeStamp + "," + countNum.fileCount + "," + countNum.intputNum + "," + countNum.dealTime + "," + countNum.dealSpeed + "," + countNum.outputNum + "," + countNum.backFillNum + "," + countNum.nobackFillNum + "," + countNum.backFillRatio + ");";
        try {
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName, Connection connection) {
        boolean flag = true;
        String sqlexit = "show tables;";
        String sql = "CREATE TABLE " + tableName + " (time datetime,timeStamp BIGINT,fileCount INT, intputNum INT,dealTime LONG,dealSpeed INT,outputNum INT,backFillNum INT,nobackFillNum INT,backFillRatio DOUBLE);";
        try {
            PreparedStatement pst = connection.prepareStatement(sqlexit);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                String string = resultSet.getString(1);
                if (tableName.equals(string)) {
                    flag = false;
                }
            }
            if (flag) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        MysqlUntil mysqlUntil = new MysqlUntil();
//        Connection connect = mysqlUntil.connect();
//        CountNum countNum = new CountNum();
//        countNum.time = "2019-12-12 12:12:13";
//        countNum.timeStamp = 100000;
//        countNum.fileCount = 1;
//        countNum.intputNum = 2;
//        countNum.dealTime = 123;
//        countNum.dealSpeed = 1234;
//        countNum.outputNum = 193;
//        countNum.backFillNum = 34;
//        countNum.nobackFillNum = 98;
//        countNum.backFillRatio = 1.2;
//        mysqlUntil.insert(countNum, connect, "sgscount");
//        mysqlUntil.query("2019-12-12 12:12:12", "2019-12-12 12:12:12", "sgscount", connect);
    }

}
