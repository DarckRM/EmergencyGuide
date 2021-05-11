package com.emergencyguide.Service.System.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Config.ContextConfig;
import com.emergencyguide.Dao.System.UserDao;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.System.UserService;
import com.emergencyguide.Utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author DarckLH
 * @date 2021/5/5 20:39
 * @Description
 */
@Service
public class UserServiceImpl implements UserService {
    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisUtil redisUtil;
    // 缓存集合Key值
    private String REDISLISTKEY = "SYSTEMUSER_LIST";
    // 缓存单数据Key值
    private String REDISINFOKEY = "SYSTEMUSER_";

    @Override
    public List<User> selectList(int page, int limit, String searchParams) {

        String username = "";
        String realname = "";
        String authority = "";
        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            username = json.getString("username");
            realname = json.getString("realname");
            authority = json.getString("authority");
        }

        Map<String, Object> params = new HashMap<>();

        params.put("username", username.isEmpty() ? null : username);
        params.put("realname", realname.isEmpty() ? null : realname);
        params.put("authority", authority.isEmpty() ? null : authority);

        List<User> user = userDao.selectList(params);
        logger.info(user.toString());
        return user;
    }

    @Override
    public int selectListCount(String searchParams) {
        return userDao.selectListCount();
    }

    @Override
    public List<User> selectAllList() {

        logger.debug(this.getClass() + "-selectAllList");
        Boolean hasKey = redisUtil.hasKey(REDISLISTKEY);
        if (hasKey) {
            List<User> redisList = redisUtil.getList(REDISLISTKEY, User.class);
            return redisList;
        }
        List<User> list = userDao.selectAllList();
        // 存在到缓存中
        redisUtil.set(REDISLISTKEY, list);
        return list;

    }

    @Override
    public User selectById(long id) {

        logger.debug(this.getClass() + "-selectById");
        Boolean hasKey = redisUtil.hasKey(REDISLISTKEY+id);
        if (hasKey) {
            User user = redisUtil.getModel(REDISLISTKEY, User.class);
            return user;
        }
        User user = userDao.selectById(id);
        // 存在到缓存中
        redisUtil.set(REDISLISTKEY, user);
        return user;

    }

    @Override
    public int deleteById(long id) {
        return 1;
    }

    @Override
    public int updateById(User user) {
        String key = REDISINFOKEY + user.getId();
        int update = userDao.updateById(user);
        if (update > 0) {
            redisUtil.del(key);
            logger.debug(this.getClass() + ">>从缓存中删除编号 >>" + user.getId());
        }
        return update;
    }

    @Override
    public int insert(User user) {

        user.setStatus("启用");
        int insert = userDao.insert(user);
        if (insert > 0) {
            redisUtil.del(REDISLISTKEY);
        }
        return insert;
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
