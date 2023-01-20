package dao;

import poji.group;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: AdrressBooks
 * @Package: dao
 * @ClassName: groupDao
 * @Author: JN
 * @Description: 1
 * @Date: 2020/11/27 9:50
 * @Version: 1.0
 */
public interface groupDao {


    /***
     *@para  查找所有组
     *@return
     */
    public List<String> findGroup(Connection con) throws SQLException ;

    /***
     *@para  根据组名判断是否存在组
     *@return
     */
    public boolean exitGroup(Connection con,String gName) throws SQLException ;
    /***
     *@para  根据组名增加组
     *@return
     */
    public int addGroup (Connection con ,String GName) throws SQLException;


    /***
     *@para  根据组名删除组
     *@return
     */
    public  int deleteGroup(Connection conn,String GName) throws SQLException;

    /***
     *@para  根据原组名组名更新新组
     *@return
     */
     public  int updateGroup(Connection con,String GName,String NewName) throws SQLException;



    /**
     *@para   判断组中是否存在好友
     *@return
     */

    public boolean GExitFriend(Connection con,String GName) throws SQLException ;


    /***
     *@para  查找组中所有的好友
     *@return
     */
    public List<String> FindFriendByGroup(Connection con,String GName) throws SQLException;


}
