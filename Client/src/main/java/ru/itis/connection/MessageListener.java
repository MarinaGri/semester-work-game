package ru.itis.connection;

import ru.itis.exceptions.IllegalMessageTypeException;
import ru.itis.exceptions.IllegalProtocolVersionException;
import ru.itis.general.entities.Car;
import ru.itis.general.entities.Player;
import ru.itis.general.helpers.*;
import ru.itis.gui.GuiManager;

import ru.itis.gui.utils.ConnectionWrapper;
import ru.itis.protocol.Message;
import ru.itis.protocol.MessageInputStream;

import javax.swing.*;
import java.io.IOException;

import static ru.itis.protocol.Constants.*;

public class MessageListener implements Runnable{
    private MessageInputStream inputStream;
    private Connection connection;
    private GuiManager guiManager;
    private MessageParser<String> parser;
    private ObjectParser<Player> playerParser;
    private ObjectParser<Car> carParser;

    public MessageListener(Connection connection, GuiManager gameController) {
        this.inputStream = connection.getInputStream();
        this.connection = connection;
        this.guiManager = gameController;
        this.parser = new TextParser();
        this.playerParser = new PlayerParser();
        this.carParser = new CarParser();
    }

    @Override
    public void run() {
        Message message;
        try {
            while ((message = inputStream.readMessage()) != null){
                switch (message.getType()){
                    case INVALID_NICKNAME:{
                        guiManager.showInvalidNameTip();
                        break;
                    }
                    case SUCCESS_NICKNAME:{
                        Player player = connection.getPlayer();
                        player.setNickname(parser.deserializeMessage(message.getData()));
                        player.setMoney(0);
                        guiManager.showEnterRoomButton();
                        break;
                    }
                    case SUCCESS_JOIN_ROOM:
                    case SUCCESS_READY:
                    case SUCCESS_EXIT_ROOM: {
                        guiManager.changePlayersInRoom(playerParser.deserializeObjects(message.getData()));
                        break;
                    }
                    case READY_REQUEST: {
                        guiManager.addReadyButton();
                        break;
                    }
                    case SEND_CARS:{
                        guiManager.showCarShop(connection.getPlayer(), carParser.deserializeObjects(message.getData()));
                        break;
                    }
                    case GAME_OVER:{
                        guiManager.showRoundResults(playerParser.deserializeObjects(message.getData()));
                        break;
                    }
                    case FINAL_GAME_OVER:{
                        guiManager.showFinalResults(playerParser.deserializeObjects(message.getData()));
                        break;
                    }
                    case FAIL_SET_DESIGN:{
                        guiManager.showNotEnoughMoney(carParser.deserializeObject(message.getData()).getPrice());
                        break;
                    }
                    case SUCCESS_SET_DESIGN:{
                        Player player = connection.getPlayer();
                        Car car = carParser.deserializeObject(message.getData());
                        player.setMoney(player.getMoney() - car.getPrice());
                        player.setCar(car);
                        guiManager.changeCarColor(player.getCar().getCarColor(), player.getCar().getWheelColor());
                        guiManager.showCarShop(player, null);
                        break;
                    }
                    case ALL_READY: {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            JOptionPane.showInternalMessageDialog(null, "Не удалось запустить игру");
                        }
                        connection.sendMessage(new Message(GAME_STARTED));
                        guiManager.showRace(connection.getPlayer());
                        guiManager.startTimers();
                        break;
                    }
                    case YOU_LOOSER: {
                        guiManager.showPaneForLooser();
                    }
                    case ROUND_END: {
                        guiManager.showRoundEnd();
                    }
                }
            }
        }catch (IllegalMessageTypeException | IOException | IllegalProtocolVersionException e) {
            connection.closeConnection();
        }
    }
}

