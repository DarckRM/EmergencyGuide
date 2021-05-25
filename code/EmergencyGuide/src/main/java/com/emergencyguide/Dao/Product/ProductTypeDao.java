package com.emergencyguide.Dao.Product;

import com.emergencyguide.Entity.PersonalLogo;
import com.emergencyguide.Entity.ProductType;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;
@Mapper
public interface ProductTypeDao {
    public List<ProductType> selectAllList(@Param("page") int page, @Param("limit") int limit,
                                           @Param("params") Map<String, Object> params);
    public int selectListCount( @Param("params")Map<String, Object> params);

    public int updateById(ProductType productType);

    public int productTypeAdd(ProductType productType);

    @Delete("delete  from t_productType where id=#{id}")
    public int productTypeDelete(int id);


    @Select("select * from t_productType")
    public List<ProductType> getProductType();
}
