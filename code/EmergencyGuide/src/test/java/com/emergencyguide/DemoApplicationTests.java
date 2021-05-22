package com.emergencyguide;

import com.emergencyguide.Dao.Community.CustomerDao;
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

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@WebAppConfiguration
class DemoApplicationTests {

    @Autowired
    RoleService roleService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    UserDao userDao;
    @Autowired
    CustomerDao customerDao;

    @Test
    void contextLoads() {

        Map<String, Object> params = new HashMap<>();

        params.put("nickname", null);
        params.put("email", null);

    }

}
