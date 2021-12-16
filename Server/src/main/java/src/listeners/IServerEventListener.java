package src.listeners;

import src.protocol.Message;
import src.server.IServer;

public interface IServerEventListener extends Runnable {
    public void init(IServer server);
    //public void handle(int connectionId, Message message);
    public byte getType();
    public void setParameters(int id, Message message);
}