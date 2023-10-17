package util;

import entity.Student;

import java.sql.*;

public class testDBBug extends JDBCTools {
    public static void main(String[] args) throws Exception{
        Student student = selectStudent("123");
        System.out.println(student.toString());
    }

    public static Student selectStudent(String id) {

        Connection connection = null;
        Statement statement = null;
        PreparedStatement prepStatement = null;
        Student stu = new Student();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = getConnection();
//            String sql = "select * from student where id = ?";
            String sql="select * from student";

            statement = getStatement(connection);
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet.next()) {
                stu.setId(resultSet.getInt("id"));
                stu.setAge(resultSet.getInt("age"));
                stu.setName(resultSet.getString("name"));
                stu.setSex(resultSet.getString("sex"));
                System.out.println("数据以传入stu");
            }


/*            prepStatement = getPreparedStatement(connection, sql);
            prepStatement.setInt(1, Integer.parseInt(id));

            ResultSet resultSet = prepStatement.executeQuery();

            if(resultSet != null){
                stu.setId(resultSet.getInt("id"));
                stu.setAge(resultSet.getInt("age"));
                stu.setName(resultSet.getString("name"));
                stu.setSex(resultSet.getString("sex"));
                stu.toString();
            }*/

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
//            closeStatement(prepStatement);
            closeStatement(statement);
            closeConnection(connection);
        }

        return stu;

    }
}
