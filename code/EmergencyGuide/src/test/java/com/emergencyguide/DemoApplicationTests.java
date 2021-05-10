package com.emergencyguide;

import com.emergencyguide.Dao.System.UserDao;
import com.emergencyguide.Dao.SystemConfigDao;
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
    SystemConfigService systemConfigService;
    @Autowired
    RedisUtil redisUtil;

    @Test
    void contextLoads() {

        System.out.println(systemConfigService.selectSystemConfig());
        System.out.println(systemConfigService.selectSystemConfig());

    }

}
