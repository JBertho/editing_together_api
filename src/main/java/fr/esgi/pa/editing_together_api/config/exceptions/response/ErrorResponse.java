package fr.esgi.pa.editing_together_api.config.exceptions.response;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class ErrorResponse {
    private int code;
    private String message;

}
