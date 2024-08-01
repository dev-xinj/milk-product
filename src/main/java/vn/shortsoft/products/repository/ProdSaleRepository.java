// package vn.shortsoft.products.repository;

// import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
// import org.springframework.stereotype.Repository;
// import vn.shortsoft.products.model.ProdSale;

// @Repository
// public interface ProdSaleRepository extends JpaRepository<ProdSale, Long> {
//     @Query(value = "SELECT count(s) FROM ProdSale s WHERE s.product.id = :productId")
//     Long totalSaleByProductId(Long productId);
// }
