package com.emergencyguide.Dao.System;

import com.emergencyguide.Entity.Image;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/6/1 23:40
 * @Description
 */
@Mapper
public interface DefaultImgDao {

    public List<Image> selectAllList();

    public int selectListCount(@Param("params") Map<String, Object> params);

    public List<Image> selectList(@Param("page") int page, @Param("limit") int limit, @Param("params") Map<String, Object> params);

    @Select("SELECT * FROM t_images WHERE id = #{id}")
    public Image selectById(long id);

    public int insert(Image image);

    public int updateById(Image image);

    @Delete("DELETE FROM t_images WHERE id = #{id}")
    public int delete(int id);

}
