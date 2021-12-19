package ru.itis.gui.components;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
@Getter
public class MainJPanel extends JPanelWithBackground{

    private CenterJPanel centerJPanel;
    private RoomJPanel roomJPanel;

    public MainJPanel(Image image) {
        super(image);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        centerJPanel = new CenterJPanel();
        roomJPanel = new RoomJPanel();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(Box.createVerticalStrut(dimension.height/10));
        this.add(centerJPanel);
    }
}
