package com.emergencyguide.Service.Community.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.Community.CustomerDao;
import com.emergencyguide.Dao.Community.PostDao;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.Post;
import com.emergencyguide.Service.Community.PostService;
import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/23 20:45
 * @Description
 */
@Service
public class PostServiceImpl implements PostService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private PostDao postDao;

    @Override
    public List<Post> selectList(int page, int limit, String searchParams) {

        String topic = "";
        String postid = "";
        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            topic = json.getString("topic");
            postid = json.getString("postid");
        }

        Map<String, Object> params = new HashMap<>();

        params.put("topic", topic.isEmpty() ? null : topic);
        params.put("postid", postid.isEmpty() ? null : postid);

        List<Post> post = postDao.selectList(page, limit, params);
        logger.info(post.toString());

        return post;
    }

    @Override
    public int selectListCount(String searchParams) {

        String topic = "";
        String postid = "";
        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            topic = json.getString("topic");
            postid = json.getString("postid");
        }

        Map<String, Object> params = new HashMap<>();

        params.put("topic", topic.isEmpty() ? null : topic);
        params.put("postid", postid.isEmpty() ? null : postid);

        return postDao.selectListCount(params);
    }

    @Override
    public List<Post> selectAllList() {
        return null;
    }

    @Override
    public Post selectById(long id) {
        return postDao.selectById(id);
    }

    @Override
    public int deleteById(long id) {
        return postDao.delete(id);
    }

    @Override
    public int updateById(Post post) {
        return postDao.updateById(post);
    }

    @Override
    public int insert(Post post) {
        return postDao.insert(post);
    }
}
