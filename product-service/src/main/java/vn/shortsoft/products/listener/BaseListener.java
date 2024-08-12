package vn.shortsoft.products.listener;

import java.sql.Timestamp;
import java.util.Date;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import vn.shortsoft.products.model.BaseEntity;

public class BaseListener {
    @PrePersist
    private void setAuditColumns(BaseEntity base) {
        base.setCreatedDate(new Timestamp(new Date().getTime()));
        base.setUpdatedDate(new Timestamp(new Date().getTime()));
    }

    @PreUpdate
    private void updateAuditColumn(BaseEntity base) {
        base.setCreatedDate(new Timestamp(new Date().getTime()));
    }
}
