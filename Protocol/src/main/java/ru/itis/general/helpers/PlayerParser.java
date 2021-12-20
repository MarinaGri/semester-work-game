package ru.itis.general.helpers;

import ru.itis.general.entities.Player;

public class PlayerParser implements MessageParser<Player>{
    @Override
    public byte[] serializeMessage(Player object) {
        return new byte[0];
    }

    @Override
    public Player deserializeMessage(byte[] message) {
        return null;
    }
}
