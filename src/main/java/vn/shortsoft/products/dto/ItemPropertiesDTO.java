package vn.shortsoft.products.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemPropertiesDTO implements Serializable{
    private String madeIn;
    private Float price;
    private String size;
    private String color;
    private String material;
    private CategoryConfigDTO categoryConfigDTO;
    private ItemProductDTO itemProductDto;


}
