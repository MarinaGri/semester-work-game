package ru.itis.exceptions;

public class ConnectionIsBrokenException extends RuntimeException{
    public ConnectionIsBrokenException() {
    }

    public ConnectionIsBrokenException(String message) {
        super(message);
    }

    public ConnectionIsBrokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectionIsBrokenException(Throwable cause) {
        super(cause);
    }
}
