package ru.itis.gui.components;

import ru.itis.general.entities.Player;
import ru.itis.gui.listeners.CheckMoneyListener;
import ru.itis.gui.utils.GuiConst;
import ru.itis.gui.utils.Loader;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

import static ru.itis.gui.utils.GuiConst.*;

public class CarShopJPanel extends JPanel {
    private JPanel carsPanel;
    private Font font;

    public CarShopJPanel(Player player) {
        font = Loader.loadFont("default.otf").deriveFont(DIMENSION.height/20f);
        this.setBackground(COLOR);
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanelWithBackground(Loader.loadImg("monetka.png"));
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

        for(int i = 0; i < 3; i++){
            constraints.gridx = i;

            JButton button = new JButton(String.valueOf(GuiConst.PRICES[i]));
            button.setBorder(carBorder);
            button.addActionListener(new CheckMoneyListener(player));
            button.setBackground(COLOR);
            button.add(new JPanelWithBackground(Loader.loadImg(i + ".png")));
            button.setPreferredSize(new Dimension(DIMENSION.width/6, DIMENSION.height/5));
            carsPanel.add(button, constraints);
        }
        constraints.gridy = 1;
        constraints.weighty = 0.3;
        constraints.anchor = GridBagConstraints.NORTH;

        for(int i = 0; i < 3; i++){
            constraints.gridx = i;

            JLabel price = new JLabel(String.valueOf(GuiConst.PRICES[i]));
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
}
