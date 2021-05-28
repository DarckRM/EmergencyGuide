package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Entity.Goods;
import com.emergencyguide.Entity.Order;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Goods.GoodsService;
import com.google.gson.JsonObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("/api/goods")
@Api(value="商城Controller",tags = {"商城API接口"})
public class GoodsApiController {
    @Autowired
    private GoodsService goodsService;


    @PostMapping("/findGoods")
    @ApiOperation(value = "查找全部商品")
    @ApiImplicitParam(name = "jsonStr", value = "{\"page\":1,\"limit\":5,\"searchParams\":{\"goodsName\":\"商品名\" }}",
            paramType = "body", required = true, dataType =  "string")
    public String findGoods(@RequestBody String jsonStr){
        JSONObject jsonObject= JSON.parseObject(jsonStr);
        Result<Goods> result=new Result<>();
        List<Goods> data=goodsService.selectAllList(jsonObject.getInteger("page"),jsonObject.getInteger("limit"),jsonObject.getString("searchParams"));
        result.setData(data);
        result.setCount(goodsService.selectListCount(jsonObject.getString("searchParams")));
        return result.toString();
    }
    @PostMapping("/findGoodsById")
    @ApiOperation(value = "根据商品id查找商品")
    @ApiImplicitParam(name = "id", value ="id",
            required = true, dataType =  "int")
    public Object findGoodsById(@RequestBody Integer id)
    {
        Result<Goods> result=new Result<>();
        Goods data=goodsService.selectById(id);
        result.setModel(data);
        return  result.toString();
    }

    @PostMapping("/orderCreate")
    @ApiOperation(value = "订单创建")
    @ApiImplicitParam(name = "jsonStr", value = "{\"orderCustomerOpenId\":\"用户openId\",\"orderGoodsId\":\"购买商品的id\",\"orderGoodsNumber\":\"商品数量\",\"orderStatus\":\"支付状态\",\"orderAddressId\":\"地址id\",\"remark\":\"订单备注\"}",
            paramType = "body", required = true, dataType =  "string")
    public String orderDeal(@RequestBody String jsonStr){
        JSONObject jsonObject=JSON.parseObject(jsonStr);
        Result result=new Result();
        Order order=new Order();
        order.setOrderStatus(jsonObject.getString("orderStatus"));
        order.setOrderGoodsId(jsonObject.getInteger("orderGoodsId"));
        order.setOrderCustomerOpenId(jsonObject.getString("orderCustomerOpenId"));
        order.setOrderCreateTime( new Timestamp(System.currentTimeMillis()));
        order.setOrderGoodsNumber(jsonObject.getInteger("orderGoodsNumber"));
        order.setOrderAddressId(jsonObject.getInteger("orderAddressId"));
        Goods goods=goodsService.selectById(jsonObject.getInteger("orderGoodsId"));
        order.setOrderWholePrice(goods.getGoodsCurrentPrice()*order.getOrderGoodsNumber());
        order.setRemark(jsonObject.getString("remark"));
        goodsService.newOrder(order);
        result.setModel(order);
        return  result.toString();
    }
    @PostMapping("/orderCancel")
    @ApiOperation(value = "取消订单")
    @ApiImplicitParam(name = "jsonStr", value = "{\"id\":\"订单id\",\"orderStatus\":\"支付状态\"}",
            paramType = "body", required = true, dataType =  "string")
    public String orderCancel(@RequestBody String jsonStr){
        JSONObject jsonObject=JSON.parseObject(jsonStr);
        Result result=new Result();
        Order order=new Order();
        order.setOrderStatus(jsonObject.getString("orderStatus"));
        order.setId(jsonObject.getInteger("id"));
        System.out.println(order.getId());
        order.setOrderCancelTime( new Timestamp(System.currentTimeMillis()));
        System.out.println(order.getOrderCancelTime());
        int n=goodsService.updateOrder(order);
            if (n > 0){
                result.setMsg("订单取消成功");
                return result.toString();
            }
            else {
                return result.failed("订单取消失败").toString();
            }

    }
    @PostMapping("/orderSuccess")
    @ApiOperation(value = "支付订单")
    @ApiImplicitParam(name = "jsonStr", value = "{\"id\":\"订单id\",\"orderStatus\":\"支付状态\"}",
            paramType = "body", required = true, dataType =  "string")
    public String orderSuccess(@RequestBody String jsonStr) {
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Result result = new Result();
        Order order = new Order();
        order.setOrderStatus(jsonObject.getString("orderStatus"));
        order.setId(jsonObject.getInteger("id"));
        order.setOrderFinishTime(new Timestamp(System.currentTimeMillis()));
            int n = goodsService.updateOrder(order);
            if (n > 0) {
                result.setMsg("订单支付成功");
                return result.toString();
            } else
                return result.failed("订单支付失败").toString();


    }

    @PostMapping("/findCustomerOrder")
    @ApiOperation(value = "查询用户订单")
    @ApiImplicitParam(name = "jsonStr", value = "{\"orderCustomerOpenId\":\"用户openId\"}",
            paramType = "body", required = true, dataType =  "string")
    public String findCustomerOrder(@RequestBody String jsonStr) {
        JSONObject jsonObject = JSON.parseObject(jsonStr);
        Result result = new Result();
        Order order = new Order();
        order.setOrderCustomerOpenId(jsonObject.getString("orderCustomerOpenId"));
        List<Order> data = goodsService.findCustomerOrder(order);
        result.setData(data);


        return  result.toString();
    }
}
