package com.emergencyguide.Service.Community.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.Community.CustomerDao;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Service.Community.CustomerService;
import com.emergencyguide.Utils.EasyGeneraterParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private CustomerDao customerDao;

    @Autowired
    private EasyGeneraterParams easyGeneraterParams;

    @Override
    public List<Customer> selectList(int page, int limit, String searchParams) {

        Map<String, Object> params = new HashMap<>();

        params = easyGeneraterParams.easySearchParams(searchParams);

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
    public int deleteById(long id) {
        return customerDao.delete(id);
    }

    @Override
    public int updateById(Customer customer) {
        return customerDao.updateById(customer);
    }

    @Override
    public int insert(Customer customer) {
        return 0;
    }
}