package src.listeners;

import general.entities.Player;
import general.helpers.MessageParser;
import general.helpers.TextParser;
import src.protocol.Constants;
import src.protocol.Message;
import src.server.Server;

public class EntranceServerListener extends AbstractServerEventListener{
    private MessageParser<String> messageParser;

    public EntranceServerListener(){
        super(Constants.ENTRANCE);
        messageParser = new TextParser();
    }

    @Override
    public void setParameters(int id, Message message) {
        connectionId = id;
        this.message = message;
    }

    //    @Override
//    public void handle(int connectionId, Message message) {
//
//    }

    @Override
    public void run() {
        Player player = Player.builder()
                .id(connectionId)
                .nickname(messageParser.deserializeMessage(message.getData()))
                .build();

        Server.getConnectionById(connectionId).setPlayer(player);
    }
}
