package ru.itis.gui.components;

import java.awt.*;

import lombok.Getter;
import lombok.Setter;
import ru.itis.gui.utils.GuiConst;

@Getter
@Setter
public class RaceJPanel extends JPanelWithBackground {

    private Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private int xForLeftWheels = (int) (dimension.getWidth()/2);
    private int xForRightWheels = (int) (dimension.getWidth()/2 + GuiConst.WIDTH_CAR + GuiConst.WIDTH_WHEEL);
    private int yForUpperWheels = (int) (dimension.getHeight()*3.5/4 - GuiConst.HEIGHT_CAR);
    private int yForLowerWheels = (int) (dimension.getHeight()*3.5/4 - GuiConst.HEIGHT_CAR + GuiConst.HEIGHT_WHEEL*(GuiConst.HEIGHT_CAR/GuiConst.HEIGHT_WHEEL));
    private int xForCar = (int) dimension.getWidth()/2 + GuiConst.WIDTH_WHEEL;
    private int yForCar = (int) dimension.getHeight() - GuiConst.HEIGHT_CAR;
    private int xForCoin = (int) dimension.getWidth()/2;
    private int yForCoin = 0;
    private Graphics2D car;
    private Graphics2D carWheel;
    private Graphics2D coin;


    public RaceJPanel(Image image) {
        super(image);
        this.setBackground(GuiConst.COLOR);
        Dimension thisSize = new Dimension(dimension.width/4, dimension.height/4);
        this.setPreferredSize(dimension);
        this.setMinimumSize(thisSize);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        carWheel = (Graphics2D) g;
        carWheel.setColor(Color.red);

//        if (count == 0){
//            count++;
//            System.out.println(count);
//
//            carWheel.drawOval(getWidth() / 2, getHeight() - HEIGHT_CAR, WIDTH_WHEEL, HEIGHT_WHEEL);
//            carWheel.fillOval(getWidth() / 2, getHeight() - HEIGHT_CAR, WIDTH_WHEEL, HEIGHT_WHEEL); //левое верхнее
//            carWheel.drawOval(getWidth() / 2, getHeight() - HEIGHT_CAR + HEIGHT_WHEEL * (HEIGHT_CAR / HEIGHT_WHEEL), WIDTH_WHEEL, HEIGHT_WHEEL);
//            carWheel.fillOval(getWidth() / 2, getHeight() - HEIGHT_CAR + HEIGHT_WHEEL * (HEIGHT_CAR / HEIGHT_WHEEL), WIDTH_WHEEL, HEIGHT_WHEEL);//левое нижнее
//            carWheel.drawOval(getWidth() / 2 + WIDTH_CAR + WIDTH_WHEEL, getHeight() - HEIGHT_CAR + HEIGHT_WHEEL * (HEIGHT_CAR / HEIGHT_WHEEL), WIDTH_WHEEL, HEIGHT_WHEEL);
//            carWheel.fillOval(getWidth() / 2 + WIDTH_CAR + WIDTH_WHEEL, getHeight() - HEIGHT_CAR + HEIGHT_WHEEL * (HEIGHT_CAR / HEIGHT_WHEEL), WIDTH_WHEEL, HEIGHT_WHEEL);//правое нижнее
//            carWheel.drawOval(getWidth() / 2 + WIDTH_CAR + WIDTH_WHEEL, getHeight() - HEIGHT_CAR, WIDTH_WHEEL, HEIGHT_WHEEL);
//            carWheel.fillOval(getWidth() / 2 + WIDTH_CAR + WIDTH_WHEEL, getHeight() - HEIGHT_CAR, WIDTH_WHEEL, HEIGHT_WHEEL);//правое верхнее
//
//        } else {
        carWheel.drawOval(xForLeftWheels, yForUpperWheels, GuiConst.WIDTH_WHEEL, GuiConst.HEIGHT_WHEEL);
        carWheel.fillOval(xForLeftWheels, yForUpperWheels, GuiConst.WIDTH_WHEEL, GuiConst.HEIGHT_WHEEL); //левое верхнее
        carWheel.drawOval(xForLeftWheels, yForLowerWheels, GuiConst.WIDTH_WHEEL, GuiConst.HEIGHT_WHEEL);
        carWheel.fillOval(xForLeftWheels, yForLowerWheels, GuiConst.WIDTH_WHEEL, GuiConst.HEIGHT_WHEEL);//левое нижнее
        carWheel.drawOval(xForRightWheels, yForLowerWheels, GuiConst.WIDTH_WHEEL,GuiConst. HEIGHT_WHEEL);
        carWheel.fillOval(xForRightWheels, yForLowerWheels, GuiConst.WIDTH_WHEEL, GuiConst.HEIGHT_WHEEL);//правое нижнее
        carWheel.drawOval(xForRightWheels, yForUpperWheels, GuiConst.WIDTH_WHEEL, GuiConst.HEIGHT_WHEEL);
        carWheel.fillOval(xForRightWheels, yForUpperWheels, GuiConst.WIDTH_WHEEL, GuiConst.HEIGHT_WHEEL);
//        }


        car = (Graphics2D) g;
        car.setColor(Color.black);
        car.drawRect(xForCar, yForCar, GuiConst.WIDTH_CAR, GuiConst.HEIGHT_CAR);
        car.fillRect(xForCar, yForCar, GuiConst.WIDTH_CAR, GuiConst.HEIGHT_CAR);

        coin = (Graphics2D) g;
        coin.setColor(Color.YELLOW);

        coin.drawOval(xForCoin, yForCoin, 20, 20);
        coin.fillOval(xForCoin, yForCoin, 20, 20);
    }

    public void paintX(int x) {
        setXForLeftWheels(getXForLeftWheels() + x);
        setXForRightWheels(getXForRightWheels() + x);
        this.validate();
        this.repaint();
    }

    public void paintY(int y) {
        setYForUpperWheels(getYForUpperWheels() + y);
        setYForLowerWheels(getYForLowerWheels() + y);
        this.validate();
        this.repaint();
    }
}