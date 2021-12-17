package ru.itis.gui.listeners;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        System.out.println(name);
    }
}
