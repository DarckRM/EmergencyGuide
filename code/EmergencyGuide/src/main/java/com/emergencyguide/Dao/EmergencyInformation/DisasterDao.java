package com.emergencyguide.Dao.EmergencyInformation;

import com.emergencyguide.Entity.Disaster;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface DisasterDao {

    public List<Disaster> selectAllList(@Param("page") int page, @Param("limit") int limit,
                                        @Param("params") Map<String, Object> params);
    public int selectListCount( @Param("params")Map<String, Object> params);

    public int updateById(Disaster disaster);

    public int disasterAdd(Disaster disaster);

    @Delete("delete  from t_disaster where id=#{id}")
    public int disasterDelete(int id);

    @Select("select * from t_disaster where id=#{id}")
    public Disaster selectById(int id);
}
