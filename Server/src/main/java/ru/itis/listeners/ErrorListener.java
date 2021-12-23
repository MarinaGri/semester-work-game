package ru.itis.listeners;

import ru.itis.general.helpers.MessageParser;
import ru.itis.general.helpers.TextParser;
import ru.itis.listeners.general.AbstractServerEventListener;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class ErrorListener extends AbstractServerEventListener {
    protected MessageParser<String> messageParser;

    public ErrorListener(){
        super(Constants.ERROR);
        messageParser = new TextParser();
    }

    @Override
    public void handle(Connection connection, Message message) {
        //
    }
}
