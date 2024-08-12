package vn.shortsoft.products.dao;

import java.util.Set;

import vn.shortsoft.products.model.ProdReview;

public interface ProdReviewDao {
    Long save(ProdReview prodReview);

    Set<ProdReview> getAllReviewByProductId(Long prodId);
}
