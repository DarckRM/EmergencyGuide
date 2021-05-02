package com.emergencyguide.Dao;

import com.emergencyguide.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM t_systemuser WHERE username = #{username} AND password = #{password}")
    public User queryLogin(User user);

    @Select("SELECT * FROM t_systemuser WHERE username = #{userName}")
    public User selectByUserName(String userName);
}
