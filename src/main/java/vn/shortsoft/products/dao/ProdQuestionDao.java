package vn.shortsoft.products.dao;

import java.util.List;

import vn.shortsoft.products.model.ProdQuestion;

public interface ProdQuestionDao {
    Long save(ProdQuestion prodQuestion);

    List<ProdQuestion> getProdQuestionByProductId(Long productId);
}
