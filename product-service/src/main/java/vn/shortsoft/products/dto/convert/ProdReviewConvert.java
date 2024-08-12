package vn.shortsoft.products.dto.convert;

import vn.shortsoft.products.dto.ProdReviewDto;
import vn.shortsoft.products.model.ProdReview;
import vn.shortsoft.products.model.Product;

public class ProdReviewConvert {

    public static ProdReview convertToProdReview(ProdReviewDto prodReviewDto) {
        return ProdReview.builder()
                .userId(prodReviewDto.getUserId())
                .fullName(prodReviewDto.getFullName())
                .star(prodReviewDto.getStar())
                .description(prodReviewDto.getDescription())
                .product(Product.builder().id(prodReviewDto.getProductDto().getId()).build())
                .build();
    }

    public static ProdReviewDto convertToProdReviewDto(ProdReview prodReview) {
        return ProdReviewDto.builder()
                .userId(prodReview.getUserId())
                .fullName(prodReview.getFullName())
                .star(prodReview.getStar())
                .description(prodReview.getDescription())
                .build();
    }
}
