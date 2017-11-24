package com.chentaiyi.service;

import com.chentaiyi.domain.User;

/**
 * Created by hasee on 2017/10/20.
 */
public interface UserService {
     public boolean hasMatchUser(String phone,String passWord);
     public <T> User findUserByData(T data);
     public void loginSuccess(User user);
     public long createUser(User user);
     public boolean updatePassword(int userId,String password);
}
