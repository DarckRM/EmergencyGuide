package com.emergencyguide.Dao.Logo;

import com.emergencyguide.Entity.QuestionAnswer;
import com.emergencyguide.Entity.Rank;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface RankDao {

    public List<Rank> selectAllList(@Param("page") int page, @Param("limit") int limit,
                                    @Param("params") Map<String, Object> params);
    public int selectListCount( @Param("params")Map<String, Object> params);

    public int updateById(Rank rank);

    public int rankAdd(Rank rank);

    @Delete("delete  from t_rank where id=#{id}")
    public int rankDelete(int id);

}
