package ph.syphym.evergreen.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ErrorResponseDTO {
    private String code;
    private String message;

    public ErrorResponseDTO(String message, String code) {
        this.message = message;
        this.code = code;
    }
}