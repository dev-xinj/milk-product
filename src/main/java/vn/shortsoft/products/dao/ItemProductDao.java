package vn.shortsoft.products.dao;

import java.util.List;

import org.springframework.data.domain.Page;

import vn.shortsoft.products.model.ItemProduct;

public interface ItemProductDao {
    ItemProduct saveItem(ItemProduct itemProduct);

    Page<ItemProduct> getAllItemProduct(int pageNo, int pageSize, String sortBy);

    List<ItemProduct> getListEmployeeBySearch(int pageNo, int pageSize, String search, String sortBy);

    void updateStatusItem(Long id, String status);

    void deleteById(Long id);

    ItemProduct updateItem(Long id, ItemProduct itemProduct);

    ItemProduct getById(Long id);

}
