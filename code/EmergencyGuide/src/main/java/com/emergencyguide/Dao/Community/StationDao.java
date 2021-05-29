package com.emergencyguide.Dao.Community;

import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Entity.Station;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface StationDao {

    public List<Station> selectAllList();

    public List<Station> selectList(@Param("page") int page, @Param("limit") int limit, @Param("params") Map<String, Object> params);

}
