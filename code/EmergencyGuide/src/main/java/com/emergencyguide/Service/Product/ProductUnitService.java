package com.emergencyguide.Service.Product;

import com.emergencyguide.Entity.ProductType;
import com.emergencyguide.Entity.ProductUnit;
import org.apache.ibatis.annotations.Delete;

import java.util.List;

public interface ProductUnitService {
    public List<ProductUnit> selectAllList(int page, int limit, String searchParams);
    public int selectListCount( String searchParams);

    public int updateById(ProductUnit productUnit);

    public int productUnitAdd(ProductUnit productUnit);

    public int productUnitDelete(int id);
    public List<ProductUnit> getProductUnit();
}
