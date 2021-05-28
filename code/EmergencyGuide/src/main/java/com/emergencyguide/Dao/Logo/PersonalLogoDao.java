package com.emergencyguide.Dao.Logo;

import com.emergencyguide.Entity.PersonalLogo;
import com.emergencyguide.Entity.Rank;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface PersonalLogoDao {
    public List<PersonalLogo> selectAllList(@Param("page") int page, @Param("limit") int limit, @Param("params") Map<String, Object> params);

    public int selectListCount( @Param("params")Map<String, Object> params);

    public int updateById(PersonalLogo personalLogo);

    public int personalLogoAdd(PersonalLogo personalLogo);

    @Delete("delete  from t_personalLogo where id=#{id}")
    public int personalLogoDelete(int id);

    @Select("SELECT DISTINCT basicLogo FROM t_personalLogo")
    public List<PersonalLogo> selectBasicLogo();

    @Select("SELECT id, subLogo FROM t_personalLogo WHERE basicLogo = #{basicLogo}")
    public List<PersonalLogo> selectSubLogo(@Param("basicLogo") String basicLogo);

}
