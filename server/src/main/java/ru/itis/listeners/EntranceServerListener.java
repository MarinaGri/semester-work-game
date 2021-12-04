package ru.itis.listeners;

public class EntranceServerListener extends AbstractServerEventListener{
    public EntranceServerListener(){
        type = Protocol.ENTRANCE;
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

    }
}
