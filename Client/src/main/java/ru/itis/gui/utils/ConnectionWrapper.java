package ru.itis.gui.utils;

import ru.itis.connection.Connection;
import ru.itis.exceptions.ConnectionLostException;

import javax.swing.*;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ConnectionWrapper {
    private static Connection connection;

    public static Connection getConnection() {
        if(connection == null){
            try {
                connection = new Connection(InetAddress.getLocalHost(), 11001);
            } catch (ConnectionLostException ex) {
                JOptionPane.showInternalMessageDialog(null, "Соединение разорвано");
            } catch (UnknownHostException ex) {
                JOptionPane.showInternalMessageDialog(null, "Подобран неправильный хост");
            }
        }
        return connection;
    }
}
