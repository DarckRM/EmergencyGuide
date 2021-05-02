package com.emergencyguide.Service;

import com.emergencyguide.Entity.User;
import org.springframework.http.HttpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginService {
    public String doLogin(User user, HttpServletResponse response, HttpServletRequest request);
}
