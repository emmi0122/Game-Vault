package se.yrgo.exception;

/**
 * Exception thrown when a login attempt fails due to invalid credentials.
 * This may occur if the username or password is incorrect, or if
 * required login data is missing.
 */
public class InvalidLoginException extends RuntimeException{
    /**
     * Constructs a new {@code InvalidLoginException} with the specified
     * detail message and cause.
     *
     * @param message a detailed error message describing the failure
     * @param cause the underlying cause of the exception
     */
    public InvalidLoginException(String message, Throwable cause) {
        super(message, cause);
    }
}
