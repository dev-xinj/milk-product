package vn.shortsoft.products.repository;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.lettuce.core.dynamic.annotation.Param;
import vn.shortsoft.products.model.ProdReview;

@Repository
public interface ProdReviewRepository extends JpaRepository<ProdReview, Long> {

    @Query(value = "SELECT re FROM ProdReview re WHERE re.product.id = :prodId")
    Optional<Set<ProdReview>> findByProductId(@Param("prodId") Long prodId);
}
