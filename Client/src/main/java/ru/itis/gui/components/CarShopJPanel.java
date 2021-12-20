package ru.itis.gui.components;

import ru.itis.general.entities.Player;
import ru.itis.gui.utils.GuiConst;
import ru.itis.gui.utils.Loader;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CarShopJPanel extends JPanel {
    private Dimension dimension;
    private JPanel carsPanel;
    private Font font;

    public CarShopJPanel(Player player) {
        dimension = Toolkit.getDefaultToolkit().getScreenSize();
        font = Loader.loadFont("default.otf").deriveFont(dimension.height/20f);
        Border lineBorder = new LineBorder(Color.BLACK, 3);
        this.setBackground(GuiConst.COLOR);
        this.setLayout(new BorderLayout());
        JPanel panel = new JPanelWithBackground(Loader.loadImg("monetka.png"));
        panel.setPreferredSize(new Dimension(dimension.height/20, dimension.height/20));

        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.LEFT));
        top.add(panel, FlowLayout.LEFT);
        top.setBackground(GuiConst.COLOR);

        JLabel money = new JLabel(String.valueOf(player.getMoney()));
        money.setFont(font);

        top.add(money);

        this.add(top, BorderLayout.PAGE_START);

        Border carBorder = new LineBorder(new Color(169,159,98), 6);

        carsPanel = new JPanel();
        carsPanel.setLayout(new GridBagLayout());
        carsPanel.setBackground(GuiConst.COLOR);

        GridBagConstraints constraints = new GridBagConstraints();

        constraints.gridy = 0;
        constraints.weighty = 1;
        constraints.weightx = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.BOTH;

        for(int i = 0; i < 3; i++){
            constraints.gridx = i;

            JButton button = new JButton(String.valueOf(i));
            button.setBorder(carBorder);
            button.setBackground(GuiConst.COLOR);
            button.add(new JPanelWithBackground(Loader.loadImg(i + ".png")));
            button.setPreferredSize(new Dimension(dimension.width/6, dimension.height/5));
            carsPanel.add(button, constraints);
        }
        constraints.gridy = 1;
        constraints.weighty = 0.3;
        constraints.anchor = GridBagConstraints.NORTH;

        for(int i = 0; i < 3; i++){
            constraints.gridx = i;

            JLabel price = new JLabel(String.valueOf(GuiConst.prices[i]));
            price.setFont(font);
            carsPanel.add(price, constraints);
        }

        this.add(carsPanel, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new FlowLayout(FlowLayout.RIGHT));
        bottom.setBackground(GuiConst.COLOR);

        JButton button = new JButton("Назад");
        button.setBackground(GuiConst.COLOR);
        Dimension bSize = new Dimension(dimension.width/10, dimension.height/25);
        button.setFont(font);
        button.setMinimumSize(bSize);
        button.setMaximumSize(bSize);
        button.setBorder(lineBorder);

        bottom.add(button);

        this.add(bottom, BorderLayout.PAGE_END);

        this.setBorder(lineBorder);
        Dimension thisSize = new Dimension(dimension.width/2, dimension.height - dimension.height/3);
        this.setMinimumSize(thisSize);
        this.setMaximumSize(thisSize);
    }
}
