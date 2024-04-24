package vn.shortsoft.products.dao;

import org.springframework.data.domain.Page;

import vn.shortsoft.products.model.ItemProduct;

public interface ItemProductDao {
    ItemProduct saveItem(ItemProduct itemProduct);

    Page<ItemProduct> getAllItemProduct(int pageNo, int pageSize, String sortBy);

    void updateStatusItem(Long id, String status);

    void deleteById(Long id);
    
    ItemProduct updateItem(Long id, ItemProduct itemProduct);

    ItemProduct getById(Long id);
}
