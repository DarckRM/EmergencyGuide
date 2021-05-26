package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.Post;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Community.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/24 12:53
 * @Description
 */
@RestController
@RequestMapping("api/customer")
@Api(value = "客户信息接口", tags = "客户信息API")
public class CustomerApiController {

    @Autowired
    private CustomerService customerService;

    @ApiOperation(value="查看当前客户的信息")
    @ApiImplicitParam(name = "openid", value = "接收参数openid,String类型", required = true, dataType = "string")
    @PostMapping("/whoami")
    public String findMyself(@RequestBody String openid) {

        Result result = new Result<>();

        Customer data = customerService.selectByOpenId(openid);
        if (data != null) {
            result.setModel(data);
            result.setMsg("请求成功");
        } else {
            result.setMsg("请求失败");
        }

        return result.toString();

    }

    @ApiOperation(value="初始化客户（第一次登录）")
    @ApiImplicitParam(name = "openid", value = "接收参数openid,String类型", required = true, dataType = "string")
    @PostMapping("/iamnew")
    public String firstLogin(@RequestBody String openid) {

        Result result = new Result<>();

        Customer data = customerService.selectByOpenId(openid);
        if (data != null) {
            result.setModel(data);
            result.setMsg("请求成功");
        } else {
            result.setMsg("请求失败");
        }

        return result.toString();

    }

}
