package ru.itis.gui.listeners;

import ru.itis.connection.Connection;
import ru.itis.general.entities.Player;
import ru.itis.gui.components.RaceJPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoinCollectingListener implements ActionListener {
    private Graphics2D car;
    private Graphics2D coin;
    private RaceJPanel panel;
    private Connection connection;

    public CoinCollectingListener(RaceJPanel panel, Connection connection) {
        this.panel = panel;
        this.car = panel.getCar();
        this.coin = panel.getCoin();
        this.connection = connection;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if ((panel.getXForCar() == panel.getXForCoin())&& (panel.getYForCar() == panel.getYForCoin())) {
            Player player = connection.getPlayer();
            player.setMoney(player.getMoney() + 1);
        }
    }
}
