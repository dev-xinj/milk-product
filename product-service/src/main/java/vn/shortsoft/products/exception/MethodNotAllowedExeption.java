package vn.shortsoft.products.exception;

public class MethodNotAllowedExeption extends RuntimeException {

    public MethodNotAllowedExeption(String message) {
        super(message);
    }

    public MethodNotAllowedExeption(String message, Throwable cause) {
        super(message, cause);
    }
}
