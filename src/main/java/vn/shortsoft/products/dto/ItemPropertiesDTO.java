package vn.shortsoft.products.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemPropertiesDTO implements Serializable{
    private static final long serialVersionUID = 1L;
    private String madeIn;
    private Float price;
    private String size;
    private String color;
    private Integer totalNumber;
    private String material;
    private CategoryConfigDTO categoryConfigDTO;
    private ItemProductDTO itemProductDto;


}
