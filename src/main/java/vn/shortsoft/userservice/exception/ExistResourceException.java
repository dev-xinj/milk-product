package vn.shortsoft.userservice.exception;

public class ExistResourceException extends RuntimeException {
    public ExistResourceException(String message) {
        super(message);
    }

    public ExistResourceException(String message, Throwable cause) {
        super(message, cause);
    }

}
