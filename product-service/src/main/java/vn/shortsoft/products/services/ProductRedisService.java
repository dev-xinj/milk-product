package vn.shortsoft.products.services;

import java.util.List;
import java.util.Set;

import vn.shortsoft.products.dto.ProductDto;
import vn.shortsoft.products.response.PageResponse;

public interface ProductRedisService {
    void save(ProductDto productDto);

    void saveAll(PageResponse pageResponse, int pageNo, int pageSize, String search, String sortBy);

    void saveByKey(ProductDto productDto);

    void clear(String key);

    void update(ProductDto productDto);

    ProductDto getById(Long id);

    Set<ProductDto> getProductDtos(int pageNo, int pageSize, String search, String sortBy);
}
