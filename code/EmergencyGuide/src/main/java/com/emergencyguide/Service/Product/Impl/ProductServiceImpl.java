package com.emergencyguide.Service.Product.Impl;

import com.emergencyguide.Dao.Product.ProductDao;
import com.emergencyguide.Entity.Product;
import com.emergencyguide.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Override
    public int newProduct(Product product) {
        return productDao.newProduct(product);
    }
}
