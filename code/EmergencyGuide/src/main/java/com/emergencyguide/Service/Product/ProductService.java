package com.emergencyguide.Service.Product;

import com.emergencyguide.Entity.Product;

import java.util.List;

public interface ProductService {
    public  int newProduct(Product product);
    public List<Product> getCustomerProduct(Product product);
    public  int updateCustomerProduct(Product product);
    public  int  customerProductDelete( int id);
    public  Product selectById(int id);
    public  List<Product> selectShelfLife(Product product);
    public  List<Product> selectRichness(Product product);
}
