package com.emergencyguide.Controller.EmergencyInformation;

import com.emergencyguide.Entity.Disaster;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.EmergencyInformation.DisasterService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RequestMapping("disaster")
@Controller
public class DisasterController {
    @Autowired
    private DisasterService disasterService;

    @RequestMapping("/findDisasterHtml")
    public ModelAndView findMainHtml(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("emergencyinformation/disaster");
        return modelAndView;
    }
    @RequestMapping("/findDisaster")
    @ResponseBody
    public  String findDisaster(int page, int limit,  String searchParams){
        Result<Disaster> result=new Result<>();
        List<Disaster> data=disasterService.selectAllList(page,limit,searchParams);
        result.setData(data);
        result.setCount(disasterService.selectListCount(searchParams));
        return result.toString();
    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestBody Disaster disaster){
        System.out.println(disaster);
        int count=0;
        count=disasterService.updateById(disaster);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/findAddDisasterHtml")
    public ModelAndView findAddDisasterHtml() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("emergencyinformation/disasterAdd");
        return modelAndView;
    }
    @RequestMapping("/findDisasterEditHtml")
    public ModelAndView findDisasterEditHtml(int id) {
        ModelAndView modelAndView = new ModelAndView();
        Disaster disaster=new Disaster();
        disaster=disasterService.selectById(id);
        modelAndView.addObject("disaster",disaster);
        modelAndView.setViewName("emergencyinformation/disasterEdit");
        return modelAndView;
    }
    @RequestMapping("/disasterAdd")
    @ResponseBody
    public String disasterAdd(@RequestBody Disaster disaster){
        int count=0;
        count=disasterService.disasterAdd(disaster);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/disasterDelete")
    @ResponseBody
    public String disasterDelete(int id){
        int count=0;
        count=disasterService.disasterDelete(id);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }

}
