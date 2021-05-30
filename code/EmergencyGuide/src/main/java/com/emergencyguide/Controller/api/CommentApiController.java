package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Entity.Post;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Community.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/28 0:35
 * @Description
 */
@RestController
@RequestMapping("api/comment")
@Api(value = "评论接口", tags = "客户评论API")
public class CommentApiController {

    @Autowired
    private CommentService commentService;

    @ApiOperation(value="分页查看当前用户的评论记录")
    @PostMapping("/mycomment")
    @ApiImplicitParam(name = "jsonStr", value = "{'page':'页数:int', 'limit':'每页显示数:int', 'searchParams':'\\{'topic':'评论的主题','customername':'用户昵称','openid':'用户openid', 'postid':'帖子ID:int'}:json(参数均可选)'} searchParams中三个参数可选择传递，一般传入openid", required = true, dataType = "string")
    public String myPost(@RequestBody String jsonStr) {

        Result<Comment> result = new Result<>();
        try
        {
            System.out.println(jsonStr);
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            List<Comment> datas = commentService.selectList(jsonObject.getInteger("page"), jsonObject.getInteger("limit"), jsonObject.getString("searchParams"));
            result.setCount(commentService.selectListCount(jsonObject.getString("searchParams")));
            result.setData(datas);
            result.setMsg("请求成功");

        } catch (Exception e)
        {
            result.setMsg("请求失败" + e.getMessage());
            return result.toString();
        }
        return result.toString();
    }

    @ApiOperation(value="为当前用户发评论")
    @PostMapping("/newcomment")
    @ApiImplicitParam(name = "jsonStr", value = "{'postid':'所评论的帖子id:int', 'content':'评论的内容:String', 'openid':'当前用户的openid:String'}", required = true, dataType = "string")

    public String newPost(@RequestBody String jsonStr) {

        Result<Comment> result = new Result<>();
        Comment comment = new Comment();

        try
        {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            comment.setReplyid(jsonObject.getInteger("postid"));
            comment.setContent(jsonObject.getString("content"));
            comment.setCustomerid(jsonObject.getString("openid"));
            commentService.insert(comment);
            result.setMsg("请求成功");

        } catch (Exception e)
        {
            System.out.println(e);
            result.setMsg("请求失败" + e.getMessage());
            return result.toString();
        }
        return result.toString();

    }

    @ApiOperation(value="删除当前用户的某条评论")
    @PostMapping("/delcomment")
    @ApiImplicitParam(name = "jsonStr", value = "{'commentid':'评论的ID:int'}", required = true, dataType = "int")
    public String delPost(@RequestBody String jsonStr) {

        Result<Post> result = new Result<>();

        try
        {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            commentService.deleteById(jsonObject.getInteger("commentid"));
            result.setMsg("请求成功");
        } catch (Exception e)
        {
            result.setMsg("请求失败" + e.getMessage());
            return result.toString();
        }
        return result.toString();

    }

    @ApiOperation(value="给某条评论点赞或点踩")
    @PostMapping("/changelike")
    @ApiImplicitParam(name = "jsonStr", value = "{'commentid':'点赞的评论的id','operate':'like或者dislike','numbers':'修改后的数量:int'}", required = true, dataType = "int")
    public String like(@RequestBody String jsonStr) {
        Result result = new Result();

        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        try {
            commentService.changeLike(jsonObject.getString("operate"),jsonObject.getInteger("commentid"),jsonObject.getInteger("numbers"));
            if (jsonObject.getString("operate") == "like") {
                result.setMsg("成功点赞!");
            } else {
                result.setMsg("成功点踩!");
            }
        } catch (Exception e) {

            result.setMsg("操作失败 错误信息"+e.getMessage());

        }
        return result.toString();
    }

}
