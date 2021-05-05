package com.emergencyguide.Dao;

import com.emergencyguide.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserDao {
    @Select("SELECT * FROM t_systemuser WHERE username = #{username} AND password = #{password}")
    public User queryLogin(User user);

    @Select("SELECT * FROM t_systemuser WHERE username = #{userName}")
    public User selectByUserName(String userName);

    public List<User> selectAllList();

    @Select("SELECT COUNT(id) FROM T_SystemUser")
    public int selectListCount();
}
