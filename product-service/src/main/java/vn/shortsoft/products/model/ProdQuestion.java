package vn.shortsoft.products.model;

import java.sql.Timestamp;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.shortsoft.products.enums.TypeQuestion;

@Data
@SuperBuilder
@Component
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prod_question")
public class ProdQuestion extends BaseEntity {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "type")
    private TypeQuestion type;

    @Column(name = "date_send")
    private Timestamp dateSend;

    @Column(name = "text_send", columnDefinition = "TEXT", length = 500)
    private String textSend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prod_id")
    private Product product;

}
