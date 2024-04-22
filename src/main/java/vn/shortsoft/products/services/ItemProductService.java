package vn.shortsoft.products.services;

import java.util.List;

import vn.shortsoft.products.model.ItemProduct;

public interface ItemProductService {
    ItemProduct saveItem(ItemProduct itemProduct);

    List<ItemProduct> getAllItemProduct(int pageNo, int pageSize);

    int updateStatusItem(Long id, String status);

    void deleteById(Long id);

    ItemProduct getById(Long id);
}
