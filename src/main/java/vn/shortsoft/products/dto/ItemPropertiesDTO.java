package vn.shortsoft.products.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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
