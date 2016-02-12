package ua.skillsup.application.market.api.exceptions;


public class NameAlreadyExistException extends RuntimeException {
    public NameAlreadyExistException(String message){
        super(message);
    }
}

