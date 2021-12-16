package src.server;

import java.util.ArrayList;
import java.util.List;

public class Room{

    protected static final int MAX_PLAYERS = 6;
    protected boolean started;

    protected List<Connection> connections;

    public Room(){
        started = true;
        connections = new ArrayList<>();
    }
}
