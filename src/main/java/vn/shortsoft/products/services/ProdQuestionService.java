package vn.shortsoft.products.services;

import java.util.Set;

import vn.shortsoft.products.dto.ProdQuestionDto;

public interface ProdQuestionService {
    Long save(ProdQuestionDto prodQuestion);

    Set<ProdQuestionDto> getAllQuestionByProductId(Long productId);
}
