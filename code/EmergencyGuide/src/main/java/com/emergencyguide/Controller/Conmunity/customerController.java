package com.emergencyguide.Controller.Conmunity;

import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Community.CustomerService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/22 14:31
 * @Description
 */
@RestController
@RequestMapping("customer")
public class customerController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping("/page")
    public ModelAndView userPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("community/customer");
        return mav;
    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@Param("id") int id) {
        Customer data = null;
        if (id != -1 && id != 0) {
            data = customerService.selectById(id);
        } else {
            data = new Customer();
        }
        ModelAndView mav = new ModelAndView();
        System.out.println(data);
        mav.setViewName("community/customer_edit");
        mav.addObject("data", data);

        return mav;
    }

    @RequestMapping("/save")
    public String save(@RequestBody Customer customer) {

        Result<Customer> result = new Result<>();
        int count = 0;

        if (customer.getId() > 0) {
            count = customerService.updateById(customer);
        } else {
            count = customerService.insert(customer);
        }
        if (count > 0) {
            result.setMsg("保存成功");
        } else {
            result.setMsg("保存失败");
        }
        return result.toString();
    }

    @RequestMapping("/findall")
    public String findAll(String searchParams, int page, int limit) {

        Result<Customer> result = new Result<>();

        List<Customer> datas = customerService.selectList(page, limit, searchParams);
        result.setCount(customerService.selectListCount(searchParams));
        result.setData(datas);
        result.setMsg("请求成功");
        return result.toString();

    }

    @RequestMapping("/delete")
    public String delete(long id) {

        Result<Customer> result = new Result<>();

        result.setCount(customerService.deleteById(id));
        result.setMsg("删除成功");
        return result.toString();

    }

}
