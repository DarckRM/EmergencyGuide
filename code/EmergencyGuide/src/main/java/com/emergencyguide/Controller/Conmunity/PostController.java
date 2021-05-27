package com.emergencyguide.Controller.Conmunity;

import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.Post;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Community.PostService;
import javafx.geometry.Pos;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("post")
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/page")
    public ModelAndView postPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("community/post");
        return mav;
    }

    @RequestMapping("/findall")
    public String findAll(String searchParams, int page, int limit) {

        Result<Post> result = new Result<>();

        //处理

        List<Post> datas = postService.selectList(page, limit, searchParams);
        result.setCount(postService.selectListCount(searchParams));
        result.setData(datas);
        result.setMsg("请求成功");
        return result.toString();

    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@Param("postid") int postid) {
        Post data = null;
        if (postid != -1 && postid != 0) {
            data = postService.selectById(postid);
        } else {
            data = new Post();
        }
        ModelAndView mav = new ModelAndView();
        System.out.println(data);
        mav.setViewName("community/post_edit");
        mav.addObject("data", data);

        return mav;
    }

    @RequestMapping("/save")
    public String save(@RequestBody Post post) {

        Result<Post> result = new Result<>();
        int count = 0;

        if (post.getPostid() > 0) {
            count = postService.updateById(post);
        } else {
            count = postService.insert(post);
        }
        if (count > 0) {
            result.setMsg("保存成功");
        } else {
            result.setMsg("保存失败");
        }
        return result.toString();
    }

    @RequestMapping("/delete")
    public String delete(long postid) {

        Result<Post> result = new Result<>();

        result.setCount(postService.deleteById(postid));
        result.setMsg("删除成功");
        return result.toString();

    }

}
