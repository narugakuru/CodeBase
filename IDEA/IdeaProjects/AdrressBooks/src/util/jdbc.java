package util;

import com.mysql.jdbc.Driver;

import java.sql.*;

/**
 * @ProjectName: AdrressBooks
 * @Package: util
 * @ClassName: jdbc
 * @Author: JN
 * @Description: jdbc
 * @Date: 2020/11/27 8:48
 * @Version: 1.0
 */
public class jdbc {

    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        init();
    }


    public static void init()  {
        url = "jdbc:mysql://localhost:3306/rubbish?serverTimezone=GMT";
        username = "root";
        password = "noesis";

    }

    /**
     * 获取数据库连接
     * @return
     */
    public  Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
           e.printStackTrace();
        }

        return connection;
    }
    /**
     * 查询操作
     * @param connection
     * @param pstm
     * @param rs
     * @param sql
     * @param params
     * @return
     */
    public static ResultSet execute(Connection connection, PreparedStatement pstm, ResultSet rs,
                                    String sql, Object[] params) throws Exception{
        pstm = connection.prepareStatement(sql);
        //给占位符复制，占位符从1开始，数组从0开始
        for(int i = 0; i < params.length; i++){
            pstm.setObject(i+1, params[i]);
        }
        rs = pstm.executeQuery();
        return rs;
    }
    /**
     * 更新操作
     * @param connection
     * @param pstm
     * @param sql
     * @param params
     * @return
     * @throws Exception
     */
    public static int execute(Connection connection,PreparedStatement pstm,
                              String sql,Object[] params) throws Exception{
        int updateRows = 0;
        pstm = connection.prepareStatement(sql);
        for(int i = 0; i < params.length; i++){
            pstm.setObject(i + 1, params[i]);

        }
        updateRows = pstm.executeUpdate();
        return updateRows;
    }

    /**
     * 释放资源
     * @param connection
     * @param pstm
     * @param rs
     * @return
     */
    public static boolean closeResource(Connection connection,PreparedStatement pstm,ResultSet rs){
        boolean flag = true;
        if(rs != null){
            try {
                rs.close();
                rs = null;//GC回收
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                flag = false;
            }
        }
        if(pstm != null){
            try {
                pstm.close();
                pstm = null;//GC回收
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                flag = false;
            }
        }
        if(connection != null){
            try {
                connection.close();
                connection = null;//GC回收
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                flag = false;
            }
        }

        return flag;
    }


    public static void main(String[] args) {

        jdbc j = new jdbc();
        Connection con = j.getConnection();
        if (con!=null){
            System.out.println("数据库连接成功！");
        }else {
            System.out.println("数据库连接失败！");
        }

    }

}
