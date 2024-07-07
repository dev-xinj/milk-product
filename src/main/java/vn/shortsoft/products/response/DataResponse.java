package vn.shortsoft.products.response;

import java.io.Serializable;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DataResponse implements Serializable{
    Integer code;
    String status;
    String message;
    Object data;

    public DataResponse(Integer code, String status, String message) {
        this.status = status;
        this.message = message;
    }
}
