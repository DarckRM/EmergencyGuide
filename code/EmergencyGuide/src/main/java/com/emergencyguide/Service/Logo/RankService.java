package com.emergencyguide.Service.Logo;

import com.emergencyguide.Entity.QuestionAnswer;
import com.emergencyguide.Entity.Rank;

import java.util.List;

public interface RankService {
    public List<Rank> selectAllList(int page, int limit, String searchParams);
    public int selectListCount( String searchParams);
    public int updateById(Rank rank);
    public  int rankAdd(Rank rank);
    public  int rankDelete(int id);
}
