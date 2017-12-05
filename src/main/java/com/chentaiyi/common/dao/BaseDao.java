package com.chentaiyi.common.dao;

import com.chentaiyi.common.plugs.ISQLPageHandler;
import com.chentaiyi.common.plugs.Page;
import com.chentaiyi.common.plugs.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.incrementer.DataFieldMaxValueIncrementer;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.Map;

/**
 * Created by hasee on 2017/11/8.
 */
public class BaseDao<T> {
    protected JdbcTemplate jdbcTemplate;
    private ISQLPageHandler sqlPageHandler;

    @Autowired
    void setJdbcTemplate(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    void setSqlPageHandler(ISQLPageHandler sqlPageHandler){
        this.sqlPageHandler = sqlPageHandler;
    }


    /*
     * 获取一行数据记录
     */
    public T getRowForObject(final String sql, Object[] args,Class<T> classT){
        if(sql == null || sql.length()<=0){
            return null;
        }
        try {
            if (args == null || args.length <= 0) {
                return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<T>(classT));
            }

            return jdbcTemplate.queryForObject(sql, args, new BeanPropertyRowMapper<T>(classT));
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    *   获取多条记录
     */
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

    /*
    *  通用增删查
     */
    public int addUpdateDelete(final String sql,Object[] args,int[] argTypes){
        int count = 0;
        try {
            if(args == null || args.length<=0){
                count = jdbcTemplate.update(sql);
            }else if(argTypes == null || argTypes.length<=0){
                count =jdbcTemplate.update(sql,args);
            }else{
                count = jdbcTemplate.update(sql,args,argTypes);
            }

        }catch (Exception e){
            e.printStackTrace();
            count = -1;
        }
        return count;
    }

    /*
     * 插入一行数据并返回id
     */
    public long insertAndGetId(final String sql,Object[] args,int[] argTypes){
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int count = args.length;
        jdbcTemplate.update(new PreparedStatementCreator() {

            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);
                for(int i=1;i<=count;i++){
                    if(argTypes == null || argTypes.length<=0){
                        ps.setObject(i,args[i-1]);
                    }else{
                        ps.setObject(i,args[i-1],argTypes[i-1]);
                    }
                }
                return ps;
            }
        },keyHolder);
        return keyHolder.getKey().longValue();
    }

    /*
    *  批量更新
     */
    public  void batchUpdate(final String sql,List<Object[]> tList){
        try {
            jdbcTemplate.batchUpdate(sql,tList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public Map<String, Object> getMapData(String sql, Object[] params) {
        return jdbcTemplate.queryForMap(sql, params);
    }


    public List<Map<String, Object>> queryList(String sql, Object[] params) {
        return jdbcTemplate.queryForList(sql, params);
    }

    /*
    *  分页处理
     */
    public Page<T> findPage(final String sql,Object[] args, PageRequest pageRequest,Class<T> tClass){
        Page<T> page = new Page<T>(pageRequest);
        List<T> results = null;
        String countSql = sqlPageHandler.convertCountSQL(sql);
        int totalItems = jdbcTemplate.queryForInt(countSql,args);
        page.setTotalItems(totalItems);
        if(pageRequest.getPageNo() <= page.getTotalPages()) {
            String pagedSQL = sqlPageHandler.convertPageSQL(sql, pageRequest.getPageNo(), pageRequest.getPageSize());
            results = getRows(pagedSQL, args, tClass);
        }
        page.setResult(results);
        return page;
    }

    public int getlastPageNo(final String sql,Object[] args,int[] types,int pageSize){
         String countSql = sqlPageHandler.convertCountSQL(sql);
         int totalItems = jdbcTemplate.queryForInt(countSql,args,types);
         PageRequest pageRequest = new PageRequest(1,pageSize);
         Page page = new Page(pageRequest);
         page.setTotalItems(totalItems);
         return page.getTotalPages();
    }
}
