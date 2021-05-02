package com.emergencyguide.Controller;

import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String webServiceDemo(){
        return "/login";
    }
    @PostMapping("/doLogin")
    @ResponseBody
    public ModelAndView login(@RequestParam Map<String, Object> params, HttpServletRequest servletRequest, User user){
        user.setUsername(params.get("username").toString());
        user.setPassword(params.get("password").toString());

        ModelAndView mav = new ModelAndView();

        user = loginService.doLogin(user);
        if (user == null) {
            User nulluser = new User();
            nulluser.setUsername("未找到");
            nulluser.setPassword("未找到");
            mav.addObject("data", nulluser);
        } else {
            mav.addObject("data", user);
        }
        mav.setViewName("/index");

        return mav;
    }
}
