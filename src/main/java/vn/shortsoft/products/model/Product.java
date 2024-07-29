package vn.shortsoft.products.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Formula;
import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@DynamicUpdate
@Table(name = "prod_product")
// @NamedEntityGraph(name = "Product.prodQuestions", attributeNodes = {
// @NamedAttributeNode("prodQuestions"),
// @NamedAttributeNode("prodReviews")
// })
public class Product extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Column(name = "brand")
    private String brand;

    @Column(name = "mfg_date")
    private Timestamp mfgDate;

    @Column(name = "properties", columnDefinition = "TEXT")
    private String properties;

    @Transient
    private Integer totalSale = 0;

    @Transient
    private Integer totalReview = 0;

    @Transient
    private Double avgReview = 0d;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Set<ProdReview> prodReviews;

    @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    private Set<ProdQuestion> prodQuestions;

    // @OneToMany(mappedBy = "product", cascade = CascadeType.REMOVE)
    // private Set<ProdSale> prodSales;

    public void addProdReviews(ProdReview prodReview) {
        if (prodReview != null) {
            if (prodReviews == null) {
                prodReviews = new HashSet<>();
            }
            prodReviews.add(prodReview);
            prodReview.setProduct(this);
        }

    }
}
