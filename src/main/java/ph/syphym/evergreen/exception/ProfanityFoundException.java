package ph.syphym.evergreen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ph.syphym.evergreen.constant.ErrorMessages;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProfanityFoundException extends RuntimeException{
    public ProfanityFoundException(){
        super(ErrorMessages.PROFANITY_FOUND.getMessage());
    }
}
