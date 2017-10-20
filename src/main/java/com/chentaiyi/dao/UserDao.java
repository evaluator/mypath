package com.chentaiyi.dao;

import com.chentaiyi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hasee on 2017/10/12.
 */
@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;
    private final String MATCH_COUNT_SQL = " SELECT count(*) FROM user WHERE username=? and password=?";
    private final String INSERT_USER_SQL = " INSERT INTO user(username,phone,password,lastip,lastvisit) "+
                                                " VALUES(?,?,?,?,?)";
    private final String UPDATE_LOGINFO_SQL = " UPDATE user SET lastip=?,lastvisit=? WHERE userid=?";
    private final String UPDATE_PASSWORD_SQL = " UPDATE user SET password = ? WHERE userid = ?";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;

    }
    public int getMatchCount(String userName,String passWord){
        return jdbcTemplate.queryForInt(MATCH_COUNT_SQL,new Object[] {userName,passWord});
    }

    public <T> User findUser(final T data){
        final User user = new User();
        jdbcTemplate.query(MATCH_COUNT_SQL, new Object[]{data}, new RowCallbackHandler() {
            public void processRow(ResultSet resultSet) throws SQLException {
                user.setUserId(resultSet.getInt("userid"));
                user.setName(resultSet.getString("username"));
                user.setPhone(resultSet.getString("phone"));
                user.setLastIp(resultSet.getString("lastip"));
                user.setLastVisit(resultSet.getTimestamp("lastvisit"));
            }
        });
        return user;
    }

    public void insertUser(User user){
         Object[] args = {user.getName(),user.getPhone(),user.getPassword(),user.getLastIp(),user.getLastVisit()};
         jdbcTemplate.update(INSERT_USER_SQL, args);
    }

    public void updateLogInfo(User user){
        Object[] args = {user.getLastIp(),user.getLastVisit(),user.getUserId()};
        jdbcTemplate.update(UPDATE_LOGINFO_SQL,args);
    }

    public void updatePassword(final int userId,final String passWord){
        jdbcTemplate.update(UPDATE_PASSWORD_SQL,new Object[] {passWord,userId});
    }

}
