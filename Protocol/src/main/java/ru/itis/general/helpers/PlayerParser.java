package ru.itis.general.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import ru.itis.general.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class PlayerParser implements ObjectParser<Player>{
    private TextParser textParser;
    private Gson gson;

    public PlayerParser(){
        textParser = new TextParser();
        gson = new Gson();
    }

    @Override
    public byte[] serializeObject(Player player) {
        return textParser.serializeMessage(gson.toJson(player));
    }

    @Override
    public byte[] serializeObject(List<Player> players) {
        return textParser.serializeMessage(gson.toJson(players));
    }

    @Override
    public Player deSerializeObject(byte[] player) {
        String text = textParser.deserializeMessage(player);

        return gson.fromJson(text, Player.class);
    }

    @Override
    public List<Player> deSerializeObjects(byte[] players) {
        List<Player> resultPlayers = new ArrayList<>();
        String text = textParser.deserializeMessage(players);

        JsonArray array = gson.fromJson(text, JsonArray.class);

        for (JsonElement jsonElement: array){
            Player player = gson.fromJson(jsonElement, Player.class);
            resultPlayers.add(player);
        }

        return resultPlayers;
    }
}
