package vn.shortsoft.products.exception;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private Timestamp timestamp;
    private int httpStatus;
    private String path;
    private String error;
    private Object message;
}
