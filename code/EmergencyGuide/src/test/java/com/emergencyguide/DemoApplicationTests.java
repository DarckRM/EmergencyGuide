package com.emergencyguide;

import com.emergencyguide.Dao.Community.CommentDao;
import com.emergencyguide.Dao.Community.CustomerDao;
import com.emergencyguide.Dao.Community.PostDao;
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
    CommentService commentService;

    @Test
    void contextLoads() {

        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());

        Comment post = new Comment();
        post.setCommentid(3);
        post.setCustomerid(0);
        post.setReplyid(2);
        post.setContent("毛，我之前用过，根本就不对");
        post.setTime(timestamp);
        post.setStatus("关闭");
        post.setLikes(1);
        post.setReply(22);
        post.setDislike(1);

        //System.out.println(post);
        Map<String, Object> params = new HashMap<>();
        params.put("replytopic","");
        params.put("replyid","");

        List<Comment> S = commentDao.selectByCustomerId(0);
        System.out.println(S);

    }

    @Test
    void SecondRs() {
        commentService.selectList(1,1,"{'replyid':'', replytopic:'摸鱼'}");
    }

}
