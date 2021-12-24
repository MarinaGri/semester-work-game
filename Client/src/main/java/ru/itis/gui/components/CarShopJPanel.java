package ru.itis.gui.components;

import lombok.Data;
import ru.itis.general.entities.*;
import ru.itis.gui.utils.*;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.util.List;

import static ru.itis.gui.utils.GuiConst.*;

@Data
public class CarShopJPanel extends JPanel {
    private MainJPanel mainJPanel;
    private JPanel carsPanel;
    private Font font;
    private List<Car> cars;

    public CarShopJPanel(MainJPanel mainJPanel, Player player, List<Car> cars) {
        this.mainJPanel = mainJPanel;
        this.cars = cars;
        font = Loader.loadFont("default.otf").deriveFont(DIMENSION.height/20f);
        this.setBackground(COLOR);
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanelWithBackground(Loader.loadImg("monetka.png"), true);
        panel.setPreferredSize(new Dimension(DIMENSION.height/20, DIMENSION.height/20));

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.LEFT));
        top.add(panel, FlowLayout.LEFT);
        top.setBackground(COLOR);

        JLabel money = new JLabel(String.valueOf(player.getMoney()));
        money.setFont(font);

        top.add(money);

        this.add(top, BorderLayout.PAGE_START);

        Border carBorder = new LineBorder(new Color(169,159,98), 6);

        carsPanel = new JPanel();
        carsPanel.setLayout(new GridBagLayout());
        carsPanel.setBackground(COLOR);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;

        int i = 0;
        for(Car car: cars){
            constraints.gridx = i++;

            JButton button = new JButton(String.valueOf(car.getPrice()));
            button.setBorder(carBorder);
            button.addActionListener(e ->{
                int price = Integer.parseInt(button.getText());
                showFrame(player.getMoney() >= price, price);
            });
            button.setBackground(COLOR);
            button.add(new JPanelWithBackground(Loader.loadImg(car.getImage() + ".jpeg"), true));
            button.setPreferredSize(new Dimension(DIMENSION.width/6, DIMENSION.height/5));
            carsPanel.add(button, constraints);
        }
        constraints.gridy = 1;
        constraints.weighty = 0.3;
        constraints.anchor = GridBagConstraints.NORTH;

        int j = 0;
        for(Car car: cars){
            constraints.gridx = j++;

            JLabel price = new JLabel(String.valueOf(car.getPrice()));
            price.setFont(font);
            carsPanel.add(price, constraints);
        }

        this.add(carsPanel, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBackground(COLOR);

        JButton button = new JButton("Назад");
        button.setBackground(COLOR);
        Dimension bSize = new Dimension(DIMENSION.width/10, DIMENSION.height/25);
        button.setFont(font);
        button.addActionListener(e -> {
            mainJPanel.showEnterRoomButton();
        });
        button.setMinimumSize(bSize);
        button.setMaximumSize(bSize);
        button.setBorder(BLACK_BORDER);

        bottom.add(button);

        this.add(bottom, BorderLayout.PAGE_END);

        this.setBorder(BLACK_BORDER);
        Dimension thisSize = new Dimension(DIMENSION.width/2, DIMENSION.height - DIMENSION.height/3);
        this.setMinimumSize(thisSize);
        this.setMaximumSize(thisSize);
    }

    public void showFrame(boolean isEnough, int price){
        ShopDialogFrameShower.showFrame(cars, isEnough, price);
    }
}

