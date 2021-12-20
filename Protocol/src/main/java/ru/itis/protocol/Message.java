package ru.itis.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    private byte type;
    private byte[] data;

    public Message(byte type){
        this.type = type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i =+ 2){
            char a = (char) (data[i]<<8 | data[i+1]);
            sb.append(a);
        }

        return sb.toString();
    }
}
