package vn.shortsoft.products.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import vn.shortsoft.products.model.ItemProduct;

public interface ItemProductRepository extends JpaRepository<ItemProduct, Long> {

    @Modifying
    @Query("UPDATE ItemProduct SET status = :status WHERE id = :id")
    void updateByStatus(@Param("id") Long id, @Param("status") String status);


}
