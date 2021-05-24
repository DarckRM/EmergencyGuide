package com.emergencyguide.Controller.EmergencyInformation;

import com.emergencyguide.Entity.Disaster;
import com.emergencyguide.Entity.DisasterType;
import com.emergencyguide.Entity.QuestionAnswer;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.EmergencyInformation.QuestionAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("questionAnswer")
public class QuestionAnswerController {

    @Autowired

    private QuestionAnswerService questionAnswerService;


    @RequestMapping("/findQuestionAnswerHtml")
    public ModelAndView findMainHtml(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("EmergencyInformation/questionAnswer");
        return modelAndView;
    }

    @RequestMapping("findAll")
    @ResponseBody
    public String findAll(int page, int limit,  String searchParams)
    {

        Result<QuestionAnswer> result=new Result<>();
        List<QuestionAnswer> data=questionAnswerService.selectAllList(page,limit,searchParams);
        result.setData(data);
        result.setCount(questionAnswerService.selectListCount(searchParams));
        return result.toString();
    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestBody QuestionAnswer questionAnswer){
        int count=0;
        count=questionAnswerService.updateById(questionAnswer);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/findAddQuestionAnswerHtml")
    public ModelAndView findAddDisasterHtml() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("EmergencyInformation/questionAnswerAdd");
        return modelAndView;
    }
    @RequestMapping("/questionAnswerAdd")
    @ResponseBody
    public String disasterAdd(@RequestBody QuestionAnswer questionAnswer){
        int count=0;
        count=questionAnswerService.questionAnswerAdd(questionAnswer);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/questionAnswerDelete")
    @ResponseBody
    public String disasterDelete(int id){
        int count=0;
        count=questionAnswerService.questionAnswerDelete(id);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
}
