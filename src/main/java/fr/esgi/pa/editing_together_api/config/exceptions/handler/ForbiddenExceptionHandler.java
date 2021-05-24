package fr.esgi.pa.editing_together_api.config.exceptions.handler;

import fr.esgi.pa.editing_together_api.config.exceptions.http.ForbiddenException;
import fr.esgi.pa.editing_together_api.config.exceptions.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ForbiddenExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorResponse> on(ForbiddenException ex) {
        ErrorResponse response = new ErrorResponse().setCode(403).setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
