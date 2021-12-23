package ru.itis.gui.listeners;

import ru.itis.exceptions.CollisionException;
import ru.itis.gui.components.RaceJPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CarCollisionListener implements ActionListener {
    private RaceJPanel racePanel;

    public CarCollisionListener(RaceJPanel racePanel) {
        this.racePanel = racePanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if  (((racePanel.getYForOtherCar() + racePanel.getHeightCar()/2) == (racePanel.getYForCar() - racePanel.getHeightCar()))  &&
                ((((racePanel.getXForOtherCar() + racePanel.getWidthCar()/2) <= (racePanel.getXForCar() + racePanel.getWidthCar()/2)) &&
                        ((racePanel.getXForOtherCar() + racePanel.getWidthCar()/2) >= (racePanel.getXForCar() - racePanel.getWidthCar()/2)) ) ||
                        (((racePanel.getXForCar() + racePanel.getWidthCar()/2) <= (racePanel.getXForOtherCar() + racePanel.getWidthCar()/2))  &&
                        ((racePanel.getXForCar() + racePanel.getWidthCar()/2) >= (racePanel.getXForOtherCar() - racePanel.getWidthCar()/2))))) {

                throw new CollisionException();
        }
    }

}
