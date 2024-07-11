package vn.shortsoft.products.services;

import vn.shortsoft.products.dto.ProductDto;
import vn.shortsoft.products.response.DataResponse;

public interface ProductService {
    DataResponse saveProduct(ProductDto productDto);

    DataResponse getAllProductsBySearch(int pageNo, int pageSize, String search, String sortBy);

    DataResponse updateStatusProduct(Long id, String status);

    DataResponse deleteById(Long id);

    DataResponse getById(Long id);
}
