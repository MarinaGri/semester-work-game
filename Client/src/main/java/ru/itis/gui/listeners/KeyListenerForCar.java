package ru.itis.gui.listeners;

import ru.itis.gui.components.RaceJPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerForCar implements KeyListener {

    private RaceJPanel panelWithCar;
    private Graphics2D carWheel;
    private Graphics2D car;

    public KeyListenerForCar(JPanel panel) {
        this.panelWithCar = (RaceJPanel) panel;
    }

    @Override
    public void keyTyped(KeyEvent event) {

    }

    @Override
    public void keyPressed(KeyEvent event) {
        carWheel = panelWithCar.getCarWheel();
        car = panelWithCar.getCar();
        if ((event.getKeyCode() == KeyEvent.VK_A)) {
            panelWithCar.paintX(-5);
        } else {
            if ((event.getKeyCode() == KeyEvent.VK_D)) {
                panelWithCar.paintX(5);
            }
//            else {
//                if ((event.getKeyCode() == KeyEvent.VK_W)) {
//
//                } else {
//                    if ((event.getKeyCode() == KeyEvent.VK_S)) {
//
//
//                    }
//                }
//            }
        }
        panelWithCar.repaint();
    }


    @Override
    public void keyReleased(KeyEvent event) {

    }
}
