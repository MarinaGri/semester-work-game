package ru.itis.server;

import lombok.Data;
import ru.itis.exceptions.IllegalMessageTypeException;
import ru.itis.exceptions.IllegalProtocolVersionException;
import ru.itis.general.entities.Player;
import ru.itis.listeners.AbstractServerEventListener;
import ru.itis.listeners.IServerEventListener;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.protocol.MessageInputStream;
import ru.itis.protocol.MessageOutputStream;

import java.io.IOException;
import java.net.Socket;
import java.util.List;

@Data
public class Connection implements Runnable{
    protected int id;

    protected IServer server;
    protected Socket socket;

    protected MessageInputStream inputStream;
    protected MessageOutputStream outputStream;

    protected Player player;

    protected List<IServerEventListener> listeners;

    public Connection(IServer server, Socket socket, int id) throws IOException{
        this.id = id;
        this.server = server;
        this.socket = socket;
        inputStream = new MessageInputStream(socket.getInputStream());
        outputStream = new MessageOutputStream(socket.getOutputStream());
        listeners = server.getListeners();
    }

    @Override
    public void run() {
        try {
            while (inputStream.readMessage() != null) {
                Message message;
                try {
                    message = inputStream.readMessage();
                } catch (IllegalProtocolVersionException e) {
                    message = new Message(Constants.ERROR, e.getMessage().getBytes());
                    outputStream.writeMessage(message);
                } catch (IllegalMessageTypeException e) {
                    message = new Message(Constants.ERROR, e.getMessage().getBytes());
                    outputStream.writeMessage(message);
                }

                IServerEventListener listener = AbstractServerEventListener.getEventListener(
                        message.getType());
                listener.handle(this, message);
            }
        }catch (IOException e){
            server.removeConnection(this);
        }
    }
}
