package com.emergencyguide.Service.EmergencyInformation;

import com.emergencyguide.Entity.Disaster;

import java.util.List;

public interface DisasterService {
    public List<Disaster> selectAllList(int page, int limit,  String searchParams);
    public int selectListCount( String searchParams);
    public int updateById(Disaster disaster);
    public  int disasterAdd(Disaster disaster);
    public  int disasterDelete(int id);
}
