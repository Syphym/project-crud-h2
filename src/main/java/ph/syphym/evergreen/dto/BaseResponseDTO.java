package ph.syphym.evergreen.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonPropertyOrder({
        "code",
        "message",
        "data"
})
public class BaseResponseDTO<T> {

    private String code;
    private String message;
    private T data;


}
