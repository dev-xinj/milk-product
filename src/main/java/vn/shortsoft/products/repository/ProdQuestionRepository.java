package vn.shortsoft.products.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.shortsoft.products.model.ProdQuestion;

public interface ProdQuestionRepository extends JpaRepository<ProdQuestion, Long> {
    Optional<List<ProdQuestion>> findByProductId(Long productId);
}
