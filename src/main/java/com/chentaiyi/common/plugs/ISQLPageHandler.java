package com.chentaiyi.common.plugs;

/**
 * Created by hasee on 2017/11/13.
 */
public interface ISQLPageHandler {
    public String convertPageSQL(String originSQL,int pageNo,int pageSize);
}
