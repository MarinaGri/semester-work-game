package ru.itis.gui.listeners;

import ru.itis.gui.components.RaceJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerForCar implements KeyListener {

    private RaceJPanel panelWithCar;

    public KeyListenerForCar(JPanel panel) {
        this.panelWithCar = (RaceJPanel) panel;
    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {

        if ((event.getKeyCode() == KeyEvent.VK_A)) {
            panelWithCar.setOffsetX(-5);
        } else {
            if ((event.getKeyCode() == KeyEvent.VK_D)) {
                panelWithCar.setOffsetX(5);
            }
        }

        panelWithCar.validate();
        panelWithCar.repaint();
    }


    @Override
    public void keyReleased(KeyEvent event) {

    }
}
