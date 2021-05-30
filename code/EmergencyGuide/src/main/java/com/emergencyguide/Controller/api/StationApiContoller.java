package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Entity.Post;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Entity.Station;
import com.emergencyguide.Service.Community.PostService;
import com.emergencyguide.Service.Community.StationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author DarckLH
 * @date 2021/5/30 13:45
 * @Description
 */
@RestController
@RequestMapping("api/station")
@Api(value = "补给站接口", tags = "补给站API")
public class StationApiContoller {

    @Autowired
    private StationService stationService;

    @ApiOperation(value="分页查询地点，比如说前端最多显示多少个点，limit就传好多")
    @PostMapping("/findstation")
    @ApiImplicitParam(name = "jsonStr", value = "{'page':'显示页数传1:int','limit':'最大显示点数:int','searchParams':\\{'openid':'查询某人设立的站点:String','type':'查询某一类站点:int(1-4)'}:均为可选参数'}", required = true, dataType = "string")
    public String myPost(@RequestBody String jsonStr) {

        Result<Station> result = new Result<>();
        try
        {
            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            List<Station> datas = stationService.selectList(jsonObject.getInteger("page"), jsonObject.getInteger("limit"), jsonObject.getString("searchParams"));
            result.setCount(stationService.selectListCount(jsonObject.getString("searchParams")));
            result.setData(datas);
            result.setMsg("请求成功");

        } catch (Exception e)
        {
            result.setMsg("请求失败" + e.getMessage());
            return result.toString();
        }
        return result.toString();
    }

    @ApiOperation(value="添加一个补给站")
    @PostMapping("/addstation")
    @ApiImplicitParam(name = "jsonStr", value = "{'longitude':'经度:float(必要)','latitude':'纬度:float(必要)','openid':'添加者的openid:String(必要)','type':'补给站类型:int(0-4,0是默认类型,必要)','info':'说明信息:String(推荐必要)'}", required = true, dataType = "string")
    public String addStation(@RequestBody String jsonStr) {

        Result result = new Result();

        Station station = new Station();

        try {

            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            station.setLongitude(jsonObject.getDouble("longitude"));
            station.setLatitude(jsonObject.getDouble("latitude"));
            station.setOpenid(jsonObject.getString("openid"));
            station.setType(jsonObject.getInteger("type"));
            station.setInfo(jsonObject.getString("info"));

            int var = stationService.insert(station);

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

    @ApiOperation(value="修改补给站信息")
    @PostMapping("/editstation")
    @ApiImplicitParam(name = "jsonStr", value = "{'id':'补给站ID:int(必要)','longitude':'经度:float(必要)','latitude':'纬度:float(必要)','openid':'添加者的openid:String(必要)','type':'补给站类型:int(0-4,0是默认类型,必要)','info':'说明信息:String(推荐必要)'}", required = true, dataType = "string")
    public String editStation(@RequestBody String jsonStr) {

        Result result = new Result();

        Station station = new Station();

        try {

            JSONObject jsonObject = JSONObject.parseObject(jsonStr);
            station.setId(jsonObject.getInteger("id"));
            station.setLongitude(jsonObject.getDouble("longitude"));
            station.setLatitude(jsonObject.getDouble("latitude"));
            station.setOpenid(jsonObject.getString("openid"));
            station.setType(jsonObject.getInteger("type"));
            station.setInfo(jsonObject.getString("info"));

            int var = stationService.updateById(station);

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

}
