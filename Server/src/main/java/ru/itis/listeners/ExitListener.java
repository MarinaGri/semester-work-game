package ru.itis.listeners;

import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class ExitListener extends AbstractServerEventListener{

    public ExitListener(){
        super(Constants.EXIT);
    }

    @Override
    public void handle(Connection connection, Message message) {
        server.removeConnection(connection);
    }
}
