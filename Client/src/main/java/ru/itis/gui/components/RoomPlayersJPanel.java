package ru.itis.gui.components;

import ru.itis.general.entities.Player;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class RoomPlayersJPanel extends JPanel {
    private List<Player> players;

    public RoomPlayersJPanel() {
        players = new ArrayList<>();
        this.setLayout(new GridLayout());

    }
}
