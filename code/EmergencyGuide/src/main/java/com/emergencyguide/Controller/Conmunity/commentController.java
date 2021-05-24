package com.emergencyguide.Controller.Conmunity;

import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Community.CommentService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/24 20:26
 * @Description
 */
@RequestMapping("comment")
@RestController
public class commentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping("/page")
    public ModelAndView postPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("community/comment");
        return mav;
    }

    @RequestMapping("/findall")
    public String findAll(String searchParams, int page, int limit) {

        Result<Comment> result = new Result<>();

        //处理

        List<Comment> datas = commentService.selectList(page, limit, searchParams);
        result.setCount(commentService.selectListCount(searchParams));
        result.setData(datas);

        result.setMsg("请求成功");
        return result.toString();

    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@Param("commentid") int commentid) {
        Comment data = null;
        if (commentid != -1 && commentid != 0) {
            data = commentService.selectById(commentid);
        } else {
            data = new Comment();
        }
        ModelAndView mav = new ModelAndView();
        System.out.println(data);
        mav.setViewName("community/comment_edit");
        mav.addObject("data", data);

        return mav;
    }

    @RequestMapping("/save")
    public String save(@RequestBody Comment comment) {

        System.out.println(comment);

        Result<Comment> result = new Result<>();
        int count = 0;

        if (comment.getCommentid() > 0) {
            count = commentService.updateById(comment);
        } else {
            count = commentService.insert(comment);
        }
        if (count > 0) {
            result.setMsg("保存成功");
        } else {
            result.setMsg("保存失败");
        }
        return result.toString();
    }

    @RequestMapping("/delete")
    public String delete(@Param("commentid") int commentid) {

        System.out.println(commentid);
        Result<Comment> result = new Result<>();

        result.setCount(commentService.deleteById(commentid));
        result.setMsg("删除成功");
        return result.toString();

    }

}
