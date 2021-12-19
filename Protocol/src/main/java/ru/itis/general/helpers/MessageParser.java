package ru.itis.general.helpers;

public interface MessageParser<T> {
    public byte[] serializeMessage(T object);
    public T deserializeMessage(byte[] message);
}
