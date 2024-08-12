package vn.shortsoft.products.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import vn.shortsoft.products.constant.ValidationConstant;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDto {
    Long id;
    @NotEmpty(message = ValidationConstant.NOT_EMPTY) // Khong cho phep gia tri blank
    String name;
    @NotEmpty(message = ValidationConstant.NOT_EMPTY)
    String brand;
    @Min(value = 0, message = ValidationConstant.NOT_EMPTY)
    Integer price;
    Double avgReview;
    Integer totalReview;
    Integer totalSale;
    Timestamp mfgDate;
    Map<String, Object> properties;
    List<ProdReviewDto> prodReviewDtos;
    List<ProdQuestionDto> prodQuestionDtos;
    List<ProdSaleDto> prodSaleDtos;
}
