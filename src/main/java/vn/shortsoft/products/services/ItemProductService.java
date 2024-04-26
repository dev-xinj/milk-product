package vn.shortsoft.products.services;

import java.util.List;

import vn.shortsoft.products.dto.ItemProductDTO;
import vn.shortsoft.products.model.ItemProduct;
import vn.shortsoft.products.response.PageResponse;

public interface ItemProductService {
    Long saveItem(ItemProductDTO itemProduct);

    PageResponse<?> getAllItemProduct(int pageNo, int pageSize, String sortBy);

    List<ItemProduct> getListEmployeeBySearch(int pageNo, int pageSize, String search, String sortBy);

    int updateStatusItem(Long id, String status);

    void deleteById(Long id);

    ItemProductDTO updateItem(Long id, ItemProductDTO itemProductDTO);

    ItemProductDTO getById(Long id);
}
