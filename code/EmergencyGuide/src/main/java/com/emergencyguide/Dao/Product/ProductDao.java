package com.emergencyguide.Dao.Product;

import com.emergencyguide.Entity.Product;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ProductDao {
    @Insert("insert into t_product (customerOpenId,productTypeId,productUnitId,productName,productInsertTime,productCreateTime,productExpirationTime,remark,productNumber,productPhoto) values(" +
            " #{customerOpenId},#{productTypeId},#{productUnitId},#{productName},#{productInsertTime},#{productCreateTime},#{productExpirationTime},#{remark},#{productNumber},#{productPhoto}) ")
    public int newProduct(Product product);



    public List<Product> getCustomerProduct(Product product);




    public  int updateCustomerProduct(Product product);


    @Delete("delete from  t_product where id=#{id}")
    public  int customerProductDelete(int id);


    public  Product selectById( int id);


    public  List<Product> selectShelfLife(Product product);

    public  List<Product> selectRichness(Product product);
}
