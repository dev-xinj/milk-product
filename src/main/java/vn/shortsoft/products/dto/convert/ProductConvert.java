package vn.shortsoft.products.dto.convert;

import java.util.ArrayList;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.core.type.TypeReference;

import vn.shortsoft.products.dto.ProductDto;
import vn.shortsoft.products.model.Product;
import vn.shortsoft.products.utils.JsonMapperUtil;

public class ProductConvert {

        public static Product convertToProduct(ProductDto productDto) {
                return Product.builder()
                                .id(productDto.getId() != null ? productDto.getId() : null)
                                .brand(productDto.getBrand())
                                .name(productDto.getName())
                                .mfgDate(productDto.getMfgDate())
                                .price(productDto.getPrice())
                                .prodQuestions(productDto.getProdQuestionDtos().stream()
                                                .map(t -> ProdQuestionConvert.convertToProdQuestion(t))
                                                .collect(Collectors.toSet()))
                                .prodReviews(productDto.getProdReviewDtos().stream()
                                                .map(t -> ProdReviewConvert.convertToProdReview(t))
                                                .collect(Collectors.toSet()))
                                .prodSales(productDto.getProdSaleDtos().stream()
                                                .map(t -> ProdSaleConvert.convertToProdSale(t))
                                                .collect(Collectors.toSet()))
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
                                .avgReview(product.getAvgReview())
                                .totalReview(product.getTotalReview())
                                .totalSale(product.getTotalSale())
                                .prodQuestionDtos(!product.getProdQuestions()
                                                .isEmpty() ? product.getProdQuestions().stream()
                                                                .map(t -> ProdQuestionConvert
                                                                                .convertToProdQuestionDto(t))
                                                                .collect(Collectors.toList()) : new ArrayList())

                                // .prodSaleDtos(!product.getProdSales().isEmpty() ? product.getProdSales().stream()
                                //                 .map(t -> ProdSaleConvert.convertToProdSaleDto(t))
                                //                 .collect(Collectors.toList()) : new ArrayList())
                                // .prodReviewDtos(!product.getProdReviews().isEmpty() ? product.getProdReviews().stream()
                                //                 .map(t -> ProdReviewConvert.convertToProdReviewDto(t))
                                //                 .collect(Collectors.toList()) : new ArrayList())
                                .properties(JsonMapperUtil.convertObject(new TypeReference<Map<String, Object>>() {
                                }, product.getProperties()))
                                .build();
        }
}
