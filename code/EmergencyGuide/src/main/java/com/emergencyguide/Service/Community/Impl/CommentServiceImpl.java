package com.emergencyguide.Service.Community.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.Community.CommentDao;
import com.emergencyguide.Dao.Community.PostDao;
import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Service.Community.CommentService;
import com.emergencyguide.Utils.EasyGeneraterParams;
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
 * @date 2021/5/24 18:39
 * @Description
 */
@Service
public class CommentServiceImpl implements CommentService {

    Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private EasyGeneraterParams easyGeneraterParams;

    public List<Comment> selectList(int page, int limit, String searchParams) {

        Map<String, Object> params = new HashMap<>();

        params = easyGeneraterParams.easySearchParams(searchParams);
        page = (page - 1) * limit;
        List<Comment> comments = commentDao.selectList(page, limit, params);
        logger.info(comments.toString());

        return comments;
    }

    public int selectListCount(String searchParams) {

        Map<String, Object> params = new HashMap<>();

        params = easyGeneraterParams.easySearchParams(searchParams);

        return commentDao.selectListCount(params);
    }

    public List<Comment> selectAllList() {
        return null;
    }

    public Comment selectById(long id) {
        return commentDao.selectById(id);
    }

    @Override
    public List<Comment> selectByCustomerId(long id) {
        return commentDao.selectByCustomerId(id);
    }

    public int deleteById(int commentid) {

        Comment comment = commentDao.selectById(commentid);
        int replyid = comment.getReplyid();

        //修改被评论帖的评论数
        postDao.changereply(-1, replyid);
        return commentDao.delete(commentid);
    }

    public int updateById(Comment comment) {
        return commentDao.updateById(comment);
    }

    public int insert(Comment comment) {

        //给评论添加默认信息
        //获取当前时间
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        comment.setTime(timestamp);
        //设置默认审核状态
        comment.setStatus("未审核");
        //修改被评论帖的发帖数
        postDao.changereply(1, comment.getReplyid());

        return commentDao.insert(comment);
    }

    @Override
    public int changeLike(String operate, int commentid, int numbers) {

        int status;
        if (operate.equals("like")) {
            status = commentDao.like(commentid,numbers);
        } else {
            status = commentDao.dislike(commentid,numbers);
        }
        return status;
    }

}
