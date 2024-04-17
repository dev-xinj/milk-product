package vn.shortsoft.products.reponsitory;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.shortsoft.products.model.ItemProduct;

public interface ItemProductRepository extends JpaRepository<ItemProduct, Long> {

}
