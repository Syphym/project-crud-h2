package ph.syphym.evergreen.config.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "profanityfilter")
public class ProfanityFilterProperty {
    private String baseUrl;
    private String apiKey;
}
