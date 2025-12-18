package se.yrgo.exception;

/**
 * Exception thrown when a user cannot be found.
 * Commonly used when searching for a user by email or "ID"
 * and no matching result exists.
 */
public class UserNotFoundException extends RuntimeException {

    /**
     * Constructs a new {@code UserNotFoundException} with the specified
     * detail message and cause.
     *
     * @param message a detailed error message
     * @param cause the underlying cause of the exception
     */
    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
