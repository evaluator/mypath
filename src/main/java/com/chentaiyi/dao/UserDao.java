package com.chentaiyi.dao;

import com.chentaiyi.common.dao.BaseDao;
import com.chentaiyi.common.plugs.Page;
import com.chentaiyi.common.plugs.PageRequest;
import com.chentaiyi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

/**
 * Created by hasee on 2017/10/12.
 */
@Repository
public class UserDao extends BaseDao<User> {

    private final String MATCH_COUNT_SQL = " SELECT count(*) FROM user WHERE phone=? and password=?";
    private final String FIND_USER_SQL = " SELECT * FROM user WHERE phone=?";
    private final String INSERT_USER_SQL = " INSERT INTO user(user_name,phone,password,last_ip,last_visit) "+
                                                " VALUES(?,?,?,?,?)";
    private final String UPDATE_LOGINFO_SQL = " UPDATE user SET last_ip=?,last_visit=? WHERE user_id=?";
    private final String UPDATE_PASSWORD_SQL = " UPDATE user SET password = ? WHERE user_id = ?";
    private final String DELETE_USER_SQL = " DELETE FROM user Where user_id=?";
    private final String QUERY_ALL_USERS_SQL =" SELECT * FROM user";

    /*
     *   查找用户名密码匹配的记录数
     */
    public int getMatchCount(String userName,String passWord){
        return jdbcTemplate.queryForInt(MATCH_COUNT_SQL,new Object[] {userName,passWord});
    }

    /*
     *   根据不同信息查找用户
     */
    public <T> User findUser(final T data){
        User user = getRowForObject(FIND_USER_SQL, new Object[]{data}, User.class);
        return user;
    }

    /*
     *   插入一条用户记录
     */
    public long insertUser(User user){
         Object[] args = { user.getUserName(),user.getPhone(),user.getPassword(),user.getLastIp(),user.getLastVisit()};
         int[] types = { Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.DATE};
         return insertAndGetId(INSERT_USER_SQL,args,null);
    }

    /*
     *   更新登录记录
     */
    public int updateLogInfo(User user){
        Object[] args = {user.getLastIp(),user.getLastVisit(),user.getUserId()};
        return addUpdateDelete(UPDATE_LOGINFO_SQL, args, null);
    }

    /*
     *   密码更新
     */

    public int updatePassword(final long userId,final String passWord){
       return addUpdateDelete(UPDATE_PASSWORD_SQL,new Object[] {passWord,userId},null);
    }
    /*
     *   删除密码
    */
    public int deleteUser(final long userId){
        return addUpdateDelete(DELETE_USER_SQL,new Object[]{userId},null);
    }

    /*
     *   调取一页用户记录
    */

    public Page<User> getPagedUser(final int pageNo,final int pageSize){
        PageRequest pageRequest = new PageRequest(pageNo,pageSize);
        return findPage(QUERY_ALL_USERS_SQL,null,pageRequest,User.class);
    }

}
