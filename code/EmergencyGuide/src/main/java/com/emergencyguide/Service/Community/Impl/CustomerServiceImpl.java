package com.emergencyguide.Service.Community.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.Community.CustomerDao;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Service.Community.CustomerService;
import com.emergencyguide.Utils.EasyGeneraterParams;
import com.emergencyguide.Utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/22 14:44
 * @Description
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    // 缓存集合Key值
    private String REDIS_LIST_KEY = "Customer_LIST";
    // 缓存单数据Key值
    private String REDIS_INFO_KEY = "Customer_";

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private EasyGeneraterParams easyGeneraterParams;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public List<Customer> selectList(int page, int limit, String searchParams) {

        Map<String, Object> params = new HashMap<>();
        params = easyGeneraterParams.easySearchParams(searchParams);
        page = (page - 1) * limit;
        List<Customer> customer = customerDao.selectList(page, limit, params);
        logger.info(customer.toString());

        return customer;
    }

    @Override
    public int selectListCount(String searchParams) {

        Map<String, Object> params = new HashMap<>();

        params = easyGeneraterParams.easySearchParams(searchParams);

        return customerDao.selectListCount(params);
    }

    @Override
    public List<Customer> selectAllList() {
        return null;
    }

    @Override
    public Customer selectById(long id) {
        return customerDao.selectById(id);
    }

    @Override
    public Customer selectByOpenId(String openid) {
        return customerDao.selectByOpenId(openid);
    }

    @Override
    public int deleteById(long id) {
        return customerDao.delete(id);
    }

    @Override
    public int updateById(Customer customer) {
        return customerDao.updateById(customer);
    }

    @Override
    public int updateByOpenId(Customer customer) {
        return customerDao.updateByOpenId(customer);
    }

    @Override
    public int insert(Customer customer) {

        //获取当前时间
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        customer.setRegistertime(timestamp);

        int insert = customerDao.insert(customer);
        if (insert > 0) {
            redisUtil.del(REDIS_LIST_KEY);
        }
        return insert;
    }
}
