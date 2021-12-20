package ru.itis.app;

import ru.itis.helpers.RoomParser;
import ru.itis.listeners.EntranceServerListener;
import ru.itis.server.Connection;
import ru.itis.server.IServer;
import ru.itis.server.Room;
import ru.itis.server.Server;

import java.rmi.ServerException;

public class ServerApp {
    public static void main(String[] args) {
        IServer server = new Server(8080);
        try {
            server.start();
        }catch (ServerException e){
            throw new IllegalStateException(e);
        }
        server.registerListener(new EntranceServerListener());
    }
}
