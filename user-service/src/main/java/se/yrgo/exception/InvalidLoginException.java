package se.yrgo.exception;

public class InvalidLoginException extends RuntimeException{
    public InvalidLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
