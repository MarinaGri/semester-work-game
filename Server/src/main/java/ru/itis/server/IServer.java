package ru.itis.server;

import ru.itis.exceptions.ConnectionIsBrokenException;
import ru.itis.listeners.IServerEventListener;
import ru.itis.protocol.Message;

import java.rmi.ServerException;
import java.util.List;

public interface IServer {
    public void start() throws ServerException;
    public void registerListener(IServerEventListener listener);
    public void registerListener(List<IServerEventListener> listeners);
    public void sendMessage(int connectionId, Message message) throws ConnectionIsBrokenException;
    public void sendBroadCastMessage(Message message) throws ConnectionIsBrokenException;
}
