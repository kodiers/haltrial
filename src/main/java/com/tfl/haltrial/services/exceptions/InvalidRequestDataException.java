package com.tfl.haltrial.services.exceptions;

public class InvalidRequestDataException extends RuntimeException {

    public InvalidRequestDataException() {
    }

    public InvalidRequestDataException(String message) {
        super(message);
    }

    public InvalidRequestDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
