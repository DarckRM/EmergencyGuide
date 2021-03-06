package com.emergencyguide.Controller.Conmunity;

import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.PersonalLogo;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Community.CustomerService;
import com.emergencyguide.Service.Logo.PersonalLogoService;
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
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private PersonalLogoService personalLogoService;

    @RequestMapping("/page")
    public ModelAndView userPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("community/customer");
        return mav;
    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@Param("id") int id, @Param("basicLogo") String basicLogo) {
        Customer data = null;
        if (id != -1 && id != 0) {
            data = customerService.selectById(id);
        } else {
            data = new Customer();
        }
        ModelAndView mav = new ModelAndView();
        System.out.println(data);
        mav.setViewName("community/customer_edit");
        mav.addObject("basicLogoList",personalLogoService.selectBasicLogo());
        mav.addObject("subLogoList",personalLogoService.selectSubLogo(basicLogo));
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
            result.setMsg("????????????");
        } else {
            result.setMsg("????????????");
        }
        return result.toString();
    }

    @RequestMapping("/findall")
    public String findAll(String searchParams, int page, int limit) {

        Result<Customer> result = new Result<>();

        List<Customer> datas = customerService.selectList(page, limit, searchParams);
        result.setCount(customerService.selectListCount(searchParams));
        result.setData(datas);
        result.setMsg("????????????");
        return result.toString();

    }

    @RequestMapping("/delete")
    public String delete(long id) {

        Result<Customer> result = new Result<>();

        result.setCount(customerService.deleteById(id));
        result.setMsg("????????????");
        return result.toString();

    }

}
