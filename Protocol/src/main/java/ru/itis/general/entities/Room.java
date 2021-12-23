package ru.itis.general.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Room {
    public static final int MAX_PLAYERS = 6;
    public static final int FAIL_USERS = 2;
    protected boolean started;

    protected List<Player> players;
    protected int currentNumberOfResults;
    protected int currentRound;

    public Room(){
        started = true;
        players = new ArrayList<>();
        currentNumberOfResults = 0;
        currentRound = 0;
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
        if (players.size() != MAX_PLAYERS){
            return false;
        }

        for (Player player: players){
            if (!player.getStatus()){
                return false;
            }
        }

        return true;
    }

    public boolean allResults(){
        if (currentNumberOfResults == players.size()){
            return true;
        }
        return false;
    }

    public List<Player> getPlayers() {
        return players;
    }


}
