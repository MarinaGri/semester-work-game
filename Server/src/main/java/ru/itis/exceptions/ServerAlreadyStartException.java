package ru.itis.exceptions;

public class ServerAlreadyStartException extends RuntimeException{
    public ServerAlreadyStartException() {
    }

    public ServerAlreadyStartException(String message) {
        super(message);
    }

    public ServerAlreadyStartException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServerAlreadyStartException(Throwable cause) {
        super(cause);
    }
}

