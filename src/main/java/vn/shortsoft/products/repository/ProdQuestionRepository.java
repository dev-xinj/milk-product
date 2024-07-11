package vn.shortsoft.products.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import io.lettuce.core.dynamic.annotation.Param;
import vn.shortsoft.products.model.ProdQuestion;

@Repository
public interface ProdQuestionRepository extends JpaRepository<ProdQuestion, Long> {

    @Query(value = "select q from ProdQuestion q where q.product.id = :productId order by q.id desc")
    Set<ProdQuestion> findByProductId(@Param("productId") Long productId);
}
