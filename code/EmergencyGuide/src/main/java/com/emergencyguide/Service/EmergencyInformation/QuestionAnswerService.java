package com.emergencyguide.Service.EmergencyInformation;

import com.emergencyguide.Entity.Disaster;
import com.emergencyguide.Entity.DisasterType;
import com.emergencyguide.Entity.QuestionAnswer;

import java.util.List;

public interface QuestionAnswerService {
    public List<QuestionAnswer> selectAllList(int page, int limit,  String searchParams);
    public int selectListCount( String searchParams);
    public int updateById(QuestionAnswer questionAnswer);
    public  int questionAnswerAdd(QuestionAnswer questionAnswer);
    public  int questionAnswerDelete(int id);
}
