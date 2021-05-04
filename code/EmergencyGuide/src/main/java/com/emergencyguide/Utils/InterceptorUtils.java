package com.emergencyguide.Utils;

import com.emergencyguide.Entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * @Auther: DarckLH
 * @Date: 2021/5/5
 * @Description: 拦截器
 */
@Component
public class InterceptorUtils implements HandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(InterceptorUtils.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String username = (String)request.getSession().getAttribute("username");
        logger.info(username);
        if(username == null) {
            response.sendRedirect(request.getContextPath()+"/login");
            return false;
        }
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        logger.info("postHandle...");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        logger.info("afterCompletion...");
    }
}
