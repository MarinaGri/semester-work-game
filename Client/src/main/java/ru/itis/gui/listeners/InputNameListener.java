package ru.itis.gui.listeners;

import ru.itis.connection.Connection;
import ru.itis.general.helpers.TextParser;
import ru.itis.gui.utils.ConnectionWrapper;
import ru.itis.protocol.Constants;
import ru.itis.protocol.Message;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class InputNameListener implements ActionListener, KeyListener {
    private JTextField textField;

    public InputNameListener(JTextField textField) {
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        sentName(textField.getText());
    }

    @Override
    public void keyTyped(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER ) {
            sentName(textField.getText());
        }
    }

    @Override
    public void keyPressed(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.VK_ENTER ) {
            sentName(textField.getText());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void sentName(String name){
        Connection connection = ConnectionWrapper.getConnection();
        TextParser textParser = new TextParser();
        Message message = new Message(Constants.ENTRANCE, textParser.serializeMessage(name));
        try {
            connection.sendMessage(message);
        } catch (IOException e) {
            JOptionPane.showInternalMessageDialog(null, "Не удалось отправить nickname");
        }
    }
}
