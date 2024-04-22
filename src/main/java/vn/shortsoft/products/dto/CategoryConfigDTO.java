package vn.shortsoft.products.dto;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryConfigDTO implements Serializable{
    private String categoryName;
    private String categoryCode;
    private int priority;
}
