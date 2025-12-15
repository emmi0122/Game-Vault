package se.yrgo.exception;

public class ProfileNotFoundException extends RuntimeException{
    public ProfileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
