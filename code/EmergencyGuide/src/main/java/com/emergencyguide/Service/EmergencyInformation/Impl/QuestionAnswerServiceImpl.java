package com.emergencyguide.Service.EmergencyInformation.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.EmergencyInformation.QuestionAnswerDao;
import com.emergencyguide.Entity.Disaster;
import com.emergencyguide.Entity.QuestionAnswer;
import com.emergencyguide.Service.EmergencyInformation.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionAnswerServiceImpl implements QuestionAnswerService {



    @Autowired
    private QuestionAnswerDao questionAnswerDao;

    @Override
    public List<QuestionAnswer> selectAllList(int page, int limit, String searchParams) {
        String question = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            question = json.getString("question");

        }
        page = (page - 1) * limit;
        Map<String, Object> params = new HashMap<>();
        params.put("question", question);
        List<QuestionAnswer> list = questionAnswerDao.selectAllList(page, limit, params);
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
        return  questionAnswerDao.selectListCount(params);
    }

    @Override
    public int updateById(QuestionAnswer questionAnswer) {
        return questionAnswerDao.updateById(questionAnswer);
    }

    @Override
    public int questionAnswerAdd(QuestionAnswer questionAnswer) {
        return questionAnswerDao.questionAnswerAdd(questionAnswer);
    }

    @Override
    public int questionAnswerDelete(int id) {
        return questionAnswerDao.questionAnswerDelete(id);
    }
}
