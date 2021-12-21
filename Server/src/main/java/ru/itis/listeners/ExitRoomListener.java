package ru.itis.listeners;

import ru.itis.general.entities.Room;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class ExitRoomListener extends AbstractServerEventListener{
    public ExitRoomListener(){
        super(Constants.EXIT_ROOM);
    }
    @Override
    public void handle(Connection connection, Message message) {
        Room playerRoom = connection.getPlayer().getRoom();

        playerRoom.deletePlayer(connection.getPlayer());
        connection.getPlayer().exitRoom();

        //success_delete? отправлять на клиент сообщение, что все ок
    }
}
