package ru.itis.connection;

import lombok.Data;
import ru.itis.exceptions.ConnectionLostException;
import ru.itis.general.entities.Player;
import ru.itis.protocol.Message;
import ru.itis.protocol.MessageInputStream;
import ru.itis.protocol.MessageOutputStream;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

@Data
public class Connection {
    private Socket socket;
    private MessageInputStream inputStream;
    private MessageOutputStream outputStream;
    private Player player;

    public Connection(InetAddress address, int port) throws ConnectionLostException {
        try {
            this.socket = new Socket(address, port);
            outputStream = new MessageOutputStream(socket.getOutputStream());
            inputStream = new MessageInputStream(socket.getInputStream());
            player = new Player();
        } catch (IOException ex) {
            throw new ConnectionLostException("", ex);
        }
    }

    public void sendMessage(Message message) throws IOException {
        if(outputStream == null) throw new IOException();
        outputStream.writeMessage(message);
    }

    public void closeConnection(){
        try {
            socket.close();
        } catch (IOException ignored) {}
    }
}
