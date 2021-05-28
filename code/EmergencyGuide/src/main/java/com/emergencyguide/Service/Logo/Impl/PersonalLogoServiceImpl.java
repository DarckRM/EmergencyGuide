package com.emergencyguide.Service.Logo.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.Logo.PersonalLogoDao;
import com.emergencyguide.Dao.Logo.RankDao;
import com.emergencyguide.Entity.PersonalLogo;
import com.emergencyguide.Entity.Rank;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.Logo.PersonalLogoService;
import com.emergencyguide.Utils.EasyGeneraterParams;
import com.emergencyguide.Utils.RedisUtil;
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

    @Autowired
    private RedisUtil redisUtil;
    // 缓存集合Key值
    private String REDISLISTKEY = "CUSTOMER_LIST";
    // 缓存单数据Key值
    private String REDISINFOKEY = "CUSTOMER_";

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

    @Override
    public List<PersonalLogo> selectBasicLogo() {

/*        Boolean hasKey = redisUtil.hasKey(REDISLISTKEY);
        if (hasKey) {
            List<PersonalLogo> redisList = redisUtil.getList(REDISLISTKEY, PersonalLogo.class);
            return redisList;
        }*/
        List<PersonalLogo> list = personalLogoDao.selectBasicLogo();
/*        // 存在到缓存中
        redisUtil.set(REDISLISTKEY, list);*/
        return list;
    }

    @Override
    public List<PersonalLogo> selectSubLogo(String basicLogo) {

/*        Boolean hasKey = redisUtil.hasKey(REDISLISTKEY);
        if (hasKey) {
            List<PersonalLogo> redisList = redisUtil.getList(REDISLISTKEY, PersonalLogo.class);
            return redisList;
        }*/
        List<PersonalLogo> list = personalLogoDao.selectSubLogo(basicLogo);
/*        // 存在到缓存中
        redisUtil.set(REDISLISTKEY, list);*/
        return list;
    }
}
