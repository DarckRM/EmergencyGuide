package com.emergencyguide.Controller.Base;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseController {

    /**
     * 得到request对象
     */
    public HttpServletRequest getRequest() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        return request;
    }
    /**
     * 得到当前后台登录用户
     */
    public String getCurUserName() {
        HttpSession session = this.getRequest().getSession();
        String username = (String) session.getAttribute("username");
        return username;
    }
}
