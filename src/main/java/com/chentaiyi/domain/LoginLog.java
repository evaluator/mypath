package com.chentaiyi.domain;

import java.io.Serializable;
import java.util.Date;


/**
 * Created by hasee on 2017/10/12.
 */
public class LoginLog implements Serializable {
    private long loginLogId;
    private long userId;
    private String ip;
    private Date loginDate;

    public long getLoginLogId() {
        return loginLogId;
    }

    public  void setLoginLogId(long loginLogId){this.loginLogId = loginLogId;}

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }
}
