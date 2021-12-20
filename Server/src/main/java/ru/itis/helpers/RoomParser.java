package ru.itis.helpers;

import com.google.gson.Gson;
import ru.itis.server.Room;

public class RoomParser {
    public static String toJson(Room room){
        Gson gson = new Gson();

        return gson.toJson(room);
    }
}
