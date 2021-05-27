package com.emergencyguide.Dao.Goods;

import com.emergencyguide.Entity.Goods;
import com.emergencyguide.Entity.Order;
import com.emergencyguide.Entity.ProductType;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsDao {
    public List<Goods> selectAllList(@Param("page") int page, @Param("limit") int limit,
                                           @Param("params") Map<String, Object> params);

    public int selectListCount( @Param("params") Map<String, Object> params);

    public int updateById(Goods goods);

    public  int goodsAdd(Goods goods);

    @Delete("delete from t_goods where id=#{id}")
    public  int goodsDelete(int id);

    public List<ProductType> getProductType();

    @Select("select * from  t_goods where id=#{id}")
    public Goods selectById(int id);

    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @Insert("insert  into t_order (orderCustomerOpenId,orderGoodsId,orderGoodsNumber,orderStatus,orderCreateTime,orderWholePrice) values (#{orderCustomerOpenId}, #{orderGoodsId},#{orderGoodsNumber}, #{orderStatus},#{orderCreateTime},#{orderWholePrice})")
    public  int newOrder(Order order);


    public int updateOrder(Order order);

    public List<Order> findCustomerOrder(Order order);
}
