package vn.shortsoft.products.dto.convert;

import vn.shortsoft.products.dto.ProdQuestionDto;
import vn.shortsoft.products.model.ProdQuestion;
import vn.shortsoft.products.model.Product;

public class ProdQuestionConvert {
    public static ProdQuestion convertToProdQuestion(ProdQuestionDto prodQuestionDto) {
        return ProdQuestion.builder()
                .id(prodQuestionDto.getId() != null ? prodQuestionDto.getId() : null)
                .fullName(prodQuestionDto.getFullName())
                .dateSend(prodQuestionDto.getDateSend())
                .textSend(prodQuestionDto.getTextSend())
                .type(prodQuestionDto.getType())
                .userId(prodQuestionDto.getUserId())
                .product(Product.builder().id(prodQuestionDto.getProductDto().getId()).build())
                .build();
    }

    public static ProdQuestionDto convertToProdQuestionDto(ProdQuestion prodQuestion) {
        return ProdQuestionDto.builder()
                .id(prodQuestion.getId() != null ? prodQuestion.getId() : null)
                .fullName(prodQuestion.getFullName())
                .dateSend(prodQuestion.getDateSend())
                .textSend(prodQuestion.getTextSend())
                .type(prodQuestion.getType())
                .userId(prodQuestion.getUserId())
                .build();
    }

}
