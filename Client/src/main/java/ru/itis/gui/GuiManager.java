package ru.itis.gui;

import ru.itis.general.entities.Car;
import ru.itis.general.entities.Player;
import ru.itis.gui.components.MainJPanel;
import ru.itis.gui.listeners.CarCollisionListener;
import ru.itis.gui.listeners.CoinCollectingListener;
import ru.itis.gui.listeners.KeyListenerForCar;
import ru.itis.gui.listeners.MotionListener;

import javax.swing.*;

import java.awt.*;
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

        window.getMainFrame().getContentPane().remove(mainJPanel);
        window.getMainFrame().addKeyListener(new KeyListenerForCar(mainJPanel.getRaceJPanel()));
        window.getMainFrame().getContentPane().add(mainJPanel.getRaceJPanel());
        window.getMainFrame().pack();

        timers = new ArrayList<>();
        timers.add(new Timer(8, new MotionListener(mainJPanel.getRaceJPanel())));
        CarCollisionListener collisionListener = new CarCollisionListener(mainJPanel.getRaceJPanel());
        timers.add(new Timer(1, collisionListener));
        timers.add(new Timer(1, e -> {
            if (collisionListener.hasCollision) {
                stopTimers();
                JOptionPane.showInternalMessageDialog(window.getMainFrame().getContentPane(), "You're loh");
                showWaitingPanel();
            }
        }));
        timers.add(new Timer(1, new CoinCollectingListener(mainJPanel.getRaceJPanel(), player)));

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

    public void showWaitingPanel() {
        window.getMainFrame().getContentPane().remove(mainJPanel.getRaceJPanel());
        window.getMainFrame().getContentPane().add(mainJPanel.getWaitingPanel());
        window.getMainFrame().pack();
    }

    public void showRoundResults(List<Player> players) {
        showResults(players, false);
    }

    public void showFinalResults(List<Player> players) {
        showResults(players, true);
    }

    private void showResults(List<Player> players, boolean flag){
        window.getMainFrame().getContentPane().remove(window.getMainFrame().getContentPane().getComponent(0));
        window.getMainFrame().getContentPane().add(mainJPanel);
        mainJPanel.showResults(players, flag);
    }

    public void showNotEnoughMoney(int price){
        mainJPanel.getCarShopJPanel().showFrame(false, price);
    }

    public void changeCarColor(Color carColor, Color wheelColor) {
            mainJPanel.getRaceJPanel().setCarColor(carColor);
            mainJPanel.getRaceJPanel().setWheelColor(wheelColor);
    }
}
