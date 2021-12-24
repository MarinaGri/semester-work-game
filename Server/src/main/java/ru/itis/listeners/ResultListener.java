package ru.itis.listeners;

import ru.itis.general.entities.Player;
import ru.itis.general.entities.Room;
import ru.itis.general.helpers.ObjectParser;
import ru.itis.general.helpers.PlayerComparator;
import ru.itis.general.helpers.PlayerParser;
import ru.itis.listeners.general.AbstractServerEventListener;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ResultListener extends AbstractServerEventListener {
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

        int res = room.getCurrentNumberOfResults().incrementAndGet();
        System.out.println("number of results" + res);

        if (room.allResults(res)){
            System.out.println(room.getPlayers());
            List<Player> players = room.getPlayers().stream()
                    .sorted(new PlayerComparator())
                    .collect(Collectors.toList());

            Message toClient = new Message(Constants.GAME_OVER,
                    playerParser.serializeObject(players));
            server.sendMulticastMessage(room, toClient);

            sendMessageFailedUsers(room, players);
            room.getCurrentNumberOfResults().set(0);

            try{
                Thread.sleep(10000);
            }catch (InterruptedException e){

            }finally {
                System.out.println("current round" + room.getCurrentRound());
                if (players.size() == 1 || room.getCurrentRound() == 3){
                    toClient = new Message(Constants.FINAL_GAME_OVER,
                            playerParser.serializeObject(players));
                    server.sendMulticastMessage(room, toClient);
                }else {
                    toClient = new Message(Constants.ALL_READY);
                    server.sendMulticastMessage(room, toClient);
                }

                room.setCurrentRound(room.getCurrentRound() + 1);
            }
        }
    }

    private void sendMessageFailedUsers(Room room, List<Player> players){
        Integer indexFailedUser = Room.MAX_PLAYERS - Room.FAIL_USERS*room.getCurrentRound();
        System.out.println("Index failed: " + indexFailedUser);

        if ((indexFailedUser != 0) && (players.size() != 1) && (players.size() > indexFailedUser)){
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
