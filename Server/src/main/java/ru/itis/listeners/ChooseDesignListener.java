package ru.itis.listeners;

import ru.itis.general.entities.Car;
import ru.itis.general.entities.Player;
import ru.itis.general.helpers.CarParser;
import ru.itis.general.helpers.ObjectParser;
import ru.itis.listeners.general.AbstractServerEventListener;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class ChooseDesignListener extends AbstractServerEventListener {
    private ObjectParser<Car> carParser;

    public ChooseDesignListener(){
        super(Constants.CHOOSE_DESIGN);
        carParser = new CarParser();
    }

    @Override
    public void handle(Connection connection, Message message) {
        Player player = connection.getPlayer();
        Car car = carParser.deserializeObject(message.getData());

        Message toClient;
        if (player.getMoney() < car.getPrice()){
            toClient = new Message(Constants.FAIL_SET_DESIGN,
                    message.getData());
        }else {
            player.setCar(car);
            player.setMoney(player.getMoney() - car.getPrice());

            toClient = new Message(Constants.SUCCESS_SET_DESIGN,
                    message.getData());
        }

        server.sendMessage(connection, toClient);
    }
}

