package ru.itis.listeners;

import ru.itis.general.entities.Player;
import ru.itis.general.entities.Room;
import ru.itis.general.helpers.ObjectParser;
import ru.itis.general.helpers.PlayerParser;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class ExitRoomListener extends AbstractServerEventListener{
    protected ObjectParser<Player> playerParser;

    public ExitRoomListener(){
        super(Constants.EXIT_ROOM);
        playerParser = new PlayerParser();
    }

    @Override
    public void handle(Connection connection, Message message) {
        Room playerRoom = connection.getPlayer().getRoom();

        playerRoom.deletePlayer(connection.getPlayer());
        connection.getPlayer().exitRoom();

        Message toClient = new Message(Constants.SUCCESS_EXIT_ROOM,
                playerParser.serializeObject(playerRoom.getPlayers()));

        server.sendMulticastMessage(playerRoom, toClient);
    }
}
