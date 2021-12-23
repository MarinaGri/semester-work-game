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

            Message toClient = new Message(Constants.GAME_OVER,
                    playerParser.serializeObject(players));
            server.sendMulticastMessage(room, toClient);

            if (players.size() == 1 || room.getCurrentRound() == 3){
                toClient = new Message(Constants.FINAL_GAME_OVER,
                        playerParser.serializeObject(players));
                server.sendMulticastMessage(room, toClient);
            }

            sendMessageFailedUsers(room, players);
            room.setCurrentNumberOfResults(0);
        }
    }

    private void sendMessageFailedUsers(Room room, List<Player> players){
        Integer indexFailedUser = Room.MAX_PLAYERS - Room.FAIL_USERS*room.getCurrentRound();

        if (players.size() != 1 && players.size() > indexFailedUser){
            Player player = players.get(indexFailedUser);

            Message toClient = new Message(Constants.YOU_LOOSER);
            server.sendMessage(server.getConnectionById(player.getId()), toClient);
        }

        if (players.size() > (indexFailedUser + 1)) {
            Player player = players.get(indexFailedUser + 1);

            Message toClient = new Message(Constants.YOU_LOOSER);
            server.sendMessage(server.getConnectionById(player.getId()), toClient);
        }
    }
}
