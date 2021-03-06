package com.chentaiyi.dao;

import com.chentaiyi.common.dao.BaseDao;
import com.chentaiyi.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by hasee on 2017/10/18.
 */
@Repository
public class LoginLogDao extends BaseDao<LoginLog> {

    private  final String INSERT_LOGINLOG_SQL = " INSERT INTO login_log(userid,ip,logindate) VALUES(?,?,?) ";

    public long insertLoginLog(LoginLog log){
        Object[] args = {log.getUserId(),log.getIp(),log.getLoginDate()};
        return addUpdateDelete(INSERT_LOGINLOG_SQL,args,null);
    }

}
