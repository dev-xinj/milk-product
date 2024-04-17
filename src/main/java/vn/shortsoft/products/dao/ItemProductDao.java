package vn.shortsoft.products.dao;

import java.util.List;

import vn.shortsoft.products.model.ItemProduct;

public interface ItemProductDao {
    ItemProduct saveItem(ItemProduct itemProduct);

    List<ItemProduct> getAllItemProduct();

    ItemProduct getById(Long id);
}
