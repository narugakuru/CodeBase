package jdbcTest;

import java.sql.*;

public class SelectWhere extends JDBCTools{
    public static void main(String[] args) throws SQLException {
        Connection connection = getConnection();
        Statement statement = getStatement(connection);
        int id = 123;
        String sql = "select * from student where id = "+id;
        PreparedStatement preparedStatement = getPreparedStatement(connection, sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            System.out.println(resultSet);
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}
