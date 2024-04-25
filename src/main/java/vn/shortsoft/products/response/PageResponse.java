package vn.shortsoft.products.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PageResponse<T> implements Serializable {
    private Integer pageNo;
    private Integer pageSize;
    private Integer pageTotal;
    private T data;
}
