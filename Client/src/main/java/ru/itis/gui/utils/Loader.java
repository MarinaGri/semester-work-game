package ru.itis.gui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class Loader {

    public static Image loadImg(String name){
        Image image = null;
        try {
            URL resource = Loader.class.getClassLoader().getResource("img/" + name);
            if (resource == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                image = ImageIO.read(new File(resource.toURI()));
            }
        } catch (URISyntaxException | IOException | IllegalArgumentException ex) {
            JOptionPane.showInternalMessageDialog(null, "Не удалось загрузить изображение");
        }
        return image;
    }

    public static Font loadFont(String name){
        Font font = null;
        try {
            URL resource = Loader.class.getClassLoader().getResource("fonts/" + name);
            if (resource == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                font = Font.createFont(Font.TRUETYPE_FONT, new File(resource.toURI()));
            }
        } catch (URISyntaxException | IOException | IllegalArgumentException | FontFormatException ex) {
            JOptionPane.showInternalMessageDialog(null, "Не удалось загрузить шрифт");
        }
        return font;
    }
}

