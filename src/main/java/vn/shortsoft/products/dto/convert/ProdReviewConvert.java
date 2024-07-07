package vn.shortsoft.products.dto.convert;

import vn.shortsoft.products.dto.ProdReviewDto;
import vn.shortsoft.products.model.ProdReview;

public class ProdReviewConvert {

    public static ProdReview convertToProdReview(ProdReviewDto prodReviewDto) {
        return ProdReview.builder()
                .fullName(prodReviewDto.getFullName())
                .star(prodReviewDto.getStar())
                .description(prodReviewDto.getDescription())
                .build();
    }

    public static ProdReviewDto convertToProdReviewDto(ProdReview prodReview) {
        return ProdReviewDto.builder()
                .fullName(prodReview.getFullName())
                .star(prodReview.getStar())
                .description(prodReview.getDescription())
                .build();
    }
}
