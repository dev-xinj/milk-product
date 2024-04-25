package vn.shortsoft.products.services;

import vn.shortsoft.products.dto.ItemProductDTO;
import vn.shortsoft.products.response.PageResponse;

public interface ItemProductService {
    Long saveItem(ItemProductDTO itemProduct);

    PageResponse<?> getAllItemProduct(int pageNo, int pageSize,String sortBy);

    int updateStatusItem(Long id, String status);

    void deleteById(Long id);

    ItemProductDTO updateItem(Long id, ItemProductDTO itemProductDTO);

    ItemProductDTO getById(Long id);
}
