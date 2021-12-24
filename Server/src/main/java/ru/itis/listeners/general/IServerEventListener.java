package ru.itis.listeners.general;

import ru.itis.protocol.Message;
import ru.itis.server.Connection;
import ru.itis.server.IServer;
public interface IServerEventListener{
    public void init(IServer server);
    public void handle(Connection connection, Message message);
    public byte getType();
}

