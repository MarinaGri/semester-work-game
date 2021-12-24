package ru.itis.gui.listeners;

import ru.itis.gui.components.RaceJPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MotionListener implements ActionListener {
    private RaceJPanel racePanel;
    private final int OFFSET = 1;

    public MotionListener(RaceJPanel raceJPanel) {
        this.racePanel = raceJPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Random random = new Random();
        racePanel.setYForCoin(racePanel.getYForCoin() + OFFSET);
        racePanel.setYForTree(racePanel.getYForTree() + OFFSET);
        racePanel.setYForOtherCar(racePanel.getYForOtherCar() + OFFSET);

        if (racePanel.getYForCoin() > racePanel.getHeight()) {
            racePanel.setYForCoin(0);
            int x = random.nextInt( racePanel.getWidth()*3/4 + 1 - racePanel.getWidth()/4) + racePanel.getWidth()/4;
            racePanel.setXForCoin(x);
        }

        if (racePanel.getYForTree() > racePanel.getHeight()) {
            racePanel.setYForTree(0) ;
        }

        if (racePanel.getYForOtherCar() > racePanel.getHeight()) {
            racePanel.setYForOtherCar(0);
            int x = random.nextInt( racePanel.getWidth()*3/4 + 1 - racePanel.getWidth()/4) + racePanel.getWidth()/4 + 1;
            racePanel.setXForOtherCar(x);
        }

        racePanel.validate();
        racePanel.repaint();
    }
}

