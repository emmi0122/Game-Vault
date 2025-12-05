package se.yrgo.exception;

public class ReviewCreationException extends RuntimeException {
    public ReviewCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
