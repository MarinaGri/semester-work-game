package ru.itis.server;

import ru.itis.exceptions.IllegalMessageTypeException;
import ru.itis.exceptions.IllegalProtocolVersionException;
import ru.itis.exceptions.ServerAlreadyStartException;
import ru.itis.general.entities.Player;
import ru.itis.general.entities.Room;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.listeners.IServerEventListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

public class Server implements IServer{
    protected int port;
    protected boolean started;

    protected List<IServerEventListener> listeners;

    protected ServerSocket server;
    protected static List<Connection> connections;

    protected List<Room> rooms;

    public Server(int port){
        this.port = port;
        started = true;
        listeners = new ArrayList<>();
        connections = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    @Override
    public void start() throws ServerException {
        try{
            server = new ServerSocket(this.port);
            started = true;

            while(true){
                Socket s = server.accept();
                handleConnection(s);
            }
        }
        catch(IOException ex){
            throw new ServerException("Problem with server starting.", ex);
        }
    }

    protected void handleConnection(Socket socket) throws ServerException{
        try {
            Connection connection = new Connection(this, socket, this.connections.size() + 1);
            this.connections.add(connection);

            new Thread(connection).start();
        }
        catch(IOException ex){
            throw new ServerException("Problem with handling connection.", ex);
        }
    }

    @Override
    public void registerListener(IServerEventListener listener){
        if (started){
            throw new ServerAlreadyStartException("Server has been started already. Can't register listener.");
        }

        listener.init(this);
        this.listeners.add(listener);
    }

    @Override
    public void registerListener(List<IServerEventListener> listeners) {
        if (started){
            throw new ServerAlreadyStartException("Server has been started already. Can't register listener.");
        }

        for(IServerEventListener listener: listeners){
            listener.init(this);
        }
        this.listeners.addAll(listeners);
    }

    @Override
    public void sendMessage(Connection connection, Message message){
        try {
            connection.getOutputStream().writeMessage(message);
        }catch (IOException e){
            removeConnection(connection);
        }
    }

    @Override
    public void sendBroadCastMessage(Room room, Message message){
        List<Player> players = room.getPlayers();

        for (Connection connection: connections){
            if (players.contains(connection.getPlayer())){
                sendMessage(connection, message);
            }
        }
    }

    @Override
    public Connection getConnectionById(Integer id){
        for (Connection connection: connections){
            if (connection.getId() == id){
                return connection;
            }
        }

        return null;
    }

    @Override
    public List<Connection> getAllConnections(){
        return connections;
    }

    @Override
    public void removeConnection(Connection connection) {
        for (Connection conn: connections){
            if (conn.getId() == connection.getId()){
                connections.remove(conn);
            }
        }
    }

    @Override
    public List<Room> getAllRooms() {
        return rooms;
    }

    @Override
    public Room createRoom() {
        Room room = new Room();
        rooms.add(room);

        return room;
    }

    @Override
    public void removeRoom(Room room) {
        rooms.remove(room);
    }

    @Override
    public List<IServerEventListener> getListeners() {
        return listeners;
    }
}
