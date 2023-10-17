package jdbcTest;

import java.sql.*;

public class InsertDemo {
    public static void main(String[] args) throws Exception {
        JDBCTools tools = null;
        Connection conn = null;
        PreparedStatement pStmt = null;
        String username = "Raisei";
        String password = "14514";
        int id = 233;
        String sex = "?";
        try {
            tools = new JDBCTools();
            conn = JDBCTools.getConnection();
            String sql="insert into signin(usename, password, id, sex) values (?,?,?,?)";
            pStmt = tools.getPreparedStatement(conn, sql);
            pStmt.setString(1, username);
            pStmt.setString(2, password);
            pStmt.setInt(3, id);
            pStmt.setString(4, sex);
//        预编译之后就不要插入sql语句了
            pStmt.executeUpdate(sql);
            System.out.println("插入成功");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            tools.closePreparedStatement(pStmt);
            tools.closeConnection(conn);
        }




    }
}
