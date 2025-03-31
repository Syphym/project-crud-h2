package ph.syphym.evergreen.constant;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product Not Found"),
    PROFANITY_FOUND("Profanity Found");

    private final String message;

    ErrorMessages(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
