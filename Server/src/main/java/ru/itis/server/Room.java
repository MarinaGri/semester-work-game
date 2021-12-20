package ru.itis.server;

import ru.itis.general.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class Room {
    protected int id;

    public static final int MAX_PLAYERS = 6;
    protected boolean started;

    protected List<Player> players;

    public Room(int id){
        this.id = id;
        started = true;
        players = new ArrayList<>();
    }

    public boolean addPlayer(Player player){
        if (players.size() < MAX_PLAYERS){
            players.add(player);
            return true;
        }else {
            return false;
        }
    }

    public int getNumberOfPlayers(){
        return players.size();
    }
}
