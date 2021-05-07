package com.emergencyguide.Controller;

import com.emergencyguide.Entity.User;
import com.emergencyguide.Service.System.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.emergencyguide.Controller.Base.BaseController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @Autowired
    private UserService userService;

    //修改默认访问页面
    @RequestMapping("/")
    public String defaultPage() {
        return "login";
    }

    @RequestMapping("/index")
    public ModelAndView index() {
        // 获取系统配置
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        mav.addObject("curUsername", getCurUserName());
        return mav;
    }

    @RequestMapping("/content")
    public ModelAndView content() {
        // 获取系统配置
        ModelAndView mav = new ModelAndView();

        mav.setViewName("content");
        mav.addObject("curUserCount",userService.selectListCount("null"));
        mav.addObject("curUsername", getCurUserName());
        return mav;
    }

}
