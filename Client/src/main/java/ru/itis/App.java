package ru.itis;

import ru.itis.connection.MessageListener;
import ru.itis.gui.GuiManager;
import ru.itis.gui.Window;
import ru.itis.connection.Connection;
import ru.itis.gui.utils.ConnectionWrapper;

public class App {
    public static void main(String[] args) {
        Connection connection = ConnectionWrapper.getConnection();
        Window window = new Window();
        GuiManager guiManager = new GuiManager(window);
        MessageListener messageListener = new MessageListener(connection, guiManager);
        Thread game = new Thread(messageListener);
        game.start();
        window.createGUI();
    }
}

