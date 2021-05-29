package com.emergencyguide.Service.Goods;

import com.emergencyguide.Entity.Goods;
import com.emergencyguide.Entity.Order;
import com.emergencyguide.Entity.Product;
import com.emergencyguide.Entity.ProductType;
import com.sun.org.apache.xpath.internal.operations.Or;

import java.util.List;

public interface GoodsService {
    public List<Goods> selectAllList(int page, int limit, String searchParams);
    public int selectListCount( String searchParams);
    public int updateById(Goods goods);
    public  int goodsAdd(Goods goods);
    public  int goodsDelete(int id);
    public List<ProductType> getProductType();
    public  Goods selectById(int id);

    public  int newOrder(Order order);


    public int updateOrder(Order order);
    List<Order> findCustomerOrder(Order order);
    public List<Order> selectOrderAllList(int page, int limit, String searchParams);
    public int selectOrderListCount( String searchParams);
}
