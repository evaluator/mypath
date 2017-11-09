package com.chentaiyi.common.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created by hasee on 2017/11/8.
 */
public class BaseDao<T> {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public T getRowForObject(final String sql, Object[] args,Class<T> classT){
        if(sql == null || sql.length()<=0){
            return null;
        }
        if(args == null || args.length<=0){
            return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<T>(classT));
        }

        return jdbcTemplate.queryForObject(sql,args,new BeanPropertyRowMapper<T>(classT));
    }

    public <T>List<T> getRows(final String sql,Object[] args,Class<T> classT){
        if(sql == null || sql.length()<=0){
            return null;
        }
        List<T> resultList = null;
        try {
            if (args != null && args.length > 0)
                resultList = jdbcTemplate.query(sql, args, new BeanPropertyRowMapper<T>(classT));
            else
                // BeanPropertyRowMapper是自动映射实体类的
                resultList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<T>(classT));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultList;

    }

}
