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
public class CategoryConfigDTO implements Serializable{
    private String categoryName;
    private String categoryCode;
    private int priority;
}
