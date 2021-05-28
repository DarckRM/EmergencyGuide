package com.emergencyguide.Controller.Logo;

import com.emergencyguide.Entity.QuestionAnswer;
import com.emergencyguide.Entity.Rank;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.EmergencyInformation.QuestionAnswerService;
import com.emergencyguide.Service.Logo.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("rank")
public class RankController {
    @Autowired

    private RankService rankService;


    @RequestMapping("/findRankHtml")
    public ModelAndView findRankHtml(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("Logo/rank");
        return modelAndView;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(int page, int limit,  String searchParams)
    {

        Result<Rank> result=new Result<>();
        List<Rank> data=rankService.selectAllList(page,limit,searchParams);
        result.setData(data);
        result.setCount(rankService.selectListCount(searchParams));
        return result.toString();
    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestBody Rank rank){
        int count=0;
        count=rankService.updateById(rank);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/findAddRankHtml")
    public ModelAndView findAddDisasterHtml() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Logo/rankAdd");
        return modelAndView;
    }
    @RequestMapping("/rankAdd")
    @ResponseBody
    public String disasterAdd(@RequestBody Rank rank){
        int count=0;
        count=rankService.rankAdd(rank);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/rankDelete")
    @ResponseBody
    public String disasterDelete(int id){
        int count=0;
        count=rankService.rankDelete(id);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
}
