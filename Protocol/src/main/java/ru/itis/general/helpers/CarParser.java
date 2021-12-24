package ru.itis.general.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ru.itis.general.entities.Car;

import java.util.ArrayList;
import java.util.List;

public class CarParser implements ObjectParser<Car> {
    private TextParser textParser;
    private Gson gson;

    public CarParser(){
        textParser = new TextParser();
        gson = new Gson();
    }

    @Override
    public byte[] serializeObject(Car car) {
        return textParser.serializeMessage(gson.toJson(car));
    }

    @Override
    public byte[] serializeObject(List<Car> cars) {
        return textParser.serializeMessage(gson.toJson(cars));
    }

    @Override
    public Car deserializeObject(byte[] car) {
        String text = textParser.deserializeMessage(car);

        return gson.fromJson(text, Car.class);
    }

    @Override
    public List<Car> deserializeObjects(byte[] cars) {
        List<Car> resultCars = new ArrayList<>();
        String text = textParser.deserializeMessage(cars);

        JsonArray array = gson.fromJson(text, JsonArray.class);

        for (JsonElement jsonElement: array){
            Car car = gson.fromJson(jsonElement, Car.class);
            resultCars.add(car);
        }

        return resultCars;
    }
}

