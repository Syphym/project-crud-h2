package ph.syphym.evergreen.util;

import org.springframework.stereotype.Component;
import ph.syphym.evergreen.config.property.ProfanityFilterProperty;
import ph.syphym.evergreen.dto.ProductDTO;
import ph.syphym.evergreen.dto.ProfanityFilterDTO;
import ph.syphym.evergreen.exception.ProfanityFoundException;
import ph.syphym.evergreen.integration.ProfanityFilterClient;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ProfanityFilter {

    private final ProfanityFilterClient profanityFilterClient;
    private final ProfanityFilterProperty profanityFilterProperty;

    public ProfanityFilter(ProfanityFilterClient profanityFilterClient, ProfanityFilterProperty profanityFilterProperty) {
        this.profanityFilterClient = profanityFilterClient;
        this.profanityFilterProperty = profanityFilterProperty;
    }

    public void validateProfanityFilter(ProductDTO productDTO){

        List<Optional<String>> fieldsToCheck = Arrays.asList(
                Optional.ofNullable(productDTO.getName()),
                Optional.ofNullable(productDTO.getDescription()),
                Optional.ofNullable(productDTO.getManufacturer())
        );

        boolean hasProfanity = fieldsToCheck.parallelStream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .anyMatch(this::containsProfanity);

        System.out.println("PROFANITY CHECK:" + hasProfanity);

        if (hasProfanity){
            throw new ProfanityFoundException();
        }

    }

    private boolean containsProfanity(String text) {
        ProfanityFilterDTO filterResult = profanityFilterClient.ProfanityFilterService(text, profanityFilterProperty.getApiKey());

        System.out.println("Profanity Result: "  + filterResult);
        return filterResult.hasProfanity;
    }
}
