package ru.itis.server;

import lombok.Data;
import ru.itis.exceptions.IllegalMessageTypeException;
import ru.itis.exceptions.IllegalProtocolVersionException;
import ru.itis.general.entities.Player;
import ru.itis.listeners.general.AbstractServerEventListener;
import ru.itis.listeners.general.IServerEventListener;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.protocol.MessageInputStream;
import ru.itis.protocol.MessageOutputStream;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

@Data
public class Connection implements Runnable{
    private static int count = 0;
    protected int id;

    protected IServer server;
    protected Socket socket;

    protected MessageInputStream inputStream;
    protected MessageOutputStream outputStream;

    protected Player player;

    public Connection(IServer server, Socket socket) throws IOException{
        this.id = count++;
        this.server = server;
        this.socket = socket;
        inputStream = new MessageInputStream(socket.getInputStream());
        outputStream = new MessageOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        Message message;

        try {
            try {
                while ((message = inputStream.readMessage()) != null) {
                    IServerEventListener listener = AbstractServerEventListener.getEventListener(
                            message.getType());
                    listener.init(server);

                    System.out.println("From client: " + message.getType());
                    if (player != null || message.getType() == Constants.ENTRANCE) {
                        listener.handle(this, message);
                    }
                }
            } catch (IllegalProtocolVersionException e) {
                message = new Message(Constants.ERROR, e.getMessage().getBytes());
                outputStream.writeMessage(message);
            } catch (IllegalMessageTypeException e) {
                message = new Message(Constants.ERROR, e.getMessage().getBytes());
                outputStream.writeMessage(message);
            }
        }catch (IOException e){
            server.removeConnection(this);
        }
    }
}
