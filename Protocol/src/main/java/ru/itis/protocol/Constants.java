package ru.itis.protocol;

import java.lang.reflect.Field;

/**
 * тип сообщения занимает 1 байт
 * сообщения от сервера начинаются с 00: 0-63
 * сообщения от клиента с 01: 64-127
 *
 * вход - ENTRANCE
 * некорректное имя - INVALID_NICKNAME
 * конец раунда - END_ROUND
 * ошибки - ERROR(+ее тип)
 * готовность игрока вопрос сервера - READY_REQUEST
 * ответ - READY_RESPONSE
 * все готовы - ALL_READY
 * выбор типа игры - TYPE_GAME
 * ответ для всех игроков(таблица результатов) - RESULTS
 * создание комнаты - CREATE_ROOM
 * выход из комнаты - EXIT_ROOM
 * выход из игры - EXIT
 * выбор дизайна - CHOOSE_DESIGN
 * установка дизайна - SET_DESIGN
 * конец игры - FINAL_GAME_OVER
 * ответ выбывшему игроку - GAME_OVER
 * пинг - PING
 * понг - PONG
 */

public class Constants {
    //*.* первые 2 байта сообщения для проверки его корректности
    public static final double VERSION = 0.1;

    //типы сообщений(только они должны иметь тип byte,
    // чтобы легко состать характеристический вектор и проверить,
    // есть ли такой тип у полученного сообщения)
    public static final byte ERROR = 0;
    public static final byte READY_REQUEST = 1;
    public static final byte ALL_READY = 2;
    public static final byte RESULTS = 3;
    public static final byte FINAL_GAME_OVER = 4;
    public static final byte GAME_OVER = 5;


    public static final byte ENTRANCE = 64;
    public static final byte TYPE_GAME = 65;
    public static final byte CREATE_ROOM = 66;
    public static final byte READY_RESPONSE = 67;
    public static final byte EXIT_ROOM = 68;
    public static final byte EXIT = 69;
    public static final byte CHOOSE_DESIGN = 70;
    public static final byte SET_DESIGN = 71;

    //возвращает характеристический вектор типов сообщений
    //элемент true => сообщение с таким типом есть
    public static boolean[] getVectorTypes(){
        boolean[] vector = new boolean[127];
        for(Field field: Constants.class.getDeclaredFields()){
            if(field.getType().equals(byte.class)){
                try {
                    vector[(byte) field.get(null)] = true;
                } catch (IllegalAccessException ignored) {
                }
            }
        }
        return vector;
    }
}
