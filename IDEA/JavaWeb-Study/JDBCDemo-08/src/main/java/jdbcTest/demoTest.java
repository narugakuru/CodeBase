package jdbcTest;

import java.sql.*;

public class demoTest {
    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306/userdata?useSSL=true&useUnicode=true&characterEncoding=UTF-8";
        String DRIVER = "com.mysql.jdbc.Driver";
        String USER = "root";
        String PASSWORD = "root";

        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
            conn = DriverManager.getConnection(URL, USER,PASSWORD);//连接数据库
            Statement statement = conn.createStatement();

            String sql = "SELECT * FROM user ";
            ResultSet resultSet = statement.executeQuery(sql);

            System.out.println(resultSet.toString());
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


    }
}
