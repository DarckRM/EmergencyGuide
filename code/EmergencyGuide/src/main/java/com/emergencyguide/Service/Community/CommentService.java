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
public interface CommentService extends BaseService<Comment> {
    public List<Comment> selectByCustomerId(long id);
    public int changeLike(String operate, int postid);
}
