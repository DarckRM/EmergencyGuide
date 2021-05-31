package com.emergencyguide.Dao.Community;

import com.emergencyguide.Entity.Address;
import com.emergencyguide.Entity.Station;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/30 16:44
 * @Description
 */
@Mapper
public interface AddressDao {

    public List<Address> selectAllList();

    public int selectListCount(@Param("params") Map<String, Object> params);

    public List<Address> selectList(@Param("page") int page, @Param("limit") int limit, @Param("params") Map<String, Object> params);

    @Delete("DELETE FROM t_customerAddress WHERE id = #{id}")
    public int delete(int id);

    public int updateById(Address address);

    public int insert(Address address);

    @Select("SELECT * FROM t_customerAddress WHERE id = #{id}")
    public Address selectById(int id);

    @Update("UPDATE t_customerAddress SET isdefault = #{isdefault} WHERE id = #{id}")
    public int changeDefault(String isdefault, int id);

    @Update("UPDATE t_customerAddress SET isdefault = '否' WHERE openid = #{openid} AND id NOT IN (#{id})")
    public int restoreDefault(String openid, int id);

}
