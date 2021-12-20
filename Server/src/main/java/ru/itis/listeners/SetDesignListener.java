package ru.itis.listeners;

import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class SetDesignListener extends AbstractServerEventListener{

    public SetDesignListener(){
        super(Constants.SET_DESIGN);
    }

    @Override
    public void handle(Connection connection, Message message) {

    }
}
