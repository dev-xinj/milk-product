package vn.shortsoft.products.services;

import java.util.List;
import java.util.Optional;

import vn.shortsoft.products.dto.ItemProductDTO;

public interface ItemProductService {
    Long saveItem(ItemProductDTO itemProduct);

    List<ItemProductDTO> getAllItemProduct(int pageNo, int pageSize,String sortBy);

    int updateStatusItem(Long id, String status);

    void deleteById(Long id);

    ItemProductDTO updateItem(Long id, ItemProductDTO itemProductDTO);

    ItemProductDTO getById(Long id);
}
