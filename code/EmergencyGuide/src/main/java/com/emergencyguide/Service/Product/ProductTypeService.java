package com.emergencyguide.Service.Product;

import com.emergencyguide.Entity.ProductType;
import com.emergencyguide.Entity.Rank;

import java.util.List;

public interface ProductTypeService {
    public List<ProductType> selectAllList(int page, int limit, String searchParams);
    public int selectListCount( String searchParams);
    public int updateById(ProductType productType);
    public  int productTypeAdd(ProductType productType);
    public  int productTypeDelete(int id);
}
