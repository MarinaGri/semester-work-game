package ru.itis.gui.listeners;

import ru.itis.connection.Connection;
import ru.itis.gui.utils.ConnectionWrapper;
import ru.itis.protocol.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static ru.itis.protocol.Constants.GET_CARS;

public class EnterStoreListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Connection connection = ConnectionWrapper.getConnection();
        try {
            connection.sendMessage(new Message(GET_CARS));
        } catch (IOException ioException) {
            JOptionPane.showInternalMessageDialog(null, "Не удалось открыть магазин");
        }
    }
}

