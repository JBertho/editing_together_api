package fr.esgi.pa.editing_together_api.app.auth.infrastructure.dtos.response;

import lombok.Data;

@Data
public class MessageResponse {
    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }
}
