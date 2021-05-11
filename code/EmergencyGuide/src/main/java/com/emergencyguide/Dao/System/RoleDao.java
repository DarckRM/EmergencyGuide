package com.emergencyguide.Dao.System;

import com.emergencyguide.Entity.Role;
import com.emergencyguide.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/11 1:22
 * @Description
 */
@Mapper
public interface RoleDao {

    public List<Role> selectAllList();

    @Select("SELECT COUNT(id) FROM t_role")
    public int selectListCount();

    public List<Role> selectList(@Param("params") Map<String, Object> params);

    @Select("SELECT * FROM t_systemuser WHERE id = #{id}")
    public Role selectById(long id);

}
