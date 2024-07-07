package vn.shortsoft.products.dto.convert;

import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import vn.shortsoft.products.dto.ProductDto;
import vn.shortsoft.products.model.Product;
import vn.shortsoft.products.utils.JsonMapperUtil;

public class ProductConvert {

        public static Product convertToProduct(ProductDto productDto) {
                return Product.builder()
                                .id(productDto.getId() > 0 ? productDto.getId() : null)
                                .brand(productDto.getBrand())
                                .name(productDto.getName())
                                .mfgDate(productDto.getMfgDate())
                                .price(productDto.getPrice())
                                .properties(JsonMapperUtil.convertJson(productDto.getProperties()))
                                .build();

        }

        public static ProductDto convertToProductDto(Product product) {
                return ProductDto.builder()
                                .id(product.getId() > 0 ? product.getId() : null)
                                .name(product.getName())
                                .brand(product.getBrand())
                                .mfgDate(product.getMfgDate())
                                .price(product.getPrice())
                                .properties(JsonMapperUtil.convertObject(new TypeReference<Map<String, Object>>() {
                                }, product.getProperties()))
                                .build();
        }
}
