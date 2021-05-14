package fr.esgi.pa.editing_together_api.config.exceptions.handler;

import fr.esgi.pa.editing_together_api.config.exceptions.http.ConflictException;
import fr.esgi.pa.editing_together_api.config.exceptions.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ConflictExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorResponse> on(ConflictException ex) {
        ErrorResponse response = new ErrorResponse().setCode(409).setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }
}
