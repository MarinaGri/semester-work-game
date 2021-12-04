package ru.itis.server;

import lombok.Getter;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Getter
public class Connection {
    protected int id;

    protected IServer server;
    protected Socket socket;
    protected InputStream inputStream;
    protected OutputStream outputStream;

    public Connection(IServer server, Socket socket, int id) throws IOException{
        this.id = id;
        this.server = server;
        this.socket = socket;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
    }
}
