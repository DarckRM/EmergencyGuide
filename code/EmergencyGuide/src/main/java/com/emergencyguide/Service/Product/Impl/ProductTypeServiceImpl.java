package com.emergencyguide.Service.Product.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.Logo.PersonalLogoDao;
import com.emergencyguide.Dao.Product.ProductTypeDao;
import com.emergencyguide.Entity.PersonalLogo;
import com.emergencyguide.Entity.ProductType;
import com.emergencyguide.Service.Product.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductTypeServiceImpl  implements ProductTypeService {
    @Autowired
    private ProductTypeDao productTypeDao;

    @Override
    public List<ProductType> selectAllList(int page, int limit, String searchParams) {
        String productTypeName = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            productTypeName = json.getString("productTypeName");

        }
        page = (page - 1) * limit;
        Map<String, Object> params = new HashMap<>();
        params.put("productTypeName", productTypeName);
        List<ProductType> list = productTypeDao.selectAllList(page, limit, params);
        return list;
    }

    @Override
    public int selectListCount(String searchParams) {
        String productTypeName = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            productTypeName = json.getString("productTypeName");

        }
        Map<String, Object> params = new HashMap<>();
        params.put("productTypeName", productTypeName);
        return  productTypeDao.selectListCount(params);
    }

    @Override
    public int updateById(ProductType productType) {
        return productTypeDao.updateById(productType);
    }

    @Override
    public int productTypeAdd(ProductType productType) {
        return productTypeDao.productTypeAdd(productType);
    }

    @Override
    public int productTypeDelete(int id) {
        return productTypeDao.productTypeDelete(id);
    }

    @Override
    public List<ProductType> selectAllList() {
        return productTypeDao.selectAllList();
    }
}
