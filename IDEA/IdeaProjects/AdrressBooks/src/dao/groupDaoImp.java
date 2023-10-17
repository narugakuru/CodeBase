package dao;

import poji.group;
import poji.person;
import util.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @ProjectName: AdrressBooks
 * @Package: dao
 * @ClassName: groupDaoImp
 * @Author: JN
 * @Description: 1
 * @Date: 2020/11/27 9:51
 * @Version: 1.0
 */
public class groupDaoImp implements groupDao {


    private Object group;


    /***
     *@para  查找所有组,返回组名
     *@return
     */
    public List<String> findGroup(Connection con) throws SQLException {
        List<String> gName = new ArrayList<>();
        if (con != null) {
            String sql = "select name from `group`;";
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
           while (rs.next()) {
               String name = rs.getString("name");
               gName.add(name);
           }
        }
        return gName ;
    }

    /***
     *@para  根据组名判断是否存在组
     *@return
     */
    public boolean exitGroup(Connection con,String gName) throws SQLException {
        ResultSet rs = null;
        boolean flag = false;
        if (con!=null){
            String sql = "select * from `group` where name = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, gName);
            rs = pstmt.executeQuery();
            if (rs.next()){//这里不能为rs
                flag = true;
            }else {
                flag = false;
            }
        }
        return flag;
    }

    /***
     *@para  根据组名增加组
     *@return
     */
    @Override
    public int addGroup(Connection con,String GName) throws SQLException {
        int rs = 0;
        if (con != null) {
            String sql = "insert into `group`(name) values (?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, GName);
            rs = pstmt.executeUpdate();
        }
        return rs;
    }


    /***person
     *@para  根据组名删除组
     *@return
     */
    @Override
    public int deleteGroup(Connection con,String GName) throws SQLException {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != con){
            String sql = "delete  from `group` where name=?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,GName);
            flag = pstm.executeUpdate();
        }
        return flag;
    }

    /***
     *@para  根据原组名更新新组
     *@return
     */
    @Override
    public int updateGroup(Connection con,String GName,String NewName) throws SQLException {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != con){
            String sql = "update `group` set name = ? where name = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,NewName);
            pstm.setString(2,GName);
            flag = pstm.executeUpdate();
        }
        return flag;
    }


    /**
     *@para   判断组中是否存在好友
     *@return
     */

    public boolean GExitFriend(Connection con,String GName) throws SQLException {
        ResultSet rs = null;
        boolean flag = false;
        if (con!=null){
            String sql = "select * from `person` where groupName = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, GName);
            rs = pstmt.executeQuery();
            if (rs.next()){//这里不能为rs
                flag = true;
            }else {
                flag = false;
            }
        }
        return flag;
    }

    /***
     *@para  查找组中所有的好友
     *@return
     */
    public List<String> FindFriendByGroup(Connection con,String GName) throws SQLException {
        ArrayList friends = new ArrayList();
        String FName = null;
        ResultSet rs = null;
        if (con!=null){
            String sql = "select name from `person` where groupName = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, GName);
            rs = pstmt.executeQuery();
            while (rs.next()){
                FName=rs.getString("name");
                friends.add(FName);
            }
        }
        return friends;
    }


    public static void main(String[] args) throws SQLException {
        groupDaoImp gImp = new groupDaoImp();
        jdbc jdbc = new jdbc();
        Connection con = jdbc.getConnection();
        int i = gImp.deleteGroup(con, "基友");

    }
}
