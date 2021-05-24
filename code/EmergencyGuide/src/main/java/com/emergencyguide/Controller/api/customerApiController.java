package com.emergencyguide.Controller.api;

import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Community.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/24 12:53
 * @Description
 */
@RestController
@RequestMapping("api/customer")
public class customerApiController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/findall")
    public String findAll(String searchParams, int page, int limit) {

        Result<Customer> result = new Result<>();

        List<Customer> datas = customerService.selectList(page, limit, searchParams);
        result.setCount(customerService.selectListCount(searchParams));
        result.setData(datas);
        result.setMsg("请求成功");
        return result.toString();

    }

}
