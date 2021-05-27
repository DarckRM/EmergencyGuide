package com.emergencyguide.Service.Logo.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.Logo.PersonalLogoDao;
import com.emergencyguide.Dao.Logo.RankDao;
import com.emergencyguide.Entity.PersonalLogo;
import com.emergencyguide.Entity.Rank;
import com.emergencyguide.Service.Logo.PersonalLogoService;
import com.emergencyguide.Utils.EasyGeneraterParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PersonalLogoServiceImpl implements PersonalLogoService {
    @Autowired
    private PersonalLogoDao personalLogoDao;

    @Autowired
    private EasyGeneraterParams easyGeneraterParams;

    @Override
    public List<PersonalLogo> selectAllList(int page, int limit, String searchParams) {

        Map<String, Object> params = new HashMap<>();
        params = easyGeneraterParams.easySearchParams(searchParams);
        page = (page - 1) * limit;

        List<PersonalLogo> list = personalLogoDao.selectAllList(page, limit, params);
        return list;
    }

    @Override
    public int selectListCount(String searchParams) {
        String personalLogoName = "";
        Map<String, Object> params = new HashMap<>();
        params = easyGeneraterParams.easySearchParams(searchParams);
        return  personalLogoDao.selectListCount(params);
    }

    @Override
    public int updateById(PersonalLogo personalLogo) {
        return personalLogoDao.updateById(personalLogo);
    }

    @Override
    public int personalLogoAdd(PersonalLogo personalLogo) {
        return personalLogoDao.personalLogoAdd(personalLogo);
    }

    @Override
    public int personalLogoDelete(int id) {
        return personalLogoDao.personalLogoDelete(id);
    }
}
