package com.emergencyguide.Dao.Community;

import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.Post;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author DarckLH
 * @date 2021/5/24 11:51
 * @Description
 */
@Mapper
public interface CommentDao {

    @Select("SELECT * FROM t_comment WHERE customerid = #{customerId}")
    public List<Comment> selectByCustomerid(int customerId);

    public List<Comment> selectAllList();

    public int selectListCount(@Param("params") Map<String, Object> params);

    public List<Comment> selectList(@Param("page") int page, @Param("limit") int limit, @Param("params") Map<String, Object> params);

    public List<Comment> selectByCustomerId(long customerid);

    public int insert(Comment comment);

    public int updateById(Comment comment);

    public Comment selectById(long commentid);

    @Delete("DELETE FROM t_comment WHERE commentid = #{commentid}")
    public int delete(long commentid);

    @Update("UPDATE t_comment SET likes = #{numbers} WHERE commentid = #{commentid}")
    public int like(long commentid, int numbers);

    @Update("UPDATE t_comment SET dislike = #{numbers} + 1 WHERE commentid = #{commentid}")
    public int dislike(long commentid, int numbers);

}
