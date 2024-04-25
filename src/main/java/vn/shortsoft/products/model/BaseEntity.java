package vn.shortsoft.products.model;

import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vn.shortsoft.products.enums.StatusEnum;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {
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
    @UpdateTimestamp
    private Timestamp updatedDate;

    @Column(name = "is_deleted")
    private Boolean isDeleted = false;

    @Column(name = "status")
    private String status;

    @Transient
    private User user = new User();

    @PrePersist
    private void setAuditColumns() {
        this.createdDate = new Timestamp(new Date().getTime());
        this.updatedDate = new Timestamp(new Date().getTime());
        this.status = StatusEnum.ACTIVE.name();
        this.createdBy = user.getUserName();
        this.updatedBy = user.getUserName();
    }

    @PreUpdate
    private void updateAuditColumn() {
        this.updatedDate = new Timestamp(new Date().getTime());
        this.updatedBy = user.getUserName();
    }

}
