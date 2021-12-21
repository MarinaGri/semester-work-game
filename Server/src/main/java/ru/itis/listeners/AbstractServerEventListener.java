package ru.itis.listeners;

import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;
import ru.itis.server.Connection;
import ru.itis.server.IServer;

import static ru.itis.protocol.Constants.*;

public abstract class AbstractServerEventListener implements IServerEventListener{
    protected boolean init;
    protected IServer server;

    //тип сообщения, которое прослушивает листенер
    protected static byte type;

    //параметры, по которым определённому connection отправляется данное сообщение
    protected Connection connection;
    protected Message message;

    public AbstractServerEventListener(byte type){
        this.type = type;
    }

    public static IServerEventListener getEventListener(byte type){
        switch (type){
            case ENTRANCE:
                return new EntranceServerListener();
            case JOIN_ROOM:
                return new JoinRoomListener();
            default: throw new IllegalArgumentException("Illegal type of listener");
        }
    }

    @Override
    public void init(IServer server){
        this.init = true;
        this.server = server;
    }

    @Override
    public byte getType() {
        return type;
    }
}
