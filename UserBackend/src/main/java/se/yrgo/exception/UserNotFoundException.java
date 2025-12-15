package se.yrgo.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message, Throwable cause){
        super(message, cause);
    }
} 