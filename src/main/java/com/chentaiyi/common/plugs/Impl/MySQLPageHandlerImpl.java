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

    public String convertCountSQL(String querySQL){
        String uppercaseStr = querySQL.toUpperCase();
        int indexSelect = uppercaseStr.indexOf("SELECT");
        int indexFrom = uppercaseStr.indexOf("FROM");
        if(indexSelect < 0 || indexFrom <0){
            return null;
        }else{
            StringBuffer buf = new StringBuffer(uppercaseStr.substring(indexFrom));
            String sql = "SELECT COUNT(*) "+buf.toString();
            return sql;
        }
    }
}
