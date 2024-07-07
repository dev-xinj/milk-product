package vn.shortsoft.products.services;

import java.util.List;

import vn.shortsoft.products.dto.ProductDto;
import vn.shortsoft.products.response.DataResponse;
import vn.shortsoft.products.response.PageResponse;

public interface ProductService {
    DataResponse saveProduct(ProductDto productDto);

    PageResponse getAllProducts(int pageNo, int pageSize, String sortBy);

    DataResponse getProductsBySearch(int pageNo, int pageSize, String search, String sortBy);

    DataResponse updateStatusProduct(Long id, String status);

    DataResponse deleteById(Long id);

    DataResponse getById(Long id);
}
