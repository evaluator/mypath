package com.chentaiyi.web;

import com.chentaiyi.domain.User;
import com.chentaiyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

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

    @RequestMapping("/login")
    @ResponseBody
    public ModelMap login(HttpServletRequest request,User user){
        ModelMap mm = new ModelMap();
        User dbUser = userService.findUserByData(user.getPhone());
        if(dbUser == null){
            mm.addAttribute(ERROR_CODE,1001);
            mm.addAttribute(ERROR_MSG, "用户不存在");
        }else if(!dbUser.getPassword().equals(user.getPassword())){
            mm.addAttribute(ERROR_CODE,1002);
            mm.addAttribute(ERROR_MSG,"密码错误");
        }else{
            dbUser.setLastIp(request.getRemoteAddr());
            String tsStr = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss").format(new Date());
            Timestamp ts = Timestamp.valueOf(tsStr);
            dbUser.setLastVisit(ts);
            userService.loginSuccess(user);
            setSessionUser(request, dbUser);
            mm.addAttribute(ERROR_CODE, 0);
            mm.addAttribute(ERROR_MSG,"success");
        }
        return mm;
    }

    @RequestMapping("/testm")
    public String  testAccess(){
        return "testmav";
    }
}
