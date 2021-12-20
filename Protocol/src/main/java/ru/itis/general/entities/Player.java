package ru.itis.general.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
//import src.server.Room;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
    protected Integer id;
    protected String nickname;
    protected boolean status;

//    protected Room room;
    protected Integer money;
    protected Car car;
}
