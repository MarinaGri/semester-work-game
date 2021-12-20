package ru.itis.general.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Player {
    protected Integer id;
    protected String nickname;
    protected boolean status;

    protected Integer money;
    protected Car car;
}
