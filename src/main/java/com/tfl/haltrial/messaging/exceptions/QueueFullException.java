package com.tfl.haltrial.messaging.exceptions;

public class QueueFullException extends RuntimeException {

    public QueueFullException() {
    }

    public QueueFullException(String message) {
        super(message);
    }

    public QueueFullException(String message, Throwable cause) {
        super(message, cause);
    }
}
