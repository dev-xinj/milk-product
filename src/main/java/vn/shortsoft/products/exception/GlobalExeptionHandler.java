package vn.shortsoft.products.exception;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler({ ResourceNotFoundException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse validationExceptionNotFound(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Timestamp(new Date().getTime()));
        errorResponse.setPath(request.getDescription(false));
        errorResponse.setMessage(e.getMessage());
        errorResponse.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
        errorResponse.setHttpStatus(HttpStatus.NOT_FOUND.value());
        return errorResponse;
    }

    @ExceptionHandler({ NullPointerException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse validationExceptionNull(Exception e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setTimestamp(new Timestamp(new Date().getTime()));
        errorResponse.setPath(request.getDescription(false));
        errorResponse.setMessage(e.getMessage());
        errorResponse.setError(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return errorResponse;
    }

}
