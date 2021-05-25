package com.emergencyguide.Service.Product.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.Product.ProductTypeDao;
import com.emergencyguide.Dao.Product.ProductUnitDao;
import com.emergencyguide.Entity.ProductType;
import com.emergencyguide.Entity.ProductUnit;
import com.emergencyguide.Service.Product.ProductUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductUnitServiceImpl implements ProductUnitService {
    @Autowired
    private ProductUnitDao productUnitDao;

    @Override
    public List<ProductUnit> selectAllList(int page, int limit, String searchParams) {
        String productUnitName = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            productUnitName = json.getString("productUnitName");

        }
        page = (page - 1) * limit;
        Map<String, Object> params = new HashMap<>();
        params.put("productUnitName", productUnitName);
        List<ProductUnit> list = productUnitDao.selectAllList(page, limit, params);
        return list;
    }

    @Override
    public int selectListCount(String searchParams) {
        String productUnitName = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            productUnitName = json.getString("productUnitName");

        }
        Map<String, Object> params = new HashMap<>();
        params.put("productUnitName", productUnitName);
        return  productUnitDao.selectListCount(params);
    }

    @Override
    public int updateById(ProductUnit productUnit) {
        return productUnitDao.updateById(productUnit);
    }

    @Override
    public int productUnitAdd(ProductUnit productUnit) {
        return productUnitDao.productUnitAdd(productUnit);
    }

    @Override
    public int productUnitDelete(int id) {
        return productUnitDao.productUnitDelete(id);
    }

    @Override
    public List<ProductUnit> selectAllList() {
        return productUnitDao.selectAllList();
    }
}
