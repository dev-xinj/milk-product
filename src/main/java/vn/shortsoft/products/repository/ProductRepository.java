package vn.shortsoft.products.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import vn.shortsoft.products.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product SET status = :status WHERE id = :id")
    void updateByStatus(@Param("id") Long id, @Param("status") String status);

    // @EntityGraph(value = "Product.prodQuestions", type = EntityGraphType.LOAD)
    // Optional<Product> findById(Long id);
}
