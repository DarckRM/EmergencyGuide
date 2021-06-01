package com.emergencyguide;

import com.emergencyguide.Controller.Logo.PersonalLogoController;
import com.emergencyguide.Controller.api.PostApiController;
import com.emergencyguide.Dao.Community.*;
import com.emergencyguide.Dao.Logo.PersonalLogoDao;
import com.emergencyguide.Dao.System.DefaultImgDao;
import com.emergencyguide.Dao.System.RoleDao;
import com.emergencyguide.Dao.System.UserDao;
import com.emergencyguide.Dao.SystemConfigDao;
import com.emergencyguide.Entity.*;
import com.emergencyguide.Service.Community.AddressService;
import com.emergencyguide.Service.Community.CommentService;
import com.emergencyguide.Service.Community.PostService;
import com.emergencyguide.Service.Community.StationService;
import com.emergencyguide.Service.System.RoleService;
import com.emergencyguide.Service.SystemConfigService;
import com.emergencyguide.Utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.cache.CacheProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
@WebAppConfiguration
class DemoApplicationTests {

    @Autowired
    RoleService roleService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    CommentDao commentDao;
    @Autowired
    DefaultImgDao defaultImgDao;

    @Test
    void contextLoads() {

        HashMap<String, Object> searchParams = new HashMap<>();

        Image image = new Image();

        image.setImgname("客户默认头像");
        image.setPath("/......");
        image.setInfo("测试内容");
        image.setId(2);
        searchParams.put("id",1);

        System.out.println(defaultImgDao.selectList(0,1,searchParams));

    }

}
