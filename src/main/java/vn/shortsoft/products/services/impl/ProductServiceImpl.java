package vn.shortsoft.products.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import vn.shortsoft.products.dao.ProductDao;
import vn.shortsoft.products.dto.ProductDto;
import vn.shortsoft.products.dto.convert.ProductConvert;
import vn.shortsoft.products.model.Product;
import vn.shortsoft.products.response.DataResponse;
import vn.shortsoft.products.response.PageResponse;
import vn.shortsoft.products.services.ProductRedisService;
import vn.shortsoft.products.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductRedisService productRedisService;

    @Override
    // @Cacheable(cacheNames = "products", key = "'listKey'") //Lưu caching
    public PageResponse getAllProducts(int pageNo, int pageSize, String sortBy) {
        int p = pageNo;
        if (pageNo > 0) {
            pageNo = pageNo - 1;
        }
        // Lấy Danh sách sản phẩm theo page
        Page<Product> products = productDao.getAllProducts(pageNo, pageSize, sortBy);
        // Convert object to object dto và lưu cache
        List<ProductDto> productDtos = products.toSet().stream()
                .map(product -> ProductConvert.convertToProductDto(product)).collect(Collectors.toList());
        return PageResponse.builder()
                .pageNo(p)
                .pageSize(pageSize)
                .pageTotal(products.getTotalPages())
                .data(productDtos)
                .build();

    }

    @Override
    // @Cacheable(cacheNames = "product", key = "#id")
    public DataResponse getById(Long id) {
        return DataResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .message("Thực hiện thành công")
                .data(ProductConvert.convertToProductDto(productDao.getById(id)))
                .build();
    }

    @Override
    @Transactional
    // @CachePut(cacheNames = "products", key = "'listKey'") //caching
    public DataResponse saveProduct(ProductDto productDto) {
        Product product = ProductConvert.convertToProduct(productDto);
        Long id = productDao.saveProduct(product).getId();
        productDto.setId(id);
        productRedisService.saveByKey(productDto);
        return DataResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.name())
                .message("Tạo Thành Công")
                .data(id)
                .build();
    }

    @Override
    // @CacheEvict(cacheNames = "product", key = "#id") //Xóa caching
    public DataResponse deleteById(Long id) {
        return new DataResponse(HttpStatus.CONTINUE.value(), HttpStatus.CONTINUE.name(), "Xóa Thành Công");
    }

    @Override
    @CacheEvict(cacheNames = "product", key = "#id")
    public DataResponse updateStatusProduct(Long id, String status) {
        productDao.updateStatusProduct(id, status);
        return new DataResponse(HttpStatus.CONTINUE.value(), HttpStatus.CONTINUE.name(), "Update Thành Công");
    }

    @Override
    public DataResponse getProductsBySearch(int pageNo, int pageSize, String search,
            String sortBy) {
        Set<Product> products = productDao.getProductsBySearch(pageNo, pageSize, search, sortBy);
        ;
        Set<ProductDto> productDtos = new HashSet<>(products.stream()
                .map(product -> ProductConvert.convertToProductDto(product)).collect(Collectors.toSet()));
        return DataResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.name())
                .message("Thành Công")
                .data(productDtos)
                .build();
    }

}
