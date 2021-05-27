package com.emergencyguide.Service.Community;

import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Service.BaseService;

/**
 * @author DarckLH
 * @date 2021/5/22 14:44
 * @Description
 */
public interface CustomerService extends BaseService<Customer> {
    public Customer selectByOpenId(String openid);
}
