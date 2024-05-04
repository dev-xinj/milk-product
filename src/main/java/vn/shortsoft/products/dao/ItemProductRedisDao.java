package vn.shortsoft.products.dao;

import java.util.List;

import vn.shortsoft.products.model.ItemProduct;

public interface ItemProductRedisDao {
    void clear();
    List<ItemProduct> getAllItemProduct();
    void saveAllItemProduct();
}
