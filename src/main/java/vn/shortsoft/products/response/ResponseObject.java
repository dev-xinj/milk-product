package vn.shortsoft.products.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseObject {
    private String status;
    private String message;
    private Object data;

    public ResponseObject(String status, String message) {
        this.status = status;
        this.message = message;
    }
}
