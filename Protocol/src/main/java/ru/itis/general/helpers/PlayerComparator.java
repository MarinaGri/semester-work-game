package ru.itis.general.helpers;

import ru.itis.general.entities.Player;

import java.util.Comparator;

public class PlayerComparator implements Comparator<Player> {
    @Override
    public int compare(Player player1, Player player2) {
        if(player1.getTime() > player2.getTime()) return 1;
        if(player1.getTime() < player2.getTime()) return -1;
        return player1.getResult().compareTo(player2.getResult());
    }
}
