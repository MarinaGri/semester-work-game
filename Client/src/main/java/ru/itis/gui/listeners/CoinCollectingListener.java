package ru.itis.gui.listeners;

import ru.itis.connection.Connection;
import ru.itis.general.entities.Player;
import ru.itis.gui.components.RaceJPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class CoinCollectingListener implements ActionListener {
    private RaceJPanel racePanel;
    private Player player;

    public CoinCollectingListener(RaceJPanel panel, Player player) {
        this.racePanel = panel;
        this.player = player;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        int x = random.nextInt( racePanel.getWidth()*3/4 + 1 - racePanel.getWidth()/4) + racePanel.getWidth()/4;

        if ((racePanel.getYForCoin() == racePanel.getYForCar()) && ((racePanel.getXForCoin() ) <= racePanel.getXForCar() + racePanel.getWidthCar()/2) && ((racePanel.getXForCoin() ) >= racePanel.getXForCar() - racePanel.getWidthCar()/2)) {
            player.setMoney(player.getMoney() + 1);
            racePanel.setYForCoin(0);
            racePanel.setXForCoin(x);
        }

        racePanel.validate();
        racePanel.repaint();
    }
}

