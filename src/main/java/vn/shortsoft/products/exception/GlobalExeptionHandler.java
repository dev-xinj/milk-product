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

    @ExceptionHandler({ ResourceNotFoundException.class, NullPointerException.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse validationExceptionNotFound(Exception e, WebRequest request) {
        return setErrorResponse(e, request, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ MethodNotAllowedExeption.class })
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorResponse methodNotAllowedExeption(Exception e, WebRequest request) {
        return setErrorResponse(e, request, HttpStatus.METHOD_NOT_ALLOWED);
    }

    private ErrorResponse setErrorResponse(Exception e, WebRequest request, HttpStatus http) {
        ErrorResponse error = new ErrorResponse();
        error.setError(http.getReasonPhrase());
        error.setHttpStatus(http.value());
        error.setMessage(e.getMessage());
        error.setPath(request.getDescription(false));
        error.setTimestamp(new Timestamp(new Date().getTime()));
        return error;
    }
}
