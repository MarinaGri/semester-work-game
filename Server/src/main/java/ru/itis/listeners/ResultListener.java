package ru.itis.listeners;

import ru.itis.general.entities.Player;
import ru.itis.general.entities.Room;
import ru.itis.general.helpers.ObjectParser;
import ru.itis.general.helpers.PlayerParser;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultListener extends AbstractServerEventListener{
    private ObjectParser<Player> playerParser;

    public ResultListener(){
        super(Constants.RESULT);
        playerParser = new PlayerParser();
    }

    @Override
    public void handle(Connection connection, Message message) {
        Player result = playerParser.deserializeObject(message.getData());
        Player player = connection.getPlayer();
        Room room = player.getRoom();

        player.setMoney(player.getMoney() + result.getResult());
        player.setTime(player.getTime() + result.getTime());
        player.setResult(player.getResult() + result.getResult());

        room.setCurrentNumberOfResults(room.getCurrentNumberOfResults() + 1);

        if (room.allResults()){
            List<Player> players = room.getPlayers().stream()
                    .sorted(Comparator.comparingInt(Player::getResult))
                    .collect(Collectors.toList());

//установить result 0
        }
    }
}
