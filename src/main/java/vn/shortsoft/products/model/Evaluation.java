package vn.shortsoft.products.model;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.springframework.data.redis.core.RedisHash;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.NonFinal;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
@RedisHash
public class Evaluation {

    @Id
    private Long id;

    @NonNull
    @Size(min = 20, max = 500)
    @ToString.Exclude
    private String comment;

    @NonNull
    @Size(min = 0, max = 5)
    @ToString.Exclude
    private Integer star;
}
