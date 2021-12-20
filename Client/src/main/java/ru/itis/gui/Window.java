package ru.itis.gui;

import lombok.Getter;
import ru.itis.gui.components.MainJPanel;
import ru.itis.gui.utils.Loader;
import ru.itis.gui.utils.GuiConst;

import javax.swing.*;
import java.awt.*;

@Getter
public class Window {
    private JFrame mainFrame;

    public Window() {
        this.mainFrame = new JFrame("Drivel");

        addComponents(mainFrame);

        mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void createGUI(){
        mainFrame.setVisible(true);
    }

    private void addComponents(JFrame mainFrame) {
        MainJPanel mainPanel = new MainJPanel(Loader.loadImg(GuiConst.MAIN_BACKGROUND));
        mainFrame.getContentPane().add(mainPanel);
        mainFrame.pack();
        mainPanel.getInputNameJPanel().getComponent(4).requestFocusInWindow();
    }
}
