package com.emergencyguide.Service.Community.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.Community.CustomerDao;
import com.emergencyguide.Dao.Community.PostDao;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.Post;
import com.emergencyguide.Service.Community.PostService;
import com.emergencyguide.Utils.EasyGeneraterParams;
import javafx.geometry.Pos;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
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

    @Autowired
    private EasyGeneraterParams easyGeneraterParams;

    @Override
    public List<Post> selectList(int page, int limit, String searchParams) {

        Map<String, Object> params = new HashMap<>();

        params = easyGeneraterParams.easySearchParams(searchParams);
        page = (page - 1) * limit;
        List<Post> post = postDao.selectList(page, limit, params);
        logger.info(post.toString());

        return post;
    }

    @Override
    public int selectListCount(String searchParams) {

        Map<String, Object> params = new HashMap<>();

        params = easyGeneraterParams.easySearchParams(searchParams);

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

        //给主题添加默认信息
        //获取当前时间
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        post.setTime(timestamp);
        //设置默认审核状态
        post.setStatus("未审核");

        return postDao.insert(post);
    }

    @Override
    public int changeLike(String operate, int postid) {

        int status;
        if (operate == "like") {
            status = postDao.like(postid);
        } else {
            status = postDao.dislike(postid);
        }
        return status;
    }
}
