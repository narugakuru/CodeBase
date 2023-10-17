package org.example;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

public class ExportDBToExcel {

    private static String driverName = "org.apache.hive.jdbc.HiveDriver";
    public static void main(String[] args) throws SQLException {
        try {
            // 加载Hive的JDBC驱动类
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.exit(1);
        }
        // 设置JDBC连接字符串并创建连接
        Connection con = DriverManager.getConnection("jdbc:mysql://192.168.133.129/test", "dev", "123456");
        //
        Statement stmt = con.createStatement();
        //
        String sql = "select hotel_id,base_price,is_business from hotel limit 10;";
        ResultSet res = stmt.executeQuery(sql);

        Workbook wb = new HSSFWorkbook();
        Sheet persons = wb.createSheet("persons");

        int num = 0;
        // 遍历结果集
        while (res.next()) {
            Row row = persons.createRow(num);
            // name
            row.createCell(0).setCellValue(res.getString(1));
            // age
            row.createCell(1).setCellValue(res.getString(2));
            //
            row.createCell(2).setCellValue(res.getString(3));
            num++;
            System.out.println(res.getString(1) + "\t" + res.getString(2));
        }

        // 打开指定路径的文件输出流
        try  (OutputStream fileOut = new FileOutputStream("reports/hotels.xls")) {
            // Excel文档内容输出到指定输出流
            wb.write(fileOut);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
