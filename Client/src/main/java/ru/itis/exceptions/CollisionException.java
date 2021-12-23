package ru.itis.exceptions;

public class CollisionException extends RuntimeException{

    public CollisionException() {
        super();
    }

    public CollisionException(String message) {
        super(message);
    }

    public CollisionException(String message, Throwable cause) {
        super(message, cause);
    }

    public CollisionException(Throwable cause) {
        super(cause);
    }
}
