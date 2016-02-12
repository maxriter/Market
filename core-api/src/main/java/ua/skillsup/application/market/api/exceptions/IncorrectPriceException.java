package ua.skillsup.application.market.api.exceptions;


public class IncorrectPriceException  extends RuntimeException {
    public IncorrectPriceException(String message){
        super(message);
    }
}
