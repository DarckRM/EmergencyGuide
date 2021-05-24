package com.emergencyguide.Controller.Logo;

import com.emergencyguide.Entity.PersonalLogo;
import com.emergencyguide.Entity.Rank;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Logo.PersonalLogoService;
import com.emergencyguide.Service.Logo.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("personalLogo")
public class PersonalLogoController {
    @Autowired

    private PersonalLogoService personalLogoService;


    @RequestMapping("/findPersonalLogoHtml")
    public ModelAndView findRankHtml(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("Logo/personalLogo");
        return modelAndView;
    }

    @RequestMapping("findAll")
    @ResponseBody
    public String findAll(int page, int limit,  String searchParams)
    {

        Result<PersonalLogo> result=new Result<>();
        List<PersonalLogo> data=personalLogoService.selectAllList(page,limit,searchParams);
        result.setData(data);
        result.setCount(personalLogoService.selectListCount(searchParams));
        return result.toString();
    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestBody PersonalLogo personalLogo){
        int count=0;
        count=personalLogoService.updateById(personalLogo);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/findAddPersonalLogoHtml")
    public ModelAndView findAddDisasterHtml() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Logo/personalLogoAdd");
        return modelAndView;
    }
    @RequestMapping("/personalLogoAdd")
    @ResponseBody
    public String personalLogoAdd(@RequestBody PersonalLogo personalLogo){
        int count=0;
        count=personalLogoService.personalLogoAdd(personalLogo);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/personalLogoDelete")
    @ResponseBody
    public String personalLogoDelete(int id){
        int count=0;
        count=personalLogoService.personalLogoDelete(id);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
}
