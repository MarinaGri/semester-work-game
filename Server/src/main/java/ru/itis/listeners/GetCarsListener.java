package ru.itis.listeners;

import ru.itis.general.entities.Car;
import ru.itis.general.helpers.CarParser;
import ru.itis.general.helpers.ObjectParser;
import ru.itis.listeners.general.AbstractServerEventListener;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

public class GetCarsListener extends AbstractServerEventListener {
    private ObjectParser<Car> carParser;

    public GetCarsListener(){
        super(Constants.GET_CARS);
        carParser = new CarParser();
    }

    @Override
    public void handle(Connection connection, Message message) {
        Message toClient = new Message(Constants.SEND_CARS,
                carParser.serializeObject(server.getAvailableCars()));

        server.sendMessage(connection, toClient);
    }
}

