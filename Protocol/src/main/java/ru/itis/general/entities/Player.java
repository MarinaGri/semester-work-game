package ru.itis.general.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Player {
    protected Integer id;
    protected String nickname;
    protected Boolean status;

    protected Integer money;
    protected Car car;
    protected Room room;

    public void exitRoom(){
        room = null;
    }
}
