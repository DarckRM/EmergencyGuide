package com.emergencyguide.Service.Community.Impl;

import com.emergencyguide.Dao.Community.AddressDao;
import com.emergencyguide.Dao.Community.StationDao;
import com.emergencyguide.Entity.Address;
import com.emergencyguide.Entity.Station;
import com.emergencyguide.Service.Community.AddressService;
import com.emergencyguide.Utils.EasyGeneraterParams;
import com.emergencyguide.Utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/30 17:14
 * @Description
 */
@Service
public class AddressServiceImpl implements AddressService {


    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    // 缓存集合Key值
    private String REDIS_LIST_KEY = "ADDRESS_LIST";
    // 缓存单数据Key值
    private String REDIS_INFO_KEY = "ADDRESS_";

    @Autowired
    private AddressDao addressDao;

    @Autowired
    private EasyGeneraterParams easyGeneraterParams;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Address> selectList(int page, int limit, String searchParams) {

        Map<String, Object> params = new HashMap<>();
        params = easyGeneraterParams.easySearchParams(searchParams);
        page = (page - 1) * limit;
        List<Address> addresses = addressDao.selectList(page, limit, params);
        logger.info(addresses.toString());

        return addresses;
    }

    @Override
    public int selectListCount(String searchParams) {

        Map<String, Object> params = new HashMap<>();

        params = easyGeneraterParams.easySearchParams(searchParams);

        return addressDao.selectListCount(params);
    }

    @Override
    public List<Address> selectAllList() {
        return null;
    }

    @Override
    public int deleteById(int id) {
        return addressDao.delete(id);
    }

    @Override
    public int updateById(Address address) {
        return addressDao.updateById(address);
    }

    @Override
    public int insert(Address address) {

        //设置默认地址为否
        address.setIsdefault("否");
        int insert = addressDao.insert(address);
        if (insert > 0) {
            redisUtil.del(REDIS_LIST_KEY);
        }
        return insert;
    }

    @Override
    public int changeDefaultAddress(int id) {

        Address address = addressDao.selectById(id);
        int change;

        if (address.getIsdefault().equals("是")) {
            change = addressDao.changeDefault("否",id);
        } else {
            change = addressDao.changeDefault("是",id);
            addressDao.restoreDefault(address.getOpenid(),id);
        }

        return change;
    }
}
