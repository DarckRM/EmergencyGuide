package com.emergencyguide.Controller.api;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.LinkedList;
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
            NumberFormat percentFormat =NumberFormat.getPercentInstance();
            List<Product> data1=new LinkedList<>();
            for(Product product1:data){

                Date dateNow=new Date();
                long betweenDay = DateUtil.between(product1.getProductExpirationTime(), product1.getProductCreateTime(), DateUnit.DAY);
                long betweenDa = DateUtil.between(product1.getProductExpirationTime(), dateNow, DateUnit.DAY);
                DecimalFormat df = new DecimalFormat("0.00");
                String num = df.format((float)betweenDa/betweenDay);//返回的是String类型
                String kl= percentFormat.format(Float.parseFloat(num));
                product1.setProductPercent(kl);
                data1.add(product1);

            }
            result.setData(data1);
            return result.toString();
        }catch (Exception e)
        {
            e.getMessage();
            return e.toString();
        }

    }
    @PostMapping("/getCustomerProductRadar")
    @ApiOperation(value="雷达图数据查询 ")
    @ApiImplicitParam(name = "jsonStr", value = "{\"customerOpenId\":\"用户OpenId:1\"}", paramType = "body",required = true, dataType =  "string")
    public  String  getCustomerProductRadar(@RequestBody String jsonStr){
        try {
            Result result = new Result();
            Product product=new Product();
            JSONObject jsonObject= JSON.parseObject(jsonStr);
            product.setCustomerOpenId(jsonObject.getString("customerOpenId"));
            List<Product> data=productService.selectShelfLife(product);
            List<Product> data1=productService.selectRichness(product);
            List<Product> data2=productService.getCustomerProduct(product);
            List<ProductType> resultData=new LinkedList<>();
            List<ProductType> resultData1=new LinkedList<>();
            List<ProductType> resultData2=new LinkedList<>();
            //丰富度
            for(Product product1:data1){
                ProductType productType=new ProductType();
                System.out.println(product1.getProductTypeName());
                System.out.println(product1.getProductNumber());
                productType.setId(product1.getId());
                productType.setProductTypeName(product1.getProductTypeName());
                if (product1.getProductNumber()<=5){
                    productType.setProductRichnessValue(product1.getProductNumber()*100);
                }else {
                    productType.setProductRichnessValue(500);
                }
                System.out.println(productType);
                resultData.add(productType);
            }
            //保质期

            for(Product product3:data){
                ProductType productType=new ProductType();
                double n=0;
                double t=0;
                double k=0;
                for (Product product2:data2){

                    if (product3.getId()==product2.getProductTypeId()){
                        Date dateNow=new Date();
                        double betweenDay = DateUtil.between(product2.getProductExpirationTime(), product2.getProductCreateTime(), DateUnit.DAY);
                        double betweenDa = DateUtil.between(product2.getProductExpirationTime(), dateNow, DateUnit.DAY);
                        n=(betweenDa/betweenDay);
                        t+=n;
                    }
                }

                productType.setId(product3.getId());
                productType.setProductTypeName(product3.getProductTypeName());
                double kkk=(t/(double)product3.getProductNumber());
                productType.setProductShelfLifeValue((int)(kkk*500));
                resultData1.add(productType);
//                    t=Math.round(n * 100) / 100;
            }
            for(ProductType productType1:resultData)
            {
                
//                System.out.println(productType1.getProductTypeName());
//                System.out.println(productType1.getProductRichnessValue());
                for (ProductType productType2:resultData1)
                {
//                    System.out.println(productType2.getProductTypeName());
//                    System.out.println(productType2.getProductShelfLifeValue());
                    if(productType1.getId()==productType2.getId()){
                        productType1.setProductShelfLifeValue(productType2.getProductShelfLifeValue());
                    }
                }
                resultData2.add(productType1);
            }
            result.setData(resultData2);
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
    @PostMapping("/productSelectById")
    @ApiOperation(value="物资精确查询by物资ID")
    @ApiImplicitParam(name = "id", value = "id", required = true,dataType = "int")
    public String productSelectById(@RequestBody Integer id)
    {
        System.out.println(id);
        Result result = new Result();
        Product product =productService.selectById(id);
        result.setModel(product);
        return result.toString();
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
