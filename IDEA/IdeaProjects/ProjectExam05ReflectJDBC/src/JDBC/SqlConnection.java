package JDBC;

import java.sql.*;

public class SqlConnection {

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        Statement statement;
        ResultSet resultSet;

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (Exception e) {
            System.out.println(e);
        }

        String url = "jdbc:mysql://localhost:3306/数据库名?useSSL=false&serverTimezone=UTC";
        String user = "数据库用户名";
        String password = "数据库密码";

        //连接数据库
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            //TODD:handle exception
            System.out.println(e);
        }

        //创建一个statement对象，封装SQL语句发送给数据库
        statement = (Statement) connection.createStatement();

        //填入SQL语句
        resultSet = ((java.sql.Statement) statement).executeQuery("select * from test where c_grade>60&&java_grade>60");

        //获取并输出信息
        while (resultSet.next()) {
            String name = resultSet.getString(1);
            double c_grade = resultSet.getDouble(2);
            double java_grade = resultSet.getDouble(3);
            String stu_id = resultSet.getString(4);
            System.out.println("所有成绩大于60的学生： " + "姓名" + name
                    + " " + c_grade
                    + " " + java_grade
                    + " id " + stu_id);
        }

        //close
        resultSet.close();
        statement.close();
        connection.close() ;

    }

}
