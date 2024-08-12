package vn.shortsoft.products.dao;

import java.util.List;

import vn.shortsoft.products.model.Product;

public interface ProductRedisDao {
    void clear();
    List<Product> getAllItemProduct();
    void saveAllItemProduct();
}
