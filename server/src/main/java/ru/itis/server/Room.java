package ru.itis.server;

import ru.itis.exceptions.ConnectionIsBrokenException;
import ru.itis.listeners.IServerEventListener;

import java.net.ServerSocket;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

public class Room implements IServer{

    protected static final int MAX_PLAYERS = 6;
    protected boolean started;

    protected ServerSocket serverRoom;
    protected List<Connection> connections;

    public Room(){
        started = true;
        connections = new ArrayList<>();
    }

    @Override
    public void start() throws ServerException {

    }

    @Override
    public void registerListener(IServerEventListener listener) {

    }

    @Override
    public void registerListener(List<IServerEventListener> listeners) {

    }

    @Override
    public void sendMessage(int connectionId, Message message) throws ConnectionIsBrokenException {

    }

    @Override
    public void sendBroadCastMessage(Message message) throws ConnectionIsBrokenException {

    }
}
