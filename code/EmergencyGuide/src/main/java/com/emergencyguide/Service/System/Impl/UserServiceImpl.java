package com.emergencyguide.Service.System.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Config.ContextConfig;
import com.emergencyguide.Dao.System.UserDao;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.System.UserService;
import com.emergencyguide.Utils.EasyGeneraterParams;
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
    @Autowired
    private EasyGeneraterParams easyGeneraterParams;
    // 缓存集合Key值
    private String REDISLISTKEY = "SYSTEMUSERLIST_";
    // 缓存单数据Key值
    private String REDISINFOKEY = "SYSTEMUSER_";
    // 存放查询条件便于操作缓存
    private String LISTNAME;

    @Override
    public List<User> selectList(int page, int limit, String searchParams) {

        Map<String, Object> params = new HashMap<>();
        LISTNAME = searchParams;

        params = easyGeneraterParams.easySearchParams(searchParams);
        //判断该查询条件下缓存中是否存在
        Boolean hasKey = redisUtil.hasKey(REDISLISTKEY+searchParams);
        if (hasKey) {
            System.out.println("已在"+searchParams+"查询条件下获取到用户列表");
            List<User> redisList = redisUtil.getList(REDISLISTKEY+searchParams, User.class);
            return redisList;
        }
        List<User> users = userDao.selectList(params);
        // 存在到缓存中
        redisUtil.set(REDISLISTKEY+searchParams, users);
        logger.info(users.toString());

        return users;
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
        Boolean hasKey = redisUtil.hasKey(REDISINFOKEY+id);
        if (hasKey) {
            System.out.println("已从Redis中取得用户数据");
            User user = redisUtil.getModel(REDISINFOKEY+id, User.class);
            return user;
        }
        User user = userDao.selectById(id);
        // 存在到缓存中
        redisUtil.set(REDISINFOKEY+id, user);
        return user;

    }

    @Override
    public int deleteById(long id) {
        String key = REDISINFOKEY + id;
        int delete = userDao.delete(id);
        if (delete > 0) {
            //修改了数据库中的内容因此之前缓存的List也要移除
            redisUtil.del(REDISLISTKEY);
            redisUtil.del(key);
            logger.debug(this.getClass() + ">>从缓存中删除编号 >>" + id);
        }
        return delete;
    }

    @Override
    public int updateById(User user) {
        String key = REDISINFOKEY + user.getId();
        int update = userDao.updateById(user);
        if (update > 0) {
            //修改了数据库中的内容因此之前缓存的List也要移除
            redisUtil.del(REDISLISTKEY+LISTNAME);
            redisUtil.del(key);
            System.out.println(this.getClass() + ">>从缓存中删除编号 >>" + user.getId());
            System.out.println((this.getClass() + ">>从缓存中删除之前的列表 >>" + LISTNAME));
        }
        return update;
    }

    @Override
    public int insert(User user) {

        user.setStatus("启用");
        int insert = userDao.insert(user);
        if (insert > 0) {
            //修改了数据库中的内容因此之前缓存的List也要移除
            redisUtil.del(REDISLISTKEY);
            redisUtil.del(REDISLISTKEY);
            logger.debug(this.getClass() + ">>从缓存中删除编号 >>" + user.getId());
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
    public User selectByName(String username){
        return userDao.selectByUserName(username);
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
