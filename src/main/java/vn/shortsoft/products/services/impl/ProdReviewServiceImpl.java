package vn.shortsoft.products.services.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import vn.shortsoft.products.dao.ProdReviewDao;
import vn.shortsoft.products.dto.ProdReviewDto;
import vn.shortsoft.products.dto.convert.ProdReviewConvert;
import vn.shortsoft.products.model.ProdReview;
import vn.shortsoft.products.response.DataResponse;
import vn.shortsoft.products.services.ProdReviewService;

@Service
public class ProdReviewServiceImpl implements ProdReviewService {

    @Autowired
    ProdReviewDao prodReviewDao;

    @Override
    public DataResponse save(ProdReviewDto prodReviewDto) {
        ProdReview prodReview = ProdReviewConvert.convertToProdReview(prodReviewDto);
        Long id = prodReviewDao.save(prodReview);
        return DataResponse.builder()
                .code(HttpStatus.CREATED.value())
                .status(HttpStatus.CREATED.name())
                .message("Tạo thành công")
                .data(id)
                .build();
    }

    @Override
    public DataResponse getAllReviewByProductId(Long prodId) {
        Set<ProdReview> listReview = prodReviewDao.getAllReviewByProductId(prodId);
        return DataResponse.builder()
                .code(HttpStatus.OK.value())
                .status(HttpStatus.OK.name())
                .message("Thành Công")
                .data(listReview.stream().map(r -> ProdReviewConvert.convertToProdReviewDto(r))
                        .collect(Collectors.toList()))
                .build();

    }

}
