package fr.esgi.pa.editing_together_api.app.auth.domain.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice

public class AlreadyCreatedExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(AlreadyCreatedException.class)
    public ResponseEntity<ErrorResponse> on(AlreadyCreatedException ex) {
        ErrorResponse response = new ErrorResponse().setCode(304).setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_MODIFIED);
    }
}
