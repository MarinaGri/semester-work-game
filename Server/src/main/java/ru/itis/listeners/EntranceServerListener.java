package ru.itis.listeners;

import ru.itis.general.entities.Player;
import ru.itis.general.helpers.MessageParser;
import ru.itis.general.helpers.TextParser;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Server;

public class EntranceServerListener extends AbstractServerEventListener{
    private MessageParser<String> messageParser;

    public EntranceServerListener(){
        super(Constants.ENTRANCE);
        messageParser = new TextParser();
    }

    @Override
    public void setParameters(int id, Message message) {
        connectionId = id;
        this.message = message;
    }

    //    @Override
//    public void handle(int connectionId, Message message) {
//
//    }

    @Override
    public void run() {
        Player player = Player.builder()
                .id(connectionId)
                .nickname(messageParser.deserializeMessage(message.getData()))
                .build();

        Server.getConnectionById(connectionId).setPlayer(player);
    }
}
