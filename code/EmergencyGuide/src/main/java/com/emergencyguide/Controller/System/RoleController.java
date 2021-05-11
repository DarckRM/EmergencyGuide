package com.emergencyguide.Controller.System;

import com.emergencyguide.Entity.Result;
import com.emergencyguide.Entity.Role;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.System.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/11 1:31
 * @Description
 */
@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/page")
    public ModelAndView rolePage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("system/role");
        return mav;
    }

    @ResponseBody
    @RequestMapping("findall")
    public String findAll(String searchParams) {

        Result<Role> result = new Result<>();
        int page  = 1;
        int limit = 1;
        List<Role> datas = roleService.selectList(page, limit, searchParams);
        result.setCount(roleService.selectListCount(searchParams));
        result.setData(datas);
        result.setMsg("请求成功");
        return result.toString();

    }

}
