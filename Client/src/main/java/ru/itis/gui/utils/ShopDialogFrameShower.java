package ru.itis.gui.utils;

import ru.itis.connection.Connection;
import ru.itis.general.entities.Car;
import ru.itis.general.helpers.CarParser;
import ru.itis.general.helpers.ObjectParser;
import ru.itis.protocol.Message;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.List;

import static ru.itis.gui.utils.GuiConst.*;
import static ru.itis.protocol.Constants.CHOOSE_DESIGN;

public class ShopDialogFrameShower {
    private static final Font font = Loader.loadFont("default.otf").deriveFont(DIMENSION.height/20f);

    public static void showFrame(List<Car> cars, boolean isEnough, int price){
        Dimension size = new Dimension(DIMENSION.width/2, DIMENSION.height/3);
        JFrame modal = new JFrame();
        Font font = Loader.loadFont("default.otf").deriveFont(DIMENSION.height/20f);

        JPanel modalPanel = new JPanel();
        Border line = new LineBorder(Color.BLACK, 3);
        modalPanel.setBorder(line);
        modalPanel.setPreferredSize(size);
        modalPanel.setBackground(COLOR);

        JPanel bottom = new JPanel();
        bottom.setBackground(COLOR);
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JLabel message = new JLabel();
        message.setHorizontalAlignment(JLabel.CENTER);
        message.setFont(font);

        modalPanel.setLayout(new BorderLayout());
        modalPanel.add(message);

        modal.getContentPane().add(modalPanel);

        if(isEnough){
            showConfirmFrame(modal, bottom, message, cars, price);
        } else {
            showNotEnoughMoneyFrame(modal, bottom, message);
        }

        modalPanel.add(bottom, BorderLayout.PAGE_END);

        modal.setSize(size);
        modal.setLocation(DIMENSION.width/2 - DIMENSION.width/4, DIMENSION.height/2 - DIMENSION.height/6);

        modal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        modal.setVisible(true);
    }

    private static void showConfirmFrame(JFrame modal, JPanel bottom, JLabel message, List<Car> cars, int price){
        message.setText("Купить?");
        JButton yes = new JButton("ДА");
        yes.setFont(font.deriveFont(DIMENSION.height/30f));
        yes.setBackground(COLOR);
        yes.setBorder(BLACK_BORDER);
        yes.addActionListener(e -> {
            Connection connection = ConnectionWrapper.getConnection();
            try {
                ObjectParser<Car> carObjectParser = new CarParser();
                byte[] data = carObjectParser.serializeObject(findCar(cars, price));
                connection.sendMessage(new Message(CHOOSE_DESIGN, data));
            } catch (IOException ioException) {
                JOptionPane.showInternalMessageDialog(null, "Не удалось купить машинку");
            }
        });
        bottom.add(yes);

        JButton no = new JButton("НЕТ");
        no.setFont(font.deriveFont(DIMENSION.height/30f));
        no.setBackground(COLOR);
        no.setBorder(BLACK_BORDER);
        no.addActionListener(e -> {
            modal.dispatchEvent(new WindowEvent(modal, WindowEvent.WINDOW_CLOSING));
        });
        bottom.add(no);
    }

    private static void showNotEnoughMoneyFrame(JFrame modal, JPanel bottom, JLabel message){
        message.setText("У вас недостаточно средств");
        JButton ok = new JButton("ОК");
        ok.setBackground(COLOR);
        ok.setFont(font.deriveFont(DIMENSION.height/30f));
        ok.setBorder(BLACK_BORDER);
        ok.addActionListener(e -> {
            modal.dispatchEvent(new WindowEvent(modal, WindowEvent.WINDOW_CLOSING));
        });
        bottom.add(ok);
    }

    private static Car findCar(List<Car> cars, int price){
        for(Car car: cars){
            if(car.getPrice() == price){
                return car;
            }
        }
        return null;
    }
}
