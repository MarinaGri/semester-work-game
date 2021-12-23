package ru.itis.listeners;

import ru.itis.general.entities.Player;
import ru.itis.general.helpers.MessageParser;
import ru.itis.general.helpers.TextParser;
import ru.itis.listeners.general.AbstractServerEventListener;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class EntranceServerListener extends AbstractServerEventListener {
    private MessageParser<String> messageParser;

    public EntranceServerListener(){
        super(Constants.ENTRANCE);
        messageParser = new TextParser();
    }

    @Override
    public void handle(Connection connection, Message message){
        String name = messageParser.deserializeMessage(message.getData());

        Message newMessage;
        if (isCorrect(name)) {
            Player player = Player.builder()
                    .id(connection.getId())
                    .status(false)
                    .nickname(name)
                    .money(0)
                    .build();

            connection.setPlayer(player);

            newMessage = new Message(Constants.SUCCESS_NICKNAME);
            server.sendMessage(connection, newMessage);
        } else {
            newMessage = new Message(Constants.INVALID_NICKNAME);
            server.sendMessage(connection, newMessage);
        }
    }

    private boolean isCorrect(String name){
        for (Connection connection: server.getAllConnections()){
            Player player = connection.getPlayer();
            if (player != null && player.getNickname().equals(name)){
                return false;
            }
        }

        return true;
    }
}
