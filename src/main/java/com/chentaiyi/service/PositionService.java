package com.chentaiyi.service;

import com.chentaiyi.common.plugs.Page;
import com.chentaiyi.domain.Position;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by hasee on 2017/11/24.
 */
public interface PositionService {
    public Page<Position> getPagedUserPositions(long userId,Timestamp startTime,Timestamp endTime,int pageNo,int pageSize);
    public Page<Position> getPagedUserPostions(long userId,int pageNo,int pageSize);
    public int addPostion(Position position);
}
