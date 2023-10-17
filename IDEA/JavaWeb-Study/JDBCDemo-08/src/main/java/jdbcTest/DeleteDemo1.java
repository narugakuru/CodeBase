package jdbcTest;

import java.sql.*;

public class DeleteDemo1 {
    public static void main(String[] args) {
        JDBCTools tools = new JDBCTools();
        Connection conn = tools.getConnection();
        Statement statement = tools.getStatement(conn);
        

    }
}
