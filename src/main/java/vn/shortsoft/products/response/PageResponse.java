package vn.shortsoft.products.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageResponse<T> implements Serializable {
    private Integer pageNo;
    private Integer pageSize;
    private Integer pageTotal;
    private T data;
}
