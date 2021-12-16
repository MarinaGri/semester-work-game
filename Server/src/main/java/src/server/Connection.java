package src.server;

import general.entities.Player;
import lombok.Data;
import lombok.Getter;
import src.protocol.MessageInputStream;
import src.protocol.MessageOutputStream;

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
