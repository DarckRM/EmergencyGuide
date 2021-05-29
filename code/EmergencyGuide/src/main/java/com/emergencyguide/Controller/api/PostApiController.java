package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Entity.Post;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Community.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/26 22:51
 * @Description
 */
@RestController
@RequestMapping("api/post")
@Api(value = "客户主题接口", tags = "客户主题API")
public class PostApiController {

    @Autowired
    private PostService postService;

    @ApiOperation(value="分页查看当前用户的发帖记录")
    @PostMapping("/mypost")
    @ApiImplicitParam(name = "jsonStr", value = "接收json字符串，包含三个属性int:page, int:limit, JSON:searchParams", required = true, dataType = "string")
    public String myPost(@RequestBody String jsonStr) {

        Result<Post> result = new Result<>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            List<Post> datas = postService.selectList(jsonObject.getInteger("page"), jsonObject.getInteger("limit"), jsonObject.getString("searchParams"));
            result.setCount(postService.selectListCount(jsonObject.getString("searchParams")));
            result.setData(datas);
            result.setMsg("请求成功");

        } catch (Exception e)
        {
            result.setMsg("请求失败" + e.getMessage());
            return result.toString();
        }
        return result.toString();
    }

    @ApiOperation(value="为当前用户发帖")
    @PostMapping("/newpost")
    @ApiImplicitParam(name = "jsonStr", value = "接收json字符串，包含三个属性String:topic, String:content, String:openid", required = true, dataType = "string")
    public String newPost(@RequestBody String jsonStr) {

        Result<Post> result = new Result<>();
        Post post = new Post();

        try
        {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            post.setTopic(jsonObject.getString("topic"));
            post.setContent(jsonObject.getString("content"));
            post.setOpenid(jsonObject.getInteger("openid"));
            postService.insert(post);
            result.setMsg("请求成功");

        } catch (Exception e)
        {
            result.setMsg("请求失败" + e.getMessage());
            return result.toString();
        }
        return result.toString();

    }

    @ApiOperation(value="删除当前用户的某条帖子")
    @PostMapping("/delpost")
    @ApiImplicitParam(name = "postid", value = "接收帖子的postid，int类型", required = true, dataType = "int")
    public String delPost(@RequestBody int postid) {

        Result<Post> result = new Result<>();

        try
        {
            postService.deleteById(postid);
            result.setMsg("请求成功");
        } catch (Exception e)
        {
            result.setMsg("请求失败" + e.getMessage());
            return result.toString();
        }
        return result.toString();

    }

    @ApiOperation(value="修改当前用户的某条帖子")
    @PostMapping("/editpost")
    @ApiImplicitParam(name = "jsonStr", value = "类似发帖的json字符串，不过要加上postid确认是修改哪个帖子 {'postid':'帖子ID','topic':'修改后的标题','content':'修改后的内容'}", required = true, dataType = "int")
    public String editPost(@RequestBody String jsonStr) {

        Result<Post> result = new Result<>();
        Post post = new Post();

        try
        {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            post.setTopic(jsonObject.getString("topic"));
            post.setContent(jsonObject.getString("content"));
            post.setPostid(jsonObject.getInteger("postid"));
            postService.insert(post);
            result.setMsg("请求成功");

        } catch (Exception e)
        {
            result.setMsg("请求失败" + e.getMessage());
            return result.toString();
        }
        return result.toString();

    }

}
