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

import java.util.Date;
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

    @ApiOperation(value = "查看当前客户的信息")
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

    @ApiOperation(value = "客户登录，包含第一次登录的初始化操作）")
    @ApiImplicitParam(name = "jsonStr", value = "{\"openId\":\"openId\",\"nickname\":\"昵称\",\"gender\":\"性别\",\"photo\":\"头像\"}", required = true, dataType = "string")
    @PostMapping("/customerLogin")
    public String customerLogin(@RequestBody String jsonStr) {

        Result result = new Result<>();

        try {
            Customer customer = new Customer();
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);

            String openId = jsonObject.getString("openId");
            Integer gender = jsonObject.getInteger("gender"); //性别 0：未知、1：男、2：女
            customer.setOpenid(openId);
            customer.setNickname(jsonObject.getString("nickname"));
            customer.setGender(gender == 0 ? "未知" : gender == 1 ? "男" : "女");
            customer.setAvatar(jsonObject.getString("photo"));

            //初始化客户
            Customer customerExist = customerService.selectByOpenId(openId);
            if (customerExist == null) {
                int n = customerService.insert(customer);
                if (n > 0) {
                    customerExist = customerService.selectByOpenId(openId);
                    result.setModel(customerExist);
                    result.setMsg("操作成功");
                } else {
                    return result.failed("操作失败").toString();
                }
            } else {
                //新逻辑，不再每次都更新用户信息
                //customerExist.setNickName(customer.getNickName());
                //customerExist.setGender(customer.getGender());
                //customerExist.setPhoto(customer.getPhoto());
                //customerService.updateById(customerExist);

                result.setModel(customerExist);
                result.setMsg("操作成功");
                return result.toString();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return result.toString();
   }
}