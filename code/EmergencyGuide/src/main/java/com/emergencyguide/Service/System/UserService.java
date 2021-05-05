package com.emergencyguide.Service.System;

import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author DarckLH
 * @date 2021/5/5 20:40
 * @Description
 */
public interface UserService extends BaseService<User> {

    public int selectByName();

    public String doLogin(User user, HttpServletResponse response, HttpServletRequest request);
}
