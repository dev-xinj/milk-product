package vn.shortsoft.products.model;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data
@RedisHash
@Builder
public class Roles {
    @Id
    private Long id;
    @Indexed
    private String name;
}
