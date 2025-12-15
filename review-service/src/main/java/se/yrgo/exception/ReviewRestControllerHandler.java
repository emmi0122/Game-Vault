package se.yrgo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class ReviewRestControllerHandler {
    @ExceptionHandler(ReviewNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleException() {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                "Review not found",
                Instant.now()
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReviewCreationException.class)
    public ResponseEntity<ErrorMessage> handleCreationException() {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Couldn't save review",
                Instant.now()
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ProfileNotFound.class)
    public ResponseEntity<ErrorMessage> handleProfileNorFound() {
        ErrorMessage errorMessage = new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                "Profile not found, please try again",
                Instant.now()
        );
        return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
    }
}
