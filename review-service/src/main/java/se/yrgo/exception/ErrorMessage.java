package se.yrgo.exception;

import java.time.Instant;

public class ErrorMessage {
    private int status;
    private String message;
    private Instant dateAndTime;

    public ErrorMessage(int status, String message, Instant dateAndTime) {
        this.status = status;
        this.message = message;
        this.dateAndTime = dateAndTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Instant getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(Instant dateAndTime) {
        this.dateAndTime = dateAndTime;
    }
}
