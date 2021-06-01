package com.emergencyguide.Controller.System;

import com.emergencyguide.Entity.Comment;
import com.emergencyguide.Entity.Image;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Community.CommentService;
import com.emergencyguide.Service.System.DefaultImageService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/6/2 0:58
 * @Description
 */
@RequestMapping("defaultImg")
@RestController
public class DefaultImgController {

    @Autowired
    private DefaultImageService defaultImageService;

    @RequestMapping("/page")
    public ModelAndView postPage() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("system/defaultimg");
        return mav;
    }

    @RequestMapping("/findall")
    public String findAll(String searchParams, int page, int limit) {

        Result<Image> result = new Result<>();

        List<Image> datas = defaultImageService.selectList(page, limit, searchParams);
        result.setCount(defaultImageService.selectListCount(searchParams));
        result.setData(datas);

        return result.toString();
    }

    @RequestMapping("/toEdit")
    public ModelAndView toEdit(@Param("id") int id) {
        Image data = null;
        if (id != -1 && id != 0) {
            data = defaultImageService.selectById(id);
        } else {
            data = new Image();
        }
        ModelAndView mav = new ModelAndView();
        System.out.println(data);
        mav.setViewName("system/defaultimg_edit");
        mav.addObject("data", data);

        return mav;
    }

    @RequestMapping("/save")
    public String save(@RequestBody Image image) {

        Result<Image> result = new Result<>();
        int count = 0;

        if (image.getId() > 0) {
            count = defaultImageService.updateById(image);
        } else {
            count = defaultImageService.insert(image);
        }
        if (count > 0) {
            result.setMsg("保存成功");
        } else {
            result.setMsg("保存失败");
        }
        return result.toString();
    }

    @RequestMapping("/delete")
    public String delete(@Param("id") int id) {

        Result result = new Result<>();

        result.setCount(defaultImageService.deleteById(id));
        result.setMsg("删除成功");
        return result.toString();

    }
}
