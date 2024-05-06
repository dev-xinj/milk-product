package vn.shortsoft.products.dto;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.shortsoft.products.model.User;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemProductDTO implements Serializable {

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
