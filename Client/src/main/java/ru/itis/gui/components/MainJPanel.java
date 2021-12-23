package ru.itis.gui.components;

import lombok.Getter;
import ru.itis.general.entities.Car;
import ru.itis.general.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static ru.itis.gui.utils.GuiConst.DIMENSION;

@Getter
public class MainJPanel extends JPanelWithBackground{

    private InputNameJPanel inputNameJPanel;
    private RoomJPanel roomJPanel;
    private CarShopJPanel carShopJPanel;
    private ResultsJPanel resultsJPanel;

    public MainJPanel(Image image) {
        super(image);
        inputNameJPanel = new InputNameJPanel();
        roomJPanel = new RoomJPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(DIMENSION.height/10));
        this.add(inputNameJPanel);
    }

    public void showEnterRoomButton(){
        this.remove(1);
        this.add(this.getRoomJPanel(), 1);

        validateAndRepaint();
    }

    public void showCarShop(Player player, List<Car> cars){
        this.remove(1);
        this.carShopJPanel = new CarShopJPanel(this, player,  cars);
        this.add(carShopJPanel, 1);

        validateAndRepaint();
    }

    public void showResults(List<Player> players, boolean isFinal){
        this.remove(1);
        this.resultsJPanel = new ResultsJPanel(this, players, isFinal);
        this.add(resultsJPanel, 1);

        validateAndRepaint();
    }

    public void validateAndRepaint(){
        this.validate();
        this.repaint();
    }
}
