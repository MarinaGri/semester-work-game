package ru.itis.general.entities;

import java.util.ArrayList;
import java.util.List;

public class Room {
    public static final int MAX_PLAYERS = 6;
    protected boolean started;

    protected List<Player> players;

    public Room(){
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

    public void deletePlayer(Player player){
        players.remove(player);
    }

    public int getNumberOfPlayers(){
        return players.size();
    }

    public boolean allReady(){
        for (Player player: players){
            if (!player.getStatus()){
                return false;
            }
        }

        return true;
    }

    public List<Player> getPlayers() {
        return players;
    }
}
