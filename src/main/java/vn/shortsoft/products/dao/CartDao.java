package vn.shortsoft.products.dao;

import java.util.List;

import vn.shortsoft.products.model.Cart;

public interface CartDao {
    String getKey(Long id);

    String getKey(Cart cart);

    Cart getById(Long id);

    Cart getByUserId(Long id);

    List<Cart> getAllById();

    List<Cart> getAllById(Iterable<Long> ids);

    <S extends Cart> S save(S cart);

    void deleteById(Long Id);

    void delete(Cart cart);

    void deleteAll();

    void deleteAll(Iterable<? extends Cart> carts);
}
