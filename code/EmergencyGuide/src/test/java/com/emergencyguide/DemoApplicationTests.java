package com.emergencyguide;

import com.emergencyguide.Dao.System.RoleDao;
import com.emergencyguide.Dao.System.UserDao;
import com.emergencyguide.Dao.SystemConfigDao;
import com.emergencyguide.Entity.Role;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.System.RoleService;
import com.emergencyguide.Service.SystemConfigService;
import com.emergencyguide.Utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
class DemoApplicationTests {

    @Autowired
    RoleService roleService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {

        User user = new User();
        user.setAuthority(0);
        user.setPassword("123213");
        user.setUsername("test");
        user.setAvatar("test");
        user.setStatus("禁用");

        userDao.insert(user);

        System.out.println(roleService.selectAllList());

    }

}
