package com.emergencyguide.Config;

import com.emergencyguide.Entity.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ContextConfig {
    // REDIS缓存时长
    public final static int REDIS_TIMEOUT = 20;
    public static final String COOKIE_NAME_TOKEN = "token";
    /**
     * token过期时间，30分
     */
    public static final int TOKEN_EXPIRE = 1800 * 1 * 1;
    public static final String OPENID="curOpenId";

    /**
     * 得到当前登录的用户对象
     */
    public static User getCurUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();
        User curUser = (User) session.getAttribute("curUser");
        return curUser;
    }
    /**
     * 得到当前登录的用户名
     */
    public static String getCurUserName() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        return username;
    }
    /**
     * 得到当前API请求的OPENID
     */
    public static String getCurApiOpenId() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute(OPENID);
        return username;
    }
}
