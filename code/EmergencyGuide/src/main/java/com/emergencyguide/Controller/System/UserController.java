package com.emergencyguide.Controller.System;

import com.emergencyguide.Entity.Result;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.System.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
        mav.setViewName("system/user");
        return mav;
    }
}
