package ru.itis.listeners;

import ru.itis.general.entities.Room;
import ru.itis.listeners.general.AbstractServerEventListener;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class GameStartedListener extends AbstractServerEventListener {

    public GameStartedListener(){
        super(Constants.GAME_STARTED);
    }

    @Override
    public void handle(Connection connection, Message message) {
        try {
            Room room = connection.getPlayer().getRoom();
            room.getCurrentNumberOfResults().set(0);

            Thread.sleep(15000);

            Message toClient = new Message(Constants.ROUND_END);
            server.sendMessage(connection, toClient);
        }catch (InterruptedException e){
            server.removeConnection(connection);
        }
    }
}
