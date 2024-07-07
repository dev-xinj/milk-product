package vn.shortsoft.products.reponsitory;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.shortsoft.products.model.Product;

@Repository
public interface ProductRedisRepository extends CrudRepository<Product, Long> {

}
