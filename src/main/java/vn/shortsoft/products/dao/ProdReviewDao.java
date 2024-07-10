package vn.shortsoft.products.dao;

import java.util.List;

import vn.shortsoft.products.model.ProdReview;

public interface ProdReviewDao {
    Long save(ProdReview prodReview);

    List<ProdReview> getAllReviewByProductId(Long prodId);
}
