package com.emergencyguide.Service.Logo.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.EmergencyInformation.QuestionAnswerDao;
import com.emergencyguide.Dao.Logo.RankDao;
import com.emergencyguide.Entity.QuestionAnswer;
import com.emergencyguide.Entity.Rank;
import com.emergencyguide.Service.Logo.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RankServiceImpl implements RankService {

    @Autowired
    private RankDao rankDao;

    @Override
    public List<Rank> selectAllList(int page, int limit, String searchParams) {
        String description = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            description = json.getString("description");

        }
        page = (page - 1) * limit;
        Map<String, Object> params = new HashMap<>();
        params.put("description", description);
        List<Rank> list = rankDao.selectAllList(page, limit, params);
        return list;
    }

    @Override
    public int selectListCount(String searchParams) {
        String question = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            question = json.getString("question");

        }
        Map<String, Object> params = new HashMap<>();
        params.put("question", question);
        return  rankDao.selectListCount(params);
    }

    @Override
    public int updateById(Rank rank) {
        return rankDao.updateById(rank);
    }

    @Override
    public int rankAdd(Rank rank) {
        return rankDao.rankAdd(rank);
    }

    @Override
    public int rankDelete(int id) {
        return rankDao.rankDelete(id);
    }
}
