package ru.itis;

import ru.itis.connection.MessageListener;
import ru.itis.general.entities.Car;
import ru.itis.general.entities.Player;
import ru.itis.gui.GuiManager;
import ru.itis.gui.Window;
import ru.itis.connection.Connection;
import ru.itis.gui.utils.ConnectionWrapper;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Connection connection = ConnectionWrapper.getConnection();
        Window window = new Window();
        GuiManager guiManager = new GuiManager(window);
        MessageListener messageListener = new MessageListener(connection, guiManager);
        Thread game = new Thread(messageListener);
        game.start();
        window.createGUI();


        Car car = Car.builder().color("jvkj").price(10).build();
        Car car1 = Car.builder().color("jvkj").price(20).build();
        Car car2 = Car.builder().color("jvkj").price(30).build();

        List<Car> cars = new ArrayList<>();
        cars.add(car);
        cars.add(car1);
        cars.add(car2);
        guiManager.showCarShop(Player.builder().money(23).build(), cars);
    }
}
