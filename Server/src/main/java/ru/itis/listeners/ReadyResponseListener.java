package ru.itis.listeners;

import ru.itis.general.helpers.MessageParser;
import ru.itis.general.helpers.TextParser;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class ReadyResponseListener extends AbstractServerEventListener{
    protected MessageParser<String> messageParser;

    public ReadyResponseListener(){
        super(Constants.READY_RESPONSE);
        messageParser = new TextParser();
    }

    @Override
    public void handle(Connection connection, Message message) {
        connection.getPlayer().setStatus(true);
    }
}
