package ru.itis.connection;

import ru.itis.exceptions.IllegalMessageTypeException;
import ru.itis.exceptions.IllegalProtocolVersionException;
import ru.itis.general.helpers.MessageParser;
import ru.itis.general.helpers.TextParser;
import ru.itis.gui.GuiManager;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.protocol.MessageInputStream;

import java.io.IOException;

public class MessageListener implements Runnable{
    private MessageInputStream inputStream;
    private Connection connection;
    private GuiManager guiManager;

    public MessageListener(Connection connection, GuiManager gameController) {
        this.inputStream = connection.getInputStream();
        this.connection = connection;
        this.guiManager = gameController;
    }

    @Override
    public void run() {
        Message message = null;
        try {
            while ((message = inputStream.readMessage()) != null){
                switch (message.getType()){
                    case Constants.INVALID_NICKNAME:{
                        guiManager.showInvalidNameTip();
                        break;
                    }
                    case Constants.SUCCESS_NICKNAME:{
                        MessageParser<String> parser = new TextParser();
        //                connection.getPlayer().setNickname(parser.deserializeMessage(message.getData()));
                        guiManager.showEnterRoomButton();
                        break;
                    }
                    case Constants.READY_REQUEST:{
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