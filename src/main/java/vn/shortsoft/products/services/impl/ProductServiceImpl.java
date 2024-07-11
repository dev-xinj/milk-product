package vn.shortsoft.products.services.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import vn.shortsoft.products.dao.ProdQuestionDao;
import vn.shortsoft.products.dao.ProductDao;
import vn.shortsoft.products.dto.ProductDto;
import vn.shortsoft.products.dto.convert.ProdQuestionConvert;
import vn.shortsoft.products.dto.convert.ProductConvert;
import vn.shortsoft.products.model.ProdQuestion;
import vn.shortsoft.products.model.Product;
import vn.shortsoft.products.response.DataResponse;
import vn.shortsoft.products.response.PageResponse;
import vn.shortsoft.products.services.ProductRedisService;
import vn.shortsoft.products.services.ProductService;
import vn.shortsoft.products.utils.JsonMapperUtil;

@Log4j2
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProdQuestionDao prodQuestionDao;

    @Autowired
    private ProductDao productDao;
    @Autowired
    private ProductRedisService productRedisService;

    @Override
    // @Cacheable(cacheNames = "product", key = "#id")
    public DataResponse getById(Long id) {
        Product prod = productDao.getById(id);
        prod.setProdQuestions(new HashSet<>());
        prod.setProdReviews(new HashSet<>());
        prod.setProdSales(new HashSet<>());
        Set<ProdQuestion> prodQuestion = prodQuestionDao.getProdQuestionByProductId(id);
        prod.setProdQuestions(prodQuestion);
        ProductDto productDto = ProductConvert.convertToProductDto(prod);
        return DataResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .message("Thực hiện thành công")
                .data(productDto)
                .build();
    }

    @Override
    @Transactional
    // @CachePut(cacheNames = "products", key = "'listKey'") //caching
    public DataResponse saveProduct(ProductDto productDto) {
        Product prod = productDao.getById(productDto.getId());
        if (prod != null) {
            isNotNull(() -> productDto.getBrand(), productDto.getBrand(), prod::setBrand);
            isNotNull(() -> productDto.getMfgDate(), productDto.getMfgDate(), prod::setMfgDate);
            isNotNull(() -> productDto.getName(), productDto.getName(), prod::setName);
            isNotNull(() -> productDto.getPrice(), productDto.getPrice(), prod::setPrice);
            isNotNull(() -> JsonMapperUtil.convertJson(productDto.getProperties()),
                    JsonMapperUtil.convertJson(productDto.getProperties()), prod::setProperties);
            productDao.saveProduct(prod);
            return DataResponse.builder()
                    .code(HttpStatus.CREATED.value())
                    .status(HttpStatus.CREATED.name())
                    .message("Cập nhật Thành Công")
                    .build();
        } else {
            Product product = ProductConvert.convertToProduct(productDto);
            Long id = productDao.saveProduct(product).getId();
            productDto.setId(id);
            // productRedisService.saveByKey(productDto);
            return DataResponse.builder()
                    .code(HttpStatus.CREATED.value())
                    .status(HttpStatus.CREATED.name())

                    .message("Tạo Thành Công")
                    .data(id)
                    .build();
        }
    }

    @Override
    // @CacheEvict(cacheNames = "product", key = "#id") //Xóa caching
    public DataResponse deleteById(Long id) {
        productDao.deleteById(id);
        return new DataResponse(HttpStatus.CONTINUE.value(), HttpStatus.CONTINUE.name(), "Xóa Thành Công");
    }

    @Override
    // @CacheEvict(cacheNames = "product", key = "#id")
    public DataResponse updateStatusProduct(Long id, String status) {
        productDao.updateStatusProduct(id, status);
        return new DataResponse(HttpStatus.CONTINUE.value(), HttpStatus.CONTINUE.name(), "Update Thành Công");
    }

    @Override
    public DataResponse getAllProductsBySearch(int pageNo, int pageSize, String search,
            String sortBy) {
        int p = pageNo;
        if (pageNo > 0) {
            pageNo = pageNo - 1;
        }
        Page<Product> page = productDao.getAllProductsBySearch(pageNo, pageSize, search, sortBy);
        List<Product> products = page.toList();
        List<ProductDto> productDtos = products.stream()
                .map(product -> ProductConvert.convertToProductDto(product)).collect(Collectors.toList());
        // Lấy Danh sách sản phẩm theo page
        return DataResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .message("Thành Công")
                .data(PageResponse.builder()
                        .pageNo(p)
                        .pageSize(pageSize)
                        .pageTotal(page.getTotalPages())
                        .data(productDtos)
                        .build())
                .build();

    }

    private <T> void isNotNull(Supplier<T> getter, T value, Consumer<T> setter) {
        T currentValue = getter.get();
        if (currentValue != null) {
            setter.accept(value);
        }
    }
}
