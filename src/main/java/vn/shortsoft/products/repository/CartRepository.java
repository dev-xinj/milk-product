package vn.shortsoft.products.repository;

import org.springframework.data.repository.CrudRepository;

import vn.shortsoft.products.model.Cart;

public interface CartRepository extends CrudRepository<Cart,Long>{

}
