package vn.shortsoft.products.dto;

import java.io.Serializable;
import java.util.List;


import lombok.Builder;
import lombok.Getter;
import vn.shortsoft.products.model.User;

@Getter
@Builder
public class ItemProductDTO implements Serializable{
    private String itemName;
    private String itemCode;
    private Long userId;
    private String type;
    private String sku;
    private User user;
    private List<ItemPropertiesDTO> listItemPropertiesDto;

}
