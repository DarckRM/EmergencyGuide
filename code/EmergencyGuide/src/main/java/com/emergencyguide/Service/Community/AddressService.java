package com.emergencyguide.Service.Community;

import com.emergencyguide.Entity.Address;
import com.emergencyguide.Entity.Station;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/30 17:14
 * @Description
 */
public interface AddressService {

    public int insert(Address address);
    public int updateById(Address address);
    public List<Address> selectAllList();
    public int selectListCount(String searchParams);
    public List<Address> selectList(int page, int limit, String searchParams);
    public int deleteById(int id);
    public int changeDefaultAddress(int id);

}
