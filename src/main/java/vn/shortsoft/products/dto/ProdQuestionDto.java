package vn.shortsoft.products.dto;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.shortsoft.products.enums.TypeQuestion;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdQuestionDto implements Serializable {
    private Long id;
    private Long userId;
    private String fullName;
    private TypeQuestion type;
    private Timestamp dateSend;
    private String textSend;
    private ProductDto productDto;
}
