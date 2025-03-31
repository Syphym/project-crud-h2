package ph.syphym.evergreen.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import ph.syphym.evergreen.dto.ProfanityFilterDTO;

@FeignClient(name = "ProfanityFilter", url = "${profanityfilter.base-url}")
public interface ProfanityFilterClient {
    @GetMapping
    ProfanityFilterDTO ProfanityFilterService(
            @RequestParam("text") String text,
            @RequestHeader("X-Api-Key") String apikey);
}
