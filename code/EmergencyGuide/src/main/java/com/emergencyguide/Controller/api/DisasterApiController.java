package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Entity.Disaster;
import com.emergencyguide.Entity.DisasterType;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.EmergencyInformation.DisasterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("api/disaster")
@Api(value = "灾难接口Controller", tags = { "灾难API接口" })
public class DisasterApiController {
    @Autowired
    private DisasterService disasterService;

    @RequestMapping("/findDisaster")
    @ResponseBody
    @ApiOperation(value="分页")
    @ApiImplicitParam(name = "jsonStr", value = "{\"page\":1,\"limit\":5,\"searchParams\":{\"disasterType\":\"灾难类型\" }}",
            paramType = "body", required = true, dataType =  "string")
    public  String findDisaster( @RequestBody  String jsonStr) {
        try {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            Result<Disaster> result = new Result<>();
            List<Disaster> data = disasterService.selectAllList(jsonObject.getInteger("page"), jsonObject.getInteger("limit"), jsonObject.getString("searchParams"));
            result.setData(data);
            result.setCount(disasterService.selectListCount(jsonObject.getString("searchParams")));
            return result.toString();
        } catch (Exception e) {
            return e.getMessage();
        }

    }
}
