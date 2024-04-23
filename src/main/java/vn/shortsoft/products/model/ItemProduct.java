package vn.shortsoft.products.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
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
@DynamicUpdate
@Table(name = "product_items")
public class ItemProduct extends BaseEntity {

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "user_id")
    private Long userId;
    
    @Column(name = "type")
    private String type;

    @Column(name = "sku")
    private String sku;

    @OneToMany(mappedBy = "itemProduct", cascade = CascadeType.ALL)
    private List<ItemProperties> listItemProperties;


    public void addItemProperties(ItemProperties itemProperties){
        if(itemProperties != null){
            if(listItemProperties == null){
                listItemProperties = new ArrayList<>();
            }
            listItemProperties.add(itemProperties);
            itemProperties.setItemProduct(this);
        }

    }
    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "product_item_id")
    // private List<ItemProperties> listItemProperties;

}
