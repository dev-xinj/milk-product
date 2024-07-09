package vn.shortsoft.products.services;

import java.util.List;
import java.util.Set;

import vn.shortsoft.products.dto.ProdQuestionDto;
import vn.shortsoft.products.model.ProdQuestion;
import vn.shortsoft.products.model.Product;

public interface ProdQuestionService {
    Long save(ProdQuestion prodQuestion);

    Set<ProdQuestionDto> getProdQuestionByProductId(Long productId);
}
