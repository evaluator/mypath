package com.chentaiyi.dao;

import com.chentaiyi.common.dao.BaseDao;
import com.chentaiyi.common.plugs.Page;
import com.chentaiyi.common.plugs.PageRequest;
import com.chentaiyi.domain.Position;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.List;

/**
 * Created by hasee on 2017/11/23.
 */
@Repository
public class PositionDao extends BaseDao<Position> {

    private final String INSERT_POSITION_SQL = " INSERT INTO position(latitude,longitude,time,userId,tripId) Values(?,?,?,?,?）";
    private final String QUERY_INTERVAL_POSITIONS_SQL=" SELECT * FROM position WHERE time>=? and time<=?";
    private final String QUERY_USER_INTERVAL_POSITION_SQL = " SELECT * FROM postion WHERE user_id=? and time>=? and time<=?";
    private final String QUERY_USER_POSITION_SQL = " SELECT * FROM position WHERE user_id=?";

    public int addPostion(Position position){
        Object[] args = {position.getLatitude(),position.getLongitude(),position.getTime(),position.getUserId(),position.getTripId()};
        int[] types = {Types.FLOAT,Types.FLOAT,Types.TIMESTAMP,Types.BIGINT, Types.BIGINT};
        return addUpdateDelete(INSERT_POSITION_SQL,args,types);
    }

    public List<Position> getPostionsByTime(Timestamp startTime,Timestamp endTime){
        if(endTime.getTime()<=startTime.getTime())
            return null;
        Object[] args = {startTime,endTime};
        List<Position> results = getRows(QUERY_INTERVAL_POSITIONS_SQL,args, Position.class);
        return results;
    }

    public Page<Position> getUserPositions(long userid,Timestamp startTime,Timestamp endTime,int pageNo,int pageSize){
        Page<Position> positionPage = null;
        PageRequest pageRequest = new PageRequest(pageNo,pageSize);
        if(startTime == null && endTime == null){
            Object[] args={userid};
            positionPage = findPage(QUERY_USER_POSITION_SQL,args,pageRequest,Position.class);
        }else{
            Object[] args={userid,startTime,endTime};
            positionPage = findPage(QUERY_USER_INTERVAL_POSITION_SQL,args,pageRequest,Position.class);
        }
        return positionPage;
    }


}