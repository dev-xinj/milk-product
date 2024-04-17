package vn.shortsoft.products.services;

import java.util.List;

import vn.shortsoft.products.model.ItemProduct;

public interface ItemProductService {
    ItemProduct saveItem(ItemProduct itemProduct);

    List<ItemProduct> getAllItemProduct();

    ItemProduct getById(Long id);
}
