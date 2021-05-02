package com.emergencyguide.Controller;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.emergencyguide.Controller.Base.BaseController;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class IndexController extends BaseController {

    @RequestMapping("/index")
    public ModelAndView index() {
        // 获取系统配置
        ModelAndView mav = new ModelAndView();
        mav.setViewName("index");
        System.out.println(getCurUserName());
        mav.addObject("data", getCurUserName());
        return mav;
    }

}
