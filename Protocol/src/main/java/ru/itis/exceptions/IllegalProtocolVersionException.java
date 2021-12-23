package ru.itis.exceptions;

public class IllegalProtocolVersionException extends RuntimeException{
    public IllegalProtocolVersionException(){
        super();
    }

    public IllegalProtocolVersionException(String message) {
        super(message);
    }

    public IllegalProtocolVersionException(String message, Throwable cause) {
        super(message, cause);
    }
}
