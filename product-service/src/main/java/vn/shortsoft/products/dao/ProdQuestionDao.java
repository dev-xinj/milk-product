package vn.shortsoft.products.dao;

import java.util.Set;

import vn.shortsoft.products.model.ProdQuestion;

public interface ProdQuestionDao {
    Long save(ProdQuestion prodQuestion);

    Set<ProdQuestion> getAllQuestionByProductId(Long productId);

    Set<ProdQuestion> getQuestionByProductId(Long id);
}
