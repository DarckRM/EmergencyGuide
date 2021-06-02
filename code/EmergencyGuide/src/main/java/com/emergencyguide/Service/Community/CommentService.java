package com.emergencyguide.Service.Community;

import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Service.BaseService;

import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/24 18:39
 * @Description
 */
public interface CommentService {
    public List<Comment> selectByCustomerId(long id);
    public int changeLike(String operate, int commentid, int numbers);
    public int insert(Comment comment);
    public int updateById(Comment comment);
    public Comment selectById(long id);
    public List<Comment> selectAllList();
    public int selectListCount(String searchParams);
    public List<Comment> selectList(int page, int limit, String searchParams);
    public int deleteById(int commentid);
    public int deleteByPostid(int postid);
}
