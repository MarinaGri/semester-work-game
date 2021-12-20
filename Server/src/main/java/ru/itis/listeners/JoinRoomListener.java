package ru.itis.listeners;

import com.google.gson.JsonObject;
import ru.itis.general.helpers.JsonParser;
import ru.itis.general.helpers.MessageParser;
import ru.itis.helpers.RoomParser;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;
import ru.itis.server.Room;

import java.util.List;

public class JoinRoomListener extends AbstractServerEventListener{
    protected MessageParser<JsonObject> messageParser;

    public JoinRoomListener(){
        super(Constants.JOIN_ROOM);
        messageParser = new JsonParser();
    }

    @Override
    public void handle(Connection connection, Message message) {
        boolean joined = false;
        Room joinedRoom = null;

        for (Room room: server.getAllRooms()){
            if (!joined && (room.getNumberOfPlayers() < Room.MAX_PLAYERS)){
                room.addPlayer(connection.getPlayer());

                joined = true;
                joinedRoom = room;
            }
        }

        if (!joined){
            List<Room> rooms = server.getAllRooms();
            joinedRoom = new Room(rooms.size() + 1);

            rooms.add(joinedRoom);
        }

        Message toClient = new Message(Constants.SUCCESS_JOIN_ROOM);
        toClient.setData(RoomParser.toJson(joinedRoom).getBytes());

        server.sendMessage(connection, toClient);
    }
}
