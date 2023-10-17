package org.example;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DriverManager;

public class HiveJdbcClient {

    // Hive JDBC驱动名
    private static String driverName = "org.apache.hive.jdbc.HiveDriver";

    /**
     * @param args
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {
        try {
            // 加载Hive的JDBC驱动类
            Class.forName(driverName);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        // 设置JDBC连接字符串并创建连接
        Connection con = DriverManager.getConnection("jdbc:hive2://192.168.133.129:10000/db_one", "hive", "");
        //
        Statement stmt = con.createStatement();
        // 设置表名
        String tableName = "testHiveDriverTable";
        // 设置建表的Serde(序列反序列化器)
        String rfs = "ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.OpenCSVSerde'";
        // 删除之前建立的表
        stmt.execute("drop table if exists " + tableName);
        // 创建表SQL
        stmt.execute("create table " + tableName + " (key int, value string) " + rfs);
        // 查看当前库的所有表
        String sql = "show tables";
        System.out.println("Running: " + sql);
        ResultSet res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
        }
        // 获得表说明
        sql = "describe " + tableName;
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        // 遍历结果集
        while (res.next()) {
            System.out.println(res.getString(1) + "\t" + res.getString(2));
        }

        // 装载数据文件到表
        // NOTE: 本地文件路径是指Hive Server本地
        // 指定数据文件路径
        String filepath = "/tmp/a.txt";
        sql = "load data local inpath '" + filepath + "' into table " + tableName;
        System.out.println("Running: " + sql);
        stmt.execute(sql);

        // select查询
        sql = "select * from " + tableName + " limit 5";
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(String.valueOf(res.getInt(1)) + "\t" + res.getString(2));
        }

        // 聚合查询
        sql = "select count(1) from " + tableName;
        System.out.println("Running: " + sql);
        res = stmt.executeQuery(sql);
        while (res.next()) {
            System.out.println(res.getString(1));
        }

        con.close();
    }
}