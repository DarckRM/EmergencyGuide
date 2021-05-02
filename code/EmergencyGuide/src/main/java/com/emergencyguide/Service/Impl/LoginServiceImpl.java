package com.emergencyguide.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.emergencyguide.Config.ContextConfig;
import com.emergencyguide.Dao.UserDao;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.LoginService;
import com.emergencyguide.Utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    UserDao userDao;
    @Autowired
    private RedisUtil redisUtil;
    // 缓存集合Key值
    private String REDISLISTKEY = "USER_LIST";
    // 缓存单数据Key值
    private String REDISINFOKEY = "USER_";

    public String doLogin(User user, HttpServletResponse response, HttpServletRequest request){

        User dbUser = userDao.selectByUserName(user.getUsername());
        if (dbUser == null) {
            return "";
        } else if (dbUser.getPassword().equals(user.getPassword())) {
            // 生成cookie
            HttpSession session = request.getSession();
            session.setAttribute("username",dbUser.getUsername());
            String token = UUID.randomUUID().toString().replace("-", "");
            addCookie(response, token, user);
            return token;
        }
        return "";

    }
    private void addCookie(HttpServletResponse response, String token, User user) {
        // 将token存入到redis
        redisUtil.set(ContextConfig.COOKIE_NAME_TOKEN + "::" + token, JSON.toJSONString(user),
                ContextConfig.TOKEN_EXPIRE);
        // 将token写入cookie
        Cookie cookie = new Cookie(ContextConfig.COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(ContextConfig.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

}
