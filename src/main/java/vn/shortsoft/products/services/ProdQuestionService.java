package vn.shortsoft.products.services;

import java.util.List;

import vn.shortsoft.products.model.ProdQuestion;

public interface ProdQuestionService {
    Long save(ProdQuestion prodQuestion);

    List<ProdQuestion> getProdQuestionByProductId(Long productId);
}
