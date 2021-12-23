package ru.itis.listeners;

import ru.itis.general.entities.Player;
import ru.itis.general.entities.Room;
import ru.itis.general.helpers.ObjectParser;
import ru.itis.general.helpers.PlayerParser;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class JoinRoomListener extends AbstractServerEventListener{
    protected ObjectParser<Player> playerParser;

    public JoinRoomListener(){
        super(Constants.JOIN_ROOM);
        playerParser = new PlayerParser();
    }

    @Override
    public void handle(Connection connection, Message message) {
        boolean joined = false;
        Room joinedRoom = null;
        Player player = connection.getPlayer();

        for (Room room: server.getAllRooms()){
            if (!joined && (room.getNumberOfPlayers() < Room.MAX_PLAYERS)){
                room.addPlayer(player);
                player.setRoom(room);

                if (room.getNumberOfPlayers() == Room.MAX_PLAYERS){
                    server.sendMulticastMessage(room, new Message(Constants.READY_REQUEST));
                }

                joined = true;
                joinedRoom = room;
            }
        }

        if (!joined){
            joinedRoom = server.createRoom();
            joinedRoom.addPlayer(player);
            player.setRoom(joinedRoom);
        }

        Message toClient = new Message(Constants.SUCCESS_JOIN_ROOM,
                playerParser.serializeObject(joinedRoom.getPlayers()));

        server.sendMulticastMessage(joinedRoom, toClient);
    }
}
