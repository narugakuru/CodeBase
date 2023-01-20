package dao;

import poji.person;

import java.sql.ClientInfoStatus;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @ProjectName: AdrressBooks
 * @Package: dao
 * @ClassName: personDao
 * @Author: JN
 * @Description: 2
 * @Date: 2020/11/27 9:51
 * @Version: 1.0
 */
public interface personDao {


    /***
     *@para  根据好友名判断是否存在好友
     *@return
     */
    public boolean exitFriend(Connection con,String fName) throws SQLException ;
    /***
     *@para 根据好友名称查找好友
     *@return
     */
    public person selectFriend(Connection con,String FName) throws SQLException;


    /***
     *@para 增加好友
     *@return
     */
    public int addFriend (Connection con,person per) throws SQLException;


    /***
     *@para 根据好友名删除好友
     *@return
     */
    public  int deleteFriend(Connection con,String FName) throws SQLException;


    /***
     *@para 根据好友名和组名删除好友
     *@return
     */
    public int deleteFriendByDG(Connection con,String FName,String GName)throws  SQLException;


    /***
     *@para 根据好友名称更新好友信息
     *@return
     */
    public  int updateFriend(Connection con,person per) throws SQLException;


    /***
     *@para  根据组名查找好友
     *@return
     */

    public List<person> findPByGName(Connection con,String GName) throws  SQLException;


    /**
     *@para  根据好友名称更新组名
     *@return
     */
    public int updateFGName(Connection con, String FName,String GName) throws SQLException;

    /***
     *@para  根据name和phone进行登录
     *@return
     */

    public  boolean Login(Connection con,String name,String phone) throws SQLException;


}
