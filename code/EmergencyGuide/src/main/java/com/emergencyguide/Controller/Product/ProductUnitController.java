package com.emergencyguide.Controller.Product;

import com.emergencyguide.Entity.ProductType;
import com.emergencyguide.Entity.ProductUnit;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Product.ProductTypeService;
import com.emergencyguide.Service.Product.ProductUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("productUnit")
public class ProductUnitController {
    @Autowired

    private ProductUnitService productUnitService;


    @RequestMapping("/findProductUnitHtml")
    public ModelAndView findProductTypeHtml(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("Product/productUnit");
        return modelAndView;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(int page, int limit,  String searchParams)
    {

        Result<ProductUnit> result=new Result<>();
        List<ProductUnit> data=productUnitService.selectAllList(page,limit,searchParams);
        result.setData(data);
        result.setCount(productUnitService.selectListCount(searchParams));
        return result.toString();
    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestBody ProductUnit productUnit){
        int count=0;
        count=productUnitService.updateById(productUnit);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/findAddProductUnitHtml")
    public ModelAndView findAddProductUnitHtml() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Product/productUnitAdd");
        return modelAndView;
    }
    @RequestMapping("/productUnitAdd")
    @ResponseBody
    public String productUnitAdd(@RequestBody ProductUnit productUnit){
        int count=0;
        count=productUnitService.productUnitAdd(productUnit);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/productUnitDelete")
    @ResponseBody
    public String productUnitDelete(int id){
        int count=0;
        count=productUnitService.productUnitDelete(id);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
}
