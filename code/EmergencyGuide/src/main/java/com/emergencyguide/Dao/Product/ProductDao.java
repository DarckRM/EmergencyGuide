package com.emergencyguide.Dao.Product;

import com.emergencyguide.Entity.Product;
import org.mapstruct.Mapper;

@Mapper
public interface ProductDao {
    public int newProduct(Product product);
}
