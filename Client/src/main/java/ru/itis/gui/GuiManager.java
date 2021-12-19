package ru.itis.gui;

import ru.itis.gui.components.MainJPanel;

import javax.swing.*;
import java.awt.*;

public class GuiManager {
    private final Window window;
    private Dimension dimension;

    public GuiManager(Window window) {
        this.window = window;
        this.dimension = Toolkit.getDefaultToolkit().getScreenSize();
    }

    public void showInvalidNameTip(){
        MainJPanel mainJPanel = (MainJPanel) window.getMainFrame().getContentPane().getComponent(0);
        mainJPanel.getCenterJPanel().showInvalidNameTip();
    }

    public void showEnterRoomButton(){
        MainJPanel mainJPanel = (MainJPanel) window.getMainFrame().getContentPane().getComponent(0);
        mainJPanel.remove(1);
        mainJPanel.add(mainJPanel.getRoomJPanel(), 1);
        mainJPanel.validate();
        mainJPanel.repaint();
    }
}
