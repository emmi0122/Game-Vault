package se.yrgo.exception;

/**
 * Exception thrown when a requested profile cannot be found.
 * Typically used when attempting to retrieve a profile
 * that does not exist in the database.
 */
public class ProfileNotFoundException extends RuntimeException {
    /**
     * Constructs a new {@code ProfileNotFoundException} with the specified
     * detail message and cause.
     *
     * @param message a detailed error message
     * @param cause   the underlying cause of the exception
     */
    public ProfileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new {@code ProfileNotFoundException} with the specified
     * detail message.
     *
     * @param message a detailed error message
     */
    public ProfileNotFoundException(String message) {
        super(message);
    }
}
