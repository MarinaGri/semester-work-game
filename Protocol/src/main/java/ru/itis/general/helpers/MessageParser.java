package ru.itis.general.helpers;

public interface MessageParser<T> {
    byte[] serializeMessage(T object);
    T deserializeMessage(byte[] message);
}
