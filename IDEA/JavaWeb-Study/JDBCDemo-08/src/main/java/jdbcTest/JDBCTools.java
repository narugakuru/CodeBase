package jdbcTest;

import java.sql.*;

public class JDBCTools {
    private static String URL = "jdbc:mysql://localhost:3306/userdata?useSSL=false&useUnicode=true&characterEncoding=UTF-8";
    //取得驱动程序
    private static String DRIVER = "com.mysql.jdbc.Driver";
    //取得用户
    private static String USER = "root";
    //登录密码
    private static String PASSWORD = "root";

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");//加载数据库驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public JDBCTools() {
    }

    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);//连接数据库
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static void closeConnection(Connection conn) {
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Statement getStatement(Connection conn) {
        Statement stmt = null;
        try {
            stmt = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }

    public static PreparedStatement getPreparedStatement(Connection conn, String sql) {
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pstmt;
    }

    public static void closePreparedStatement(PreparedStatement pStmt) {
        try {
            if (pStmt != null) {
                pStmt.close();
                pStmt = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static ResultSet executeQuery(Statement stmt, String sql) {
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet executeQuery(Connection conn, String sql) {//重载
        ResultSet rs = null;
        try {
            rs = conn.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
