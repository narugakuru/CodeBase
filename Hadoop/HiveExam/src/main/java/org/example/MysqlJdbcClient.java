package org.example;

import java.sql.*;

public class MysqlJdbcClient {
    public static void main(String[] args) {
        try {
            // 加载Hive的JDBC驱动类
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            String connStr = "jdbc:mysql://192.168.133.129/test";
            conn = DriverManager.getConnection(connStr, "root", "123456");

            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM hotel limit 5");

            while (rs.next()) {
                System.out.println(rs.getString("base_price"));
            }

        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { } // ignore

                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { } // ignore

                stmt = null;
            }
        }
    }
}
