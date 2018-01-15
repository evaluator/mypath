package com.chentaiyi.service.impl;

import com.chentaiyi.common.plugs.Page;
import com.chentaiyi.dao.PositionDao;
import com.chentaiyi.domain.Position;
import com.chentaiyi.service.PositionService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Timestamp;

/**
 * Created by hasee on 2017/11/27.
 */
public class PositionServiceImpl implements PositionService {

    private PositionDao positionDao;
    @Autowired
    public void setPositionDao(PositionDao positionDao){
        this.positionDao = positionDao;
    }

    @Override
    public Page<Position> getPagedUserPositions(long userId, Timestamp startTime, Timestamp endTime, int pageNo, int pageSize) {
        Page<Position> positionPage = null;
        if(startTime.getTime()>=endTime.getTime()){
            return null;
        }else{
            positionPage = positionDao.getUserPositions(userId,startTime,endTime,pageNo,pageSize);
        }        return positionPage;
    }
    @Override
    public Page<Position> getPagedUserPostions(long userId,int pageNo,int pageSize){
        return positionDao.getUserPositions(userId,pageNo,pageSize);
    }
    @Override
    public int addPostion(Position position) {
       return positionDao.addPostion(position);
    }


}
