package se.yrgo.exception;

public class ProfileNotFound extends RuntimeException {
    public ProfileNotFound(String message) {
        super(message);
    }
}
