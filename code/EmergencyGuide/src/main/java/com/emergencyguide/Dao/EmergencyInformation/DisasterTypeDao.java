package com.emergencyguide.Dao.EmergencyInformation;

import com.emergencyguide.Entity.Disaster;
import com.emergencyguide.Entity.DisasterType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface DisasterTypeDao {
    public List<DisasterType> selectAllList(@Param("disasterNumber") int disasterNumber);
    public int selectListCount( @Param("params")int id);

    public int updateById(DisasterType disasterType);

    public int disasterTypeAdd(DisasterType disasterType);

    @Delete("delete  from t_disasterType where id=#{id}")
    public int disasterTypeDelete(int id);
}

