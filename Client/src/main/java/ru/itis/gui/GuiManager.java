package ru.itis.gui;

import ru.itis.general.entities.Player;
import ru.itis.gui.components.MainJPanel;

import java.awt.*;
import java.util.List;

public class GuiManager {
    private final Window window;
    private MainJPanel mainJPanel;

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

    public void showCarShop(Player player){
        mainJPanel.showCarShop(player);
    }
    public void showRace() {
        mainJPanel.showRace();
    }

}
