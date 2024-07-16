package com.tfl.haltrial.services.exceptions;

public class CannotSendMessageException extends RuntimeException {

    public CannotSendMessageException() {
    }

    public CannotSendMessageException(String message) {
        super(message);
    }

    public CannotSendMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
