package ru.itis.exceptions;

public class IllegalMessageTypeException extends Exception{
    public IllegalMessageTypeException(){
        super();
    }

    public IllegalMessageTypeException(String message) {
        super(message);
    }

    public IllegalMessageTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
