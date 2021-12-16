package ru.itis.server;

import ru.itis.exceptions.ConnectionIsBrokenException;
import ru.itis.exceptions.IllegalMessageTypeException;
import ru.itis.exceptions.IllegalProtocolVersionException;
import ru.itis.exceptions.ServerAlreadyStartException;
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

            Message message;
            try {
                message = connection.getInputStream().readMessage();
            }catch (IllegalProtocolVersionException e){
                message = new Message(Constants.ERROR, "Error in version of protocol".getBytes());
                connection.getOutputStream().writeMessage(message);
            }catch (IllegalMessageTypeException e){
                message = new Message(Constants.ERROR, "Error in type of message".getBytes());
                connection.getOutputStream().writeMessage(message);
            }

            System.out.println("New message:");
            System.out.println(message.toString());

            for(IServerEventListener listener : listeners){
                if(message.getType() == listener.getType()){
                    // One by one! Another left listeners will wait current
                    // Another thread could be created here or before for every Listener
                    listener.setParameters(connection.getId(), message);
                    new Thread(listener).start();
                }
            }
        }
        catch(IOException ex){
            throw new ServerException("Problem with handling connection.", ex);
        }
    }

    @Override
    public void registerListener(IServerEventListener listener) {
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
    public void sendMessage(int connectionId, Message message) throws ConnectionIsBrokenException {
    }

    @Override
    public void sendBroadCastMessage(Message message) throws ConnectionIsBrokenException {

    }

    public static Connection getConnectionById(Integer id){
        for (Connection connection: connections){
            if (connection.getId() == id){
                return connection;
            }
        }

        return null;
    }
}
