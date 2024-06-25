package vn.shortsoft.userservice.exception;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExistResourceException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse existResourceException(Exception e, WebRequest request) {
        ErrorResponse error = setErrorResponse(e, request, HttpStatus.CONFLICT);
        return error;
    }
    @ExceptionHandler(NotExistResourceException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse notExistResourceException(Exception e, WebRequest request) {
        ErrorResponse error = setErrorResponse(e, request, HttpStatus.NOT_FOUND);
        return error;
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse incorrectPasswordException(Exception e, WebRequest request) {
        ErrorResponse error = setErrorResponse(e, request, HttpStatus.BAD_REQUEST);
        return error;
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
