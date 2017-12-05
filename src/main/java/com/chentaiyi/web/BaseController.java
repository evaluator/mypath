package com.chentaiyi.web;

import com.chentaiyi.domain.User;
import sun.net.httpserver.HttpServerImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by hasee on 2017/12/2.
 */
public class BaseController {
    protected static final String ERROR_CODE = "errorCode";
    protected static final String ERROR_MSG = "errorMSG";
    protected static final String USER_CONTEXT="userContext";

    protected User getSessionUser(HttpServletRequest request){
         return (User) request.getSession().getAttribute(USER_CONTEXT);
    }

    protected void setSessionUser(HttpServletRequest request,User user){
        request.getSession().setAttribute(USER_CONTEXT,user);
    }
}
