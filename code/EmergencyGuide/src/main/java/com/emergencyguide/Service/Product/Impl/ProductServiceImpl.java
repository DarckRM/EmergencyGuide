package com.emergencyguide.Service.Product.Impl;

import com.emergencyguide.Dao.Product.ProductDao;
import com.emergencyguide.Entity.Product;
import com.emergencyguide.Service.Product.ProductService;
import com.emergencyguide.Utils.EasyGeneraterParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl  implements ProductService {

    @Autowired
    private  ProductDao productDao;

    @Override
    public int newProduct(Product product) {
        return productDao.newProduct(product);
    }

    @Override
    public List<Product> getCustomerProduct(Product product) {
        return productDao.getCustomerProduct(product);
    }

    @Override
    public int updateCustomerProduct(Product product) {
        return productDao.updateCustomerProduct(product);
    }

    @Override
    public int customerProductDelete(int id) {
        return productDao.customerProductDelete(id);
    }

    @Override
    public Product selectById(int id) {
        return productDao.selectById(id);
    }

    @Override
    public List<Product> selectShelfLife(Product product) {
        return productDao.selectShelfLife(product);
    }

    @Override
    public List<Product> selectRichness(Product product) {
        return productDao.selectRichness(product);
    }
}
