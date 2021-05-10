package com.emergencyguide.Service.EmergencyInformation.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.EmergencyInformation.DisasterDao;
import com.emergencyguide.Dao.EmergencyInformation.DisasterTypeDao;
import com.emergencyguide.Entity.Disaster;
import com.emergencyguide.Entity.DisasterType;
import com.emergencyguide.Service.EmergencyInformation.DisasterTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DisasterTypeServiceImpl  implements DisasterTypeService {
    @Autowired
    private DisasterTypeDao disasterTypeDao;
    @Override
    public List<DisasterType> selectAllList( int disasterNumber) {

        List<DisasterType> list = disasterTypeDao.selectAllList(disasterNumber);
        return list;
    }
    @Override
    public int selectListCount(int id){

        return  disasterTypeDao.selectListCount(id);
    }

    @Override
    public int updateById(DisasterType disasterType) {
        return disasterTypeDao.updateById(disasterType);
    }
    @Override
    public int disasterTypeAdd(DisasterType disasterType) {
        return disasterTypeDao.disasterTypeAdd(disasterType);
    }

    @Override
    public int disasterTypeDelete(int id) {
        return disasterTypeDao.disasterTypeDelete(id);
    }

}
