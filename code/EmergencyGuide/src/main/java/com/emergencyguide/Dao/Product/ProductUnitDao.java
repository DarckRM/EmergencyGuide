package com.emergencyguide.Dao.Product;

import com.emergencyguide.Entity.ProductType;
import com.emergencyguide.Entity.ProductUnit;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@Mapper
public interface ProductUnitDao {
    public List<ProductUnit> selectAllList(@Param("page") int page, @Param("limit") int limit,
                                           @Param("params") Map<String, Object> params);
    public int selectListCount( @Param("params")Map<String, Object> params);

    public int updateById(ProductUnit productUnit);

    public int productUnitAdd(ProductUnit productUnit);

    @Delete("delete  from t_productUnit where id=#{id}")
    public int productUnitDelete(int id);

    @Select("select *from t_productUnit ")
    public List<ProductUnit> getProductUnit();
}
