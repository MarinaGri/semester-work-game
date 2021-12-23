package ru.itis.gui;

import ru.itis.general.entities.Car;
import ru.itis.general.entities.Player;
import ru.itis.gui.components.MainJPanel;
import ru.itis.gui.listeners.CarCollisionListener;
import ru.itis.gui.listeners.CoinCollectingListener;
import ru.itis.gui.listeners.MotionListener;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GuiManager {
    private final Window window;
    private MainJPanel mainJPanel;
    private List<Timer> timers;

    public GuiManager(Window window) {
        this.window = window;
        this.mainJPanel = (MainJPanel) window.getMainFrame().getContentPane().getComponent(0);
    }

    public void showInvalidNameTip(){
        mainJPanel.getInputNameJPanel().showInvalidNameTip();
    }

    public void showEnterRoomButton(){
        mainJPanel.showEnterRoomButton();
    }

    public void changePlayersInRoom(List<Player> players){
        mainJPanel.getRoomJPanel().changePlayers(players);
    }

    public void addReadyButton(){
        mainJPanel.getRoomJPanel().addReadyButton();
    }

    public void showCarShop(Player player, List<Car> cars){
        if(cars == null){
            mainJPanel.showCarShop(player, mainJPanel.getCarShopJPanel().getCars());
        } else {
            mainJPanel.showCarShop(player, cars);
        }
    }
    public void showRace(Player player) {
        timers = new ArrayList<>();
        timers.add(new Timer(5, new MotionListener(mainJPanel.getRaceJPanel())));
        timers.add(new Timer(1, new CarCollisionListener(mainJPanel.getRaceJPanel())));
        timers.add(new Timer(1, new CoinCollectingListener(mainJPanel.getRaceJPanel(), player)));

        mainJPanel.showRace();
    }

    public void startTimers() {
        for (Timer timer : timers) {
            timer.start();
        }
    }

    public void stopTimers() {
        for (Timer timer : timers) {
            timer.stop();
        }
    }

    public void showRoundResults(List<Player> players) {
        mainJPanel.showResults(players, false);
    }

    public void showFinalResults(List<Player> players) {
        mainJPanel.showResults(players, true);
    }

    public void showNotEnoughMoney(int price){
        mainJPanel.getCarShopJPanel().showFrame(false, price);
    }
}
