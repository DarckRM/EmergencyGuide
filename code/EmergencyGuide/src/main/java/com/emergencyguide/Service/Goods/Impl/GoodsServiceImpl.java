package com.emergencyguide.Service.Goods.Impl;

import com.alibaba.fastjson.JSONObject;
import com.emergencyguide.Dao.Goods.GoodsDao;
import com.emergencyguide.Entity.Goods;
import com.emergencyguide.Entity.Order;
import com.emergencyguide.Entity.Product;
import com.emergencyguide.Entity.ProductType;
import com.emergencyguide.Service.Goods.GoodsService;
import com.emergencyguide.Utils.EasyGeneraterParams;
import com.emergencyguide.Utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private EasyGeneraterParams easyGeneraterParams;
    // 缓存集合Key值
    private String REDISLISTKEY = "PRODUCTLIST_";
    // 缓存单数据Key值
    private String REDISINFOKEY = "PRODUCT_";
    // 存放查询条件便于操作缓存
    private String LISTNAME;
    @Override
    public List<Goods> selectAllList(int page, int limit, String searchParams) {
        String goodsName = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            goodsName = json.getString("goodsName");

        }
        page = (page - 1) * limit;
        Map<String, Object> params = new HashMap<>();
        params.put("goodsName", goodsName);
        List<Goods> list = goodsDao.selectAllList(page, limit, params);
        return list;
    }

    @Override
    public int selectListCount(String searchParams) {
        String goodsName = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            goodsName = json.getString("goodsName");

        }
        Map<String, Object> params = new HashMap<>();
        params.put("goodsName", goodsName);
        return  goodsDao.selectListCount(params);
    }

    @Override
    public int updateById(Goods goods) {
        return goodsDao.updateById(goods);
    }

    @Override
    public int goodsAdd(Goods goods) {
        return goodsDao.goodsAdd(goods);
    }

    @Override
    public int goodsDelete(int id) {
        return goodsDao.goodsDelete(id);
    }

    @Override
    public List<ProductType> getProductType() {
        return null;
    }

    @Override
    public Goods selectById(int id) {
        return goodsDao.selectById(id);
    }

    @Override
    public int newOrder(Order order) {
        return goodsDao.newOrder(order);
    }

    @Override
    public int updateOrder(Order order) {
        return goodsDao.updateOrder(order);
    }

    @Override
    public List<Order> findCustomerOrder(Order order) {
        return goodsDao.findCustomerOrder(order);
    }

    @Override
    public List<Order> selectOrderAllList(int page, int limit, String searchParams) {
        String goodsName = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            goodsName = json.getString("goodsName");

        }
        page = (page - 1) * limit;
        Map<String, Object> params = new HashMap<>();
        params.put("goodsName", goodsName);
        List<Order> list = goodsDao.selectOrderAllList(page, limit, params);
        return list;
    }

    @Override
    public int selectOrderListCount(String searchParams) {
        String goodsName = "";

        if (searchParams != null) {
            JSONObject json = JSONObject.parseObject(searchParams);
            goodsName = json.getString("goodsName");

        }
        Map<String, Object> params = new HashMap<>();
        params.put("goodsName", goodsName);
        return  goodsDao.selectOrderListCount(params);
    }
}
