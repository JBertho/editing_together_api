package fr.esgi.pa.editing_together_api.app.auth.domain.exceptions;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
class ErrorResponse {
    private int code;
    private String message;

}
