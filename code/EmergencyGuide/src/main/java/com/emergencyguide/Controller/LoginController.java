package com.emergencyguide.Controller;

import com.emergencyguide.Entity.Result;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class LoginController {

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;

    @GetMapping("/login")
    public String webServiceDemo(){
        return "/login";
    }

    @PostMapping("/doLogin")
    @ResponseBody
    public String login(@RequestBody(required = false) User user, HttpServletResponse response, HttpServletRequest request){

        String token = loginService.doLogin(user, response, request);

        if (token != null && !token.isEmpty()) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().success("操作失败").toString();
        }
    }
}
