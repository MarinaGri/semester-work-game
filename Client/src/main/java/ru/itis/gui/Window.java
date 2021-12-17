package ru.itis.gui;

import ru.itis.gui.components.MainJPanel;
import ru.itis.gui.utils.ImageLoader;
import ru.itis.gui.utils.Constants;

import javax.swing.*;
import java.awt.*;

public class Window {
    public void createGUI(){
        JFrame mainFrame = new JFrame(Constants.GAME_NAME);

        addComponents(mainFrame);

        mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainFrame.setVisible(true);
    }

    private void addComponents(JFrame mainFrame) {
        ImageLoader loader = new ImageLoader();
        MainJPanel mainPanel = new MainJPanel(loader.loadImg(Constants.MAIN_BACKGROUND));
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.pack();
        mainPanel.getPanel().getComponent(4).requestFocusInWindow();
    }
}
