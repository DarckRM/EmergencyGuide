package com.emergencyguide.Dao.Community;

import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.Post;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/23 20:30
 * @Description
 */
@Mapper
public interface PostDao {

    @Select("SELECT * FROM t_post WHERE customerid = #{customerId}")
    public List<Post> selectByCustomerid(int customerId);

    public List<Post> selectAllList();

    public int selectListCount(@Param("params") Map<String, Object> params);

    public List<Post> selectList(@Param("page") int page, @Param("limit") int limit, @Param("params") Map<String, Object> params);

    @Select("SELECT * FROM t_post WHERE postid = #{postid}")
    public Post selectById(long postid);

    public int insert(Post post);

    public int updateById(Post post);

    @Delete("DELETE FROM t_post WHERE postid = #{postid}")
    public int delete(long postid);

    @Update("UPDATE t_post SET like = like + 1 WHERE postid = #{postid}")
    public int like(long postid);

    @Update("UPDATE t_post SET dislike = dislike + 1 WHERE postid = #{postid}")
    public int dislike(long postid);

}
