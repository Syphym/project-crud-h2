package ph.syphym.evergreen.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfanityFilterDTO {
    public String original;
    public String censored;
    @JsonProperty("has_profanity")
    public boolean hasProfanity;
}
