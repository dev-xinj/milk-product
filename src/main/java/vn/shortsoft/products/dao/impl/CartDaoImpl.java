package vn.shortsoft.products.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;

import vn.shortsoft.products.dao.CartDao;
import vn.shortsoft.products.model.Cart;

public class CartDaoImpl implements CartDao {

    private final static String idPrefix = Cart.class.getName();
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private SetOperations<String, String> redisSets() {
        return redisTemplate.opsForSet();
    }

    private HashOperations<String, String, String> redisHash() {
        return redisTemplate.opsForHash();
    }

    @Override
    public <S extends Cart> S save(S cart) {
        Random r = new Random();
        // set cart id
        if (cart.getId() == null) {
            cart.setId(r.nextLong());
        }
        String key = getKey(cart);
        redisSets().add(idPrefix, key);
        redisHash().put("carts-by-user-id-idx", cart.getUserId().toString(), cart.getId().toString());
        return cart;
    }

    @Override
    public String getKey(Long id) {
        return String.format("%s:%s", idPrefix, id.toString());
    }

    @Override
    public String getKey(Cart cart) {
        return String.format("%s:%s", idPrefix, cart.getId());
    }

    @Override
    public Cart getById(Long id) {
        Optional<Object> cart = Optional.of(redisTemplate.opsForHash().get(getKey(id), Cart.class));
        return (Cart) cart.get();

    }

    @Override
    public Cart getByUserId(Long id) {
        String cartId = redisHash().get("carts-by-user-id-idx", id.toString());
        return (cartId != null) ? getById(Long.valueOf(cartId)) : null;
    }

    @Override
    public List<Cart> getAllById() {

        throw new UnsupportedOperationException("Unimplemented method 'getAllById'");
    }

    @Override
    public List<Cart> getAllById(Iterable<Long> ids) {
        // String[] keys = StreamSupport.stream(ids.spliterator(), false) //
        //         .map(id -> getKey(id)).toArray(String[]::new);
        // return (List<Cart>) redisTemplate.opsForValue()(Cart.class, keys);
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void deleteById(Long Id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteById'");
    }

    @Override
    public void delete(Cart cart) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

    @Override
    public void deleteAll(Iterable<? extends Cart> carts) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteAll'");
    }

}
