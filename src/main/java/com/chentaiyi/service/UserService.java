package com.chentaiyi.service;

import com.chentaiyi.domain.User;

/**
 * Created by hasee on 2017/10/20.
 */
public interface UserService {
     public boolean hasMatchUser(String userName,String passWord);
     public <T> User findUserByData(T data);
     public void LoginSuccess(User user);
     public void createUser(User user);
     public void updatePassword(int userId,String password);
}