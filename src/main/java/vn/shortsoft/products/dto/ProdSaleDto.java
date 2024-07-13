package vn.shortsoft.products.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.shortsoft.products.constant.ValidationConstant;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdSaleDto implements Serializable {
    @Min(value = 1, message = ValidationConstant.NOT_EMPTY)
    private Long userId;
    @Min(value = 1, message = ValidationConstant.NOT_EMPTY)
    private Long orderId;
    private ProductDto productDto;
}
