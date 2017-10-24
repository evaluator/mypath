package com.chentaiyi.service.impl;

import com.chentaiyi.dao.LoginLogDao;
import com.chentaiyi.dao.UserDao;
import com.chentaiyi.domain.LoginLog;
import com.chentaiyi.domain.User;
import com.chentaiyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hasee on 2017/10/23.
 */
@Service
public class UserServiceImpl  implements UserService{
    private UserDao userDao;
    private LoginLogDao loginLogDao;
    @Autowired
    void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    @Autowired
    void setLoginLogDao(LoginLogDao loginLogDao){
        this.loginLogDao = loginLogDao;
    }

    public boolean hasMatchUser(String phone, String passWord) {
        int count = userDao.getMatchCount(phone,passWord);
        return count > 0;
    }

    public <T> User findUserByData(T data) {
        return userDao.findUser(data);
    }
    @Transactional
    public void loginSuccess(User user) {
        userDao.updateLogInfo(user);
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        loginLogDao.updateLoginLog(loginLog);
    }

    @Transactional
    public boolean createUser(User user) {
        return userDao.insertUser(user);
    }

    @Transactional
    public boolean updatePassword(int userId, String password) {
        return userDao.updatePassword(userId,password);
    }
}
