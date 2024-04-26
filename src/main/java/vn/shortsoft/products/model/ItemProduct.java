package vn.shortsoft.products.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "total_number")
    private Integer totalNumber;

    @Column(name = "purchase_number")
    private Integer purchaseNumber;

    @Column(name = "see_number")
    private Integer seeNumber;

    @Column(name = "like_number")
    private Integer likeNumber;

    @Column(name = "description", length = 2500)
    private String description;

    @Column(name = "type")
    private String type;

    @Column(name = "sku")
    private String sku;

    @JsonIgnore
    @OneToMany(mappedBy = "itemProduct", cascade = CascadeType.ALL)
    private List<ItemProperties> listItemProperties;

    public void addItemProperties(ItemProperties itemProperties) {
        if (itemProperties != null) {
            if (listItemProperties == null) {
                listItemProperties = new ArrayList<>();
            }
            listItemProperties.add(itemProperties);
            if (totalNumber == null) {
                totalNumber = 0;
            }
            totalNumber += itemProperties.getTotalNumber();
            itemProperties.setItemProduct(this);
        }

    }
    // @OneToMany(cascade = CascadeType.ALL)
    // @JoinColumn(name = "product_item_id")
    // private List<ItemProperties> listItemProperties;

}
