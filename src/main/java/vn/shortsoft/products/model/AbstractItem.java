package vn.shortsoft.products.model;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OrderColumn;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@MappedSuperclass
public class AbstractItem {
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


    @PrePersist
    private void setAuditColumns(){
        this.createdDate = new Timestamp(new Date().getTime());
        this.updatedDate = new Timestamp(new Date().getTime());
    }
    
    @PreUpdate
    private void updateAuditColumn(){
        this.updatedDate = new Timestamp(new Date().getTime());
    }
    



}
