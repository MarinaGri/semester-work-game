package ru.itis.general.helpers;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import ru.itis.general.entities.Player;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class PlayerParser implements ObjectParser<Player>{
    private TextParser textParser;

    public PlayerParser(){
        textParser = new TextParser();
    }

    @Override
    public byte[] serializeObject(Player player) {
        return new byte[0];
    }

    @Override
    public byte[] serializeObject(List<Player> players) {
        return new byte[0];
    }

    @Override
    public Player deSerializeObject(byte[] player) {
        return null;
    }

    @Override
    public List<Player> deSerializeObjects(byte[] players) {
        return null;
    }
}
