package com.emergencyguide.Service.Product.Impl;

import com.emergencyguide.Entity.Product;
import com.emergencyguide.Service.Product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private ProductService productService;
    @Override
    public int newProduct(Product product) {
        return productService.newProduct(product);
    }
}
