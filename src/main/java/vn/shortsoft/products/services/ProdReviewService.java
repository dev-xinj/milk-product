package vn.shortsoft.products.services;

import vn.shortsoft.products.dto.ProdReviewDto;
import vn.shortsoft.products.response.DataResponse;

public interface ProdReviewService {
    DataResponse save(ProdReviewDto prodReviewDto);

    DataResponse getAllReviewByProductId(Long prodId);
}
