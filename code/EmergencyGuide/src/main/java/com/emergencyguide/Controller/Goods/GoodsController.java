package com.emergencyguide.Controller.Goods;

import com.emergencyguide.Entity.Goods;
import com.emergencyguide.Entity.ProductType;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Goods.GoodsService;
import com.emergencyguide.Service.Product.ProductTypeService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
@RequestMapping("goods")
public class GoodsController {
    @Autowired

    private GoodsService goodsService;


    @RequestMapping("/findGoodsHtml")
    public ModelAndView findGoodsHtml(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("goods/goods");
        return modelAndView;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(int page, int limit,  String searchParams)
    {

        Result<Goods> result=new Result<>();
        List<Goods> data=goodsService.selectAllList(page,limit,searchParams);
        result.setData(data);
        result.setCount(goodsService.selectListCount(searchParams));
        return result.toString();
    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestBody Goods goods){
        int count=0;
        if (goods.getId()!=0){
            count=goodsService.updateById(goods);
        }
       else {
           count=goodsService.goodsAdd(goods);
        }
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/findAddGoodsHtml")
    public ModelAndView findAddGoodsHtml(@Param("id") Integer id) {
        Goods goods =new Goods();
        ModelAndView modelAndView = new ModelAndView();
        if(id==0){
        modelAndView.addObject("goods",goods);
        modelAndView.setViewName("goods/goodsAdd");
        }
        else {
            goods=goodsService.selectById(id);
            modelAndView.addObject("goods",goods);
            modelAndView.setViewName("goods/goodsAdd");
        }
        return modelAndView;
    }
    @RequestMapping("/goodsAdd")
    @ResponseBody
    public String goodsAdd(@RequestBody Goods goods){
        int count=0;

        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/goodDelete")
    @ResponseBody
    public String goodDelete(int id){
        int count=0;
        count=goodsService.goodsDelete(id);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
}
