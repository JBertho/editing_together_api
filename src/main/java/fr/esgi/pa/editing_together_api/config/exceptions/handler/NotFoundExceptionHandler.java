package fr.esgi.pa.editing_together_api.config.exceptions.handler;

import fr.esgi.pa.editing_together_api.config.exceptions.http.NotFoundException;
import fr.esgi.pa.editing_together_api.config.exceptions.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class NotFoundExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> on(NotFoundException ex) {
        ErrorResponse response = new ErrorResponse().setCode(404).setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
