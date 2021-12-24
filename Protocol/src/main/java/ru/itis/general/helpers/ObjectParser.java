package ru.itis.general.helpers;

import java.util.List;

public interface ObjectParser<T> {
    byte[] serializeObject(T object);
    byte[] serializeObject(List<T> objects);

    T deserializeObject(byte[] object);
    List<T> deserializeObjects(byte[] objects);
}

