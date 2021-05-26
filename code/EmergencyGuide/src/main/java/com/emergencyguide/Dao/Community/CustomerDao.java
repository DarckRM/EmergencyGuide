package com.emergencyguide.Dao.Community;

import com.emergencyguide.Entity.Customer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/22 14:35
 * @Description
 */
@Mapper
public interface CustomerDao {

    @Select("SELECT * FROM t_customer WHERE username = #{userName}")
    public Customer selectByUserName(String customerName);

    public List<Customer> selectAllList();

    public int selectListCount(@Param("params") Map<String, Object> params);

    public List<Customer> selectList(@Param("page") int page, @Param("limit") int limit, @Param("params") Map<String, Object> params);

    @Select("SELECT * FROM t_customer WHERE id = #{id}")
    public Customer selectById(long id);

    @Select("SELECT * FROM t_customer WHERE openid = #{openid}")
    public Customer selectByOpenId(String openid);

    public int insert(Customer customer);

    public int updateById(Customer customer);

    public int updateByOpenId(Customer customer);

    @Delete("DELETE FROM t_customer WHERE id = #{id}")
    public int delete(long id);

    @Delete("DELETE FROM t_customer WHERE openid = #{openid}")
    public int deleteByOpenId(String openid);

}
