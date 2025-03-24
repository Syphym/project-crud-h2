package ph.syphym.evergreen.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import ph.syphym.evergreen.constant.ErrorMessages;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super(ErrorMessages.PRODUCT_NOT_FOUND.getMessage());
    }
}
