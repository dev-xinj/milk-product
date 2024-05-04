package vn.shortsoft.products.reponsitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.shortsoft.products.model.ItemProduct;

@Repository
public interface ItemProductRedisRepository extends CrudRepository<ItemProduct, Long> {

}
