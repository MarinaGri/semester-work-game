package ru.itis.general.helpers;

import ru.itis.general.entities.Car;

import java.util.List;

public class CarParser implements ObjectParser<Car>{
    @Override
    public byte[] serializeObject(Car object) {
        return new byte[0];
    }

    @Override
    public byte[] serializeObject(List<Car> objects) {
        return new byte[0];
    }

    @Override
    public Car deSerializeObject(byte[] object) {
        return null;
    }

    @Override
    public List<Car> deSerializeObjects(byte[] objects) {
        return null;
    }
}
