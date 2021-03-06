package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Entity.Customer;
import com.emergencyguide.Entity.Post;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Community.CustomerService;
import com.emergencyguide.Utils.CheckIsNull;
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
        JSONObject jsonObject = JSONObject.parseObject(openid);

        String openId = jsonObject.getString("openid");

        Customer data = customerService.selectByOpenId(openId);
        if (data != null) {
            result.setModel(data);
            result.setMsg("请求成功");
        } else {
            result.setMsg("请求失败");
        }

        return result.toString();

    }

    @ApiOperation(value = "客户登录，包含第一次登录的初始化操作）")
    @ApiImplicitParam(name = "jsonStr", value = "{\"openId\":\"openId\",\"nickName\":\"昵称\",\"gender\":\"性别\",\"photo\":\"头像\"}", required = true, dataType = "string")
    @PostMapping("/customerLogin")
    public String customerLogin(@RequestBody String jsonStr) {

        Result result = new Result<>();
        CheckIsNull checkIsNull = new CheckIsNull();

        try {
            Customer customer = new Customer();
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);

            String openId = jsonObject.getString("openId");
            Integer gender = jsonObject.getInteger("gender"); //性别 0：未知、1：男、2：女
            customer.setOpenid(openId);
            customer.setNickname(jsonObject.getString("nickName"));
            customer.setGender(gender == 0 ? "未知" : gender == 1 ? "男" : "女");
            customer.setAvatar(jsonObject.getString("photo"));

            //初始化客户
            int count = customerService.selectCountByOpenid(openId);

            if (count == 1) {

                result.setModel(customerService.selectByOpenId(openId));
                result.setMsg("操作成功");
                return result.toString();

            } else {

                int n = customerService.insert(customer);
                if (n > 0) {
                    result.setModel(customerService.selectByOpenId(openId));
                    result.setMsg("操作成功");
                } else {
                    return result.failed("操作失败").toString();
                }

            }

        } catch (Exception e) {
            System.out.println("完了，全完了" + e);
        }
        return result.toString();
   }

    @ApiOperation(value = "更新客户信息,json字符串")
    @ApiImplicitParam(name = "jsonStr", value = "{'openId':'openId', 'nickName':'昵称', 'realname':'真实姓名', 'mobilephone':'手机号码', 'profession':'职业', 'gender':'性别', 'photo':'头像', 'email':'电子邮箱' }", required = true)
    @PostMapping("/customerUpdate")
    public String updateCustomerInfo(@RequestBody String jsonStr) {

        Result result = new Result<>();
        try {
            Customer customer = new Customer();
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);

            String openId = jsonObject.getString("openId");
            Integer gender = jsonObject.getInteger("gender"); //性别 0：未知、1：男、2：女

            customer.setOpenid(openId);
            customer.setNickname(jsonObject.getString("nickName"));
            customer.setRealname(jsonObject.getString("realname"));
            customer.setMobilephone(jsonObject.getString("mobilephone"));
            customer.setProfession(jsonObject.getInteger("profession"));
            customer.setGender(gender == 0 ? "未知" : gender == 1 ? "男" : "女");
            customer.setAvatar(jsonObject.getString("photo"));
            customer.setEmail(jsonObject.getString("email"));

            if (customerService.updateByOpenId(customer) > 0) {
                result.setCount(1);
                result.setMsg("更新成功");
            } else {
                result.setCount(0);
                result.setMsg("更新失败");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return result.toString();
    }

}