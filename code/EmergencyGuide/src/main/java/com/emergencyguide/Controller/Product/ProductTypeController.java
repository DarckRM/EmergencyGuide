package com.emergencyguide.Controller.Product;

import com.emergencyguide.Entity.PersonalLogo;
import com.emergencyguide.Entity.ProductType;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Logo.PersonalLogoService;
import com.emergencyguide.Service.Product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("productType")
public class ProductTypeController {
    @Autowired

    private ProductTypeService productTypeService;


    @RequestMapping("/findProductTypeHtml")
    public ModelAndView findProductTypeHtml(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.setViewName("Product/productType");
        return modelAndView;
    }

    @RequestMapping("/findAll")
    @ResponseBody
    public String findAll(int page, int limit,  String searchParams)
    {

        Result<ProductType> result=new Result<>();
        List<ProductType> data=productTypeService.selectAllList(page,limit,searchParams);
        result.setData(data);
        result.setCount(productTypeService.selectListCount(searchParams));
        return result.toString();
    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestBody ProductType productType){
        int count=0;
        count=productTypeService.updateById(productType);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/findAddProductTypeHtml")
    public ModelAndView findAddProductTypeHtml() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("Product/productTypeAdd");
        return modelAndView;
    }
    @RequestMapping("/productTypeAdd")
    @ResponseBody
    public String productTypeAdd(@RequestBody ProductType productType){
        int count=0;
        count=productTypeService.productTypeAdd(productType);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
    @RequestMapping("/productTypeDelete")
    @ResponseBody
    public String productTypeDelete(int id){
        int count=0;
        count=productTypeService.productTypeDelete(id);
        if (count > 0) {
            return new Result<>().success("操作成功").toString();
        } else {
            return new Result<>().failed("操作失败").toString();
        }
    }
}
