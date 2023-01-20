package dao;

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
 * @ClassName: personDaoImp
 * @Author: JN
 * @Description:
 * @Date: 2020/11/27 9:52
 * @Version: 1.0
 */
public class personDaoImp implements personDao {

    public boolean exitFriend(Connection con,String fName) throws SQLException {
        ResultSet rs = null;
        boolean flag = false;
        if (con!=null){
            String sql = "select * from `person` where name = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, fName);
            rs = pstmt.executeQuery();
            if (rs.next()){
                flag = true;
            }else {
                flag = false;
            }
        }
        return flag;
    }
    /***
     *@para 根据好友名称查找好友
     *@return
     */
    @Override
    public person selectFriend(Connection con, String FName) throws SQLException {
        person resultPerson = null;
        if (con != null) {
            String sql = "select * from person where name = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, FName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                resultPerson = new person();
                resultPerson.setId(rs.getInt("id"));
                resultPerson.setName(FName);
                resultPerson.setPhone(rs.getString("phone"));
                resultPerson.setMobile(rs.getString("mobile"));
                resultPerson.setAddress(rs.getString("address"));
                resultPerson.setGender(rs.getString("gender"));
                resultPerson.setGroupName(rs.getString("groupName"));
            }
        }
        return resultPerson;
    }


    /***
     *@para 增加好友
     *@return
     */
    @Override
    public int addFriend(Connection con, person per) throws SQLException {
        int rs = 0;
        if (con != null) {
            String sql = "insert into `person`(id,name,phone,mobile,address,gender,groupName) values (?,?,?,?,?,?,?)";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, per.getId());
            pstmt.setString(2, per.getName());
            pstmt.setString(3, per.getPhone());
            pstmt.setString(4, per.getMobile());
            pstmt.setString(5, per.getAddress());
            pstmt.setString(6, per.getGender());
            pstmt.setString(7, per.getGroupName());
            rs = pstmt.executeUpdate();
        }
        return rs;
    }



    /***
     *@para 删除好友
     *@return
     */
    @Override
    public int deleteFriend(Connection con, String FName) throws SQLException {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != con){
            String sql = "delete from `person` where name=?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,FName);
            flag = pstm.executeUpdate();
        }
        return flag;
    }



    /***
     *@para 根据好友名和组名删除好友
     *@return
     */
    @Override
    public int deleteFriendByDG(Connection con, String FName, String GName) throws SQLException {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != con){
            String sql = "delete from `person` where name=? and groupName = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,FName);
            pstm.setString(2,GName);

            flag = pstm.executeUpdate();
        }
        return flag;
    }


    //修改
    @Override
    public int updateFriend(Connection con, person per) throws SQLException {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != con){
            String sql = "update `person` set phone=?,mobile=?,address=?,gender=?,groupName=? where name = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,per.getPhone());
            pstm.setString(2,per.getMobile());
            pstm.setString(3,per.getAddress());
            pstm.setString(4,per.getGender());
            pstm.setString(5,per.getGroupName());
            pstm.setString(6,per.getName());
            flag = pstm.executeUpdate();
        }
        return flag;
    }

    @Override
    public List<person> findPByGName(Connection con, String GName) throws SQLException {
        ArrayList<person> people = new ArrayList<>();
        person resultPerson = null;
        if (con != null) {
            String sql = "select * from person where groupName = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, GName);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                resultPerson = new person();
                resultPerson.setId(rs.getInt("id"));
                resultPerson.setName(rs.getString("name"));
                resultPerson.setPhone(rs.getString("phone"));
                resultPerson.setMobile(rs.getString("mobile"));
                resultPerson.setAddress(rs.getString("address"));
                resultPerson.setGender(rs.getString("gender"));
                resultPerson.setGroupName(rs.getString("groupName"));
                people.add(resultPerson);
            }
        }
        return people;
    }

    /**
     *@para  根据好友名称更新组名
     *@return
     */

    public int updateFGName(Connection con, String FName,String NGName) throws SQLException {
        PreparedStatement pstm = null;
        int flag = 0;
        if(null != con){
            String sql = "update `person` set groupName=? where name = ?";
            pstm = con.prepareStatement(sql);
            pstm.setString(1,NGName);
            pstm.setString(2,FName);
            flag = pstm.executeUpdate();
        }
        return flag;
    }

    /***
     *@para  根据name和phone进行登录
     *@return
     */
    @Override
    public boolean Login(Connection con, String name, String phone) throws SQLException {
        ResultSet rs = null;
        boolean flag = false;
        if (con!=null){
            String sql = "select * from `person` where name = ? and phone = ?";
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setString(2, phone);
            rs = pstmt.executeQuery();
            if (rs.next()){
                flag = true;
            }else {
                flag = false;
            }
        }
        return flag;
    }


    public static void main(String[] args) throws SQLException {

        jdbc jdbc = new jdbc();
        Connection con = jdbc.getConnection();
        personDaoImp pD = new personDaoImp();
        int i = pD.deleteFriendByDG(con, "Smith", "朋友");
        System.out.println(i);
    }
}
