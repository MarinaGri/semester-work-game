package ru.itis.gui.components;

import lombok.Data;

import javax.swing.*;
import java.awt.*;

@Data
public class JPanelWithBackground extends JPanel {
    private Image backgroundImage;
    private boolean flag;

    public JPanelWithBackground(Image image, boolean flag){
        this.backgroundImage = image;
        this.flag = flag;
    }

    public void paintComponent(Graphics g) {
        if (isFlag()) {
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        } else {
            g.drawImage(backgroundImage, this.getWidth()/4, 0, this.getWidth()/2, this.getHeight(), this);

        }
    }
}
