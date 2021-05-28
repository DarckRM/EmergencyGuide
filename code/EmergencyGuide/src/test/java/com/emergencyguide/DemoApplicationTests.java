package com.emergencyguide;

import com.emergencyguide.Controller.Logo.PersonalLogoController;
import com.emergencyguide.Controller.api.PostApiController;
import com.emergencyguide.Dao.Community.CommentDao;
import com.emergencyguide.Dao.Community.CustomerDao;
import com.emergencyguide.Dao.Community.PostDao;
import com.emergencyguide.Dao.Logo.PersonalLogoDao;
import com.emergencyguide.Dao.System.RoleDao;
import com.emergencyguide.Dao.System.UserDao;
import com.emergencyguide.Dao.SystemConfigDao;
import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Entity.Post;
import com.emergencyguide.Entity.Role;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.Community.CommentService;
import com.emergencyguide.Service.Community.PostService;
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
    PersonalLogoController personalLogoDao;

    @Test
    void contextLoads() {

        System.out.println(personalLogoDao.selectSubLogo("律师"));

    }

}
