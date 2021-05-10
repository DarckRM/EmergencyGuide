package com.emergencyguide.Controller.EmergencyInformation;

import com.emergencyguide.Entity.Disaster;
import com.emergencyguide.Entity.DisasterType;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.EmergencyInformation.DisasterService;
import com.emergencyguide.Service.EmergencyInformation.DisasterTypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@RequestMapping("disasterType")
@Controller
public class DisasterTypeController {

    @Autowired
    private DisasterTypeService disasterTypeService;

    @RequestMapping("/findDisasterTypeHtml")
    public ModelAndView findMainHtml(@Param("id") Integer id){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("disasterNumber",id);
        modelAndView.setViewName("EmergencyInformation/disasterType");
        return modelAndView;
    }
    @RequestMapping("/findDisasterType")
    @ResponseBody
    public  String findDisaster(@Param("disasterNumber") Integer disasterNumber){
        Result<DisasterType> result=new Result<>();
        List<DisasterType> data=disasterTypeService.selectAllList(disasterNumber);
        result.setData(data);
        return result.toString();
    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestBody DisasterType disasterType){
        System.out.println(disasterType);
        int count=0;
        count=disasterTypeService.updateById(disasterType);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/findDisasterTypeAddHtml")
    public ModelAndView findDisasterTypeAddHtml(@Param("disasterNumber") Integer disasterNumber) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("disasterNumber",disasterNumber);
        modelAndView.setViewName("EmergencyInformation/disasterTypeAdd");
        return modelAndView;
    }
    @RequestMapping("/disasterTypeAdd")
    @ResponseBody
    public String disasterTypeAdd(@RequestBody DisasterType disasterType){
        System.out.println(disasterType.getDisasterNumber());
        int count=0;
        count=disasterTypeService.disasterTypeAdd(disasterType);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/disasterTypeDelete")
    @ResponseBody
    public String disasterTypeDelete(int id){
        int count=0;
        count=disasterTypeService.disasterTypeDelete(id);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
}
