package com.emergencyguide.Service.Community;

import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Entity.Station;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/30 13:14
 * @Description
 */
public interface StationService {

    public int insert(Station station);
    public int updateById(Station station);
    public Station selectById(int id);
    public List<Station> selectAllList();
    public int selectListCount(String searchParams);
    public List<Station> selectList(int page, int limit, String searchParams);
    public int deleteById(int id);
}
