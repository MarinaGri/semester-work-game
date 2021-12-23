package ru.itis.gui.components;

import java.awt.*;

import lombok.Getter;
import lombok.Setter;
import ru.itis.gui.listeners.KeyListenerForCar;
import ru.itis.gui.utils.GuiConst;

@Getter
@Setter
public class RaceJPanel extends JPanelWithBackground {

    private int xForCar;
    private int yForCar;
    private int xForOtherCar;
    private int yForOtherCar;
    private int xForCoin;
    private int yForCoin;
    private int offsetX;
    private int offsetY;
    private int widthWheel;
    private int heightWheel;
    private int widthCar;
    private int heightCar;
    private int xForTree;
    private int yForTree;
    private Graphics2D car;
    private Graphics2D carWheel;
    private Graphics2D coin;
    private Dimension dimension;


    public RaceJPanel(Image image) {
        super(image);
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.setBackground(GuiConst.COLOR);
        Dimension thisSize = new Dimension(dimension.width/4, dimension.height/4);
        this.addKeyListener(new KeyListenerForCar(this));
        this.setPreferredSize(dimension);
        this.setMinimumSize(thisSize);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        carWheel = (Graphics2D) g.create();
        carWheel.setColor(Color.darkGray);

        heightCar = getHeight()/4;
        widthCar = getWidth()/12;

        widthWheel = getWidth()/25;
        heightWheel = getHeight()/25;

        carWheel.fillOval(getWidth() / 2 + offsetX, getHeight()  + offsetY -getHeight()/4, widthWheel, heightWheel); //левое верхнее
        carWheel.fillOval(getWidth() / 2 + offsetX, getHeight() - heightCar +  offsetY + heightWheel * (yForCar / heightWheel), widthWheel, heightWheel);//левое нижнее);
        carWheel.fillOval(getWidth() / 2 + offsetX + widthCar + widthWheel, getHeight()  + offsetY - heightCar + heightWheel * (heightCar / heightWheel), widthWheel, heightWheel);//правое нижнее);
        carWheel.fillOval(getWidth() / 2 + offsetX + widthCar + widthWheel, getHeight()  + offsetY - heightCar, widthWheel, heightWheel);//правое верхнее);
        carWheel.dispose();

        xForCar = getWidth()/2 + widthWheel + offsetX;
        yForCar = getHeight() - getHeight()/4 + offsetY;

        car = (Graphics2D) g.create();
        car.setColor(Color.black);
        car.drawRect(xForCar, yForCar, widthCar, heightCar);
        car.fillRect(xForCar, yForCar, widthCar, heightCar);
        car.dispose();

        coin = (Graphics2D) g.create();
        coin.setColor(GuiConst.COLOR_COINS);

        int radiusForCoin = getWidth()/30;
        coin.fillOval( xForCoin, yForCoin, radiusForCoin, radiusForCoin);
        coin.dispose();

        Graphics2D tree = (Graphics2D)  g.create();

        int widthTree = getWidth();
        tree.setColor(Color.GREEN);
        int radius = getWidth()/20;

        tree.fillOval((widthTree / 4) - radius, yForTree - (radius * 2), radius * 2, radius * 2);
        tree.fillOval((widthTree / 4) - radius, yForTree - radius, radius * 2, radius * 2);
        tree.fillOval((widthTree / 4) - (radius * 2), yForTree - radius, radius * 2, radius * 2);
        tree.fillOval((widthTree / 4), yForTree - radius, radius * 2, radius * 2);
        tree.dispose();

        Graphics2D otherCarWheel = (Graphics2D) g.create();
        otherCarWheel.setColor(Color.darkGray);
        xForOtherCar = getWidth()/2;

        otherCarWheel.fillOval(xForOtherCar, yForOtherCar, widthWheel, heightWheel); //левое верхнее
        otherCarWheel.fillOval(xForOtherCar, yForOtherCar + heightCar, widthWheel, heightWheel);//левое нижнее
        otherCarWheel.fillOval(xForOtherCar + widthCar + widthWheel, yForOtherCar + heightCar, widthWheel, heightWheel);//правое нижнее
        otherCarWheel.fillOval(xForOtherCar + widthCar + widthWheel, yForOtherCar , widthWheel, heightWheel);//правое верхнее
        otherCarWheel.dispose();

        Graphics2D otherCar = (Graphics2D) g.create();
        otherCar.setColor(Color.lightGray);
        otherCar.drawRect(xForOtherCar + widthWheel , yForOtherCar ,  widthCar, heightCar);
        otherCar.fillRect(xForOtherCar + widthWheel, yForOtherCar,  getWidth()/12, heightCar);

    }

}