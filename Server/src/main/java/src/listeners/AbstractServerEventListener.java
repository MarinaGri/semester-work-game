package src.listeners;

import src.protocol.Message;
import src.server.IServer;

public abstract class AbstractServerEventListener implements IServerEventListener{
    protected boolean init;
    protected IServer server;
    protected static byte type;
    protected int connectionId;
    protected Message message;

    public AbstractServerEventListener(byte type){
        this.type = type;
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
