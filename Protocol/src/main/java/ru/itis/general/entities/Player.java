package ru.itis.general.entities;

import lombok.Builder;
import lombok.Data;
//import src.server.Room;

@Data
@Builder
public class Player {
    protected Integer id;
    protected String nickname;

//    protected Room room;
    protected Integer money;
    protected Car car;
}
