package ru.itis.general.helpers;

import ru.itis.general.entities.Car;

public class CarParser implements MessageParser<Car>{
    @Override
    public byte[] serializeMessage(Car object) {
        return new byte[0];
    }

    @Override
    public Car deserializeMessage(byte[] message) {
        return null;
    }
}
