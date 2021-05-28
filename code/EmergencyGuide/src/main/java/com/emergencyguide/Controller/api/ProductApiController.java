package com.emergencyguide.Controller.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Entity.Product;
import com.emergencyguide.Entity.ProductType;
import com.emergencyguide.Entity.ProductUnit;
import com.emergencyguide.Entity.Result;
import com.emergencyguide.Service.Product.ProductService;
import com.emergencyguide.Service.Product.ProductTypeService;
import com.emergencyguide.Service.Product.ProductUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/product")
@Api(value = "物资Controller", tags = { "物资API接口" })
public class ProductApiController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductTypeService productTypeService;
    @Autowired
    private ProductUnitService productUnitService;

    @PostMapping("/newProduct")
    @ApiOperation(value="物资录入")
    @ApiImplicitParam(name = "jsonStr", value = "{\"customerOpenId\":\"用户OpenId\",\"productTypeId\":\"物资类别Id\",\"productName\":\"物资名\",\"productNumber\":\"物资数量\"," +
            "\"productUnitId\":\"物资单位Id\",\"productInsertTime\":\"物资存入时间\",\"productCreateTime\":\"物资生产时间\",\"productExpirationTime\":\"物资过期时间\"," +
            "\"productPhoto\":\"物资图片\",\"remark\":\"备注\"}",
            paramType = "body", required = true, dataType =  "string")
    public String newProduct(@RequestBody String jsonStr){
        Result result = new Result();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Product product=new Product();
        product.setCustomerOpenId(jsonObject.getString("customerOpenId"));
        product.setProductTypeId(jsonObject.getInteger("productTypeId"));
        product.setProductUnitId(jsonObject.getInteger("productUnitId"));
        product.setProductCreateTime(jsonObject.getDate("productCreateTime"));
        product.setProductInsertTime(jsonObject.getDate("productInsertTime"));
        product.setProductExpirationTime(jsonObject.getDate("productExpirationTime"));
        product.setProductName(jsonObject.getString("productName"));
        product.setRemark(jsonObject.getString("remark"));
        product.setProductNumber(jsonObject.getInteger("productNumber"));
        product.setProductPhoto(jsonObject.getString("productPhoto"));
        int n= productService.newProduct(product);
        System.out.println(n);
        if (n > 0){
            result.setMsg("操作成功");
            return result.toString();
        }
        else
            return result.failed("操作失败").toString();
    }

    @PostMapping("/getCustomerProduct")
    @ApiOperation(value="用户物资查询 ")
    @ApiImplicitParam(name = "jsonStr", value = "{\"customerOpenId\":\"用户OpenId:1\"}", paramType = "body",required = true, dataType =  "string")
    public  String  getCustomerProduct(@RequestBody String jsonStr){
        try {
            Result result = new Result();
            Product product=new Product();
            JSONObject jsonObject= JSON.parseObject(jsonStr);
            product.setCustomerOpenId(jsonObject.getString("customerOpenId"));
            List<Product> data=productService.getCustomerProduct(product);
            result.setData(data);
            return result.toString();
        }catch (Exception e)
        {
            e.getMessage();
            return e.toString();
        }

    }

    @PostMapping("/updateCustomerProduct")
    @ApiOperation(value="用户物资修改")
    @ApiImplicitParam(name = "jsonStr", value = "{\"id\":\"用户物资\",\\\"productTypeId\\\":\\\"物资类别Id\\\",\\\"productName\\\":\\\"物资名\\\",\\\"productNumber\\\":\\\"物资数量\\\",\" +\n" +
            "            \"\\\"productUnitId\\\":\\\"物资单位Id\\\",\\\"productInsertTime\\\":\\\"物资存入时间\\\",\\\"productCreateTime\\\":\\\"物资生产时间\\\",\\\"productExpirationTime\\\":\\\"物资过期时间\\\",\" +\n" +
            "            \"\\\"productPhoto\\\":\\\"物资图片\\\",\\\"remark\\\":\\\"备注\\\"}",
            paramType = "body", required = true, dataType =  "string")
    public String updateCustomerProduct(@RequestBody String jsonStr){
        Result result = new Result();
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
        Product product=new Product();
        product.setId(jsonObject.getInteger("id"));
        product.setProductTypeId(jsonObject.getInteger("productTypeId"));
        product.setProductUnitId(jsonObject.getInteger("productUnitId"));
        product.setProductCreateTime(jsonObject.getDate("productCreateTime"));
        product.setProductInsertTime(jsonObject.getDate("productInsertTime"));
        product.setProductExpirationTime(jsonObject.getDate("productExpirationTime"));
        product.setProductName(jsonObject.getString("productName"));
        product.setRemark(jsonObject.getString("remark"));
        product.setProductNumber(jsonObject.getInteger("productNumber"));
        product.setProductPhoto(jsonObject.getString("productPhoto"));
        int n =productService.updateCustomerProduct(product);
        if (n > 0){
            result.setMsg("操作成功");
            return result.toString();
        }
        else
            return result.failed("操作失败").toString();
    }
    @PostMapping("/customerProductDelete")

    @ApiOperation(value="用户物资删除")
    @ApiImplicitParam(name = "id", value = "id", required = true,dataType = "int")
    public String customerProductDelete(@RequestBody Integer id)
    {
        System.out.println(id);
        Result result = new Result();
        int n =productService.customerProductDelete(id);
        if (n > 0){
            result.setMsg("操作成功");
            return result.toString();
        }
        else
            return result.failed("操作失败").toString();
    }
    @GetMapping("/getProductType")
    @ApiOperation(value="用户物资类型查询")
    public  String getProductType()
    {
        Result<ProductType> result=new Result<>();
        List<ProductType> data=productTypeService.getProductType();
        result.setData(data);
        return result.toString();
    }
    @GetMapping("/getProductUnit")
    @ApiOperation(value="用户物资单位查询")
    public  String getProductUnit()
    {
        Result<ProductUnit> result=new Result<>();
        List<ProductUnit> data=productUnitService.getProductUnit();
        result.setData(data);
        return result.toString();
    }
}
