package com.emergencyguide;

import com.emergencyguide.Dao.UserDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

@SpringBootTest
@WebAppConfiguration
class DemoApplicationTests {

    @Autowired
    UserDao userDao;

    @Test
    void contextLoads() {
        System.out.println(userDao.selectAllList());
    }

}
