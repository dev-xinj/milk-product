package vn.shortsoft.products.services;

import java.util.List;

import vn.shortsoft.products.dto.ItemProductDTO;

public interface ItemProductRedisService {
    void save(ItemProductDTO itemProductDTO);

    void saveAll(List<ItemProductDTO> listItemProductDto, int pageNo, int pageSize, String search, String sortBy);

    void clear(String key);

    void update(ItemProductDTO itemProductDTO);

    ItemProductDTO getById(Long id);

    List<ItemProductDTO> getListItemProductDTO(int pageNo, int pageSize, String search, String sortBy);
}
