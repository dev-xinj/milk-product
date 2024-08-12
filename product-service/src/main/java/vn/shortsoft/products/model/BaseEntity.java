package vn.shortsoft.products.model;

import java.io.Serializable;
import java.sql.Timestamp;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OrderColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.shortsoft.products.listener.BaseListener;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Component
@MappedSuperclass
@RedisHash
@EntityListeners(BaseListener.class)
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "created_by")
    @OrderColumn
    private String createdBy;

    @Column(name = "created_date")
    @OrderColumn
    private Timestamp createdDate;

    @Column(name = "updated_by")
    @OrderColumn
    private String updatedBy;

    @Column(name = "updated_date")
    @OrderColumn
    private Timestamp updatedDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "status")
    private String status;

}
