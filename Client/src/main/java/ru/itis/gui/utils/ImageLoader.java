package ru.itis.gui.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ImageLoader {

    public Image loadImg(String name){
        Image image = null;
        try {
            URL resource = ImageLoader.class.getClassLoader().getResource("img\\" + name);
            if (resource == null) {
                throw new IllegalArgumentException("file not found!");
            } else {
                image = ImageIO.read(new File(resource.toURI()));
            }
        } catch (URISyntaxException | IOException | IllegalArgumentException ex) {
            JOptionPane.showInternalMessageDialog(null, Constants.IMG_LOAD_FAIL);
        }
        return image;
    }
}
