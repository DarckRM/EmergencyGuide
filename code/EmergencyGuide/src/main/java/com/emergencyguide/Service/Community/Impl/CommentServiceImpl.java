package com.emergencyguide.Service.Community.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.Community.CommentDao;
import com.emergencyguide.Dao.Community.PostDao;
import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.Community.CommentService;
import com.emergencyguide.Utils.EasyGeneraterParams;
import com.emergencyguide.Utils.RedisUtil;
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

    @Autowired
    private RedisUtil redisUtil;

    // 缓存集合Key值
    private String REDISLISTKEY = "CUSTOMER_LIST_";
    // 缓存单数据Key值
    private String REDISINFOKEY = "CUSTOMER_";
    // 存放查询条件便于操作缓存
    private String LISTNAME;

    public List<Comment> selectList(int page, int limit, String searchParams) {

        Map<String, Object> params = new HashMap<>();

        params = easyGeneraterParams.easySearchParams(searchParams);
        page = (page - 1) * limit;
        //修改该次查询列表的缓存名字
        LISTNAME = searchParams + page + limit;

        //判断该查询条件下缓存中是否存在
        Boolean hasKey = redisUtil.hasKey(REDISLISTKEY+LISTNAME);
        if (hasKey) {
            System.out.println("已在"+searchParams+"查询条件下获取到用户列表");
            List<Comment> redisList = redisUtil.getList(REDISLISTKEY+LISTNAME, Comment.class);
            return redisList;
        }
        List<Comment> comments = commentDao.selectList(page, limit, params);
        // 存在到缓存中
        redisUtil.set(REDISLISTKEY+LISTNAME, comments);

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

        Boolean hasKey = redisUtil.hasKey(REDISINFOKEY+id);
        if (hasKey) {
            System.out.println("已从Redis中取得评论数据");
            Comment comment = redisUtil.getModel(REDISINFOKEY+id, Comment.class);
            return comment;
        }
        Comment comment = commentDao.selectById(id);
        // 存在到缓存中
        redisUtil.set(REDISINFOKEY+id, comment);

        return comment;
    }

    @Override
    public List<Comment> selectByCustomerId(long id) {
        return commentDao.selectByCustomerId(id);
    }

    public int deleteById(int commentid) {
        String key = REDISINFOKEY + commentid;
        int delete;
        Comment comment = commentDao.selectById(commentid);
        int replyid = comment.getReplyid();

        //修改被评论帖的评论数
        postDao.changereply(-1, replyid);
        delete = commentDao.delete(commentid);
        if (delete > 0) {
            //修改了数据库中的内容因此之前缓存的List也要移除
            redisUtil.del(REDISLISTKEY + LISTNAME);
            logger.debug(this.getClass() + ">>从缓存中删除编号 >>" + commentid);
        }
        return delete;
    }

    @Override
    public int deleteByPostid(int postid) {
        return commentDao.deleteByPostid(postid);
    }

    public int updateById(Comment comment) {

        String key = REDISINFOKEY + comment.getCommentid();
        int update = commentDao.updateById(comment);

        if (update > 0) {
            //修改了数据库中的内容因此之前缓存的List也要移除
            redisUtil.del(REDISLISTKEY + LISTNAME);
            System.out.println((this.getClass() + ">>从缓存中删除之前的列表 >>" + LISTNAME));
        }

        return update;
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

        int insert = commentDao.insert(comment);

        if (insert > 0) {
            //修改了数据库中的内容因此之前缓存的List也要移除
            redisUtil.del(REDISLISTKEY + LISTNAME);
            System.out.println((this.getClass() + ">>从缓存中删除之前的列表 >>" + LISTNAME));
        }
        return insert;
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
