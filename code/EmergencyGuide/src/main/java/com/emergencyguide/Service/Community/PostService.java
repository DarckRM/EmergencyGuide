package com.emergencyguide.Service.Community;

import com.emergencyguide.Entity.Post;
import com.emergencyguide.Service.BaseService;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/23 20:45
 * @Description
 */
public interface PostService extends BaseService<Post> {
    public int changeLike(String operate, int postid, int numbers);
    public List<Post> hotPost();
}
