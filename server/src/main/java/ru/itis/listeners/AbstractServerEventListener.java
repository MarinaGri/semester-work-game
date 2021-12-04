package ru.itis.listeners;

import ru.itis.server.IServer;

public abstract class AbstractServerEventListener implements IServerEventListener{
    protected boolean init;
    protected IServer server;
    protected static byte type;
    protected int connectionId;
    protected Message message;

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
