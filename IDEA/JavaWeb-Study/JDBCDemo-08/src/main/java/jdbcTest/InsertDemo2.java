package jdbcTest;

import java.sql.*;

public class InsertDemo2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String URL = "jdbc:mysql://localhost:3306/userdata?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String DRIVER = "com.mysql.jdbc.Driver";
        String USER = "root";
        String PASSWORD = "root";

        String username = "鹏子哥";
        String password = "666";
        int id = 14514;
        String sex = "男姐姐";


        Class.forName(DRIVER);
        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
        String sql="insert into signin(usename, password, id, sex) values (?,?,?,?)";
//       向数据库发送sql语句
        PreparedStatement pStmt = connection.prepareStatement(sql);

        pStmt.setString(1, username);
        pStmt.setString(2, password);
        pStmt.setInt(3, id);
        pStmt.setString(4, sex);

        int i = pStmt.executeUpdate();
//        释放资源

        pStmt.close();
        connection.close();
    }
}
