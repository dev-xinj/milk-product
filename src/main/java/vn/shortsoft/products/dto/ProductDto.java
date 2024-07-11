package vn.shortsoft.products.dto;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    String name;
    Integer price;
    String brand;
    Double avgReview;
    Integer totalReview;
    Integer totalSale;
    Timestamp mfgDate;
    Map<String, Object> properties;
    List<ProdReviewDto> prodReviewDtos;
    List<ProdQuestionDto> prodQuestionDtos;
    List<ProdSaleDto> prodSaleDtos;
}
