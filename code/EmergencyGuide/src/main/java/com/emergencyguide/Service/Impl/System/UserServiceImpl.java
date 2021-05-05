package com.emergencyguide.Service.Impl.System;

import com.alibaba.fastjson.JSON;
import com.emergencyguide.Config.ContextConfig;
import com.emergencyguide.Dao.UserDao;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.System.UserService;
import com.emergencyguide.Utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * @author DarckLH
 * @date 2021/5/5 20:39
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    UserDao userDao;
    @Autowired
    private RedisUtil redisUtil;
    // 缓存集合Key值
    private String REDISLISTKEY = "USER_LIST";
    // 缓存单数据Key值
    private String REDISINFOKEY = "USER_";

    @Override
    public List<User> selectList(int page, int llimit, String searchParams) {
        List<User> user = userDao.selectAllList();
        logger.info(user.toString());
        return user;
    }

    @Override
    public int selectListCount(String searchParams) {
        return userDao.selectListCount();
    }

    @Override
    public List<User> selectAllList() {
        return (List)new User();
    }

    @Override
    public User selectById(long id) {
        return new User();
    }

    @Override
    public int deleteById(long id) {
        return 1;
    }

    @Override
    public int updateById(User model) {
        return 0;
    }

    @Override
    public int insert(User model) {
        return 0;
    }

    @Override
    public String doLogin(User user, HttpServletResponse response, HttpServletRequest request){

        User dbUser = userDao.selectByUserName(user.getUsername());
        if (dbUser == null) {
            return "";
        } else if (dbUser.getPassword().equals(user.getPassword())) {
            // 生成cookie
            HttpSession session = request.getSession();
            session.setAttribute("username",dbUser.getUsername());
            logger.trace("当前登录用户"+session.getAttribute("username"));
            String token = UUID.randomUUID().toString().replace("-", "");
            addCookie(response, token, user);
            return token;
        }
        return "";

    }

    @Override
    public int selectByName(){
        return 0;
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
