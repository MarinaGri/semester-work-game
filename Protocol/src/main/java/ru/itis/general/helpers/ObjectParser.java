package ru.itis.general.helpers;

import java.util.List;

public interface ObjectParser<T> {
    byte[] serializeObject(T object);
    byte[] serializeObject(List<T> objects);

    T deSerializeObject(byte[] object);
    List<T> deSerializeObjects(byte[] objects);
}
