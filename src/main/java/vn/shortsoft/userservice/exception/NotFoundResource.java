package vn.shortsoft.userservice.exception;

public class NotFoundResource extends RuntimeException {

    public NotFoundResource(String message) {
        super(message);
    }

    public NotFoundResource(String message, Throwable cause) {
        super(message, cause);
    }
}
