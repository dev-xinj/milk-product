package vn.shortsoft.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.sql.Timestamp;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class, NullPointerException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse validationExceptionNotFound(Exception e, WebRequest request) {
        return setErrorResponse(e, request, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler({BindException.class})
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorResponse bindException(MethodArgumentNotValidException e, WebRequest request) {
        return processErrors(e, request);
    }

    @ExceptionHandler({MethodNotAllowedExeption.class})
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

    private ErrorResponse processErrors(MethodArgumentNotValidException e, WebRequest request) {
        List<String> validationErrorMessages = new ArrayList<String>();
        ErrorResponse error = new ErrorResponse();
        error.setError("VALIDATION");
        error.setHttpStatus(1400);
        error.setPath(request.getDescription(false));
        error.setTimestamp(new Timestamp(new Date().getTime()));
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            validationErrorMessages.add(MessageFormat.format(fieldError.getDefaultMessage(), fieldError.getField()));

        }
        error.setMessage(validationErrorMessages);
        return error;
    }
}
