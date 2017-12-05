package com.chentaiyi.dao;

import com.chentaiyi.common.plugs.Page;
import com.chentaiyi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import java.util.List;

import static org.testng.Assert.*;

/**
 * Created by hasee on 2017/11/21.
 */
@ContextConfiguration("classpath*:/mypath-context.xml")
public class UserDaoTest extends AbstractTransactionalTestNGSpringContextTests {


    private UserDao userDao;
    @Autowired
    void setUserDao(UserDao userDao){
        this.userDao = userDao;
    }
    private long phone= 1300000029;

    @Test
    public void testGetMatchCount() throws Exception {

    }

    @Test
    public void testFindUser() throws Exception {

    }

    @Rollback(false)
    @Test
    public void testInsertUser() throws Exception {
        User user = new User();
        user.setUserName("aaa");
        user.setPassword("123456");
        user.setPhone(String.valueOf(phone));
        user.setLastIp("192.168.1.5");
        long id= userDao.insertUser(user);
        assert (id > 0);
    }

    @Test
    public void testUpdateLogInfo() throws Exception {

    }

    @Rollback(false)
    @Test
    public void testUpdatePassword() throws Exception {
        int count = userDao.updatePassword(25,"456");
        assertTrue(count==1);
    }
    //@Rollback(false)
    @Test
    public void testDeleteUser() throws Exception {
        int count = userDao.deleteUser(25);
        assertTrue(count == 1);
    }


    @Test
    public void testGetPagedUser() throws Exception {
        Page<User> userPage = userDao.getPagedUser(1, 5);
        List<User> userList = userPage.getResult();
        User user = userList.get(0);
        assertTrue(user.getUserId()==(long)1);
    }
}