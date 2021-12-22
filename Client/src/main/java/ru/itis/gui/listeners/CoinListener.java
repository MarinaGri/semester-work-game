package ru.itis.gui.listeners;

import ru.itis.gui.components.RaceJPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CoinListener implements ActionListener {
    private RaceJPanel raceJPanel;

    public CoinListener(RaceJPanel raceJPanel) {
        this.raceJPanel = raceJPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        raceJPanel.setYForCoin(raceJPanel.getXForCoin() + 1);
        raceJPanel.validate();
        raceJPanel.repaint();
    }
}
