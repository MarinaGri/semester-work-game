package ru.itis.connection;

import ru.itis.exceptions.IllegalMessageTypeException;
import ru.itis.exceptions.IllegalProtocolVersionException;
import ru.itis.general.entities.Player;
import ru.itis.general.helpers.MessageParser;
import ru.itis.general.helpers.ObjectParser;
import ru.itis.general.helpers.PlayerParser;
import ru.itis.general.helpers.TextParser;
import ru.itis.gui.GuiManager;
import ru.itis.protocol.Message;
import ru.itis.protocol.MessageInputStream;

import java.io.IOException;
import java.util.Arrays;

import static ru.itis.protocol.Constants.*;

public class MessageListener implements Runnable{
    private MessageInputStream inputStream;
    private Connection connection;
    private GuiManager guiManager;
    private ObjectParser<Player> playerParser;

    public MessageListener(Connection connection, GuiManager gameController) {
        this.inputStream = connection.getInputStream();
        this.connection = connection;
        this.guiManager = gameController;
        this.playerParser = new PlayerParser();
    }

    @Override
    public void run() {
        Message message = null;
        try {
            while ((message = inputStream.readMessage()) != null){
                switch (message.getType()){
                    case INVALID_NICKNAME:{
                        guiManager.showInvalidNameTip();
                        break;
                    }
                    case SUCCESS_NICKNAME:{
                        MessageParser<String> parser = new TextParser();
                        connection.getPlayer().setNickname(parser.deserializeMessage(message.getData()));
                        guiManager.showEnterRoomButton();
                        break;
                    }
                    case SUCCESS_JOIN_ROOM:{
                        System.out.println(message.toString());
                        guiManager.changePlayersInRoom(playerParser.deserializeObjects(message.getData()));
                        break;
                    }
                    case READY_REQUEST:{
                        guiManager.addReadyButton();
                        break;
                    }

                }
            }
        }catch (IllegalMessageTypeException | IOException | IllegalProtocolVersionException e) {
            connection.closeConnection();
        }
    }
}
