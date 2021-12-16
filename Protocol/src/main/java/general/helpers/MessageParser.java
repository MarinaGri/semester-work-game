package general.helpers;

import src.protocol.Message;

public interface MessageParser<T> {
    public byte[] serializeMessage(T object);
    public T deserializeMessage(byte[] message);
}
