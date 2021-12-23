package ru.itis.server;

import ru.itis.general.entities.Car;
import ru.itis.general.entities.Room;
import ru.itis.listeners.general.IServerEventListener;
import ru.itis.protocol.Message;

import java.rmi.ServerException;
import java.util.List;

public interface IServer {
    public void start() throws ServerException;

    public void registerListener(IServerEventListener listener);
    public void registerListener(List<IServerEventListener> listeners);

    public void sendMessage(Connection connection, Message message);
    public void sendMulticastMessage(Room room, Message message);

    public void removeConnection(Connection connection);
    public Connection getConnectionById(Integer id);
    public List<Connection> getAllConnections();

    public List<Room> getAllRooms();
    public Room createRoom();
    public void removeRoom(Room room);

    public List<Car> getAvailableCars();
}
