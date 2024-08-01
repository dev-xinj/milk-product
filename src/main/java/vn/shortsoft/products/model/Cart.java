package vn.shortsoft.products.model;

import java.util.Set;

import org.springframework.data.redis.core.RedisHash;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RedisHash
@Entity
@Table(name = "prod_cart")
public class Cart extends BaseEntity {
    Long id;
    Long userId;


    @Singular
    Set<CartItem> cartItems;

    public Integer count() {
        return getCartItems().size();
      }
    
      public Double getTotal() {
        return cartItems //
            .stream() //
            .mapToDouble(ci -> ci.getPrice() * ci.getQuantity()) //
            .sum();
      }
}
