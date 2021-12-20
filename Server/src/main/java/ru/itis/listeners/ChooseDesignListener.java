package ru.itis.listeners;

import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class ChooseDesignListener extends AbstractServerEventListener{
    public ChooseDesignListener(){
        super(Constants.CHOOSE_DESIGN);
    }

    @Override
    public void handle(Connection connection, Message message) {

    }
}
