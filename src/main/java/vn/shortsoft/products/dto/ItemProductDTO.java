package vn.shortsoft.products.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import vn.shortsoft.products.model.BaseEntity;
import vn.shortsoft.products.model.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ItemProductDTO extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    private String itemName;
    private String itemCode;
    private Long userId;
    private String type;
    private String sku;
    private Integer totalNumber;
    private Integer purchaseNumber;
    private Integer seeNumber;
    private Integer likeNumber;
    private String description;
    private User user;
    private List<ItemPropertiesDTO> listItemPropertiesDto;



}
