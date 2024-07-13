package vn.shortsoft.products.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.experimental.FieldDefaults;
import vn.shortsoft.products.constant.ValidationConstant;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProdReviewDto implements Serializable {
    @Min(value = 1, message = ValidationConstant.NOT_EMPTY)
    Long userId;
    @NotEmpty(message = ValidationConstant.NOT_EMPTY)
    String fullName;
    @Min(value = 1, message = ValidationConstant.NOT_EMPTY)
    @Max(value = 5, message = ValidationConstant.NOT_EMPTY)
    Integer star;
    @NotEmpty(message = ValidationConstant.NOT_EMPTY)
    String description;
    ProductDto productDto;
}
