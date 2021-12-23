package ru.itis.listeners;

import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class GameStartedListener extends AbstractServerEventListener{

    public GameStartedListener(){
        super(Constants.GAME_STARTED);
    }

    @Override
    public void handle(Connection connection, Message message) {
        try {
            connection.getPlayer().getRoom().setCurrentNumberOfResults(0);
            Thread.sleep(5000);

            Message toClient = new Message(Constants.ROUND_END);
            server.sendMessage(connection, toClient);
        }catch (InterruptedException e){
            server.removeConnection(connection);
        }
    }
}
