package com.emergencyguide.Service.EmergencyInformation.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.EmergencyInformation.DisasterDao;
import com.emergencyguide.Entity.Disaster;
import com.emergencyguide.Service.EmergencyInformation.DisasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DisasterServiceImpl implements DisasterService {
    @Autowired
    private DisasterDao disasterDao;
    @Override
    public List<Disaster> selectAllList(int page, int limit,  String searchParams) {
        String disasterType = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            disasterType = json.getString("disasterType");

        }
        page = (page - 1) * limit;
        Map<String, Object> params = new HashMap<>();
        params.put("disasterType", disasterType);
        List<Disaster> list = disasterDao.selectAllList(page, limit, params);
        return list;
    }
    @Override
    public int selectListCount( String searchParams){
        String disasterType = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            disasterType = json.getString("disasterType");

        }
        Map<String, Object> params = new HashMap<>();
        params.put("disasterType", disasterType);
        return  disasterDao.selectListCount(params);
    }

    @Override
    public int updateById(Disaster disaster) {
        return disasterDao.updateById(disaster);
    }
    @Override
    public int disasterAdd(Disaster disaster) {
        return disasterDao.disasterAdd(disaster);
    }

    @Override
    public int disasterDelete(int id) {
        return disasterDao.disasterDelete(id);
    }

}
