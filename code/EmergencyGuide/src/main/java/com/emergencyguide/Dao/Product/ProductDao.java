package com.emergencyguide.Dao.Product;

import com.emergencyguide.Entity.Product;
import org.apache.ibatis.annotations.Insert;
import org.mapstruct.Mapper;

@Mapper
public interface ProductDao {
    @Insert("insert into t_product (productTypeId,productUnitId,productName,productInsertTime,productCreateTime,productExpirationTime,remark,productNumber) values(" +
            " #{productTypeId},#{productUnitId},#{productName},#{productInsertTime},#{productCreateTime},#{productExpirationTime},#{remark},#{productNumber}) ")
    public int newProduct(Product product);
}
