package ru.itis.server;

import ru.itis.listeners.IServerEventListener;
import ru.itis.protocol.Message;

import java.rmi.ServerException;
import java.util.List;

public interface IServer {
    public void start() throws ServerException;

    public void registerListener(IServerEventListener listener);
    public void registerListener(List<IServerEventListener> listeners);

    public void sendMessage(Connection connection, Message message);
    public void sendBroadCastMessage(Message message);

    public void removeConnection(Connection connection);
    public Connection getConnectionById(Integer id);
    public List<Connection> getAllConnections();

    public List<Room> getAllRooms();

    public List<IServerEventListener> getListeners();
}
