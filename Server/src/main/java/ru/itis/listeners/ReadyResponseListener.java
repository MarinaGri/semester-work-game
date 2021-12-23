package ru.itis.listeners;

import ru.itis.general.entities.Player;
import ru.itis.general.entities.Room;
import ru.itis.general.helpers.MessageParser;
import ru.itis.general.helpers.ObjectParser;
import ru.itis.general.helpers.PlayerParser;
import ru.itis.general.helpers.TextParser;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class ReadyResponseListener extends AbstractServerEventListener{
    private ObjectParser<Player> playerParser;

    public ReadyResponseListener(){
        super(Constants.READY_RESPONSE);
        playerParser = new PlayerParser();
    }

    @Override
    public void handle(Connection connection, Message message) {
        Player player = connection.getPlayer();
        Room room = player.getRoom();
        player.setStatus(true);

        Message toClient = new Message(Constants.SUCCESS_READY,
                playerParser.serializeObject(room.getPlayers()));
        server.sendMessage(connection, toClient);

        if (room.allReady()){
            Message multicastMessage = new Message(Constants.ALL_READY);
            server.sendMulticastMessage(room, multicastMessage);
        }
    }
}
