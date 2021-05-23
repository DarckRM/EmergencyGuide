package com.emergencyguide;

import com.emergencyguide.Dao.Community.CustomerDao;
import com.emergencyguide.Dao.Community.PostDao;
import com.emergencyguide.Dao.System.RoleDao;
import com.emergencyguide.Dao.System.UserDao;
import com.emergencyguide.Dao.SystemConfigDao;
import com.emergencyguide.Entity.Post;
import com.emergencyguide.Entity.Role;
import com.emergencyguide.Entity.User;
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
import java.util.Map;

@SpringBootTest
@WebAppConfiguration
class DemoApplicationTests {

    @Autowired
    RoleService roleService;
    @Autowired
    RedisUtil redisUtil;
    @Autowired
    PostDao postDao;

    @Test
    void contextLoads() {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Post post = new Post();
        post.setPostid(5);
        post.setCustomerid(2);
        post.setTopic("测试主题");
        post.setContent("测试");
        post.setTime(timestamp);
        post.setStatus("关闭");
        post.setLikes(1);
        post.setReply(22);
        post.setDislike(1);

        //System.out.println(post);
        Map<String, Object> params = new HashMap<>();
        params.put("topic","测试");

        int s = postDao.selectListCount(params);
        System.out.println(s);

    }

}
