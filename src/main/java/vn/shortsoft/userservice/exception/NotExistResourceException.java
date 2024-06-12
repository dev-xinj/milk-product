package vn.shortsoft.userservice.exception;

public class NotExistResourceException extends RuntimeException {

    public NotExistResourceException(String messString) {
        super(messString);
    }

    public NotExistResourceException(String messString, Throwable cause) {
        super(messString, cause);
    }
}
