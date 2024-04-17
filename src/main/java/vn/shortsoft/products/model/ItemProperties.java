package vn.shortsoft.products.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "product_items_properties")
public class ItemProperties extends AbstractItem {

    @Column(name = "made_in")
    private String madeIn;

    @Column(name = "price")
    private Float price;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;

    @Column(name = "material")
    private String material;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "category_id", unique = false)
    private CategoryConfig categoryConfig;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_item_id", unique = false)
    private ItemProduct itemProduct;
}
