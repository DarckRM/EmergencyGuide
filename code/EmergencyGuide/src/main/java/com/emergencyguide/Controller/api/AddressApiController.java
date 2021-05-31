package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Entity.Address;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Entity.Station;
import com.emergencyguide.Service.Community.AddressService;
import com.emergencyguide.Service.Community.StationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/30 17:36
 * @Description
 */
@RestController
@RequestMapping("api/station")
@Api(value = "客户地址接口", tags = "客户地址API")
public class AddressApiController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value="分页查询客户地址")
    @PostMapping("/findaddress")
    @ApiImplicitParam(name = "jsonStr", value = "{'page':'显示页数传:int','limit':'最大显示数:int','searchParams':\\{'openid':'查询某人的所有地址:String','id':'根据id查询，当修改某条地址时也许会用到:int'}:'openid, id均为可选参数'", required = true, dataType = "string")
    public String findAddress(@RequestBody String jsonStr) {

        Result<Address>result = new Result<>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            List<Address> datas = addressService.selectList(jsonObject.getInteger("page"), jsonObject.getInteger("limit"), jsonObject.getString("searchParams"));
            result.setCount(addressService.selectListCount(jsonObject.getString("searchParams")));
            result.setData(datas);
            result.setMsg("请求成功");

        } catch (Exception e)
        {
            result.setMsg("请求失败" + e.getMessage());
            return result.toString();
        }
        return result.toString();
    }

    @ApiOperation(value="添加一个地址")
    @PostMapping("/addaddress")
    @ApiImplicitParam(name = "jsonStr", value = "{'openid':'用户openid:String', 'recipient':'收件人:String', 'mobilephone':'收件人电话:String', 'address':'地址信息:String'}不用传是否默认，系统添加地址默认都为否，在修改默认地址接口提交变更默认地址请求", required = true, dataType = "string")
    public String addAddress(@RequestBody String jsonStr) {

        Result result = new Result();

        Address address = new Address();

        try {

            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            address.setOpenid(jsonObject.getString("openid"));
            address.setRecipient(jsonObject.getString("recipient"));
            address.setMobilephone(jsonObject.getString("mobilephone"));
            address.setAddress(jsonObject.getString("address"));
            //默认添加地址都是不作为默认地址
            address.setIsdefault("否");

            //每人最多设置四个地址，先检查
            String openid = jsonObject.getString("openid");
            int count =  addressService.selectListCount("{'openid':'"+ openid+"'}");
            if (count >= 4) {
                result.setMsg("您已拥有4条地址!");
                return result.toString();
            }

            int var = addressService.insert(address);

            if (var > 0) {
                result.setMsg("添加成功");
            } else {
                result.setMsg("添加失败");
            }

        } catch (Exception e) {

            result.setMsg("后台处理错误" + e.getMessage());

        }

        return result.toString();
    }

    @ApiOperation(value="修改一个地址")
    @PostMapping("/editaddress")
    @ApiImplicitParam(name = "jsonStr", value = "{'id':'地址id:int', 'recipient':'收件人:String', 'mobilephone':'收件人电话:String', 'address':'地址信息:String', 'isdefault':'是否默认:String(是/否)'}", required = true, dataType = "string")
    public String editAddress(@RequestBody String jsonStr) {

        Result result = new Result();

        Address address = new Address();

        try {

            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            address.setId(jsonObject.getInteger("id"));
            address.setRecipient(jsonObject.getString("recipient"));
            address.setMobilephone(jsonObject.getString("mobilephone"));
            address.setAddress(jsonObject.getString("address"));
            address.setIsdefault(jsonObject.getString("isdefault"));

            int var = addressService.updateById(address);

            if (var > 0) {
                result.setMsg("修改成功");
            } else {
                result.setMsg("修改失败");
            }

        } catch (Exception e) {

            result.setMsg("后台处理错误" + e.getMessage());

        }

        return result.toString();
    }

    @ApiOperation(value="修改一个默认地址")
    @PostMapping("/changedefault")
    @ApiImplicitParam(name = "jsonStr", value = "{'id':'地址id:int'}", required = true, dataType = "string")
    public String changeDefault(@RequestBody String jsonStr) {

        Result result = new Result();

        try {

            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            int id = jsonObject.getInteger("id");
            int var = addressService.changeDefaultAddress(id);

            if (var > 0) {
                result.setMsg("修改成功");
            } else {
                result.setMsg("修改失败");
            }

        } catch (Exception e) {

            result.setMsg("后台处理错误" + e.getMessage());

        }

        return result.toString();
    }

    @ApiOperation(value="删除一个地址")
    @PostMapping("/deladdress")
    @ApiImplicitParam(name = "jsonStr", value = "{'id':'地址id:int'}", required = true, dataType = "string")
    public String delAddress(@RequestBody String jsonStr) {

        Result result = new Result();

        try {

            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            int id = jsonObject.getInteger("id");
            int var = addressService.deleteById(id);

            if (var > 0) {
                result.setMsg("删除成功");
            } else {
                result.setMsg("删除失败");
            }

        } catch (Exception e) {

            result.setMsg("后台处理错误" + e.getMessage());

        }

        return result.toString();
    }


}
