package com.chentaiyi.common.plugs.Impl;

import com.chentaiyi.common.plugs.ISQLPageHandler;

/**
 * Created by hasee on 2017/11/13.
 */
public class MySQLPageHandlerImpl implements ISQLPageHandler {
    public String convertPageSQL(String originSQL,int pageNo,int pageSize){
        StringBuffer sql = new StringBuffer(originSQL);
        if (pageSize > 0) {
            int firstResult = (pageNo - 1)*pageSize;
            if (firstResult <= 0) {
                sql.append(" limit ").append(pageSize);
            } else {
                sql.append(" limit ").append(firstResult).append(",")
                        .append(pageSize);
            }
        }
        return sql.toString();
    }
    public int getTotalItems(String querySQL){
        int count =0;
        return count;
    }
}
