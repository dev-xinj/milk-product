package vn.shortsoft.products.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component
@Entity
@Table(name = "product_items_category_config")
public class CategoryConfig extends BaseEntity {
    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_code")
    private String categoryCode;

    @Column(name = "priority")
    private int priorityValue;

    // @OneToMany(mappedBy = "categoryConfig", cascade = CascadeType.DETACH)
    // private List<ItemProperties> listItemProperties;

}
