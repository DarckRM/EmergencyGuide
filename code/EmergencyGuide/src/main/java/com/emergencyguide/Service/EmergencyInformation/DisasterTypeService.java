package com.emergencyguide.Service.EmergencyInformation;

import com.emergencyguide.Entity.Disaster;
import com.emergencyguide.Entity.DisasterType;

import java.util.List;

public interface DisasterTypeService {
    public List<DisasterType> selectAllList( int disasterNumber);
    public int selectListCount( int id);
    public int updateById(DisasterType disasterType);
    public  int disasterTypeAdd(DisasterType disasterType);
    public  int disasterTypeDelete(int id);
}
