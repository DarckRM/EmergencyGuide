package com.emergencyguide.Dao.System;

import com.emergencyguide.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserDao {

    @Select("SELECT * FROM t_systemuser WHERE username = #{username} AND password = #{password}")
    public User queryLogin(User user);

    @Select("SELECT * FROM t_systemuser WHERE username = #{userName}")
    public User selectByUserName(String userName);

    public List<User> selectAllList();

    @Select("SELECT COUNT(id) FROM t_systemuser")
    public int selectListCount();

    public List<User> selectList(@Param("params") Map<String, Object> params);

    @Select("SELECT * FROM t_systemuser WHERE id = #{id}")
    public User selectById(long id);

    public int insert(User user);

    public int updateById(User user);
}
