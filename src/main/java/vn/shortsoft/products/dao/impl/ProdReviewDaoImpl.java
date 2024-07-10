package vn.shortsoft.products.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import vn.shortsoft.products.dao.ProdReviewDao;
import vn.shortsoft.products.model.ProdReview;
import vn.shortsoft.products.repository.ProdReviewRepository;

@Component
public class ProdReviewDaoImpl implements ProdReviewDao {

    @Autowired
    ProdReviewRepository prodReviewRepository;

    @Override
    public Long save(ProdReview prodReview) {
        return prodReviewRepository.save(prodReview).getId();
    }

    @Override
    public List<ProdReview> getAllReviewByProductId(Long prodId) {
        Optional<List<ProdReview>> optList = prodReviewRepository.findByProductId(prodId);
        return optList.isPresent() ? optList.get() : null;
    }

}
