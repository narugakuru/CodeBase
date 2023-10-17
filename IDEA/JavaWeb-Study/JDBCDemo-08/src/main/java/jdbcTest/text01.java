package jdbcTest;

import java.sql.*;

public class text01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String URL = "jdbc:mysql://localhost:3306/userdata?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
        String DRIVER = "com.mysql.jdbc.Driver";
        String USER = "root";
        String PASSWORD = "root";

        Class.forName(DRIVER);

        Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

//        向数据库发送sql语句
        Statement statement = connection.createStatement();

        String sql="select * from user";

        ResultSet query = statement.executeQuery(sql);

        while(query.next()){
            System.out.println("id:"+query.getObject("id"));
            System.out.println("name:"+query.getObject("name"));
            System.out.println("sex:"+query.getObject("sex"));
        }
//        释放资源
        query.close();
        statement.close();
        connection.close();
    }

}


