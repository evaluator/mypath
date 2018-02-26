package com.chentaiyi.web;

import com.alibaba.fastjson.JSON;
import com.chentaiyi.domain.User;
import com.chentaiyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hasee on 2017/12/2.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
    private UserService userService;
    @Autowired
    void setUserService(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(value="/login")
    @ResponseBody
    public Map<String,Object> login(HttpServletRequest request, @RequestBody User user){
        Map<String,Object> map = new HashMap<String,Object>();
        User dbUser = userService.findUserByData(user.getPhone());
        if(dbUser == null){
            map.put(ERROR_CODE,1001);
            map.put(ERROR_MSG, "用户不存在");
        }else if(!dbUser.getPassword().equals(user.getPassword())){
            map.put(ERROR_CODE, 1002);
            map.put(ERROR_MSG, "密码错误");
        }else{
            dbUser.setLastIp(request.getRemoteAddr());
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String tsStr = df.format(new Date());
            Timestamp ts = Timestamp.valueOf(tsStr);
            dbUser.setLastVisit(ts);
            userService.loginSuccess(user);
            setSessionUser(request, dbUser);
            map.put(ERROR_CODE, 0);
            map.put(ERROR_MSG,"success");
        }
        return map;
    }

    @RequestMapping("/testm")
    @ResponseBody
    public String  testAccess(){
        return "error:2000";
    }
}
