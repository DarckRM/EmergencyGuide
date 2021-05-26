package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Controller.UploadController;
import com.emergencyguide.Entity.Product;
import com.emergencyguide.Entity.ProductType;
import com.emergencyguide.Entity.ProductUnit;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Product.ProductService;
import com.emergencyguide.Service.Product.ProductTypeService;
import com.emergencyguide.Service.Product.ProductUnitService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping("api/product")
public class ProductApiController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductUnitService productUnitService;

    @RequestMapping("/newProduct")
    @ResponseBody
    @ApiOperation(value="分页")
    @ApiImplicitParam(name = "jsonStr", value = "{\"page\":1,\"limit\":5,\"searchParams\":{\"disasterType\":\"灾难类型\" }}",
            paramType = "body", required = true, dataType =  "string")
    public Object newProduct(@RequestBody String jsonStr){
        Result result = new Result();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
//        Product product=new Product();
//        product.setProductTypeId(jsonObject.getInteger("productTypeId"));
//        product.setProductUnitId(jsonObject.getInteger("productUnitId"));
//        product.setProductCreateTime(jsonObject.getTimestamp("productCreateTime"));
//        product.setProductInsertTime(jsonObject.getTimestamp("productInsertTime"));
//        product.setProductExpirationTime(jsonObject.getTimestamp("productExpirationTime"));
//        product.setProductName(jsonObject.getString("productName"));
//        product.setRemark(jsonObject.getString("remark"));
//        product.setProductNumber(jsonObject.getInteger("productNumber"));
//        product.setProductPhoto(jsonObject.getString("productPhoto"));
        Product product=new Product();
        product.setProductTypeId(1);
        product.setProductUnitId(1);
        String kk="2021-05-24 21:17:10";
        Timestamp ts = Timestamp.valueOf(kk);
        product.setProductCreateTime(ts);
        product.setProductInsertTime(ts);
        product.setProductExpirationTime(ts);
        product.setProductName("productName");
        product.setRemark("remark");
        product.setProductNumber(1);
        product.setProductPhoto("productPhoto");
        int n= productService.newProduct(product);
        System.out.println(n);
        if (n > 0){
            result.setModel(product);
            result.setMsg("操作成功");
            return result.toString();
        }
        else
            return result.failed("操作失败").toString();
    }


    @GetMapping("/getProductType")
    @ResponseBody
    public  String getProductType()
    {
        Result<ProductType> result=new Result<>();
        List<ProductType> data=productTypeService.getProductType();
        result.setData(data);
        return result.toString();
    }
    @GetMapping("/getProductUnit")
    @ResponseBody
    public  String getProductUnit()
    {
        Result<ProductUnit> result=new Result<>();
        List<ProductUnit> data=productUnitService.getProductUnit();
        result.setData(data);
        return result.toString();
    }
}
