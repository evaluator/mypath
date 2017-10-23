package com.chentaiyi.dao;

import com.chentaiyi.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by hasee on 2017/10/18.
 */
@Repository
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;
    private  final String INSERT_LOGINLOG_SQL = " INSERT INTO login_log(userid,ip,logindate) VALUES(?,?,?) ";

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean updateLoginLog(LoginLog log){
        Object[] args = {log.getUserId(),log.getIp(),log.getLoginDate()};
        int count = jdbcTemplate.update(INSERT_LOGINLOG_SQL,args);
        return count > 0;
    }

}
