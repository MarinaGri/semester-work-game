package ru.itis.server;

import lombok.Data;
import ru.itis.general.entities.Player;
import ru.itis.protocol.MessageInputStream;
import ru.itis.protocol.MessageOutputStream;

import java.io.IOException;
import java.net.Socket;

@Data
public class Connection {
    protected int id;

    protected IServer server;
    protected Socket socket;

    protected MessageInputStream inputStream;
    protected MessageOutputStream outputStream;

    protected Player player;

    public Connection(IServer server, Socket socket, int id) throws IOException{
        this.id = id;
        this.server = server;
        this.socket = socket;
        inputStream = new MessageInputStream(socket.getInputStream());
        outputStream = new MessageOutputStream(socket.getOutputStream());
    }
}
