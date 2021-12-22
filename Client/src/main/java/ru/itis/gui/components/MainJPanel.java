package ru.itis.gui.components;

import lombok.Getter;
import ru.itis.general.entities.Player;
import ru.itis.gui.utils.GuiConst;
import ru.itis.gui.utils.Loader;

import javax.swing.*;
import java.awt.*;
import java.lang.management.PlatformLoggingMXBean;

@Getter
public class MainJPanel extends JPanelWithBackground{

    private InputNameJPanel inputNameJPanel;
    private RoomJPanel roomJPanel;
    private CarShopJPanel carShopJPanel;
    private RaceJPanel raceJPanel;

    public MainJPanel(Image image) {
        super(image);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        inputNameJPanel = new InputNameJPanel();
        roomJPanel = new RoomJPanel();
        raceJPanel = new RaceJPanel(Loader.loadImg(GuiConst.RACE_BACKGROUND));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(dimension.height/10));
        this.add(inputNameJPanel);
    }

    public void showEnterRoomButton(){
        this.remove(1);
        this.add(this.getRoomJPanel(), 1);

        this.validate();
        this.repaint();
    }

    public void showCarShop(Player player){
        this.remove(1);
        this.carShopJPanel = new CarShopJPanel(player);
        this.add(carShopJPanel, 1);

        this.validate();
        this.repaint();
    }

    public void showRace() {
        this.remove(1);
        this.add(raceJPanel, 1);

        this.validate();
        this.repaint();
    }
}
