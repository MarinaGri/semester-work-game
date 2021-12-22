package ru.itis.general.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@Builder
public class Player {
    protected Integer id;
    protected String nickname;
    protected Boolean status;

    protected Integer time;
    protected Integer result;
    protected Integer money;
    protected Car car;
    protected transient Room room;

    public void exitRoom(){
        room.deletePlayer(this);
        room = null;
    }
}
