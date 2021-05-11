package com.emergencyguide.Controller.System;

import com.emergencyguide.Entity.Result;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.System.RoleService;
import com.emergencyguide.Service.System.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/5 20:38
 * @Description
 */
@RestController
@RequestMapping("user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping("/login")
    public String webServiceDemo(){
        return "/index";
    }

    @PostMapping("/doLogin")
    @ResponseBody
    public String login(@RequestBody(required = false) User user, HttpServletResponse response, HttpServletRequest request){

        String token = userService.doLogin(user, response, request);

        if (token != null && !token.isEmpty()) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().success("操作失败").toString();
        }
    }

    @RequestMapping("/findall")
    public String findAll(String searchParams) {

        Result<User> result = new Result<>();
        int page  = 1;
        int limit = 1;
        List<User> datas = userService.selectList(page, limit, searchParams);
        result.setCount(userService.selectListCount(searchParams));
        result.setData(datas);
        result.setMsg("请求成功");
        return result.toString();

    }

    @RequestMapping("/page")
    public ModelAndView userPage() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("roles",roleService.selectAllList());
        mav.setViewName("system/user");
        return mav;
    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@Param("id") int id) {
        User data = null;
        if (id != -1 && id != 0) {
            data = userService.selectById(id);
        } else {
            data = new User();
        }
        ModelAndView mav = new ModelAndView();
        System.out.println(data);
        mav.setViewName("/system/user_edit");
        mav.addObject("roles",roleService.selectAllList());
        mav.addObject("data", data);

        return mav;
    }

    @RequestMapping("/save")
    public String save(@RequestBody User user) {

        Result<User> result = new Result<>();
        int count = 0;

        if (user.getId() > 0) {
            count = userService.updateById(user);
        } else {
            count = userService.insert(user);
        }
        if (count > 0) {
            result.setMsg("保存成功");
        } else {
            result.setMsg("保存失败");
        }
        return result.toString();
    }

}
