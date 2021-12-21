package ru.itis.listeners;

import ru.itis.general.entities.Player;
import ru.itis.general.entities.Room;
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
        Player player = connection.getPlayer();
        Room room = player.getRoom();
        player.setStatus(true);

        if (room.allReady()){
            Message toClient = new Message(Constants.ALL_READY);
            server.sendBroadCastMessage(room, toClient);
        }
    }
}
