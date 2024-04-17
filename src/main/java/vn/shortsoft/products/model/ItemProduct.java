package vn.shortsoft.products.model;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
@Entity
@Table(name = "product_items")
public class ItemProduct extends AbstractItem {

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "type")
    private String type;

    @Column(name = "sku")
    private String sku;

    @OneToMany(mappedBy = "itemProduct", cascade = CascadeType.ALL)
    private List<ItemProperties> listItemProperties;

}
