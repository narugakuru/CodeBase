package util;

import entity.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DBStudent extends JDBCTools {
    private static String DRIVER = "com.mysql.jdbc.Driver";

    static {
        try {
            Class.forName(DRIVER);//加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public DBStudent() {
    }

    public static List<Student> selectQuery() {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Student> users = null;
        try {
            connection = DBStudent.getConnection();
            String sql = "select * from student";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setName(resultSet.getString("name"));
                student.setSex(resultSet.getString("sex"));
                users.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return users;
    }


    //    插入数据
    public static int register(Student stu) {
        int result = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {

            Class.forName("com.mysql.jdbc.Driver");
            connection = getConnection();

            String sql = "insert into student(id,name,age,sex) value(?,?,?,?)";
            preparedStatement = getPreparedStatement(connection, sql);

            preparedStatement.setInt(1, stu.getId());
            preparedStatement.setString(2, stu.getName());
            preparedStatement.setInt(3, stu.getAge());
            preparedStatement.setString(4, stu.getSex());
            preparedStatement.executeUpdate();

            System.out.println("Student插入完毕" + stu.toString());

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {//关闭资源
            closePreparedStatement(preparedStatement);
            closeConnection(connection);
        }
        return result;
    }

    //    按学号查询
    public static Student selectId(String id) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Student stu = new Student();
        try {
            connection = getConnection();
            String sql = "select * from student where id = ?";
            preparedStatement = getPreparedStatement(connection, sql);
            preparedStatement.setInt(1, Integer.parseInt(id));
            ResultSet resultSet = preparedStatement.executeQuery();
//            将查询到的数据传入stu
            if (resultSet.next()) {
                stu.setId(resultSet.getInt("id"));
                stu.setAge(resultSet.getInt("age"));
                stu.setName(resultSet.getString("name"));
                stu.setSex(resultSet.getString("sex"));
                System.out.println("数据以传入stu");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStatement(preparedStatement);
            closeConnection(connection);
        }
        return stu;
    }

    //    修改数据
    public static int update(Student stu) {
        int result = 0;
        Connection connection = null;
        PreparedStatement prepStatement = null;

        try {
            Class.forName(DRIVER);
            connection = getConnection();

            String sql = "update student set name=?,age=?,sex=? where id=?";

            prepStatement = getPreparedStatement(connection, sql);
//
            prepStatement.setString(1, stu.getName());
            prepStatement.setInt(2, stu.getAge());
            prepStatement.setString(3, stu.getSex());
            prepStatement.setInt(4, stu.getId());

            prepStatement.executeUpdate();
            System.out.println(stu.getId() + "的信息修改了");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(prepStatement);
            closeConnection(connection);
        }
        return result;
    }

    public static int delete(String id) {
        Connection connection = null;
        PreparedStatement prepStatement = null;
        try {
            Class.forName(DRIVER);
            connection = getConnection();
            String sql = "delete from student where id = ?";
            prepStatement = getPreparedStatement(connection, sql);
            prepStatement.setInt(1, Integer.parseInt(id));
//            int result = prepStatement.executeUpdate();
            prepStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            closePreparedStatement(prepStatement);
            closeConnection(connection);
        }
        return 0;
    }


    /*模板
    int result = 0;
    Connection connection = null;
    Statement statement = null;

        try {
        Class.forName(DRIVER);
        connection = getConnection();
        statement = getStatement(connection);
        String sql = "";

    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    } finally {
        closeStatement(statement);
        closeConnection(connection);
    }
        return 0;*/
}
