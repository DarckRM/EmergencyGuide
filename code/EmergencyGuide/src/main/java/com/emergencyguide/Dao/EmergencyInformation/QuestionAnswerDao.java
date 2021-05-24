package com.emergencyguide.Dao.EmergencyInformation;

import com.emergencyguide.Entity.Disaster;
import com.emergencyguide.Entity.QuestionAnswer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface QuestionAnswerDao {
    public List<QuestionAnswer> selectAllList(@Param("page") int page, @Param("limit") int limit,
                                              @Param("params") Map<String, Object> params);
    public int selectListCount( @Param("params")Map<String, Object> params);

    public int updateById(QuestionAnswer questionAnswer);

    public int questionAnswerAdd(QuestionAnswer QuestionAnswer);

    @Delete("delete  from t_questionAnswer where id=#{id}")
    public int questionAnswerDelete(int id);
}
