package vn.shortsoft.products.exception;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class ErrorResponse {
    private Timestamp timestamp;
    private int httpStatus;
    private String path;
    private String error;
    private String message;
}
