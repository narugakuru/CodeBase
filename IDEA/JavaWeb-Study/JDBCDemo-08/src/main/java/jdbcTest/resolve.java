package jdbcTest;

import java.sql.*;

public class resolve {
    private static String URL = "jdbc:mysql://localhost:3306/userdata?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    //取得驱动程序
    private static String DRIVER = "com.mysql.jdbc.Driver";
    //取得用户
    private static String USER = "root";
    //登录密码
    private static String PASSWORD = "root";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
            connection = DriverManager.getConnection(URL, USER, PASSWORD);//连接数据库
            statement = connection.createStatement();//获取sql编译对象,statement只能执行静态sql
            String sql = "select * from student where id = " + id;
            resultSet = statement.executeQuery(sql);//执行sql语句

/*            String sql = "select * from student where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,114514);
            ResultSet resultSet = preparedStatement.executeQuery();*/

            while (resultSet.next()) {
                System.out.println(resultSet);//捕获并打印异常
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            //关闭所有连接
            resultSet.close();
            statement.close();
            connection.close();
        }
    }


}

