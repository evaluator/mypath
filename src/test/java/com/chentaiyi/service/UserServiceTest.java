package com.chentaiyi.service;

import com.chentaiyi.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hasee on 2017/10/23.
 */
@ContextConfiguration("classpath*:/mypath-context.xml")
public class UserServiceTest extends AbstractTransactionalTestNGSpringContextTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private UserService userService;
    @Autowired
    void setUserService(UserService userService){
        this.userService = userService;
    }

    @Test
    public void hasMatchUser(){
         boolean b1 = userService.hasMatchUser("13575714535","123456");
         boolean b2 = userService.hasMatchUser("13575714535","123");
         boolean b3 = userService.hasMatchUser("13555555555","123456");
        assert(b1==true);
        assert(b2==false);
        assert(b3==false);
    }
    /*
    @Test

    public void createUser(){


        final User user  = new User();
        user.setPhone("13968131600");
        user.setPassword("123");
        user.setName("jiying");
        boolean success= userService.createUser(user);

        //assert(success == true);

    }*/

    @Test
    public void findUser(){
        User user = userService.findUserByData("13575714535");
        String userName = user.getName();
        assert(userName=="chentaiyi");
    }
}