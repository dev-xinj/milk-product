package vn.shortsoft.products.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.shortsoft.products.constant.ValidationConstant;
import vn.shortsoft.products.enums.TypeQuestion;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdQuestionDto implements Serializable {
    private Long id;
    @Min(value = 1, message = ValidationConstant.NOT_EMPTY)
    private Long userId;
    @NotEmpty(message = ValidationConstant.NOT_EMPTY)
    private String fullName;
    @NotEmpty(message = ValidationConstant.NOT_EMPTY)
    private TypeQuestion type;
    @NotEmpty(message = ValidationConstant.NOT_EMPTY)
    private Timestamp dateSend;
    @NotEmpty(message = ValidationConstant.NOT_EMPTY)
    private String textSend;
    private ProductDto productDto;
}
