package ru.itis.general.helpers;

import com.google.gson.JsonObject;

public class JsonParser implements MessageParser<JsonObject>{
    @Override
    public byte[] serializeMessage(JsonObject object) {
        return new byte[0];
    }

    @Override
    public JsonObject deserializeMessage(byte[] message) {
        return null;
    }
}
